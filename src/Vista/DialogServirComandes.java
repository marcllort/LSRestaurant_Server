package Vista;

import javax.swing.*;
import java.awt.*;

public class DialogServirComandes extends JPanel{

    private JList jlLlistaSi;
    private JList jlLlistaNo;
    private JButton jbServir;

    public DialogServirComandes() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel jpBox1 = new JPanel();
        this.add(jpBox1);
        jpBox1.setLayout(new BoxLayout(jpBox1, BoxLayout.Y_AXIS));


        DefaultListModel modelLlista = new DefaultListModel();

        modelLlista.addElement("Plat1");
        modelLlista.addElement("Plat2");


        JPanel jpNoServitLabel = new JPanel();
        jpBox1.add(jpNoServitLabel);

        JLabel jlNoServit = new JLabel("No servit:");
        jpNoServitLabel.add(jlNoServit);

        JPanel jpNoServit = new JPanel();
        jpBox1.add(jpNoServit);
        jpNoServit.setLayout(new BorderLayout(0, 0));

        jlLlistaNo = new JList(modelLlista);
        jpNoServit.add(jlLlistaNo);

        JPanel jpCenter = new JPanel();
        this.add(jpCenter);
        jpCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        jbServir = new JButton("Servir");
        jpCenter.add(jbServir);

        JPanel jpBox2 = new JPanel();
        this.add(jpBox2);
        jpBox2.setLayout(new BoxLayout(jpBox2, BoxLayout.Y_AXIS));

        JPanel jpServitLabel = new JPanel();
        jpBox2.add(jpServitLabel);

        JLabel jlServit = new JLabel("Servit:");
        jpServitLabel.add(jlServit);

        JPanel jpServit = new JPanel();
        jpBox2.add(jpServit);
        jpServit.setLayout(new BorderLayout(0, 0));

        jlLlistaSi = new JList(modelLlista);
        jpServit.add(jlLlistaSi);
    }

}