package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Finestra que s'obre en el moment que volem editar un plat
 */
public class DialogUpdatePlat extends JFrame {

    private JTextField jtfUnitats;
    private JLabel jlNom = new JLabel();
    private JLabel jlPreu = new JLabel();
    private JButton jbActualizar = new JButton();
    private JButton jbEliminar = new JButton();
    private String unitats = "";




    /**
     * Constructor de la finestra
     */
    public DialogUpdatePlat() {
        setLayout(new GridLayout(4, 0, 0, 0));
        setLocationRelativeTo(null);

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

    /**
     * Funcio que registra el controlador de la finestra
     *
     * @param controlador
     */
    public void registraControlador(ActionListener controlador) {
        jbActualizar.addActionListener(controlador);
        jbActualizar.setActionCommand("ACTUALITZAR");

        jbEliminar.addActionListener(controlador);
        jbEliminar.setActionCommand("ELIMINA");
    }

    /**
     * Funcio que escriu el nom en questio a la finestra
     *
     * @param nom nom que volem escriure a la finestra
     */
    public void setJlNom(String nom) {
        this.jlNom.setText("Nom: " + nom);
    }

    /**
     * funció que escriu el preu en questió a la finestra
     *
     * @param preu preu a escriure a la finestra
     */
    public void setJlPreu(Float preu) {
        this.jlPreu.setText("Preu: " + preu);
    }

    /**
     * Funcio que retorna el numero d'unitats que s'ha escrit per afegir al plat seleccionat
     *
     * @return numero escrit a les unitats
     */
    public int getJtfUnitats() {
        return Integer.parseInt(jtfUnitats.getText());
    }

    /**
     * Funció que neteja els camps de la finestra
     */
    public void netejaCamps() {
        jtfUnitats.setText("");
    }

    public void setUnitats(String unitats) {
        this.unitats = unitats;
        this.jtfUnitats.setText(unitats);
    }


}
