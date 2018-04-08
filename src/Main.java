import Model.*;

import java.sql.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        Serverbbdd();

       /* ServidorVista vista = new ServidorVista();          //Creo vista del server
        vista.setVisible(true);                             //La fem visible

        Comandador comandador = new Comandador();           //Creo el model del servidor

        Server servidor = new Server(comandador);           //Creo servidor que conte el model, per poder enviar i guardar dades

        Controlador controlador = new Controlador(vista, comandador, servidor);             //Creo controlador que conte el model, vista i servidor per poder controlarlos quan toqui

        vista.registraControlador(controlador);             //Registro els elements de la vista al controlador

        servidor.startServer();    */                         //Inicio servidor

    }


    public static void Serverbbdd() {
        try {

            BDD bdd = new BDD();
            Gestionador gestionador = new Gestionador(bdd);
            //System.out.println(bdd.comprovaPassword("Alex", "yBxcpR"));

            //bdd.insereixPlat("gamba",3,10,0);
            //bdd.updatePlat("caca", 10);
            //bdd.serveixPlat("caca", "Alex");

            Comanda comand = bdd.mostraPlatsComanda("Alex");


            // bdd.eliminaPlat("caca");


            /*for (int i = 1 ;i<3; i++){
                bdd.createTable(2);
            }

            Reserva reserva1 = new Reserva("Alex", 1, gestionador.newData(6, 2, 2222), new Time(15, 00, 00));
            gestionador.creaReserva(reserva1);
           */ArrayList<Plat> arr= new ArrayList<Plat>();
            ArrayList<Plat> arr1= new ArrayList<Plat>();
           arr.add(new Plat("caca",12));
            Comanda comanda=new Comanda(arr,gestionador.newData(12,1,2001), new Time(12,40,00),"Alex");
            arr1 = bdd.platsNoDisponibles(comanda);
            System.out.println(arr1.get(0).getNomPlat());
            //bdd.creaComanda(comanda);
           ArrayList<InfoComandes> prova = bdd.LlistatComandes();
            System.out.println("PLATSPENDENTS"+prova.get(0).getPlatsPendents());
        } catch (Exception e) {
            System.out.println("ERROOR");
            e.printStackTrace();
        }
    }

}






