/*package Network;

import Controlador.Controlador;
//import Model.Comandador;

import Model.Gestionador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static final int Port = 4444;                                               //Declarem els atributs
    private ServerSocket sServerReserva;
    private final ArrayList<ServidorDedicat> serversReserva;

    private ServerSocket sServerEntrada;
    //private final ServidorEntrada serverEntrada;

    private final Gestionador gestionador;
    private Controlador controller;
    private boolean funciona;


    public Server(Gestionador gestionador) {
        serversReserva = new ArrayList<>();
        sServerReserva = null;
        this.gestionador = gestionador;
    }


    public void startServer() {
        try {
            sServerReserva = new ServerSocket(Port);                                           //Iniciem socket al port corespoenent
            funciona = true;
            System.out.println("Socket obert");

            while (funciona) {
                System.out.println("Esperant client...");
                //cal detectar si ens esta acceptatn entrada o reservca i segons fagi, fer una cosa o una altra
                Socket sClient = sServerReserva.accept();                                      //Esperem a la connexio del client
                System.out.println("Client connectat");
                ServidorDedicat servidordedicat = new ServidorDedicat(sClient, serversReserva, gestionador, controller);                //Creem un servidor dedicat
                serversReserva.add(servidordedicat);                                                                                   //Afegim el serverdedicat a un arraylist on els tenim tots
                servidordedicat.start();                                                                                        //Iniciem server dedicat
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sServerReserva != null && !sServerReserva.isClosed()) {
                try {
                    sServerReserva.close();                                                                                            //Tanquem servidor
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void enviaC(String user) {                                                                           //Funcio que fem servir al controlador per enviar a tots els serversReserva dedicats la nova llista de comandes
        for (ServidorDedicat servidor : serversReserva) {
            if (servidor.getUser().equals(user)) {
                servidor.enviaComanda();
            }
        }
    }

    /*public void setController(Controlador controller) {                                             //Serveix per donar el controlador al server, la usem al controlador
        this.controller = controller;
    }*/


