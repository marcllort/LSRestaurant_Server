package Controlador;

import Model.Comandador;
import Network.Server;
import Vista.ServidorVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    private final ServidorVista vista;
    private Comandador model;
    private Server servidor;


    public Controlador(ServidorVista vista, Comandador model, Server servidor) {
        this.vista = vista;
        this.model = model;
        this.servidor = servidor;
        connectaServer();                                                               //Donc al servidor el controaldor
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            model.removeComandaAt(0);                                           //Quan hem clicken borro la primera coomanda del array
            updateVista(model.getAllComandes());                                      //Actualitzo la vista amb la nova llista de comandes actualitzada

            servidor.enviaC();                                                        //Envio a els clients la nova llista


        } catch (IndexOutOfBoundsException e1) {                                      //En el cas de que no tingui elements, quamn hem salti la excepcio faig el seguent
            vista.enableButton(false);                                          //Desactivo boto quan llista esta buida
            servidor.enviaC();                                                        //Envio a els clients la nova llista
            vista.setJlUser("------------");                                          //Actualitzo tots els elemtens indicant que la llista esta buida
            vista.setJlProductes("------------");
            vista.setJlOrders(0);
            vista.setJtaComandes("Llista buida");
            vista.showError("Llista buida!");
        }
    }


    public void updateVista(String comandes) {
        vista.setJtaComandes(comandes);
        vista.setJlUser(model.getComandaAt(0).getNom());
        vista.setJlProductes(model.getComandaAt(0).getPreu());
        vista.setJlOrders(model.sizeComanda());
    }

    private void connectaServer() {
        servidor.setController(this);
    }

    public void enableBut(boolean bool) {
        vista.enableButton(bool);
    }

}