package Network;

import Model.Gestionador;
import Vista.VistaComandes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Classe del socket de reserva que implementa runnable per poder correl en el thread
 */
public class ServerSocketReserva implements Runnable {

    private int port;
    private ServerSocket sServerReserva;
    private final Gestionador gestionador;
    private final ArrayList<ServidorReserva> serversReserva;
    private VistaComandes vistaComanda;

    /**
     * Constructor de el socket de reserva que rep el seu port i el gestionador i inicialitza el array de servers
     *
     * @param gestionador
     * @param port
     */
    public ServerSocketReserva(Gestionador gestionador, int port, VistaComandes vistaComanda) {
        this.port = port;
        this.gestionador = gestionador;
        this.serversReserva = new ArrayList<>();
        this.vistaComanda = vistaComanda;
    }

    /**
     * Envia la comanda actualitzada al usuari del que li volem actualitzar la comanda
     *
     * @param user
     */
    public void enviaC(String user) {                                                                           //Funcio que fem servir al controlador per enviar a tots els serversReserva dedicats la nova llista de comandes amb estat actualitzat
        for (ServidorReserva servidor : serversReserva) {                                                       //cal arreglar, segurament no va
            if (servidor.getUser().equals(user)) {
                servidor.enviaComanda();
            }
        }
    }

    /**
     * Override de run per crear el serversocket i posteriorment estar esperant clients
     * Un cop rebem client creem el servidor dedicat de reserva per cada client i fem start
     */
    @Override
    public void run() {
        try {

            sServerReserva = new ServerSocket(port);
            System.out.println("Reserva: Server Obert");

            while (true) {
                Socket sClient = sServerReserva.accept();                                      //Esperem a la connexio del client
                System.out.println("Reserva: Client connectat");

                ServidorReserva servidordedicat = new ServidorReserva(sClient, serversReserva, gestionador, vistaComanda);                //Creem un servidor dedicat
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


    public ServidorReserva getServerReserva(String user) {
        for (ServidorReserva s : serversReserva) {
            if (s.getUser().equals(user)) {
                return s;
            }
        }
        return null;
    }

    public void enviaCarta(){
        for (ServidorReserva servidor : serversReserva) {                                                       //cal arreglar, segurament no va
            servidor.enviaCarta();
        }
    }

}
