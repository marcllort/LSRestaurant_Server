package Model;

import java.security.SecureRandom;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Gestionador {

    private BDD bbdd;


    public Gestionador(BDD bbdd) {
        this.bbdd = bbdd;
    }


    //Funcions

    public synchronized boolean isValidDate(String input) {                                          //COPMPROVAR SI LA DATA ES CORRECTA
        String formatString = "yyyy-MM-dd";

        try {
            SimpleDateFormat data = new SimpleDateFormat(formatString);
            data.setLenient(false);
            data.parse(input);
        } catch (ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public synchronized Date newData(int dia, int mes, int any) {
        if (isValidDate(any + "-" + mes + "-" + dia)) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, any);
            cal.set(Calendar.MONTH, mes - 1);
            cal.set(Calendar.DAY_OF_MONTH, dia);


            return new Date(cal.getTimeInMillis());
        }
        return null;
    }               //Cal pasar funcio a la app entrada

    public synchronized ArrayList<Plat> retornaCarta() {
        return bbdd.llistaPlatsDisponibles();                          //retorna plats diosponibles per fer la carta
    }


    //Password

    public synchronized String generatePass() {

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder pass = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            pass.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return pass.toString();
    }

    public synchronized boolean comprovaUserPass(String user, String pass) {
        //funcio bbdd comprovar

        return bbdd.comprovaPassword(user, pass);
    }


    //Comanda

    public synchronized void addComanda(Comanda comanda) {
        //funcio de la bbdd, tenir en compte si es la 1a comnada o cal actualizarla

        try {
            bbdd.creaComanda(comanda);
            for(Plat plat: comanda.getPlats()){
                bbdd.updatePlat(plat.getNomPlat(),1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error comanda!");
        }

    }

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

    public synchronized Comanda retornaComanda(String user) {
        //retorna comanda actualitzada de la bbdd
        return bbdd.mostraPlatsComanda(user);
    }


    //Reserva

    private synchronized String buscaTaula(Reserva reserva, String password) {

        try {
            int id_taula = -1;
            int i = reserva.getnComencals();

            while ((i < (reserva.getnComencals() + 3)) && (id_taula == -1)) {
                id_taula = bbdd.reservaTaula(i, reserva.getData(), reserva.getHora());
                //System.out.println("ID TAULA ASSIGNADA: " + id_taula);
                if (id_taula != -1) {
                    System.out.println("ID TAULA ASSIGNADA: " + id_taula);
                    bbdd.creaReserva(reserva.getUsuari(), password, reserva.getnComencals(), reserva.getData(), reserva.getHora(), id_taula);
                    return "true";
                }
                i++;
            }
            System.out.println("No hi ha taula disponible en el dia i hora seleccionats");
            return "No hi ha taula disponible en el dia i hora seleccionats";

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nom d'usuari ja registrat, escull-ne un altre!");
            return "Nom d'usuari ja registrat, escull-ne un altre!";
        }
    }

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


    //Top 5

    /*public synchronized ArrayList<Plat> topCincSemanal() {
        return
    }

    public synchronized ArrayList<Plat> topCincTotal() {
        return
    }*/

}

