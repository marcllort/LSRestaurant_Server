import Model.BDD;

import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

//import Controlador.Controlador;
//import Model.Comandador;
//import Network.Server;
import Model.Gestionador;
import Model.Reserva;
import Vista.ServidorVista;

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


    public static void Serverbbdd(){
        try {
            BDD bdd = new BDD();
            Gestionador gestionador = new Gestionador(bdd);
           // bdd.insereixPlat("c",3,10,0);
           // bdd.updatePlat("c", 1);
            /*for (int i = 1 ;i<3; i++){
                bdd.createTable(2);
            }*/
                //bdd.createTable(3);
            String a = "SELECT * FROM Plat ";

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2019);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);

            Reserva reserva1 = new Reserva("Alexalmansaassssd", 2, gestionador.newData(-5, 23, 2222), new Time(15, 00, 00));
            gestionador.creaReserva(reserva1);

            //bdd.creaReserva("una3", "000",2,new java.sql.Date(cal.getTimeInMillis()), new Time(1,00,00),bdd.reservaTaula(2, new java.sql.Date(cal.getTimeInMillis()),new Time(1,00,00) ) );
            bdd.queriePlat(a);
        }catch (Exception e){
            System.out.println("ERROOR");
            e.printStackTrace();
        }
    }

}






