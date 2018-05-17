package Vista;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class PaginaCarta extends JPanel {
    /**
     * Classe que canvia de pàgines la carta
     */

    private int numPagina;
    private ArrayList<Plat> plats;
    private ArrayList<BotoPlat> jbArrray;

    /**
     * Funcio que crea les pagines
     * @param numPagina pagina a la que vols iniciar la carta
     */
    public PaginaCarta(int numPagina) {

        this.numPagina = numPagina;
        this.setLayout(new GridLayout(2, 3));
        this.repaint();
        this.revalidate();

    }

    /**
     * Funcio que canvia a la pagina desitjada
     * @param numPagina num de la pagina a la que volem anar
     */
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

    /**
     * Funcio que reparteix els plats en pagines
     * @param plats
     */
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

    /**
     * Funcio que registra el controlador per tots els botons
     * @param controler
     */
    public void registraControler(ActionListener controler) {


        for (BotoPlat p : jbArrray) {

            p.getBoto().addActionListener(controler);

            p.getBoto().setActionCommand(p.getNomPlat());

        }

    }

    /**
     * Funcio que afegeix un botó , en cas que creem un nou plat
     * @param controller controller per registrar el nou boto
     * @param nom nom del plat que crearem
     */
    public void afegeixBoto(ActionListener controller, String nom) {
        BotoPlat butt = new BotoPlat(nom);
        butt.registraController(controller, nom);
        jbArrray.add(butt);
        System.out.println("SIZEEEE" + jbArrray.size() + "NAMEEE :" + nom);
        canviaPagina(numPagina);

    }

    /**
     * Funcio que elminia un boto, en cas que eliminem un plat
     * @param boto boto a eliminar
     */
    public void eliminaBoto(String boto) {
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