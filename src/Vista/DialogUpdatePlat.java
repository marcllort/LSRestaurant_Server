package Vista;

import javax.swing.*;
import java.awt.*;

public class DialogUpdatePlat extends JPanel {

    private JTextField jtfUnitats;
    private JLabel jlNom;
    private JLabel jlPreu;

    public DialogUpdatePlat() {
        setLayout(new GridLayout(4, 0, 0, 0));

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

        JButton jbActualizar = new JButton("Actualitzar");
        jpBoto.add(jbActualizar);

    }


    public void setJlNom(String nom){
        this.jlNom.setText("Nom: " + nom);
    }

    public void setJlPreu(String preu){
        this.jlNom.setText("Preu: " + preu);
    }

}
