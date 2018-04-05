package Model;

import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Gestionador {

    public static boolean isValidDate(String input) {                                          //COPMPROVAR SI LA DATA ES CORRECTA
        String formatString = "MM/dd/yyyy";

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


    public static String generatePass() {

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder pass = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            pass.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return pass.toString();

    }





    public static void main(String[] args) {
        Reserva reserva1 = new Reserva("marca", 4,new Date(12,02,2019),new Time(15,00,12));
    }

}
