package Model;

import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Gestionador {

    private BDD bbdd;

    public Gestionador(BDD bbdd) {
        this.bbdd = bbdd;
    }

    public boolean isValidDate(String input) {                                          //COPMPROVAR SI LA DATA ES CORRECTA
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

    public String generatePass() {

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder pass = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            pass.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return pass.toString();
    }

    public Date newData(int dia, int mes, int any) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, any);
        cal.set(Calendar.MONTH, mes - 1);
        cal.set(Calendar.DAY_OF_MONTH, dia);

        return new Date(cal.getTimeInMillis());
    }               //Cal pasar funcio a la app entrada

    private String buscaTaula(Reserva reserva) {
        try {
            for (int i = reserva.getnComencals(); i < reserva.getnComencals() + 3; i++) {
                if (bbdd.reservaTaula(reserva.getUsuari(), reserva.getnComencals(), reserva.getData(), reserva.getHora(), generatePass())) {
                    return "Reserva realitzada amb exit!";
                }
            }
            return "No hi ha taula disponible en el dia i hora seleccionats";
        }catch (Exception e){
            return "Nom d'usuari ja registrat, escull-ne un altre!";
        }
    }

    private void enviaMissatge(String missatge){
        //WRITEUTF?
    }

    public void creaReserva(Reserva reserva) {

        if (isValidDate(reserva.getData().toString())) {

            if ((reserva.getUsuari() != null) && (reserva.getnComencals() != null) && (reserva.getData() != null) && (reserva.getHora() != null)) {

                enviaMissatge(buscaTaula(reserva));
                System.out.println(buscaTaula(reserva));

            } else {
                //missatge error de camp buit
                System.out.println("camp buit");
            }

        } else {
            System.out.println("data incorrecta");
            //ennvia error
        }

    }


    //Main per fer proves

    /*public static void main(String[] args) {

        Reserva reserva1 = new Reserva("marca", 4, this.newData(2, 2, 2222), new Time(15, 00, 00));
        System.out.println(reserva1.getData());

        this.creaReserva(reserva1);

    }*/

}
