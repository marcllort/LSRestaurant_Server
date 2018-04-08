package Network;

//import Controlador.Controlador;

import Model.Comanda;
import Model.Gestionador;

import java.io.ObjectInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorDedicat extends Thread {


    private String user;
    private final Gestionador gestionador;
    private Socket sClient;
    private ArrayList<ServidorDedicat> servers;
    private ObjectOutputStream doStream;
    private ObjectInputStream diStream;
    private Controlador controller;


    public ServidorDedicat(Socket sClient, ArrayList<ServidorDedicat> servers, Gestionador gestionador, Controlador controller) {
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

                doStream.writeObject(gestionador.);

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
                doStream.writeUTF("Usuari o password incorrectes!");            //preparar networkReserva per rebre un string
            }

        } catch (IOException | ClassNotFoundException e) {
            servers.remove(this);                                                   //En cas de que es desconnecti el client o hi hagi algun error tanco el server dedicat
        }
    }

    public void enviaMissatge() {
        try {
            if (comanda.getAllComandes().equals("")) {
                doStream.writeUTF("No hi ha cua!");                                 //Si no hi ha cap comanda al missatge indico que no hi ha cua
            } else {
                doStream.writeUTF(comanda.getAllComandes());                            //Si hi ha cua, envio la llista de totes les comandes
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUser() {
        return user;
    }
}
