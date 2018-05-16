package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DialogUpdatePlat extends JFrame {

    private JTextField jtfUnitats;
    private JLabel jlNom = new JLabel();
    private JLabel jlPreu = new JLabel();
    private JButton jbActualizar = new JButton();
    private JButton jbEliminar = new JButton();


    public DialogUpdatePlat() {
        setLayout(new GridLayout(4, 0, 0, 0));
        setSize(400, 250);                                      //Dono les propietats inicials al JFrame
        setResizable(false);

        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 11, 364, 189);
        getContentPane().add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(10, 11, 90, 90);
        panel.add(panel_1);


        jlNom.setText("Nom del plat");
        jlNom.setFont(new Font("Tahoma", Font.BOLD, 20));
        jlNom.setBounds(110, 11, 212, 33);
        panel.add(jlNom);

        jlPreu.setText("Preu: x \u20AC");
        jlPreu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        jlPreu.setBounds(110, 46, 149, 22);
        panel.add(jlPreu);

        JLabel lblUnitats = new JLabel("Unitats:");
        lblUnitats.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUnitats.setBounds(110, 79, 85, 22);
        panel.add(lblUnitats);

        jbEliminar.setText("ELIMINAR");
        jbEliminar.setFont(new Font("Tahoma", Font.BOLD, 18));
        jbEliminar.setBounds(74, 132, 201, 34);
        panel.add(jbEliminar);

        jtfUnitats = new JTextField();
        jtfUnitats.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jtfUnitats.setBounds(181, 78, 43, 23);
        panel.add(jtfUnitats);
        jtfUnitats.setColumns(10);

        jbActualizar.setText("Actualitza");
        jbActualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
        jbActualizar.setBounds(233, 75, 121, 33);
        panel.add(jbActualizar);

    }

    public void registraControlador(ActionListener controlador) {
        jbActualizar.addActionListener(controlador);
        jbActualizar.setActionCommand("ACTUALITZAR");

        jbEliminar.addActionListener(controlador);
        jbEliminar.setActionCommand("ELIMINA");
    }

    public void setJlNom(String nom) {
        this.jlNom.setText("Nom: " + nom);
    }

    public void setJlPreu(Float preu) {
        this.jlPreu.setText("Preu: " + preu);
    }

    public int getJtfUnitats() {
        return Integer.parseInt(jtfUnitats.getText());
    }

    public void netejaCamps() {
        jtfUnitats.setText("");
    }


}
