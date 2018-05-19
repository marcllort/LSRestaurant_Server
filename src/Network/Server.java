package Network;

import Model.Gestionador;
import Model.Json.*;
import Vista.VistaComandes;

/**
 * Classe servidor que es comunicarà amb els "dos" clients (reserva, entrada)
 */
public class Server {

    private static int portReserva;                                               //Declarem els atributs
    private static int portEntrada;
    private final Gestionador gestionador;
    private VistaComandes vistaComanda;
    private ServerSocketReserva sReserva;

    /**
     * Constructor de Server que rep gestionador per usarlo posteriorment
     * Fa us del lector json per llegiur els ports
     *
     * @param gestionador tipus gestionador
     */
    public Server(Gestionador gestionador, VistaComandes vistaComanda) {
        this.gestionador = gestionador;
        this.vistaComanda = vistaComanda;
        ConfiguracioServer conf = LectorJson.llegeixConfiguracioServer();
        portReserva = conf.lectorPortReserva();
        portEntrada = conf.lectorPortEntrada();
    }

    /**
     * Funcio start server per inicialitzar els sockets dels dos clients
     * Posteriorment fem els threads per poder correr els dos "servers" de forma independent
     */
    public void startServer() {
        sReserva = new ServerSocketReserva(gestionador, portReserva, vistaComanda);
        ServerSocketEntrada sEntrada = new ServerSocketEntrada(gestionador, portEntrada);
        Thread t1 = new Thread(sReserva);
        Thread t2 = new Thread(sEntrada);
        t1.start();
        t2.start();
    }

    /**
     * Getter de serversocket de reserva per que el controlador pugui fer-ne us
     *
     * @return reotrna el socket de reserva
     */
    public ServerSocketReserva getsReserva() {
        return sReserva;
    }

}




