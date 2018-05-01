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
    //private DialogUpdatePlat dialogUpdatePlat;

    private JPanel layout;

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


        this.vistaTaules = new VistaTaules();
        this.vistaComandes = new VistaComandes();
        this.vistaPlats = new VistaPlats();
        this.vistaTop5 = new VistaTop5();

        layout = new JPanel(new CardLayout());

        layout.add(vistaTaules, "TAULES");
        layout.add(vistaPlats, "CARTA");
        layout.add(vistaComandes, "COMANDES");
        layout.add(vistaTop5, "TOP5");

        getContentPane().add(layout);

        //CardLayout cardLayout = (CardLayout) layout.getLayout();
        //cardLayout.show(layout, "COMANDES");

        creaMenu(new Controlador(this));

    }


    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

        jmiTaula.addActionListener(controller);
        jmiTaula.setActionCommand("TAULES");
        jmiPlats.addActionListener(controller);
        jmiPlats.setActionCommand("CARTA");
        jmiComandes.addActionListener(controller);
        jmiComandes.setActionCommand("COMANDES");
        jmiTop5.addActionListener(controller);
        jmiTop5.setActionCommand("TOP5");

        vistaTaules.registraControlador(controller);

    }


    public void activaTaula(Controlador controller){
        this.vistaTaules = new VistaTaules();
        CardLayout cardLayout = (CardLayout) layout.getLayout();
        cardLayout.show(layout, "TAULES");
        vistaTaules.registraControlador(controller);
    }

    public void activaCarta(Controlador controller){
        this.vistaPlats = new VistaPlats();
        CardLayout cardLayout = (CardLayout) layout.getLayout();
        cardLayout.show(layout, "CARTA");
        vistaPlats.registraControlador(controller);
    }

    public void activaComanda(Controlador controller){
        this.vistaComandes = new VistaComandes();
        CardLayout cardLayout = (CardLayout) layout.getLayout();
        cardLayout.show(layout, "COMANDES");
        vistaComandes.registraControlador(controller);
    }

    public void activaTop5(Controlador controller){
        this.vistaTop5 = new VistaTop5();
        CardLayout cardLayout = (CardLayout) layout.getLayout();
        cardLayout.show(layout, "TOP5");
        vistaTop5.registraControlador(controller);
    }


    public VistaTaules getVistaTaules() {
        return vistaTaules;
    }

    public VistaComandes getVistaComandes() {
        return vistaComandes;
    }

    public VistaPlats getVistaPlats() {
        return vistaPlats;
    }

    public VistaTop5 getVistaTop5() {
        return vistaTop5;
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

    public JPanel getJpanelLayout() {
        return layout;
    }

    public LayoutManager getLayout() {
        return layout.getLayout();
    }

}
