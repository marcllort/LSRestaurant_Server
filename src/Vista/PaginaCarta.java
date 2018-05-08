package Vista;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaginaCarta extends JPanel {

    private ArrayList<BotoPlat> botons;
    private int numPagina;
    private ArrayList<Plat> plats;
    private ArrayList<BotoPlat> jbArrray;

    public PaginaCarta(ArrayList<Plat> plats, int numPagina) {
        this.plats = plats;
        this.numPagina = numPagina;

        this.setLayout(new BorderLayout());
        this.setLayout(new GridLayout(2, 3));

        jbArrray = creaButtons();

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
}
