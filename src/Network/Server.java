package Network;


import Model.Gestionador;
import Model.Json.*;



public class Server {

    private static int portReserva ;                                               //Declarem els atributs
    private static int portEntrada ;
    private final Gestionador gestionador;
    //private Controlador controller;


    public Server(Gestionador gestionador) {
        this.gestionador = gestionador;
        ConfiguracioServer conf = LectorJson.llegeixConfiguracioServer();
        portReserva = conf.lectorPortReserva();

        portEntrada = conf.lectorPortEntrada();
    }


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




