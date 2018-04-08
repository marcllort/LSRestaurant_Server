package Network;

//import Controlador.Controlador;

import Model.Comanda;
import Model.Gestionador;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorReserva extends Thread {


    private String user;
    private final Gestionador gestionador;
    private Socket sClient;
    private ArrayList<ServidorReserva> servers;
    private ObjectOutputStream doStream;
    private ObjectInputStream diStream;
    private Controlador controller;


    public ServidorReserva(Socket sClient, ArrayList<ServidorReserva> servers, Gestionador gestionador, Controlador controller) {
        this.sClient = sClient;
        this.servers = servers;
        this.gestionador = gestionador;
        this.controller = controller;
    }


    @Override

    public void run() {

        try {

            diStream = new ObjectInputStream(sClient.getInputStream());
            doStream = new ObjectOutputStream(sClient.getOutputStream());

            user = diStream.readUTF();
            String pass = diStream.readUTF();

            if (gestionador.comprovaUserPass(user, pass)) {
                doStream.writeUTF("true");                                                  //enviem true en cas de haver entrat correctaemnt

                doStream.writeObject(gestionador.retornaCarta());                               //enviem lña carta amb plats disponibles

                while (true) {

                    Comanda com = (Comanda) diStream.readObject();                          //Rebem la comanda enviada pel usuari
                    String analisi = gestionador.analitzarComanda();
                    if (analisi.equals("true")) {

                        gestionador.addComanda(com);                                                //Guardo la comanda
                        doStream.writeUTF("Comanda realitzada amb exit!");
                        //actualitzar vista d egestionar comandes

                    } else {
                        doStream.writeUTF("No queden suficients unitats de:" + analisi);//enviar error
                    }

                    //controller.updateVista(comanda.getAllComandes());                       //Actualitzo vista de el server
                    //controller.enableBut(true);                                       //Activo el boto, per si estava desactivcat

                }
            } else {
                doStream.writeUTF("error");            //preparar networkReserva per rebre un string
                servers.remove(this);
            }

        } catch (IOException | ClassNotFoundException e) {
            servers.remove(this);                                                   //En cas de que es desconnecti el client o hi hagi algun error tanco el server dedicat
        }
    }

    public void enviaComanda() {
        try {
            doStream.writeObject(gestionador.retornaCarta());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUser() {
        return user;
    }
}
