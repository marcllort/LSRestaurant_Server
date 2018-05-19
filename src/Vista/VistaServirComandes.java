package Vista;

import Model.Comanda;
import Model.Gestionador;
import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaServirComandes extends JFrame {
    /**
     * Classe que conté la finestraque permet servir els plats
     */
    private JList jlLlistaSi;
    private JList jlLlistaNo;
    private JButton jbServirPlat;
    private DefaultListModel modelLlistaServits;
    private DefaultListModel modelLlistaNoServits;
    private String user;

    /**
     * Constructor de la finestra
     *
     * @param gestionador
     * @param usuari
     */
    public VistaServirComandes(Gestionador gestionador, String usuari) {

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        JPanel jpBox1 = new JPanel();
        this.add(jpBox1);
        jpBox1.setLayout(new BoxLayout(jpBox1, BoxLayout.Y_AXIS));
        setSize(600, 400);                                      //Dono les propietats inicials al JFrame

        user = usuari;
        Comanda comanda = gestionador.retornaComanda(usuari);
        jlLlistaNo = new JList();
        jlLlistaSi = new JList() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                super.setSelectionInterval(-1, -1);
            }
        };
        ferLlistes(comanda);


        JPanel jpNoServitLabel = new JPanel();
        jpBox1.add(jpNoServitLabel);

        JLabel jlNoServit = new JLabel("No servit:");
        jpNoServitLabel.add(jlNoServit);

        JPanel jpNoServit = new JPanel();
        jpBox1.add(jpNoServit);
        jpNoServit.setLayout(new BorderLayout(0, 0));


        jpNoServit.add(new JScrollPane(jlLlistaNo));


        JPanel jpCenter = new JPanel();
        this.add(jpCenter);
        jpCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        jbServirPlat = new JButton("Servir");
        jpCenter.add(jbServirPlat);

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

        jpServit.add(new JScrollPane(jlLlistaSi));
    }

    /**
     * Funcio que crea la llista de plats servits i plats per servir del usuari
     *
     * @param comanda Comanda amb els plats
     */
    public void ferLlistes(Comanda comanda) {
        modelLlistaServits = new DefaultListModel();
        modelLlistaNoServits = new DefaultListModel();


        for (Plat p : comanda.getPlats()) {
            if (p.isServit()) {
                modelLlistaServits.addElement(p.getNomPlat());
            } else {
                modelLlistaNoServits.addElement(p.getNomPlat());
            }
        }

        jlLlistaNo.setModel(modelLlistaNoServits);
        jlLlistaNo.repaint();
        jlLlistaNo.revalidate();
        jlLlistaSi.setModel(modelLlistaServits);
        jlLlistaSi.repaint();
    }

    /**
     * Registra el controlador del botó
     *
     * @param controlador tipus controlador
     */
    public void registraControladorDialog(ActionListener controlador) {
        jbServirPlat.addActionListener(controlador);
        jbServirPlat.setActionCommand("SERVIR");
    }

    /**
     * Funcio que retorna el nom del plat seleccionat
     *
     * @return string del plat seleccionat
     */
    public String platSeleccionat() {
        try {
            return (String) modelLlistaNoServits.getElementAt(jlLlistaNo.getSelectedIndex());
        } catch (ArrayIndexOutOfBoundsException ae) {
            JOptionPane.showMessageDialog(this, "Cap plat seleccionat!");
            return null;
        }
    }

    public String usuariComanda() {
        return user;
    }

}