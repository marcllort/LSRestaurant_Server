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
    private JButton jbActualitza;

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
        //jpLlista.setLayout();

        jlstLlista = new JList();



        jpLlista.add(new JScrollPane(jlstLlista));

        JPanel jpButons = new JPanel();
        jpLlista.add(jpButons);

        jbActualitza = new JButton("Actualitza");
        jpButons.add(jbActualitza, BorderLayout.SOUTH);

        jbDeleteTable = new JButton("Eliminar Taula");
        jpButons.add(jbDeleteTable);


    }

    private void right (JSplitPane jspSplit){

        JPanel jpReserva = new JPanel();
        jspSplit.setRightComponent(jpReserva);
        jpReserva.setLayout(new GridLayout(0, 1, 0, 0));

        //taula
        JPanel jpTaula = new JPanel();
        JLabel jlReserves = new JLabel("Reserves:");
        jpTaula.add(jlReserves);

        jtTaula = new JTable(){
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(350, jtTaula.getRowHeight() * 14);
            }
        };

        jpTaula.add(new JScrollPane(jtTaula));
        jpReserva.add(jpTaula);


        //ADD
        JPanel jpAddTaula = new JPanel();
        jpReserva.add(jpAddTaula);
        jpAddTaula.setLayout(new BoxLayout(jpAddTaula, BoxLayout.X_AXIS));

        JLabel jlNcomencals = new JLabel("Afegir taula de N començals: ");
        jpAddTaula.add(jlNcomencals);

        jtfNcomencals = new JTextField();
        jtfNcomencals.setMaximumSize(new Dimension(Integer.MAX_VALUE, jtfNcomencals.getMinimumSize().height));
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

        jbActualitza.addActionListener(controller);
        jbActualitza.setActionCommand("ACTUALITZA");


    }

    public String getJtfText(){
        return jtfNcomencals.getText();
    }

    public String getJlstLlista() throws Exception{
        return jlstLlista.getSelectedValue().toString();
    }

    public void actualitzaLlista(DefaultListModel model1) {
        jlstLlista.setModel(model1);
    }

    public void actualitzaTaula (DefaultTableModel model){
        jtTaula.setModel(model);
    }

}
