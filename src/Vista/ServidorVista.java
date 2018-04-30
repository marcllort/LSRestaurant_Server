package Vista;


import Controlador.Controlador;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;



public class ServidorVista extends JFrame {

    private VistaTaules vistaTaules;
    private VistaComandes vistaComandes;
    private VistaPlats vistaPlats;
    private VistaTop5 vistaTop5;

    private CardLayout layout;

    //menu
    private JMenuBar jmbBarra;
    private JMenuItem jmiPlats;
    private JMenuItem jmiComandes;
    private JMenuItem jmiTop5;
    private JMenuItem jmiTaula;


    public ServidorVista() {

        setSize(600, 400);                                      //Dono les propietats inicials al JFrame
        setLocationRelativeTo(null);
        setTitle("DPO2-1718-PCS2-Server");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        layout = new CardLayout();
        this.vistaTaules = new VistaTaules();
        this.vistaComandes = new VistaComandes();
        this.getContentPane().setLayout(layout);
        this.getContentPane().add("TAULES", vistaComandes);
        creaMenu(new Controlador());

    }



    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);                               //JDialog per quan tinguem algun error el mostri
    }

    public void creaMenu(Controlador c) {

        jmiTaula = new JMenuItem("Taules");
        jmiPlats = new JMenuItem("Carta");
        jmiComandes = new JMenuItem("Comandes");
        jmiTop5 = new JMenuItem("TOP 5");

        jmbBarra = new JMenuBar();
        jmbBarra.setBackground(Color.LIGHT_GRAY);
        jmbBarra.setBorderPainted(true);
        jmbBarra.add(jmiTaula);
        jmbBarra.add(jmiPlats);
        jmbBarra.add(jmiComandes);
        jmbBarra.add(jmiTop5);


        this.setJMenuBar(jmbBarra);

        // Registrem controlador a les diferents opcions del menu
        jmiPlats.addActionListener(c);
        jmiPlats.setActionCommand("ACCES CARTA");
        jmiTop5.addActionListener(c);
        jmiTop5.setActionCommand("ACCES TOP5");
        jmiComandes.setActionCommand("ACCES COMANDA");
        jmiComandes.addActionListener(c);
        jmiTaula.addActionListener(c);
        jmiTaula.setActionCommand("ACCES TAULA");

    }

}
