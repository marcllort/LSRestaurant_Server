package Vista;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaginaCarta extends JPanel{
    private ArrayList<BotoPlat> botons;
    private int numPagina;
    private ArrayList<Plat> plats;
    private ArrayList<BotoPlat> jbArrray ;

    public PaginaCarta(ArrayList<Plat> plats, int numPagina){
        this.plats = plats;
        this.numPagina= numPagina;
        jbArrray = creaButtons();
        this.setLayout(new GridLayout(2, 3));
        int i = 6*(numPagina-1);

        while( i  < (6*numPagina) && i < jbArrray.size()){

            this.add(jbArrray.get(i).getBoto());
            i++;
            System.out.println("iiiii"+i);;
        }
        this.repaint();
        this.revalidate();


    }

    private ArrayList<BotoPlat> creaButtons(){
        ArrayList<BotoPlat> array = new ArrayList<BotoPlat>();

        for(Plat p: plats){
            BotoPlat a = new BotoPlat(p.getNomPlat());

            array.add(a);
        }
        return array;
    }
    public PaginaCarta getpaginaCarta(){
        return this;
    }
}
