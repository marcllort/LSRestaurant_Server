package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Gestionador {

    private static boolean isValidDate(String input) {                                          //COPMPROVAR SI LA DATA ES CORRECTA
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

    /*public static void main(String[] args){
        System.out.println(isValidDate("45/23/234")); // false
        System.out.println(isValidDate("12/12/2111")); // true
    }*/

}
