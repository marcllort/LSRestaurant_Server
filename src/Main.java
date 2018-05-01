import Controlador.Controlador;
import Model.*;
import Network.Server;
import Vista.ServidorVista;

import java.sql.*;
import java.util.ArrayList;


public class Main {

    /**
     * Inicialitzem el servidor i la seva base de dades
     * Creem la vista i  el seu controlador
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            BDD bdd = new BDD();
            Serverbbdd(bdd);
            ServidorVista vista = new ServidorVista();
            Controlador controlador = new Controlador(vista, bdd);
            vista.registraControlador(controlador);
            vista.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * A Serverbbdd creem la bbdd que farem servir, connectada a mysql
     * El gestionador sera l'encarregat de dur a terme totes les funcions que relacionen el servidor amb la bbdd
     * Posteriorment creem el server amb el gesataionador que conte la bbdd i li fem start
     */
    public static void Serverbbdd(BDD bdd) {
        try {


            Gestionador gestionador = new Gestionador(bdd);
            Server server = new Server(new Gestionador(bdd));
            Time hora = new Time(System.currentTimeMillis());
            ArrayList<Reserva> as= new ArrayList<>();
            as = bdd.mostraReservesTaula(3);



            //System.out.println(hora);
            //afegeixPlatsBdd(bdd);
            //afegeixReservaBdd(gestionador);

            server.startServer();

        } catch (Exception e) {
            System.out.println("ERROR MAIN");
            e.printStackTrace();
        }
    }


    /**
     * Serveix per durant la primera execucio del server afegir plats i taules a la bbdd
     *
     * @param bdd
     */
    public static void afegeixPlatsBdd(BDD bdd) {
        try {
            bdd.insereixPlat("gambka", 3, 10, 0);
            bdd.insereixPlat("Filet", 30, 10, 0);
            bdd.insereixPlat("Llenguado", 20, 10, 0);
            bdd.insereixPlat("Croquetes", 3, 10, 0);
            bdd.insereixPlat("Patatas", 3, 10, 0);

            for (int i = 1; i < 3; i++) {
                bdd.createTable(2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Serveix per durant la primera execucio del server afegir reserves i comandes
     *
     * @param gestionador
     */
    public static void afegeixReservaBdd(Gestionador gestionador) {
        Reserva reserva1 = new Reserva("Alex", 1, gestionador.newData(6, 2, 2222), new Time(15, 00, 00));
        //gestionador.creaReserva(reserva1);

        ArrayList<Plat> arr = new ArrayList<Plat>();
        ArrayList<Plat> arr1 = new ArrayList<Plat>();
        arr.add(new Plat("provan", 12));
        Comanda comanda = new Comanda(arr, gestionador.newData(12, 1, 2001), new Time(12, 40, 00), "Alex");

        System.out.println(gestionador.retornaComanda("marsssdcS").getData());
        System.out.println(gestionador.retornaComanda("marsssdcS").getHora());
        System.out.println(gestionador.retornaComanda("marsssdcS").getPlats());

    }

}




