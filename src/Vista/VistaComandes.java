package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaComandes extends JPanel{

    private JTable jtTaula;

    public VistaComandes() {

        this.setLayout(new BorderLayout(0, 0));

        //Creem taula
        DefaultTableModel modelTaula = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        modelTaula.addColumn("Reserva");
        modelTaula.addColumn("Plats Totals");
        modelTaula.addColumn("Plats Pendents");
        modelTaula.addColumn("Data/Hora");

        //Posem informacio
        Object rowData[] = {"Row1-Column1", "Row1-Column2", "Row1-Column3", "sdsad"};
        modelTaula.addRow(rowData);

        //Acabada
        jtTaula = new JTable(modelTaula);


        this.add(new JScrollPane(jtTaula), BorderLayout.CENTER);

    }


    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant



    }
}
