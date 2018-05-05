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

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 584, 291);
        this.add(tabbedPane);

        JPanel Carta = new JPanel();
        tabbedPane.addTab("       Carta       ", null, Carta, null);
        Carta.setLayout(null);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(10, 11, 559, 184);
        Carta.add(panel_3);
        panel_3.setLayout(null);

        JButton button = new JButton("Plat");
        button.setBounds(10, 11, 125, 75);
        panel_3.add(button);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JButton button_1 = new JButton("Plat");
        button_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button_1.setBounds(145, 11, 125, 75);
        panel_3.add(button_1);

        JButton button_2 = new JButton("Plat");
        button_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button_2.setBounds(280, 11, 125, 75);
        panel_3.add(button_2);

        JButton button_3 = new JButton("Plat");
        button_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button_3.setBounds(415, 11, 125, 75);
        panel_3.add(button_3);

        JButton button_4 = new JButton("Plat");
        button_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button_4.setBounds(10, 97, 125, 75);
        panel_3.add(button_4);

        JButton button_5 = new JButton("Plat");
        button_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button_5.setBounds(145, 97, 125, 75);
        panel_3.add(button_5);

        JButton button_6 = new JButton("Plat");
        button_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button_6.setBounds(280, 97, 125, 75);
        panel_3.add(button_6);

        JButton button_7 = new JButton("Plat");
        button_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button_7.setBounds(415, 97, 125, 75);
        panel_3.add(button_7);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(10, 206, 559, 46);
        Carta.add(panel_4);
        panel_4.setLayout(null);

        JButton button_8 = new JButton("Anterior");
        button_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button_8.setBounds(10, 6, 107, 35);
        panel_4.add(button_8);

        JButton btnSegent = new JButton("Seg\u00FCent");
        btnSegent.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSegent.setBounds(442, 6, 107, 35);
        panel_4.add(btnSegent);

        JLabel lblPgina = new JLabel("P\u00E0gina 1");
        lblPgina.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblPgina.setBounds(244, 12, 77, 23);
        panel_4.add(lblPgina);

        JPanel panel = new JPanel();
        tabbedPane.addTab("   Estat comanda   ", null, panel, null);
        panel.setLayout(null);

        JPanel panel_5 = new JPanel();
        panel_5.setBounds(10, 11, 530, 80);
        panel.add(panel_5);
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

        JSpinner spinner = new JSpinner();
        spinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spinner.setBounds(284, 45, 35, 22);
        panel_5.add(spinner);

        JButton btnNewButton = new JButton("x");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(477, 11, 41, 31);
        panel_5.add(btnNewButton);

        JPanel panel_7 = new JPanel();
        panel_7.setLayout(null);
        panel_7.setBounds(10, 102, 530, 80);
        panel.add(panel_7);

        JPanel panel_8 = new JPanel();
        panel_8.setBounds(0, 0, 80, 80);
        panel_7.add(panel_8);

        JLabel label = new JLabel("Foto");
        panel_8.add(label);

        JLabel label_1 = new JLabel("Nom del plat");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_1.setBounds(90, 11, 125, 22);
        panel_7.add(label_1);

        JLabel label_2 = new JLabel("Preu: x \u20AC");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_2.setBounds(90, 44, 149, 22);
        panel_7.add(label_2);

        JLabel label_3 = new JLabel("Quantitat:");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_3.setBounds(202, 44, 85, 22);
        panel_7.add(label_3);

        JSpinner spinner_1 = new JSpinner();
        spinner_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spinner_1.setBounds(284, 45, 35, 22);
        panel_7.add(spinner_1);

        JButton button_9 = new JButton("x");
        button_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button_9.setBounds(477, 11, 41, 31);
        panel_7.add(button_9);

        JPanel panel_9 = new JPanel();
        panel_9.setLayout(null);
        panel_9.setBounds(10, 193, 530, 80);
        panel.add(panel_9);

        JPanel panel_10 = new JPanel();
        panel_10.setBounds(0, 0, 80, 80);
        panel_9.add(panel_10);

        JLabel label_4 = new JLabel("Foto");
        panel_10.add(label_4);

        JLabel label_5 = new JLabel("Nom del plat");
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_5.setBounds(90, 11, 125, 22);
        panel_9.add(label_5);

        JLabel label_6 = new JLabel("Preu: x \u20AC");
        label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_6.setBounds(90, 44, 149, 22);
        panel_9.add(label_6);

        JLabel label_7 = new JLabel("Quantitat:");
        label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_7.setBounds(202, 44, 85, 22);
        panel_9.add(label_7);

        JSpinner spinner_2 = new JSpinner();
        spinner_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spinner_2.setBounds(284, 45, 35, 22);
        panel_9.add(spinner_2);

        JButton button_10 = new JButton("x");
        button_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button_10.setBounds(477, 11, 41, 31);
        panel_9.add(button_10);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("   Pagar i tancar   ", null, panel_2, null);
        panel_2.setLayout(null);

        JPanel panel_11 = new JPanel();
        panel_11.setBounds(0, 0, 579, 263);
        panel_2.add(panel_11);
        panel_11.setLayout(null);

        JPanel panel_12 = new JPanel();
        panel_12.setLayout(null);
        panel_12.setBounds(0, 0, 579, 263);
        panel_11.add(panel_12);

        JPanel panel_13 = new JPanel();
        panel_13.setLayout(null);
        panel_13.setBounds(10, 11, 530, 80);
        panel_12.add(panel_13);

        JPanel panel_14 = new JPanel();
        panel_14.setBounds(0, 0, 80, 80);
        panel_13.add(panel_14);

        JLabel label_8 = new JLabel("Foto");
        panel_14.add(label_8);

        JLabel label_9 = new JLabel("Nom del plat");
        label_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_9.setBounds(90, 11, 125, 22);
        panel_13.add(label_9);

        JLabel label_10 = new JLabel("Preu: x \u20AC");
        label_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_10.setBounds(90, 44, 149, 22);
        panel_13.add(label_10);

        JPanel panel_19 = new JPanel();
        panel_19.setBounds(396, 24, 111, 32);
        panel_13.add(panel_19);

        JLabel lblServit = new JLabel("SERVIT");
        lblServit.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel_19.add(lblServit);

        JPanel panel_15 = new JPanel();
        panel_15.setLayout(null);
        panel_15.setBounds(10, 102, 530, 80);
        panel_12.add(panel_15);

        JPanel panel_16 = new JPanel();
        panel_16.setBounds(0, 0, 80, 80);
        panel_15.add(panel_16);

        JLabel label_12 = new JLabel("Foto");
        panel_16.add(label_12);

        JLabel label_13 = new JLabel("Nom del plat");
        label_13.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_13.setBounds(90, 11, 125, 22);
        panel_15.add(label_13);

        JLabel label_14 = new JLabel("Preu: x \u20AC");
        label_14.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_14.setBounds(90, 44, 149, 22);
        panel_15.add(label_14);

        JPanel panel_20 = new JPanel();
        panel_20.setBounds(395, 23, 111, 32);
        panel_15.add(panel_20);

        JLabel label_11 = new JLabel("SERVIT");
        label_11.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel_20.add(label_11);

        JPanel panel_17 = new JPanel();
        panel_17.setLayout(null);
        panel_17.setBounds(10, 193, 530, 80);
        panel_12.add(panel_17);

        JPanel panel_18 = new JPanel();
        panel_18.setBounds(0, 0, 80, 80);
        panel_17.add(panel_18);

        JLabel label_16 = new JLabel("Foto");
        panel_18.add(label_16);

        JLabel label_17 = new JLabel("Nom del plat");
        label_17.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_17.setBounds(90, 11, 125, 22);
        panel_17.add(label_17);

        JLabel label_18 = new JLabel("Preu: x \u20AC");
        label_18.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_18.setBounds(90, 44, 149, 22);
        panel_17.add(label_18);

        JPanel panel_21 = new JPanel();
        panel_21.setBounds(395, 25, 111, 32);
        panel_17.add(panel_21);

        JLabel label_15 = new JLabel("SERVIT");
        label_15.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel_21.add(label_15);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("      Comanda      ", null, panel_1, null);
        panel_1.setLayout(null);

        JLabel label_19 = new JLabel("LS_RESTAURANT");
        label_19.setFont(new Font("Tahoma", Font.BOLD, 28));
        label_19.setBounds(139, 23, 261, 39);
        panel_1.add(label_19);

        JPanel panel_22 = new JPanel();
        panel_22.setBounds(149, 86, 218, 116);
        panel_1.add(panel_22);
        panel_22.setLayout(null);

        JLabel lblHaDePagar = new JLabel("Ha de pagar: x \u20AC");
        lblHaDePagar.setBounds(10, 11, 198, 25);
        panel_22.add(lblHaDePagar);
        lblHaDePagar.setFont(new Font("Tahoma", Font.PLAIN, 20));

        JButton btnPagar = new JButton("Pagar");
        btnPagar.setBounds(53, 71, 107, 35);
        panel_22.add(btnPagar);
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
