import Controlador.Controlador;
import Model.Comandador;
import Network.Server;
import Vista.ServidorVista;

public class Main {

    public static void main(String[] args) {

        ServidorVista vista = new ServidorVista();          //Creo vista del server
        vista.setVisible(true);                             //La fem visible

        Comandador comandador = new Comandador();           //Creo el model del servidor

        Server servidor = new Server(comandador);           //Creo servidor que conte el model, per poder enviar i guardar dades

        Controlador controlador = new Controlador(vista, comandador, servidor);             //Creo controlador que conte el model, vista i servidor per poder controlarlos quan toqui

        vista.registraControlador(controlador);             //Registro els elements de la vista al controlador

        servidor.startServer();                             //Inicio servidor

    }

}
