package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BotoPlat extends JButton {
    private JButton button;
    private String nomPlat;

    public String getNomPlat() {
        return nomPlat;
    }

    public BotoPlat(String text) {
        nomPlat = text;

        button = new JButton(text);
        button.setBounds(10, 11, 125, 75);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));

    }

    public JButton getBoto() {
        return button;
    }
    public void registraController(ActionListener controller, String text){
        button.addActionListener(controller);
        button.setActionCommand(text);
    }
}
