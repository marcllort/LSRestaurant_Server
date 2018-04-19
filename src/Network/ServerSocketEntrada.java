package Network;

import Model.Gestionador;
import Model.Json.ConfiguracioServer;
import Model.Json.LectorJson;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketEntrada implements Runnable {

    private int port;
    private final Gestionador gestionador;


    public ServerSocketEntrada(Gestionador gestionador, int port) {

        ConfiguracioServer config = LectorJson.llegeixConfiguracioServer();
        this.port = config.lectorPortEntrada() ;
        this.gestionador = gestionador;

    }


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
