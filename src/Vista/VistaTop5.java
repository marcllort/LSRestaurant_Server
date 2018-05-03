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


    public  VistaTop5(){
         gr = new Grafic();
        jbTotal = new JButton("Ranking Total");
        jbSemanal = new JButton("Ranking Semanal");
        ArrayList<String> plats = new ArrayList<>();
        plats.add("ASd");
        plats.add("ASdas");
        plats.add("ASdasd");
        plats.add("ASdawe");
        plats.add("ASddass");
        int[] es = {1,2,3,10,20};
        gr.grafic(es,plats);
        this.setLayout(new BorderLayout());
        this.add(gr, BorderLayout.CENTER);
        JPanel jpButton = new JPanel();
        jpButton.add(jbSemanal);
        jpButton.add(jbTotal);
        this.add(jpButton,BorderLayout.NORTH);

    }


    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant
        jbSemanal.addActionListener(controller);
        jbSemanal.setActionCommand("Semanal");
        jbTotal.addActionListener(controller);
        jbTotal.setActionCommand("Total");


    }

    public void grSemanal(int[] indexes){
        ArrayList<String> plats = new ArrayList<>();
        plats.add("ASd");
        plats.add("ASdas");
        plats.add("ASdasd");
        plats.add("ASdawe");
        plats.add("ASddass");

        this.remove(gr);
        gr = new Grafic();
        gr.grafic(indexes,plats);

        //this.add(new JLabel("HOLA"), BorderLayout.CENTER);
        this.add(gr, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();

    }

}