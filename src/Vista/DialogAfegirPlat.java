package Vista;

import javax.swing.*;
import java.awt.*;

public class DialogAfegirPlat extends JPanel{

    private JTextField jtfNom;
    private JTextField jtfPreu;
    private JTextField jtfUnitats;

    public DialogAfegirPlat() {
        setLayout(new GridLayout(4, 0, 0, 0));

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

        JButton jbAfegir = new JButton("Afegir");
        jpBoto.add(jbAfegir);
    }

}