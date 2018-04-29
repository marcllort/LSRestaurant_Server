package Network;

import Model.Gestionador;
import Model.Json.*;

/**
 * Classe servidor que es comunicarà amb els "dos" clients (reserva, entrada)
 */
public class Server {

    private static int portReserva;                                               //Declarem els atributs
    private static int portEntrada;
    private final Gestionador gestionador;
    //private Controlador controller;

    /**
     * Constructor de Server que rep gestionador per usarlo posteriorment
     * Fa us del lector json per llegiur els ports
     *
     * @param gestionador
     */
    public Server(Gestionador gestionador) {
        this.gestionador = gestionador;

        ConfiguracioServer conf = LectorJson.llegeixConfiguracioServer();
        portReserva = conf.lectorPortReserva();
        portEntrada = conf.lectorPortEntrada();
    }

    /**
     * Funcio start server per inicialitzar els sockets dels dos clients
     * Posteriorment fem els threads per poder correr els dos "servers" de forma independent
     */
    public void startServer() {
        ServerSocketReserva sReserva = new ServerSocketReserva(gestionador, portReserva);
        ServerSocketEntrada sEntrada = new ServerSocketEntrada(gestionador, portEntrada);
        Thread t1 = new Thread(sReserva);
        Thread t2 = new Thread(sEntrada);
        t1.start();
        t2.start();
    }



    /*public void setController(Controlador controller) {                                             //Serveix per donar el controlador al server, la usem al controlador
        this.controller = controller;
    }*/
}




