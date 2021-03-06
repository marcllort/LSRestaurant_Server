package Model;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Classe que conte totes les funcions per fer diferentes tasques referents a la base de dades
 */
public class Gestionador {

    private BDD bbdd;


    /**
     * Construncor del gestionador on donem valor a la bbdd
     *
     * @param bbdd base de dades
     */
    public Gestionador(BDD bbdd) {
        this.bbdd = bbdd;
    }


    /**
     * Funcio per comprovar si la data introduida per el usuari existeix
     *
     * @param input string de la data
     * @return boolea de lsi la data es correcta
     */
    private synchronized boolean isValidDate(String input) {
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
     * Funció on apartir dels ints de dia mes i any creem un tipus date
     * Abans de crear-lo comprovem que el dia sigui correcte
     *
     * @param dia data dia
     * @param mes data mes
     * @param any data any
     * @return tipus data de sql
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
     * Funcio que rep la carta de la bbdd de mysql i la retorna
     *
     * @return carta
     */
    public synchronized Carta retornaCarta() {
        return new Carta(bbdd.llistaPlatsDisponibles());                           //retorna plats diosponibles per fer la carta
    }

    /**
     * Funcio que rep la carta de la bbdd de mysql i la retorna
     *
     * @return carta
     */
    public synchronized Carta retornaPlats() {
        return new Carta(bbdd.llistaPlats());                           //retorna plats diosponibles per fer la carta
    }

    /**
     * Funcio per generar un password aleatori
     *
     * @return un password aleatori
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
     * @param user string user
     * @param pass string password
     * @return boolea de si usuari i contrasenya són correctes
     */
    public synchronized boolean comprovaUserPass(String user, String pass) {
        if (user.equals("") || pass.equals("")) {
            return false;
        }
        return bbdd.comprovaPassword(user, pass);
    }

    /**
     * Funció per afegir comanda a la base de dades
     * Frem catch quan la usem
     *
     * @param comanda Comanda a afegir a la bbdd
     * @throws SQLException si troba error al afegir
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
     * @param comanda comanda a mirar si esta correcta
     * @return String de si la comanda es correcta amb els plats incorrectes, o un true si es correcta
     */
    public synchronized String analitzarComanda(Comanda comanda) {      //mirem si hi ha unitatas de tos els prodfuctes

        if (comanda.getPlats().size() == 0) {
            return "Pagat";
        }

        ArrayList<Plat> platsError = bbdd.llistaPlatsNoDisponibles(comanda);

        String llistaPlats = "";
        System.out.println("Numero plats amb error:" + platsError.size());

        if (platsError.size() != 0) {
            for (Plat plats : platsError) {
                llistaPlats = llistaPlats + " " + plats.getNomPlat();
            }

            return llistaPlats;
        } else {
            for (Plat p : comanda.getPlats()) {
                System.out.println("NOM " + p.getNomPlat());
                int occurrences = Collections.frequency(comanda.getPlats(), p);
                System.out.println("PLATS: " + occurrences);
                if (occurrences > retornaUnitats(p.getNomPlat())) {
                    int u = occurrences - retornaUnitats(p.getNomPlat());
                    return "Falten " + u + " unitats de: " + p.getNomPlat();
                }
            }
            return "true";
        }
    }

    /**
     * Funció que ens retorna la comanda guardada a la bbdd de de el usuari que volguem
     *
     * @param user usuari del que volem la comanda
     * @return la comanda guardada al mysql del usuari
     */
    public synchronized Comanda retornaComanda(String user) {
        return bbdd.mostraPlatsComanda(user);                       //retorna comanda actualitzada de la bbdd
    }

    /**
     * Funció que ens busca la taula disponible per el nombre de començals seleccionat o proper
     * Màxim ens donara una taula de 3 començals extra i ens controla que el usuari no existeixi
     *
     * @param reserva reserva a buscar taula
     * @param password password del usuari
     * @return missatge d'error, o true si ha trobat una taula
     */
    private synchronized String buscaTaula(Reserva reserva, String password) {

        try {
            int i = reserva.getnComencals();

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
            System.out.println("Entrada: Nom d'usuari ja registrat, escull-ne un altre!");
            return "Nom d'usuari ja registrat, escull-ne un altre!";
        }
    }

    /**
     * Funcio per crear la reserva, despres de comprovar que tots els camps estiguin correctes
     *
     * @param reserva reserva a afegir bbdd
     * @param password password del usuari que fa la reserva
     * @return missatge d'error, o true si ha trobat una taula
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
            return ("Error! Data incorrecta!");                             //envia error
        }
    }

    /**
     * Retorna el valor del top5 de plats més demanats de la semana
     *
     * @return array de plats més demanats de la semana
     */
    public synchronized ArrayList<InfoComandes> topCincSemanal() {
        return bbdd.top5PlatsSemanals();
    }

    /**
     * Retorna el valor del top5 de plats més demanats de la sempre
     *
     * @return array de plats més demanats de la sempre
     */
    public synchronized ArrayList<InfoComandes> topCincTotal() {
        return bbdd.top5Plats();
    }

    /**
     * Funcio crear taula a la bbdd
     *
     * @param i el nombre de començals
     * @throws java.sql.SQLException error al crear taula
     */
    public synchronized void creaTaula(int i) throws java.sql.SQLException {
        bbdd.createTable(i);
    }

    /**
     * Funció que retorna arraylist dels id de les taules que hi ha a la bbdd
     *
     * @return arraylis de ints amb els ide de cada taula
     */
    public synchronized ArrayList<Integer> llistaTaules() {
        ArrayList<Integer> result = new ArrayList<>();
        try {
            result = bbdd.mostraTaules();
        } catch (Exception e) {
            System.out.println("No hi ha taules");
        }
        return result;
    }

    /**
     * Funció que mostra les reserves de la taula de le que passem el id
     *
     * @param i el id de la taula
     * @return arraylist de reserves que té la taula
     */
    public synchronized ArrayList<Reserva> mostraReseves(int i) {
        ArrayList<Reserva> res;

        res = bbdd.mostraReservesTaula(i);
        Iterator<Reserva> iter = res.iterator();

        while (iter.hasNext()) {
            Reserva info = iter.next();

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            Date dataNow = cal.getTime();
            if (info.getData().before(dataNow)) {
                iter.remove();
            }
        }
        return res;
    }

    /**
     * Elimina la taula del id taula que li passem
     *
     * @param i el id de la taula
     * @throws Exception error al borrar taula
     */
    public synchronized void eliminaTaula(int i) throws Exception {
        bbdd.eliminaTaula(i);
    }

    /**
     * Funció que retorna un arraylist de infocomandes de les comandes pendents per servir
     *
     * @return array de comandes amb nombre plats demanats i servits
     * @throws Exception error al crear array o de la bbdd
     */
    public synchronized ArrayList<InfoComandes> llistaComandes() throws Exception {
        ArrayList<InfoComandes> arr;

        arr = bbdd.llistaComandes();

        Iterator<InfoComandes> iter = arr.iterator();

        while (iter.hasNext()) {
            InfoComandes info = iter.next();

            if (info.getPlatsPendents() == 0) {
                iter.remove();
            }
            Date dataNow = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dataNow);
            c.add(Calendar.DATE, -1);
            dataNow = c.getTime();

            if (info.getDate().before(dataNow)) {
                iter.remove();
            }
        }
        return arr;
    }

    /**
     * Funció per servir el plat del usuari pasat per parametres
     *
     * @param plat nom plat
     * @param user nom usuari
     */
    public synchronized void serveixPlat(String plat, String user) {
        bbdd.serveixPlat(plat, user);
    }

    public int retornaUnitats(String plat) {
        return bbdd.getUnitatsPlat(plat);
    }

    /**
     * Funció per inserir un nou plat
     *
     * @param nom_plat string de plat
     * @param preu preu del plat
     * @param unitats_disponibles unitats del plat
     */
    public synchronized void insereixPlat(String nom_plat, float preu, int unitats_disponibles) throws SQLException {
        bbdd.insereixPlat(nom_plat, preu, unitats_disponibles, 0);
    }

    /**
     * Funció per gastar les unitats del plat que passem per parametres
     *
     * @param nom nom plat
     * @param unitats noves unitats disponibles
     * @throws SQLException fallo al canviar les unitats
     */
    public synchronized void updatePlat(String nom, int unitats) throws SQLException {
        bbdd.updatePlat(nom, unitats);
    }

    /**
     * Funció per afegir les unitats del plat que passem per parametres
     *
     * @param nom nom del plat a afegir unitats
     * @param unitats noves unitats disponibles
     * @throws SQLException error al afegirne
     */
    public synchronized void afegeixUnitats(String nom, int unitats) throws SQLException {
        bbdd.afegeixUnitats(nom, unitats);
    }

    /**
     * Crida la funció elimina plat de la bbdd
     *
     * @param nom nom del plat
     * @throws SQLException error al eliminar plat
     */
    public synchronized void eliminaPlat(String nom) throws SQLException {
        bbdd.eliminaPlat(nom);
    }

    /**
     * Crida la funció serveixPlatsUsuari de la bbdd, per servir tots els plats de l'usuari
     *
     * @param usuari usuari del que servir plats
     * @throws SQLException error al servir plats
     */
    public synchronized void serveixPlatsUsuari(String usuari) {
        bbdd.serveixPlatsUsuari(usuari);
    }

}

