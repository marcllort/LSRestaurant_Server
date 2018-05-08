package Vista;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaPlats extends JPanel {

    private DialogAfegirPlat dialogAfegirPlat;
    private DialogUpdatePlat dialogUpdatePlat;
    private JButton jbAnterior;
    private JButton jbSeguent;
    private int numPagina;

    private JLabel jlPgina;
    private PaginaCarta pag;

    public VistaPlats() {

       pag = new PaginaCarta(1);


        this.setLayout(new BorderLayout());

        this.add(pag, BorderLayout.CENTER);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(10, 206, 559, 46);

        //panel_4.setLayout(null);

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


    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

        dialogAfegirPlat.registraControlador(controller);
        dialogUpdatePlat.registraControlador(controller);
        jbAnterior.setActionCommand("ANTERIOR");
        jbAnterior.addActionListener(controller);
        jbSeguent.setActionCommand("SEGUENT");
        jbSeguent.addActionListener(controller);


    }

    public DialogAfegirPlat getDialogAfegirPlat() {
        return dialogAfegirPlat;
    }

    public DialogUpdatePlat getDialogUpdatePlat() {
        return dialogUpdatePlat;
    }

    public void paginaCarta(ArrayList<Plat> plats, int pagina) {
        numPagina = pagina;

        pag.canviaPagina(pagina);

        this.remove(((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.CENTER));
        this.add(pag, BorderLayout.CENTER);
        jlPgina.setText("P\u00E0gina " + numPagina);

        //this.remove(((BorderLayout)this.getLayout()).getLayoutComponent(BorderLayout.SOUTH).getComponentAt(1,3));

        this.repaint();
        this.revalidate();
    }

    public int getPaginaCarta() {
        return numPagina;
    }
    public void registraBotons(ActionListener controller){

    }

    public PaginaCarta getPag() {
        return pag;
    }
}
