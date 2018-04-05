package Model;

import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class Gestionador {

    public static boolean isValidDate(String input) {                                          //COPMPROVAR SI LA DATA ES CORRECTA
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


    public static String generatePass() {

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder pass = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            pass.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return pass.toString();

    }

    public void creaReserva(Reserva reserva){

        if (isValidDate(reserva.getData())){

        }else{
            //ennvia error
        }

    }



    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        Reserva reserva1 = new Reserva("marca", 4,new Date(cal.getTimeInMillis()),new Time(15,00,12));
        System.out.println(reserva1.getData());

    }

}
