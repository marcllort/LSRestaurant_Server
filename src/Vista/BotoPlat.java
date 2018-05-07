package Vista;

import javax.swing.*;
import java.awt.*;

public class BotoPlat extends JButton{
    public BotoPlat(String text){
        JButton button = new JButton(text);
        button.setBounds(10, 11, 125, 75);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));

    }
    public BotoPlat getBoto(){
        return this;
    }
}
