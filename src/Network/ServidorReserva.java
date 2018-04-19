package Network;


import Model.Comanda;
import Model.Gestionador;
import Model.Usuari;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServidorReserva extends Thread {

    private final Gestionador gestionador;
    private Socket sClient;
    private Usuari user;
    private ArrayList<ServidorReserva> servers;
    private ObjectOutputStream ooStream;
    private DataOutputStream doStream;
    private DataInputStream diStream;
    private ObjectInputStream oiStream;

    //private Controlador controller;


    public ServidorReserva(Socket sClient, ArrayList<ServidorReserva> servers, Gestionador gestionador) {
        this.sClient = sClient;
        this.servers = servers;
        this.gestionador = gestionador;
        //this.controller = controller;
    }


    @Override

    public void run() {

        try {
            diStream = new DataInputStream(sClient.getInputStream());
            doStream = new DataOutputStream(sClient.getOutputStream());
            ooStream = new ObjectOutputStream(sClient.getOutputStream());
            oiStream = new ObjectInputStream(sClient.getInputStream());

            user = (Usuari) oiStream.readObject();
            System.out.println("Reserva: " + user.getUser() + " - " + user.getPassword());

            if (gestionador.comprovaUserPass(user.getUser(), user.getPassword())) {

                doStream.writeUTF("true");                                                  //enviem true en cas de haver entrat correctaemnt
                ooStream.writeObject(gestionador.retornaCarta());                               //enviem lña carta amb plats disponibles

                while (true) {
                    ooStream.writeObject(gestionador.retornaComanda(user.getUser()));
                    Comanda com = (Comanda) oiStream.readObject();                          //Rebem la comanda enviada pel usuari
                    String analisi = gestionador.analitzarComanda(com);

                    if (analisi.equals("true")) {
                        gestionador.addComanda(com);                                                //Guardo la comanda
                        doStream.writeUTF("true");
                        //actualitzar vista de gestionar comandes
                    } else {
                        doStream.writeUTF("No queden suficients unitats de:" + analisi);//enviar error
                    }
                }
            } else {
                doStream.writeUTF("Usuari o Password incorrectes!");            //preparar networkReserva per rebre un string
                System.out.println("Reserva: Usuari o Password incorrectes!");
                servers.remove(this);
            }
        }catch(MySQLIntegrityConstraintViolationException r){

            try {
                doStream.writeUTF("El plat no existeix");
                System.out.println("EEEEEE");
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (IOException | ClassNotFoundException  | SQLException e) {
            servers.remove(this);                                                   //En cas de que es desconnecti el client o hi hagi algun error tanco el server dedicat
            e.printStackTrace();
            System.out.println("WWWW");
        }
    }


    //Funcions

    public void enviaComanda() {
        try {
            ooStream.writeObject(gestionador.retornaCarta());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUser() {
        return user.getUser();
    }
}
