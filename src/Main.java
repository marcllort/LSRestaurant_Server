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
            Gestionador gestionador = new Gestionador(bdd);
            Server server = new Server(new Gestionador(bdd));
            server.startServer();

            ServidorVista vista = new ServidorVista();

            Controlador controlador = new Controlador(vista, gestionador);

            vista.registraControlador(controlador);
            vista.setVisible(true);

            ArrayList<Plat> arr = new ArrayList<Plat>();
            ArrayList<Plat> arr1 = new ArrayList<Plat>();
            arr.add(new Plat("gamba", 12));
            Comanda comanda = new Comanda(arr, gestionador.newData(12, 1, 2019), new Time(12, 40, 00), "alex");

            bdd.creaComanda(comanda);

        } catch (SQLException e) {
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




