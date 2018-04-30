package Vista;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;



public class ServidorVista extends JFrame {

    private VistaTaules vistaTaules;
    private VistaComandes vistaComandes;
    private VistaPlats vistaPlats;
    private VistaTop5 vistaTop5;

    private CardLayout layout;

    public ServidorVista() {

        setSize(600, 400);                                      //Dono les propietats inicials al JFrame
        setLocationRelativeTo(null);
        setTitle("DPO2-1718-PCS2-Server");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        layout = new CardLayout();
        this.vistaTaules = new VistaTaules();
        this.getContentPane().setLayout(layout);
        this.getContentPane().add("TAULES", vistaTaules);


    }



    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);                               //JDialog per quan tinguem algun error el mostri
    }

}
