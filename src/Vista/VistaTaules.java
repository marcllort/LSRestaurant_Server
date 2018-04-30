package Vista;

import javax.swing.*;
import java.awt.*;

public class VistaTaules extends JPanel {


    private JTable jtTaula;
    private JTextField jtaNcomencals;
    private JList jlstLlista;
    private JButton jbDeleteTable;

    public VistaTaules() {

        this.setLayout(new BorderLayout());

        JSplitPane jspSplit = new JSplitPane();
        jspSplit.setBounds(0, 0, 704, 535);
        this.add(jspSplit);

        left(jspSplit);

        right(jspSplit);

    }

    private void left(JSplitPane jspSplit){

        JPanel jpLlista = new JPanel();
        jspSplit.setLeftComponent(jpLlista);
        jpLlista.setLayout(new GridLayout(0, 1, 0, 0));

        jlstLlista = new JList();


        jpLlista.add(jlstLlista);

        jbDeleteTable = new JButton("Eliminar Taula");
        jpLlista.add(jbDeleteTable);

    }

    private void right (JSplitPane jspSplit){

        JPanel jpReserva = new JPanel();
        jspSplit.setRightComponent(jpReserva);
        jpReserva.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel jpTaula = new JPanel();
        jpReserva.add(jpTaula);



        Object rowData[][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3"},
                {"Row2-Column1", "Row2-Column2", "Row2-Column3"}};

        Object columnNames[] = {"Reserva", "N persones", "Data/Hora"};


        JTable jTable = new JTable(rowData, columnNames);
        jpReserva.add(new JScrollPane(jTable));

        JLabel jlReserves = new JLabel("Reserves:");
        jpTaula.add(jlReserves);



        jtTaula = new JTable();
        jpTaula.add(jtTaula);

        JPanel jpAddTaula = new JPanel();
        jpReserva.add(jpAddTaula);
        jpAddTaula.setLayout(new BoxLayout(jpAddTaula, BoxLayout.X_AXIS));

        JLabel jlNcomencals = new JLabel("Afegir taula de N començals: ");
        jpAddTaula.add(jlNcomencals);

        jtaNcomencals = new JTextField();
        jpAddTaula.add(jtaNcomencals);
        jtaNcomencals.setColumns(5);

        JButton jbAfegir = new JButton("Afegir");
        jpAddTaula.add(jbAfegir);

    }

}
