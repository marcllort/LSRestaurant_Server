package Vista;

import javax.swing.*;
import javax.xml.bind.JAXBPermission;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class VistaTop5 extends JPanel {
    private JButton jbSemanal;
    private JButton jbTotal;
    private Grafic gr;




    public VistaTop5() {
        gr = new Grafic();
        jbTotal = new JButton("Ranking Total");
        jbSemanal = new JButton("Ranking Semanal");
        jbTotal.setSelected(true);
        this.setLayout(new BorderLayout());
        this.add(gr, BorderLayout.CENTER);
        JPanel jpButton = new JPanel();
        jpButton.add( jbTotal);
        jpButton.add(jbSemanal);
        this.add(jpButton, BorderLayout.NORTH);

    }


    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant
        jbSemanal.addActionListener(controller);
        jbSemanal.setActionCommand("Semanal");
        jbTotal.addActionListener(controller);
        jbTotal.setActionCommand("Total");


    }

    public void grSemanal(int[] indexes, ArrayList<String> plats) throws Exception{


        this.remove(gr);
        gr = new Grafic();
        gr.grafic(indexes, plats);

        //this.add(new JLabel("HOLA"), BorderLayout.CENTER);
        this.add(gr, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();

    }
    public JButton getJbSemanal() {
        return jbSemanal;
    }

    public JButton getJbTotal() {
        return jbTotal;
    }

}