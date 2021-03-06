package Vista;


import Controlador.Controlador;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;


public class ServidorVista extends JFrame {
    /**
     * Classe que té la vista general del servidor, principalment s'encarrega d'intercambiar les pestanyes
     */
    private VistaTaules vistaTaules;
    private VistaComandes vistaComandes;
    private VistaPlats vistaPlats;
    private VistaTop5 vistaTop5;

    private JPanel layout;

    //menu
    private JMenuBar jmbBarra;
    private JMenuItem jmiPlats;
    private JMenuItem jmiComandes;
    private JMenuItem jmiTop5;
    private JMenuItem jmiTaula;

    /**
     * Constructor de la vista general
     */
    public ServidorVista() {

        setSize(600, 330);                                      //Dono les propietats inicials al JFrame
        setLocationRelativeTo(null);
        setTitle("LSRestaurant-Server");
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


    }

    /**
     * Funcio que registra el controlador de totes les pestanyes
     * @param controller tipus controlador
     */
    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

        vistaTaules.registraControlador(controller);
        vistaTop5.registraControlador(controller);
        vistaPlats.registraControlador(controller);
        vistaComandes.registraControlador(controller);

    }

    /**
     * Funcio que activa la pestanya de les taules
     * @param controller tipus controlador
     */
    public void activaTaula(Controlador controller) {
        CardLayout cardLayout = (CardLayout) layout.getLayout();
        cardLayout.show(layout, "TAULES");
    }

    /**
     * Funcio que activa la pestanya de la carta
     */
    public void activaCarta() {
        CardLayout cardLayout = (CardLayout) layout.getLayout();
        cardLayout.show(layout, "CARTA");

    }

    /**
     * Funcio que activa la pestanya de les comandes
     */
    public void activaComanda() {
        CardLayout cardLayout = (CardLayout) layout.getLayout();
        cardLayout.show(layout, "COMANDES");
    }

    /**
     * Funcio que activa la pestanya del grafic
     */
    public void activaTop5() {
        CardLayout cardLayout = (CardLayout) layout.getLayout();
        cardLayout.show(layout, "TOP5");
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

    /**
     * Funcio que crea el menu per alternar entre pestanyes
     * @param controller tipus controlador
     */
    public void creaMenu(Controlador controller) {

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

        jmiTaula.addActionListener(controller);
        jmiTaula.setActionCommand("TAULES");
        jmiPlats.addActionListener(controller);
        jmiPlats.setActionCommand("CARTA");
        jmiComandes.addActionListener(controller);
        jmiComandes.setActionCommand("COMANDES");
        jmiTop5.addActionListener(controller);
        jmiTop5.setActionCommand("TOP5");


    }

    public JPanel getJpanelLayout() {
        return layout;
    }

    public LayoutManager getLayout() {
        return layout.getLayout();
    }

}
