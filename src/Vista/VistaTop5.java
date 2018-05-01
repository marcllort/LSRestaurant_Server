package Vista;

import javax.swing.*;
import javax.xml.bind.JAXBPermission;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class VistaTop5 extends JPanel {
    public  VistaTop5(){
        Grafic gr = new Grafic();
        ArrayList<String> plats = new ArrayList<>();
        plats.add("ASd");
        plats.add("ASdas");
        plats.add("ASdasd");
        plats.add("ASdawe");
        plats.add("ASddass");
        int[] es = {1,2,3,4,5};
        gr.grafic(es,plats);
        this.setLayout(new BorderLayout());
        this.add(gr, BorderLayout.CENTER);

    }








    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant


    }

}