package Network;

//import Controlador.Controlador;
//import Model.Comandador;

import Model.Gestionador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static final int port = 4444;                                               //Declarem els atributs
    private ServerSocket sServerReserva;
    private final ArrayList<ServidorReserva> serversReserva;

    private ServerSocket sServerEntrada;
    //private final ServidorEntrada serverEntrada;

    private final Gestionador gestionador;
    //private Controlador controller;
    private boolean funciona;


    public Server(Gestionador gestionador) {
        serversReserva = new ArrayList<>();
        sServerReserva = null;
        this.gestionador = gestionador;
    }


    public void startServer() {
        ServerSocketReserva sReserva = new ServerSocketReserva(gestionador, port);
        ServerSocketEntrada sEntrada = new ServerSocketEntrada(gestionador, 5555);
        Thread t1 = new Thread(sReserva);
        Thread t2 = new Thread(sEntrada);
        t1.start();
        t2.start();
    }

    public void enviaC(String user) {                                                                           //Funcio que fem servir al controlador per enviar a tots els serversReserva dedicats la nova llista de comandes
        for (ServidorReserva servidor : serversReserva) {
            if (servidor.getUser().equals(user)) {
                servidor.enviaComanda();
            }
        }
    }
}

    /*public void setController(Controlador controller) {                                             //Serveix per donar el controlador al server, la usem al controlador
        this.controller = controller;
    }*/


