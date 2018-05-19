package Vista;

import javax.swing.*;
import javax.xml.bind.JAXBPermission;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Classe que s'encarrega de la vista del top 5 de plats
 */
public class VistaTop5 extends JPanel {
    private JButton jbSemanal;
    private JButton jbTotal;
    private Grafic gr;

    /**
     * Funcio que crea la vista del top 5 de plats
     */

    public VistaTop5() {
        gr = new Grafic();
        jbTotal = new JButton("Ranking Total");
        jbSemanal = new JButton("Ranking Semanal");
        jbTotal.setSelected(true);
        this.setLayout(new BorderLayout());
        this.add(gr, BorderLayout.CENTER);
        JPanel jpButton = new JPanel();
        jpButton.add(jbTotal);
        jpButton.add(jbSemanal);
        this.add(jpButton, BorderLayout.NORTH);

    }

    /**
     * Funcio que regiistra els dos botons de la vista
     *
     * @param controller controller
     */
    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant
        jbSemanal.addActionListener(controller);
        jbSemanal.setActionCommand("Semanal");
        jbTotal.addActionListener(controller);
        jbTotal.setActionCommand("Total");
    }

    /**
     * Funcio que actualitza els valors del grafic
     *
     * @param indexes valors del nou grafic
     * @param plats   noms dels nous plats
     * @throws Exception Quan no té valors
     */
    public void updateGrafic(int[] indexes, ArrayList<String> plats) throws Exception {

        this.remove(gr);
        gr = new Grafic();
        gr.grafic(indexes, plats);

        this.add(gr, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();

    }

    /**
     * Retorna el botó semanal
     *
     * @return boto
     */
    public JButton getJbSemanal() {
        return jbSemanal;
    }

    /**
     * Retorna el botó total
     *
     * @return boto
     */
    public JButton getJbTotal() {
        return jbTotal;
    }

}