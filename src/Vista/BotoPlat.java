package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe que conté la informació dels botons que hi ha a la carta
 */
public class BotoPlat extends JButton {
    private JButton button;
    private String nomPlat;

    /**
     * Constructor de la classe
     *
     * @param text text que volem que mostri el boto
     */

    public BotoPlat(String text) {
        nomPlat = text;



            button = new JButton("<html>" + text.replaceAll("\\n", "<br>") + "</html>");

        button.setBounds(10, 11, 125, 75);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));

    }

    /**
     * funcio que retorna el boto
     *
     * @return el boto
     */
    public JButton getBoto() {
        return button;
    }

    /**
     * Funció que retorna el nom que hiha al voto
     *
     * @return string amb el nom
     */
    public String getNomPlat() {
        return nomPlat;
    }

    /**
     * Funcio que registra el controlador al botó
     *
     * @param controller controlador al que registrarem el boto
     * @param text       text que usarem com a actioncomand
     */
    public void registraController(ActionListener controller, String text) {
        button.addActionListener(controller);
        button.setActionCommand(text);
    }
}
