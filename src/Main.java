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


       // afegeixComanda(gestionador, bdd);
        //afegeixReservaBdd(gestionador);
       // afegeixPlatsBdd(bdd);


    }


    /**
     * Serveix per durant la primera execucio del server afegir plats i taules a la bbdd
     *
     * @param bdd
     */
    public static void afegeixPlatsBdd(BDD bdd) {
        try {
            bdd.insereixPlat("Gambes de Palamós", 21, 10, 0);
            bdd.insereixPlat("Filet de vedella", 19, 10, 0);
            bdd.insereixPlat("Entrecot de vedella", 17, 20, 0);
            bdd.insereixPlat("Bistec de vedella", 11, 30, 0);
            bdd.insereixPlat("Llenguado al forn", 18, 10, 0);
            bdd.insereixPlat("Croquetes de pollastre", 5, 23, 0);
            bdd.insereixPlat("Croquetes de carn ", 5, 21, 0);
            bdd.insereixPlat("Patates braves", 5, 10, 0);
            bdd.insereixPlat("Espaguetis a la carbonara", 11, 20, 0);
            bdd.insereixPlat("Macarrons amb tomàquet", 11, 19, 0);
            bdd.insereixPlat("Escalopa de vedella", 10, 27, 0);
            bdd.insereixPlat("Pizza Prosciuto", 10, 20, 0);
            bdd.insereixPlat("Pizza Funghi", 11, 15, 0);
            bdd.insereixPlat("Pizza Quatre Estacions", 12, 15, 0);
            bdd.insereixPlat("Pizza Barbacoa", 12, 15, 0);
            bdd.insereixPlat("Sopa de pollastre", 9, 20, 0);
            bdd.insereixPlat("Amanida Cesar", 7, 23, 0);
            bdd.insereixPlat("Amanida de pasta", 7, 25, 0);
            bdd.insereixPlat("Amanida verda", 7, 25, 0);
            bdd.insereixPlat("Arròs a la cubana", 10, 10, 0);
            bdd.insereixPlat("Paella", 12, 40, 0);


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
        try {
            for (int i = 1; i < 15; i++) {
                gestionador.creaTaula(i);
            }
        } catch (Exception e) {

        }


        Reserva reserva1 = new Reserva("Alex", 1, gestionador.newData(20, 5, 2018), new Time(14, 30, 00));
        gestionador.creaReserva(reserva1, "pass");

        Reserva reserva2 = new Reserva("Marc", 4, gestionador.newData(21, 5, 2018), new Time(14, 30, 00));
        gestionador.creaReserva(reserva2, "pass");

        Reserva reserva3 = new Reserva("Paula", 3, gestionador.newData(21, 5, 2018), new Time(21, 30, 00));
        gestionador.creaReserva(reserva3, "pass");

        Reserva reserva4 = new Reserva("Manel", 4, gestionador.newData(20, 5, 2018), new Time(21, 30, 00));
        gestionador.creaReserva(reserva4, "pass");

        Reserva reserva5 = new Reserva("Alberto", 12, gestionador.newData(22, 5, 2018), new Time(14, 30, 00));
        gestionador.creaReserva(reserva5, "pass");

        Reserva reserva6 = new Reserva("Pol", 2, gestionador.newData(22, 5, 2018), new Time(13, 00, 00));
        gestionador.creaReserva(reserva6, "pass");

        Reserva reserva7 = new Reserva("Puigdemont", 1, gestionador.newData(22, 5, 2018), new Time(19, 30, 00));
        gestionador.creaReserva(reserva7, "pass");

        Reserva reserva8 = new Reserva("Messi", 10, gestionador.newData(23, 5, 2018), new Time(14, 30, 00));
        gestionador.creaReserva(reserva8, "pass");

        Reserva reserva9 = new Reserva("Samantha", 1, gestionador.newData(23, 5, 2018), new Time(13, 00, 00));
        gestionador.creaReserva(reserva9, "pass");

        Reserva reserva10 = new Reserva("Marialejandra", 3, gestionador.newData(23, 5, 2018), new Time(19, 30, 00));
        gestionador.creaReserva(reserva10, "pass");

        Reserva reserva11 = new Reserva("Guim", 6, gestionador.newData(23, 5, 2018), new Time(21, 30, 00));
        gestionador.creaReserva(reserva11, "pass");

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




