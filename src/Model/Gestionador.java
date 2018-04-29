package Model;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Classe que conte totes les funcions per fer diferentes tasques referents a la base de dades
 */
public class Gestionador {

    private BDD bbdd;

    /**
     * Construncor del gestionador on donem valor a la bbdd
     *
     * @param bbdd
     */
    public Gestionador(BDD bbdd) {
        this.bbdd = bbdd;
    }


    /**
     * Funcio per comprovar si la data introduida per el usuari existeix
     *
     * @param input
     * @return
     */
    public synchronized boolean isValidDate(String input) {
        String formatString = "yyyy-MM-dd";
        try {
            SimpleDateFormat data = new SimpleDateFormat(formatString);
            data.setLenient(false);
            data.parse(input);
        } catch (ParseException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    /**
     * Funcio on apartir dels ints de dia mes i any creem un tipus date
     * Abans de crearlo comprovem que el dia sigui correcte
     *
     * @param dia
     * @param mes
     * @param any
     * @return
     */
    public synchronized java.sql.Date newData(Integer dia, Integer mes, Integer any) {
        if (isValidDate(any + "-" + mes + "-" + dia)) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, any);
            cal.set(Calendar.MONTH, mes - 1);
            cal.set(Calendar.DAY_OF_MONTH, dia);

            java.sql.Date data = new java.sql.Date(cal.getTimeInMillis());
            java.util.Date dataNow = new java.util.Date();
            if (data.before(dataNow)) {
                return null;
            }
            return new java.sql.Date(cal.getTimeInMillis());
        }
        return null;
    }

    /**
     * Funcio que rep la carta de la bbdd i la retorna
     *
     * @return
     */
    public synchronized Carta retornaCarta() {
        return new Carta(bbdd.llistaPlatsDisponibles());                           //retorna plats diosponibles per fer la carta
    }
    
    /**
     * Funcio per generar un password aleatori
     *
     * @return
     */
    public synchronized String generatePass() {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder pass = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            pass.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return pass.toString();
    }

    /**
     * Funció que cridas la funcio de la bbdd que serveix per comprovar el usuari i contrasenya inserits
     *
     * @param user
     * @param pass
     * @return
     */
    public synchronized boolean comprovaUserPass(String user, String pass) {
        return bbdd.comprovaPassword(user, pass);
    }

    /**
     * Funció per afegir comanda a la base de dades
     * Frem catch quan la usem
     *
     * @param comanda
     * @throws SQLException
     */
    public synchronized void addComanda(Comanda comanda) throws SQLException {
        //funcio de la bbdd, tenir en compte si es la 1a comnada o cal actualizarla
        bbdd.creaComanda(comanda);
        for (Plat plat : comanda.getPlats()) {
            bbdd.updatePlat(plat.getNomPlat(), 1);
        }
    }

    /**
     * Funció que ens serveix per comprovar que es pugui realitzar la comanda
     * Comprova que hi hagi unitats disponibles i que el plat existeixi
     *
     * @param comanda
     * @return
     */
    public synchronized String analitzarComanda(Comanda comanda) {      //mirem si hi ha unitatas de tos els prodfuctes

        ArrayList<Plat> platsError = bbdd.llistaPlatsNoDisponibles(comanda);

        String llistaPlats = new String();
        System.out.println(platsError.size());

        if (platsError.size() != 0) {
            for (Plat plats : platsError) {
                llistaPlats = llistaPlats + plats.getNomPlat();
            }

            return llistaPlats;
        } else {
            return "true";
        }
    }

    /**
     * Funció que ens retorna la comanda guardada a la bbdd de de el usuari que volguem
     *
     * @param user
     * @return
     */
    public synchronized Comanda retornaComanda(String user) {
        //retorna comanda actualitzada de la bbdd
        return bbdd.mostraPlatsComanda(user);
    }

    /**
     * Funció que ens busca la taula disponible per el nombre de començals seleccionat o proper
     * Màxim ens donara una taula de 3 començals extra i ens controla que el usuari no existeixi
     *
     * @param reserva
     * @param password
     * @return
     */

    private synchronized String buscaTaula(Reserva reserva, String password) {

        try {
            //int id_taula = -1;
            int i = reserva.getnComencals();

            // while ((i < (reserva.getnComencals() + 3)) && (id_taula == -1)) {
            while ((i < (reserva.getnComencals() + 3))) {
                int id_taula = bbdd.reservaTaula(i, reserva.getData(), reserva.getHora());
                if (id_taula != -1) {
                    bbdd.creaReserva(reserva.getUsuari(), password, reserva.getnComencals(), reserva.getData(), reserva.getHora(), id_taula);
                    System.out.println("Entrada: ID taula: " + id_taula);
                    return "true";
                }
                i++;
            }
            System.out.println("Entrada: No hi ha taula disponible en el dia i hora seleccionats");
            return "No hi ha taula disponible en el dia i hora seleccionats";

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Entrada: Nom d'usuari ja registrat, escull-ne un altre!");
            return "Nom d'usuari ja registrat, escull-ne un altre!";
        }
    }

    /**
     * Funcio per crear la reserva, despres de comprovar que tots els camps estiguin correctes
     *
     * @param reserva
     * @param password
     * @return
     */
    public synchronized String creaReserva(Reserva reserva, String password) {
        try {
            if ((reserva.getUsuari() != null) && (reserva.getnComencals() != null) && (reserva.getData() != null) && (reserva.getHora() != null)) {
                if (reserva.getnComencals() > 0) {
                    return buscaTaula(reserva, password);
                } else {
                    return ("Error! Nombre de començals impossible!");
                }

            } else {
                //missatge error de camp buit
                System.out.println(reserva.getData().getHours());           //Per fer saltar la excepcio en cas de que nhi hagi
                return ("Error! Camps buits!");
            }

        } catch (NullPointerException w) {
            return ("Error! Data incorrecta!");
            //ennvia error
        }
    }

    /**
     * Retorna el valor del top5 de plats mes demanats de la semana
     *
     * @return
     */
    public synchronized ArrayList<InfoComandes> topCincSemanal() {
        return bbdd.top5PlatsSemanals();
    }

    /**
     * Retorna el valor del top5 de plats mes demanats de la sempre
     *
     * @return
     */
    public synchronized ArrayList<InfoComandes> topCincTotal() {
        return bbdd.top5Plats();
    }

}

