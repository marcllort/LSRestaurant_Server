package Vista;

import javax.swing.*;
import java.awt.*;

public class BotoPlat extends JButton{
    private JButton button;
    public BotoPlat(String text){
         button = new JButton(text);
        button.setBounds(10, 11, 125, 75);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));

    }

    public JButton getBoto(){
        return button;
    }
}
