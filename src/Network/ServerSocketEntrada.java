package Network;

import Model.Gestionador;


import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe del socket d'entrada que implementa runnable per poder correl en el thread
 */
public class ServerSocketEntrada implements Runnable {

    private int port;
    private final Gestionador gestionador;

    /**
     * Constructor de el socket d'entrada que rep el seu port i el gestionador
     *
     * @param gestionador
     * @param port
     */
    public ServerSocketEntrada(Gestionador gestionador, int port) {
        this.port = port;
        this.gestionador = gestionador;
    }

    /**
     * Override de run per crear el serversocket i posteriorment estar esperant clients
     * Un cop rebem client creem el servidor dedicat de entrada i fem start
     */
    @Override
    public void run() {
        try {
            ServerSocket sServerEntrada = new ServerSocket(port);
            System.out.println("Entrada: Server Obert");

            while (true) {
                Socket sClient = sServerEntrada.accept();
                System.out.println("Entrada: Client connectat");
                ServidorEntrada servidorEntrada = new ServidorEntrada(sClient, gestionador);
                servidorEntrada.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Entrada: Error");
        }
    }

}
