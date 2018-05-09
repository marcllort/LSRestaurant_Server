package Vista;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PaginaCarta extends JPanel {


    private int numPagina;
    private ArrayList<Plat> plats;
    private ArrayList<BotoPlat> jbArrray;

    public PaginaCarta(int numPagina) {

        this.numPagina = numPagina;
        this.setLayout(new GridLayout(2, 3));
        this.repaint();
        this.revalidate();

    }

    public void canviaPagina(int numPagina) {
        this.numPagina = numPagina;
        this.removeAll();
        GridLayout a = new GridLayout(3, 4);


        int i = 6 * (numPagina - 1);

        while (i < (6 * numPagina) && i < jbArrray.size()) {
            System.out.println("AFEGEIX " + i);
            this.add(jbArrray.get(i).getBoto());
            i++;

        }
        this.repaint();
        this.revalidate();
    }

    private ArrayList<BotoPlat> creaButtons() {
        ArrayList<BotoPlat> array = new ArrayList<BotoPlat>();

        for (Plat p : plats) {
            BotoPlat buton = new BotoPlat(p.getNomPlat());
            array.add(buton);
            //buton.addActionListener(controler);
            //buton.setActionCommand(p.getNomPlat());

        }
        return array;
    }

    public JPanel getpaginaCarta() {
        return this;
    }

    public ArrayList<BotoPlat> getJbArrray() {
        return jbArrray;
    }

    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
        jbArrray = creaButtons();
        int i = 6 * (numPagina - 1);

        while (i < (6 * numPagina) && i < jbArrray.size()) {

            this.add(jbArrray.get(i).getBoto());
            i++;
            System.out.println(i);

        }
    }

    public ArrayList<Plat> getPlats() {
        return plats;
    }


    public void registraControler(ActionListener controler) {


        for (BotoPlat p : jbArrray) {

            p.getBoto().addActionListener(controler);

            p.getBoto().setActionCommand(p.getNomPlat());

        }

    }
}