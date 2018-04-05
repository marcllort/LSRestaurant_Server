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

    public static Date newData(int dia, int mes, int any){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, any);
        cal.set(Calendar.MONTH, mes - 1);
        cal.set(Calendar.DAY_OF_MONTH, dia);

        return new Date(cal.getTimeInMillis());
    }

    public static void main(String[] args) {


        Reserva reserva1 = new Reserva("marca", 4,newData(2,2,2222),new Time(15,00,12));
        System.out.println(reserva1.getData());

    }

}
