package Vista;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

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
    public void afegeixBoto(ActionListener controller, String nom){
        BotoPlat butt = new BotoPlat(nom);
        butt.registraController(controller, nom);
        jbArrray.add(butt);
        canviaPagina(numPagina);

    }
    public void eliminaBoto(String boto){
        int i = 0;
        Iterator<BotoPlat> iter = jbArrray.iterator();

        while (iter.hasNext()) {
            BotoPlat a = iter.next();
            if (a.getNomPlat().equals(boto))
                iter.remove();

        }

        canviaPagina(numPagina);
    }

}