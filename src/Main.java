import Model.BDD;

import java.sql.*;

import Model.Gestionador;
import Model.Reserva;


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

            // bdd.insereixPlat("c",3,10,0);
            // bdd.updatePlat("c", 1);

            /*for (int i = 1 ;i<3; i++){
                bdd.createTable(2);
            }*/

            Reserva reserva1 = new Reserva("Alexalgsmaassssd", 1, gestionador.newData(6, 2, 2222), new Time(15, 00, 00));
            gestionador.creaReserva(reserva1);

        } catch (Exception e) {
            System.out.println("ERROOR");
            e.printStackTrace();
        }
    }

}






