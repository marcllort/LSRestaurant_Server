package Vista;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaPlats extends JPanel {
    /**
     * Vista que te la carta i les opcions d'afegir, modificar i eliminar plats
     */
    private DialogAfegirPlat dialogAfegirPlat;
    private DialogUpdatePlat dialogUpdatePlat;
    private JButton jbAnterior;
    private JButton jbSeguent;
    private JButton jbAfegir;
    private int numPagina;

    private JLabel jlPgina;
    private PaginaCarta pag;

    /**
     * Constructor de la finestra
     */
    public VistaPlats() {

        pag = new PaginaCarta(1);


        this.setLayout(new BorderLayout());

        this.add(pag, BorderLayout.CENTER);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(10, 206, 559, 46);


        jbAfegir = new JButton("Afegir Plat");
        jbAfegir.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jbAfegir.setBounds(10, 6, 107, 35);
        panel_4.add(jbAfegir);

        jbAnterior = new JButton("Anterior");
        jbAnterior.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jbAnterior.setBounds(10, 6, 107, 35);
        panel_4.add(jbAnterior);

        jbSeguent = new JButton("Seg\u00FCent");
        jbSeguent.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jbSeguent.setBounds(442, 6, 107, 35);
        panel_4.add(jbSeguent);

        jlPgina = new JLabel("P\u00E0gina 1");
        jlPgina.setFont(new Font("Tahoma", Font.PLAIN, 17));
        jlPgina.setBounds(244, 12, 77, 23);
        panel_4.add(jlPgina);


        numPagina = 1;


        this.add(panel_4, BorderLayout.SOUTH);
        dialogAfegirPlat = new DialogAfegirPlat();
        dialogUpdatePlat = new DialogUpdatePlat();


    }

    /**
     * Funcio que registra el controlador de la finestra
     * @param controller tipus controlador
     */

    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

        dialogAfegirPlat.registraControlador(controller);
        dialogUpdatePlat.registraControlador(controller);
        jbAnterior.setActionCommand("ANTERIOR");
        jbAnterior.addActionListener(controller);
        jbSeguent.setActionCommand("SEGUENT");
        jbSeguent.addActionListener(controller);
        pag.registraControler(controller);
        jbAfegir.addActionListener(controller);
        jbAfegir.setActionCommand("AFEGIR NOU PLAT");

    }

    /**
     * Funcio que retorna el dialog de afegir plat
     * @return dialog
     */
    public DialogAfegirPlat getDialogAfegirPlat() {
        return dialogAfegirPlat;
    }

    /**
     * Funcio que retorna la finestra de actualitzar un plat
     * @return finestra de actualitzar un plat
     */
    public DialogUpdatePlat getDialogUpdatePlat() {
        return dialogUpdatePlat;
    }

    /**
     * Funcio que canvia de pagina
     * @param plats Array de plats
     * @param pagina Pagina a la que volem anar
     */
    public void paginaCarta(ArrayList<Plat> plats, int pagina) {
        numPagina = pagina;

        pag.canviaPagina(pagina);

        this.remove(((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.CENTER));
        this.add(pag, BorderLayout.CENTER);
        jlPgina.setText("P\u00E0gina " + numPagina);


        this.repaint();
        this.revalidate();
    }

    /**
     * Funcio que retorna el numero de pagina en que ens trobem
     * @return numero de pagina
     */
    public int getPaginaCarta() {
        return numPagina;
    }

    /**
     * Funcio que retorna la carta
     * @return tipus paginacarta
     */
    public PaginaCarta getPag() {
        return pag;
    }

    /**
     * Funcio que afegeix un nou boto
     * @param controlador Controlador per registrar el boto
     * @param nom nom que tindra el boto
     */
    public void afegeixBoto(ActionListener controlador, String nom) {
        pag.afegeixBoto(controlador, nom);
    }

    /**
     * Funcio per eliminar un boto
     * @param boto nom del boto a eliminar
     */
    public void eliminaBoto(String boto) {
        pag.eliminaBoto(boto);

    }

}
