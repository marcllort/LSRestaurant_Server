package Vista;

import Controlador.Controlador;
import Model.Comanda;
import Model.Gestionador;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogServirComandes extends JDialog {

    private JList jlLlistaSi;
    private JList jlLlistaNo;
    private JButton jbServir;
    private DefaultListModel modelLlistaServits;
    private DefaultListModel modelLlistaNoServits;
    private String user;

    public DialogServirComandes(Gestionador gestionador, String usuari) {

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        JPanel jpBox1 = new JPanel();
        this.add(jpBox1);
        jpBox1.setLayout(new BoxLayout(jpBox1, BoxLayout.Y_AXIS));
        setSize(600, 400);                                      //Dono les propietats inicials al JFrame

        user = usuari;
        Comanda comanda = gestionador.retornaComanda(usuari);

        ferLlistes(comanda);


        JPanel jpNoServitLabel = new JPanel();
        jpBox1.add(jpNoServitLabel);

        JLabel jlNoServit = new JLabel("No servit:");
        jpNoServitLabel.add(jlNoServit);

        JPanel jpNoServit = new JPanel();
        jpBox1.add(jpNoServit);
        jpNoServit.setLayout(new BorderLayout(0, 0));


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

        jpServit.add(jlLlistaSi);
    }

    public void ferLlistes(Comanda comanda) {
        modelLlistaServits = new DefaultListModel();
        modelLlistaNoServits = new DefaultListModel();

        modelLlistaNoServits.addElement("plat no0");
        modelLlistaServits.addElement("plat si");

        for (Plat p : comanda.getPlats()) {
            if (p.isServit()) {
                modelLlistaServits.addElement(p.getNomPlat());
            } else {
                modelLlistaNoServits.addElement(p.getNomPlat());
            }
        }

        jlLlistaNo = new JList(modelLlistaNoServits);
        jlLlistaSi = new JList(modelLlistaServits) {

            @Override
            public void setSelectionInterval(int index0, int index1) {
                super.setSelectionInterval(-1, -1);
            }
        };

    }

    public void registraControladorDialog(ActionListener controlador) {
        System.out.println("registrat");
        jbServir.addActionListener(controlador);
        jbServir.setActionCommand("SERVIR");
    }

    public String platSeleccionat() {
        System.out.println(modelLlistaNoServits.getElementAt(jlLlistaNo.getSelectedIndex()));
        return (String) modelLlistaNoServits.getElementAt(jlLlistaNo.getSelectedIndex());
    }

    public String usuariComanda(){
        return user;
    }

}