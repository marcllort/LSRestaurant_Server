package Model;

import javax.swing.*;
import javax.xml.bind.JAXBPermission;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Grafic extends JPanel {
    private int[] graphSource;
    private Integer max = 0;
    public void grafic(){

        graphSource = new int[] {1,2,20,4,5};
       for(int j = 0 ; j< graphSource.length; j++){
           if (graphSource[j] > max){
               max = graphSource[j];
           }
       }
        setupPanel();
        repaint();

    }
    public void paintComponent(Graphics grafic){
        super.paintComponent(grafic);
        Graphics2D maingrafic = (Graphics2D) grafic;
        for(int i = 0 ; i < graphSource.length; i++){
            int height = (graphSource[i]* (((this.getHeight())/max)));
            int width = this.getWidth()/graphSource.length+1;
            int x = width * (i);
            int y = this.getHeight()-(graphSource[i]* (this.getHeight()/max));

            int red = (int)(Math.random() * 256);
            int blue = (int)(Math.random() * 256);
            int green = (int)(Math.random() * 256);


            maingrafic.setColor(new Color(red,green,blue));
            maingrafic.fill(new Rectangle(x,y,width,height));



        }



    }
    private void setupPanel(){
        this.setBackground(Color.WHITE);

    }
}
