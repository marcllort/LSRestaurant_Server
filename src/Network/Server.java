/*package Network;

import Controlador.Controlador;
import Model.Comandador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static final int Port = 4444;                                               //Declarem els atributs
    private ServerSocket sServer;
    private final ArrayList<ServidorDedicat> servers;
    private final Comandador comandador;
    private Controlador controller;
    private boolean funciona;


    public Server(Comandador comandador) {
        servers = new ArrayList<>();
        sServer = null;
        this.comandador = comandador;
    }


    public void startServer() {
        try {
            sServer = new ServerSocket(Port);                                           //Iniciem socket al port corespoenent
            funciona = true;
            System.out.println("Socket obert");

            while (funciona) {
                System.out.println("Esperant client...");
                Socket sClient = sServer.accept();                                      //Esperem a la connexio del client
                System.out.println("Client connectat");
                ServidorDedicat servidordedicat = new ServidorDedicat(sClient, servers, comandador, controller);                //Creem un servidor dedicat
                servers.add(servidordedicat);                                                                                   //Afegim el serverdedicat a un arraylist on els tenim tots
                servidordedicat.start();                                                                                        //Iniciem server dedicat
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sServer != null && !sServer.isClosed()) {
                try {
                    sServer.close();                                                                                            //Tanquem servidor
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void enviaC() {                                                                           //Funcio que fem servir al controlador per enviar a tots els servers dedicats la nova llista de comandes
        for (ServidorDedicat servidor : servers) {
            servidor.enviaMissatge();
        }
    }

    public void setController(Controlador controller) {                                             //Serveix per donar el controlador al server, la usem al controlador
        this.controller = controller;
    }

}
*/