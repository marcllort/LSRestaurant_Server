import Model.BDD;

import java.sql.*;

public class Main {


    //prova

    public static void main(String[] args) {


        try {
            BDD bdd = new BDD();
            String s = "ALL";
            //bdd.insereixPlat(s,3,10,0);
            bdd.updatePlat(6,7);
            bdd.createTable(2);
            String a = "SELECT * FROM Plat ";

            bdd.queriePlat(a);
        }catch (Exception e){
            System.out.println("ERROOR");
            e.printStackTrace();
        }

        //funciona
    }
}
