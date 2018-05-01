package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaTaules extends JPanel {


    private JTable jtTaula;
    private JTextField jtfNcomencals;
    private JList jlstLlista;
    private JButton jbDeleteTable;
    private JButton jbAfegir;

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

        DefaultTableModel modelTaula = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        modelTaula.addColumn("Reserva");
        modelTaula.addColumn("N persones");
        modelTaula.addColumn("Data/Hora");

        Object rowData[] = {"Row1-Column1", "Row1-Column2", "Row1-Column3"};


        JTable jTable = new JTable(modelTaula);

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

        jtfNcomencals = new JTextField();
        jpAddTaula.add(jtfNcomencals);
        jtfNcomencals.setColumns(5);

        jbAfegir = new JButton("Afegir");
        jpAddTaula.add(jbAfegir);

    }

    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

        jbAfegir.addActionListener(controller);
        jbAfegir.setActionCommand("AFEGIR");

        jbDeleteTable.addActionListener(controller);
        jbDeleteTable.setActionCommand("DELETE");



    }

    public String getJtfText(){
        return jtfNcomencals.getText();
    }

    public String getJlstLlista() {
        return jlstLlista.getSelectedValue().toString();
    }

    public void actualitzaLlista(DefaultListModel model1) {
        jlstLlista.setModel(model1);
    }

    public void actualitzaTaula (DefaultTableModel model){
        jtTaula.setModel(model);
    }

}
