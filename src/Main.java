import Model.BDD;

import java.sql.*;

public class Main {


    //prova

    public static void main(String[] args) {


        try {
            BDD bdd = new BDD();
            String s = "ALL";
            //bdd.insereixPlat(s,3,10,0);
            String a = "SELECT * FROM Plat WHERE preu = 1";
            bdd.queriePlat(a);
        }catch (Exception e){
            System.out.println("ERROOR");
            e.printStackTrace();
        }

        //funciona
    }
}
