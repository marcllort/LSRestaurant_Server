package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogUpdatePlat extends JFrame {

    private JTextField jtfUnitats;
    private JLabel jlNom;
    private JLabel jlPreu;
    private JButton jbActualizar;

    public DialogUpdatePlat() {
        setLayout(new GridLayout(4, 0, 0, 0));
        setSize(600, 330);                                      //Dono les propietats inicials al JFrame


        JPanel jpName = new JPanel();
        this.add(jpName);
        jpName.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        jlNom = new JLabel("Nom: ");
        jpName.add(jlNom);

        JPanel jpPreu = new JPanel();
        add(jpPreu);

        jlPreu = new JLabel("Preu: ");
        jpPreu.add(jlPreu);

        JPanel jpUnitats = new JPanel();
        FlowLayout flowLayout = (FlowLayout) jpUnitats.getLayout();
        flowLayout.setAlignOnBaseline(true);
        add(jpUnitats);

        JLabel jlUnitats = new JLabel("Unitats: ");
        jpUnitats.add(jlUnitats);

        jtfUnitats = new JTextField();
        jpUnitats.add(jtfUnitats);
        jtfUnitats.setColumns(10);

        JLabel jlUts = new JLabel("uts.");
        jpUnitats.add(jlUts);

        JPanel jpBoto = new JPanel();
        add(jpBoto);

        jbActualizar = new JButton("Actualitzar");
        jpBoto.add(jbActualizar);

    }

    public void registraControlador(ActionListener controlador) {
        jbActualizar.addActionListener(controlador);
        jbActualizar.setActionCommand("ACTUALITZAR");
    }

    public void setJlNom(String nom) {
        this.jlNom.setText("Nom: " + nom);
    }

    public void setJlPreu(String preu) {
        this.jlPreu.setText("Preu: " + preu);
    }

    public int getJtfUnitats() {
        return Integer.parseInt(jtfUnitats.getText());
    }

    public void netejaCamps() {
        jtfUnitats.setText("");
    }


}
