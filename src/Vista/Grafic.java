package Vista;



import javax.swing.*;
import javax.xml.bind.JAXBPermission;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Grafic extends JPanel {

    private int[] graphSource;
    private Integer max = 0;
    private ArrayList<String> nomPlat;


    public void grafic(int[] graphSource, ArrayList<String> nomPlat) throws Exception{
        this.graphSource = graphSource;
        this.nomPlat = nomPlat;
        for (int j = 0; j < graphSource.length; j++) {

            if (graphSource[j] > max) {
                max = graphSource[j];
            }
        }
        if(max > 0 && max <= 4){
            max = 5;
        }
            int p = 1/max;

        setupPanel();
        repaint();
    }


    public void paintComponent(Graphics grafic) throws ArithmeticException{
        if (max > 0 ) {
            super.paintComponent(grafic);
            int ymax = 0;
            int hmax = 0;
            int eix = (this.getWidth()) / (graphSource.length + 2);
            int width = (this.getWidth()) / (graphSource.length + 2);
            Graphics2D maingrafic = (Graphics2D) grafic;
            for (int i = 0; i < llargada(); i++) {
                int height = (graphSource[i] * (((this.getHeight()) / max))) / 2;

                int x = (width) * (i + 1);
                int y = (this.getHeight() - (graphSource[i] * (this.getHeight() / max))) / 2 + this.getHeight() / 4;

                int red = (int) (Math.random() * 256);
                int blue = (int) (Math.random() * 256);
                int green = (int) (Math.random() * 256);
                ymax = (this.getHeight() - (max * (this.getHeight() / max))) / 2 + this.getHeight() / 4;
                hmax = (max * (((this.getHeight()) / max))) / 2;


                maingrafic.setColor(new Color(red, green, blue));
                maingrafic.fill(new Rectangle(x, y, width, height));
                if(nomPlat.size()> 10){
                    System.out.println("AAAA");
                    String[] nom = preparaNomPlat(i);
                    maingrafic.drawString(nom[0], x + width / 2 - nomPlat.get(i).length() * 3, this.getHeight() - 30);
                    maingrafic.drawString(nom[1], x + width / 2 - nomPlat.get(i).length() * 3, this.getHeight() - 10);


                } else{
                    maingrafic.drawString(nomPlat.get(i), x + width / 2 - nomPlat.get(i).length() * 3, this.getHeight() - 30);

                }




            }
            for (int i = 0; i < 5; i++) {
                Integer a = max / 5 * (i + 1);
                maingrafic.setColor(Color.BLACK);
                maingrafic.drawString(a.toString(), eix / 2, ymax + (hmax) / 5 * (4 - i));
            }
            maingrafic.setColor(Color.BLACK);
            maingrafic.drawString("0", eix / 2, ymax + hmax);
            maingrafic.setColor(Color.LIGHT_GRAY);
            maingrafic.fill(new Rectangle(eix, 20, 5, ymax + hmax - 20));
            maingrafic.fill(new Rectangle(eix, ymax + hmax, width * 5, 5));
        }
        else {

        }

    }

    private void setupPanel() {
        this.setBackground(Color.WHITE);
    }
    private String[] preparaNomPlat(int i){
        String string = nomPlat.get(i);
        String[] parts = string.split(" ");
        String part1 = parts[0]; // 123
        String part2 = parts[1]; // 654321
        return parts;

    }
    private int llargada(){
        int i = 0;
        try {
            while (i < 5) {
                i++;
                nomPlat.get(i);
            }
            return i;
        }catch (Exception e){
            return i;
        }
    }
}