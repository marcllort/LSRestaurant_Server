package Network;

import Model.Gestionador;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketEntrada implements Runnable {

    private  int port;
    private ServerSocket sServerEntrada;
    private ServidorEntrada servidorEntrada;
    private final Gestionador gestionador;


    public ServerSocketEntrada(Gestionador gestionador, int port) {

        this.port = port;
        this.gestionador = gestionador;

    }


    @Override
    public void run() {
        try {
            ServerSocket sServerEntrada = new ServerSocket(port);
            while (true) {
                System.out.println("Esperant entrades...");
                Socket sClient = sServerEntrada.accept();
                System.out.println("Entrada connectat");
                ServidorEntrada servidorEntrada = new ServidorEntrada(sClient, gestionador);
                servidorEntrada.start();

            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
