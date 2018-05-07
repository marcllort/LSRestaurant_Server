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

        BDD bdd = null;
        try {
            bdd = new BDD();
        } catch (SQLException e) {
            System.out.println("Fallo al conectar la bdd! Contrasenya Incorrecte");
        }
        Gestionador gestionador = new Gestionador(bdd);
            ServidorVista vista = new ServidorVista();
            Server server = new Server(new Gestionador(bdd), vista.getVistaComandes());
            server.startServer();


            Controlador controlador = new Controlador(vista, gestionador, server.getsReserva());

            vista.registraControlador(controlador);
            vista.setVisible(true);

            //afegeixReservaBdd(gestionador);
            //afegeixPlatsBdd(bdd);
            //afegeixComanda(gestionador, bdd);




    }


    /**
     * Serveix per durant la primera execucio del server afegir plats i taules a la bbdd
     *
     * @param bdd
     */
    public static void afegeixPlatsBdd(BDD bdd) {
        try {
            bdd.insereixPlat("gamba", 3, 10, 0);
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
     * Serveix per durant la primera execucio del server afegir reserves
     *
     * @param gestionador
     */
    public static void afegeixReservaBdd(Gestionador gestionador) {
        Reserva reserva1 = new Reserva("Alex", 1, gestionador.newData(6, 2, 2222), new Time(15, 00, 00));
        gestionador.creaReserva(reserva1, "pass");

        ArrayList<Plat> arr = new ArrayList<Plat>();
        ArrayList<Plat> arr1 = new ArrayList<Plat>();
        arr.add(new Plat("provan", 12));
        Comanda comanda = new Comanda(arr, gestionador.newData(12, 1, 2001), new Time(12, 40, 00), "Alex");


    }


    /**
     * Serveix per durant la primera execucio del server afegir comandes
     *
     * @param gestionador
     */
    public static void afegeixComanda(Gestionador gestionador, BDD bdd) {

        ArrayList<Plat> arr = new ArrayList<Plat>();
        ArrayList<Plat> arr1 = new ArrayList<Plat>();
        arr.add(new Plat("Croquetes", 12));
        Comanda comanda = new Comanda(arr, gestionador.newData(12, 1, 2019), new Time(12, 53, 00), "Alex");
        try {
            gestionador.addComanda(comanda);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}




