package Vista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogAfegirPlat extends JFrame {
    /**
     * Classe que crea un panell en el cas que vulguem afegir un plat nou a la bdd
     */

    private JTextField jtfNom;
    private JTextField jtfPreu;
    private JTextField jtfUnitats;
    private JButton jbAfegir = new JButton();

    /**
     * Funció que crea aquesta finestra
     */
    public DialogAfegirPlat() {

        setSize(new Dimension(400, 250));
        setResizable(false);

        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 364, 189);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Nom:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label.setBounds(35, 45, 57, 22);
        panel.add(label);

        JLabel label_1 = new JLabel("Preu:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_1.setBounds(35, 78, 63, 22);
        panel.add(label_1);

        JLabel label_2 = new JLabel("Unitats:");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_2.setBounds(35, 111, 63, 22);
        panel.add(label_2);

        jbAfegir.setText("AFEGIR");
        jbAfegir.setFont(new Font("Tahoma", Font.BOLD, 18));
        jbAfegir.setBounds(88, 144, 201, 34);
        panel.add(jbAfegir);


        jtfNom = new JTextField();
        jtfNom.setColumns(10);
        jtfNom.setBounds(108, 43, 181, 24);
        panel.add(jtfNom);


        jtfPreu = new JTextField();
        jtfPreu.setColumns(10);
        jtfPreu.setBounds(108, 78, 100, 24);
        panel.add(jtfPreu);

        jtfUnitats = new JTextField();
        jtfUnitats.setColumns(10);
        jtfUnitats.setBounds(108, 111, 100, 24);
        panel.add(jtfUnitats);


        JLabel label_3 = new JLabel("\u20AC");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_3.setBounds(218, 78, 63, 22);
        panel.add(label_3);

        JLabel label_4 = new JLabel("uts.");
        label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_4.setBounds(218, 111, 63, 22);
        panel.add(label_4);

        JLabel label_5 = new JLabel("Nou plat:");
        label_5.setFont(new Font("Tahoma", Font.BOLD, 20));
        label_5.setBounds(10, 0, 191, 44);
        panel.add(label_5);
    }

    /**
     * funcio que registra el controlador dels botons
     * @param controlador controlador a registrar
     */
    public void registraControlador(ActionListener controlador) {
        jbAfegir.addActionListener(controlador);
        jbAfegir.setActionCommand("AFEGIR PLAT");
    }

    /**
     * Funcio que retorna el nom que s'ha escrit per el nou plat
     * @return nom
     */
    public String getJtfNom() {
        return jtfNom.getText();
    }

    /**
     * Funcio que retorna el preu que s'ha escrit per el nou plat
     * @return preu
     */
    public Float getJtfPreu() throws NumberFormatException {

        return Float.parseFloat(jtfPreu.getText());
    }

    /**
     * Funcio que retorna les unitats que s'ha escrit per el nou plat
     * @return unitats
     */
    public int getJtfUnitats() throws NumberFormatException {
        return Integer.parseInt(jtfUnitats.getText());
    }

    /**
     * Funció que neteja tots els camps de la finestra
     */
    public void netejaCamps() {
        jtfNom.setText("");
        jtfPreu.setText("");
        jtfUnitats.setText("");
    }

}