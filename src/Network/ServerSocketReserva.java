package Network;

import Model.Gestionador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSocketReserva implements Runnable {

    private int port;
    private ServerSocket sServerReserva;
    private final Gestionador gestionador;
    private final ArrayList<ServidorReserva> serversReserva;


    public ServerSocketReserva(Gestionador gestionador, int port) {
        this.port = port;
        serversReserva = new ArrayList<>();
        sServerReserva = null;
        this.gestionador = gestionador;
    }

    @Override
    public void run() {
        try {

            sServerReserva = new ServerSocket(port);
            System.out.println("Reserva: Server Obert");

            while (true) {
                Socket sClient = sServerReserva.accept();                                      //Esperem a la connexio del client
                System.out.println("Reserva: Client connectat");

                ServidorReserva servidordedicat = new ServidorReserva(sClient, serversReserva, gestionador);                //Creem un servidor dedicat
                serversReserva.add(servidordedicat);                                                                        //Afegim el serverdedicat a un arraylist on els tenim tots
                servidordedicat.start();                                                                                    //Iniciem server dedicat
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Reserva: Error");
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
