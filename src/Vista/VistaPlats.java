package Vista;

import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class VistaPlats extends JPanel {

    private DialogAfegirPlat dialogAfegirPlat;
    private DialogUpdatePlat dialogUpdatePlat;


    public VistaPlats() {

        this.setLayout(null);



        JPanel panel_5 = new JPanel();
        panel_5.setBounds(10, 11, 530, 80);
        panel_5.setLayout(null);

        JPanel panel_6 = new JPanel();
        panel_6.setBounds(0, 0, 80, 80);
        panel_5.add(panel_6);

        JLabel lblFoto = new JLabel("Foto");
        panel_6.add(lblFoto);

        JLabel lblNomDelPlat = new JLabel("Nom del plat");
        lblNomDelPlat.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNomDelPlat.setBounds(90, 11, 125, 22);
        panel_5.add(lblNomDelPlat);

        JLabel lblPreuX = new JLabel("Preu: x \u20AC");
        lblPreuX.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPreuX.setBounds(90, 44, 149, 22);
        panel_5.add(lblPreuX);

        JLabel lblQuantitat = new JLabel("Quantitat:");
        lblQuantitat.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblQuantitat.setBounds(202, 44, 85, 22);
        panel_5.add(lblQuantitat);


        JButton btnPagar = new JButton("Pagar");
        btnPagar.setBounds(53, 71, 107, 35);
        btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 20));

        dialogAfegirPlat = new DialogAfegirPlat();
        dialogUpdatePlat = new DialogUpdatePlat();

    }


    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

        dialogAfegirPlat.registraControlador(controller);
        dialogUpdatePlat.registraControlador(controller);

    }

    public DialogAfegirPlat getDialogAfegirPlat() {
        return dialogAfegirPlat;
    }

    public DialogUpdatePlat getDialogUpdatePlat() {
        return dialogUpdatePlat;
    }
}
