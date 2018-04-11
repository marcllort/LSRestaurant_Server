package Network;

import Model.Gestionador;
import Model.Reserva;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorEntrada extends Thread {

    private final Gestionador gestionador;
    private Socket sClient;
    private ObjectOutputStream doStream;
    private ObjectInputStream diStream;
    private ServidorEntrada servidorEntrada;
    //private Controlador controller;


    public ServidorEntrada(Socket sClient, Gestionador gestionador) {
        this.sClient = sClient;
        this.gestionador = gestionador;
        //this.controller = controller;
    }


    @Override

    public void run() {

        try {

            diStream = new ObjectInputStream(sClient.getInputStream());
            doStream = new ObjectOutputStream(sClient.getOutputStream());
            System.out.println("provca");
            Reserva reserva = (Reserva) diStream.readObject();
            System.out.println(reserva.getnComencals()+"funciona");
            String estatReserva = gestionador.creaReserva(reserva, gestionador.generatePass());

            if (estatReserva.equals("true")) {
                doStream.writeUTF(gestionador.generatePass());
                //servers.remove(this);
            } else {
                doStream.writeUTF(estatReserva);            //preparar networkReserva per rebre un string
                //servers.remove(this);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            //servers.remove(this);                                                   //En cas de que es desconnecti el client o hi hagi algun error tanco el server dedicat
        }
    }


    //Funcions

    public void enviaCarta() {
        try {
            doStream.writeObject(gestionador.retornaCarta());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
