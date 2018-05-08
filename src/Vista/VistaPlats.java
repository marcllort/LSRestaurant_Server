package Vista;

import Model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.border.Border;

public class VistaPlats extends JPanel {

    private DialogAfegirPlat dialogAfegirPlat;
    private DialogUpdatePlat dialogUpdatePlat;
    private JButton jbAnterior;
    private JButton jbSeguent;


    public VistaPlats() {

        //this.setLayout(null);

this.setLayout(new BorderLayout());




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

        JLabel lblPgina = new JLabel("P\u00E0gina 1");
        lblPgina.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblPgina.setBounds(244, 12, 77, 23);
        panel_4.add(lblPgina);
        ArrayList<Plat> plats = new ArrayList<>();
        plats.add(new Plat("gamb",12));
        plats.add(new Plat("gamb",12));
        plats.add(new Plat("gamb",12)); plats.add(new Plat("gamb",12));
        plats.add(new Plat("gamb",12)); plats.add(new Plat("gamb",12));

        plats.add(new Plat("gambaaa",12));

PaginaCarta pag = new PaginaCarta(plats,1);

this.add(pag, BorderLayout.CENTER);
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
    public void paginaCarta(int pagina){
        ArrayList<Plat> plats = new ArrayList<>();
        plats.add(new Plat("gamb",12));
        plats.add(new Plat("gamb",12));
        plats.add(new Plat("gamb",12));
        plats.add(new Plat("gamb",12));
        plats.add(new Plat("gamb",12));
        plats.add(new Plat("gamb",12));

        plats.add(new Plat("gambaaa",12));
        PaginaCarta pag = new PaginaCarta(plats, pagina);
        this.remove(((BorderLayout)this.getLayout()).getLayoutComponent(BorderLayout.CENTER));
        this.add(pag, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }
}
