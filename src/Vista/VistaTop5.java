package Vista;

import javax.swing.*;
import javax.xml.bind.JAXBPermission;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class VistaTop5 extends JPanel {

    private int[] graphSource;
    private Integer max = 0;
    private ArrayList<String> nomPlat;


    public void VistaTop5(int[] graphSource, ArrayList<String> nomPlat) {
        this.graphSource = graphSource;
        this.nomPlat = nomPlat;
        for (int j = 0; j < graphSource.length; j++) {
            if (graphSource[j] > max) {
                max = graphSource[j];
            }
        }
        setupPanel();
        repaint();
    }


    public void paintComponent(Graphics grafic) {
        super.paintComponent(grafic);
        Graphics2D maingrafic = (Graphics2D) grafic;
        for (int i = 0; i < graphSource.length; i++) {
            int height = (graphSource[i] * (((this.getHeight()) / max))) / 2;
            int width = (this.getWidth()) / (graphSource.length + 1);
            int x = (width) * (i + 1);
            int y = (this.getHeight() - (graphSource[i] * (this.getHeight() / max))) / 2 + this.getHeight() / 4;

            int red = (int) (Math.random() * 256);
            int blue = (int) (Math.random() * 256);
            int green = (int) (Math.random() * 256);

            Integer a = graphSource[i];
            maingrafic.setColor(new Color(red, green, blue));
            maingrafic.fill(new Rectangle(x, y, width, height));
            maingrafic.drawString(nomPlat.get(i), x + width / 2 - nomPlat.get(i).length() * 3, this.getHeight() - 30);
            maingrafic.drawString(a.toString(), 20, (this.getHeight() - (graphSource[i] * (this.getHeight() / max))) / 2 + this.getHeight() / 4);


        }

    }

    private void setupPanel() {
        this.setBackground(Color.WHITE);
    }


    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant


    }

}