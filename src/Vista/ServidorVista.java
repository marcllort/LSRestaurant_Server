package Vista;

import Model.Grafic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ServidorVista extends JFrame {

    private static final String SERVE_ACTION_COMMAND = "SERVE";
    private JLabel jlUser;
    private JButton jbServe;
    private JLabel jlProductes;
    private JTextArea jtaComandes;
    private JLabel jlOrders;


    public ServidorVista() {

        setSize(600, 400);                                      //Dono les propietats inicials al JFrame
        setLocationRelativeTo(null);
        setTitle("DPO2-1718-PCS2-Server");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JPanel jpLeft = Left();                                               //Creo amb la funcio left tota la part esquerra de la pantalla
        JScrollPane jspRight = Right();                                       //Creo amb la funcio right tota la part dreta de la pantalla

        JSplitPane jspCentre = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpLeft, jspRight);           //Creo un JSplitPane que hem serveix per poder moure el separador. Incloc els dos panels

        getContentPane().add(jspCentre);                                      //Afegeixo tot al contentPane

        //Creacio de grafic uy
        Grafic gr = new Grafic();
        int[] a = {1,2,3,4,5};
        ArrayList<String> arr = new ArrayList<>();
        arr.add("gamba");
        arr.add("gambas");
        arr.add("gambad");
        arr.add("gambaf");
        arr.add("gambaa");
        gr.grafic(a,arr);
        getContentPane().add(gr);

    }


    private JPanel Left() {

        JPanel jpLeft = new JPanel();                                                                       //Creo panel on posar tots els elements

        jpLeft.setLayout(new GridLayout(5, 1, 10, 0));                       //Faig us de el grid layout per tenirlos en posicio vertical

        jpLeft.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Order"),                                           //Poso el nom  del apartat al border que tindra el jpanel
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));


        JPanel jpUser = new JPanel();                                                                //Creo jpanel on posarem tots elements de la primera fila
        JLabel jlUser1 = new JLabel("Username: ");                                              //Jlabel amb nom de username
        jlUser = new JLabel("------------");                                                    //Inicialitzo el usuari dietn que la llista esta buida, perque encara cap usuari haura entrat el seu nom
        jlUser.setPreferredSize(new Dimension(190, 25));
        jpUser.add(jlUser1);                                                                         //Afeixo els dos elemtns al jpanel
        jpUser.add(jlUser);


        JPanel jpComanda = new JPanel();                                                             //Repeteixo el mateix procediemt del usuari amb la comanda
        JLabel jlComanda = new JLabel("Products: ");
        jlProductes = new JLabel("------------");
        jlProductes.setPreferredSize(new Dimension(190, 25));
        jlProductes.setMinimumSize(new Dimension(200, 25));                            //Poso minimum sizer perque no puguin reduir mes el splitPane
        jpComanda.add(jlComanda);
        jpComanda.add(jlProductes);


        JPanel jpSend = new JPanel(new BorderLayout());                                              //Faig un jpanel amb border, perque el boto sexpandeixi i ocupi el maxim d'espai
        jbServe = new JButton("Serve");
        jbServe.setEnabled(false);
        jpSend.add(jbServe, BorderLayout.NORTH);


        Container jpbuit = new Container();                                                          //Creo un container per afegir un a dalt i un abaix de el reste d'element sper aconseguir centrarlos
        jpLeft.add(jpbuit);

        jpLeft.add(jpUser);                                                                          //Afegeixo tot al jpane principal
        jpLeft.add(jpComanda);
        jpLeft.add(jpSend);

        return jpLeft;

    }

    private JScrollPane Right() {

        JScrollPane jspRight = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);       //Faig us de un scrollpane per si la llista es molt llarga

        jspRight.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Waiting queue"),                                  //Poso el nom  del apartat al border que tindra el jpanel
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JPanel jpRight = new JPanel();
        jpRight.setLayout(new BoxLayout(jpRight, BoxLayout.Y_AXIS));                                //Un box layout per poder posar primer un jlabel de les orders i despres el jtextarea de les comandes

        jtaComandes = new JTextArea();
        jtaComandes.setOpaque(false);                                                               //Perque quedi gris com el fons
        jtaComandes.setEditable(false);                                                             //Per no poder ser editatada

        jlOrders = new JLabel("Pending orders : 0");                                           //Iniciada dient que hi ha 0 orders perque ningu haura entrat cap encara

        JPanel jpOrders = new JPanel(new GridLayout());                                             //Faig us de un jpanel per vitar que el Jlabel es mogui quan canvia el seu text
        jpOrders.add(jlOrders);

        jpRight.add(jpOrders);
        jpRight.add(jtaComandes);                                                                   //Afegeixo tot al jpanel que despres posare al scrollpane

        jspRight.setViewportView(jpRight);

        return jspRight;
    }

    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant
        jbServe.addActionListener(controller);
        jbServe.setActionCommand(SERVE_ACTION_COMMAND);
    }


    //Setters per usarlos posteriorment al controlador

    public void enableButton(Boolean bool) {
        jbServe.setEnabled(bool);
    }

    public void setJlUser(String user) {
        jlUser.setText(user);
    }

    public void setJlProductes(String productes) {
        jlProductes.setText(productes);
    }

    public void setJtaComandes(String comandes) {
        jtaComandes.setText(comandes);
    }

    public void setJlOrders(int orders) {
        jlOrders.setText("Pending orders : " + orders);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);                               //JDialog per quan tinguem algun error el mostri
    }

}
