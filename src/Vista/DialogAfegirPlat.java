package Vista;

import jdk.nashorn.internal.runtime.ECMAException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogAfegirPlat extends JFrame {

    private JTextField jtfNom;
    private JTextField jtfPreu;
    private JTextField jtfUnitats;
    private JButton jbAfegir;

    public DialogAfegirPlat() {
        setLayout(new GridLayout(4, 0, 0, 0));
        setSize(new Dimension(400,300));
        JPanel jpName = new JPanel();
        add(jpName);
        jpName.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel jlNom = new JLabel("Nom: ");
        jpName.add(jlNom);

        jtfNom = new JTextField();
        jpName.add(jtfNom);
        jtfNom.setColumns(10);

        JPanel jpPreu = new JPanel();
        add(jpPreu);

        JLabel jlPreu = new JLabel("Preu: ");
        jpPreu.add(jlPreu);

        jtfPreu = new JTextField();
        jpPreu.add(jtfPreu);
        jtfPreu.setColumns(10);

        JLabel jlEuro = new JLabel("\u20AC");
        jpPreu.add(jlEuro);

        JPanel jpUnitats = new JPanel();
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

        jbAfegir = new JButton("Afegir");
        jpBoto.add(jbAfegir);
    }

    public void registraControlador(ActionListener controlador) {
        jbAfegir.addActionListener(controlador);
        jbAfegir.setActionCommand("AFEGIR PLAT");
    }

    public String getJtfNom() {
        return jtfNom.getText();
    }

    public int getJtfPreu() throws NumberFormatException {
        return Integer.parseInt(jtfPreu.getText());
    }

    public int getJtfUnitats() throws NumberFormatException {
        return Integer.parseInt(jtfUnitats.getText());
    }

    public void netejaCamps() {
        jtfNom.setText("");
        jtfPreu.setText("");
        jtfUnitats.setText("");
    }

}