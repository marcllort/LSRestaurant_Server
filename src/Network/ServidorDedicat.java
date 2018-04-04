package Network;

import Controlador.Controlador;
import Model.Comanda;
import Model.Comandador;

import java.io.ObjectInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorDedicat extends Thread {

    private final Comandador comanda;
    private Socket sClient;
    private ArrayList<ServidorDedicat> servers;
    private DataOutputStream doStream;
    private ObjectInputStream diStream;
    private Controlador controller;


    public ServidorDedicat(Socket sClient, ArrayList<ServidorDedicat> servers, Comandador comanda, Controlador controller) {
        this.sClient = sClient;
        this.servers = servers;
        this.comanda = comanda;
        this.controller = controller;
    }


    @Override

    public void run() {

        try {

            diStream = new ObjectInputStream(sClient.getInputStream());
            doStream = new DataOutputStream(sClient.getOutputStream());

            while (true) {

                for (ServidorDedicat servidor : servers) {                              //Enviem missatges tal i com es connecta el client per si ja hi ha missatges
                    servidor.enviaMissatge();
                }

                Comanda com = (Comanda) diStream.readObject();                          //Rebem la comanda enviada pel usuari
                comanda.addComanda(com);                                                //Guardo la comanda
                controller.updateVista(comanda.getAllComandes());                       //Actualitzo vista de el server
                controller.enableBut(true);                                       //Activo el boto, per si estava desactivcat

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


}
