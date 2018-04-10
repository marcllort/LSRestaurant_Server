package Network;

import Model.Gestionador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSocketReserva implements Runnable {

    private static int Port;
    private ServerSocket sServerReserva;
    private final Gestionador gestionador;
    private boolean funciona;
    private final ArrayList<ServidorReserva> serversReserva;


    public ServerSocketReserva(Gestionador gestionador, int port) {
        this.Port = port;
        serversReserva = new ArrayList<>();
        sServerReserva = null;
        this.gestionador = gestionador;
    }

    @Override
    public void run() {
        try {
            sServerReserva = new ServerSocket(Port);
            funciona = true;
            System.out.println("Socket obert");

            while (funciona) {
                System.out.println("Esperant clients...");
                Socket sClient = sServerReserva.accept();                                      //Esperem a la connexio del client
                System.out.println("Client connectat");
                ServidorReserva servidordedicat = new ServidorReserva(sClient, serversReserva, gestionador);                //Creem un servidor dedicat
                serversReserva.add(servidordedicat);                                                                                   //Afegim el serverdedicat a un arraylist on els tenim tots
                servidordedicat.start();                                                                                        //Iniciem server dedicat
            }
        } catch (Exception e) {
            System.out.println("Error");
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
}
