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

    /**
     * Constructor del panel de la gestió de taules
     * Crea un splitpane
     */
    public VistaTaules() {

        this.setLayout(new BorderLayout());
        JSplitPane jspSplit = new JSplitPane();
        jspSplit.setBounds(0, 0, 600, 330);
        this.add(jspSplit);

        left(jspSplit);
        right(jspSplit);

    }

    /**
     * Funció encarregada de generar la vista de la part esquerra
     * Genera una llista de taules i un parell de botons, per actualitzar i per borrar taules
     *
     * @param jspSplit tipus jsplitpane
     */
    private void left(JSplitPane jspSplit) {

        JPanel jpLlista = new JPanel();
        jspSplit.setLeftComponent(jpLlista);
        jpLlista.setLayout(new BorderLayout(0, 0));

        jlstLlista = new JList();


        jpLlista.add(new JScrollPane(jlstLlista));

        JPanel jpButons = new JPanel();
        jpLlista.add(jpButons, BorderLayout.SOUTH);

        jbActualitza = new JButton("Actualitza");
        jpButons.add(jbActualitza, BorderLayout.SOUTH);

        jbDeleteTable = new JButton("Eliminar Taula");
        jpButons.add(jbDeleteTable);


    }

    /**
     * Funció encarregada de generar la vista de la part dreta
     * Genera una taula que conté les reserves de la taula seleccionada
     *
     * @param jspSplit tipus jsplitpane
     */
    private void right(JSplitPane jspSplit) {

        JPanel jpReserva = new JPanel();
        jspSplit.setRightComponent(jpReserva);
        jpReserva.setLayout(new BorderLayout(0, 0));

        //taula
        JPanel jpTaula = new JPanel();
        JLabel jlReserves = new JLabel("Reserves:");
        jpTaula.add(jlReserves);

        jtTaula = new JTable() {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(310, jtTaula.getRowHeight() * 12);
            }
        };

        jpTaula.add(new JScrollPane(jtTaula));
        jpReserva.add(jpTaula, BorderLayout.CENTER);


        //ADD
        JPanel jpAddTaula = new JPanel();
        jpReserva.add(jpAddTaula, BorderLayout.SOUTH);
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

    /**
     * Funcio per registrar el controlador de aquest panel
     *
     * @param controller tipus controller
     */
    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

        jbAfegir.addActionListener(controller);
        jbAfegir.setActionCommand("AFEGIR");

        jbDeleteTable.addActionListener(controller);
        jbDeleteTable.setActionCommand("DELETE");

        jbActualitza.addActionListener(controller);
        jbActualitza.setActionCommand("ACTUALITZA");


    }

    /**
     * Getter de el text del jtextfield
     *
     * @return text
     */
    public String getJtfText() {
        return jtfNcomencals.getText();
    }

    /**
     * Getter del valor que esta seleccionat a la llista
     *
     * @return value de la taula seleccionada
     * @throws Exception excepcio si no existeix el value de la llista
     */
    public String getJlstLlista() throws Exception {
        return jlstLlista.getSelectedValue().toString();
    }

    /**
     * Funció per actualitzar els valors de la llista
     *
     * @param model1 informacio de la llista
     */
    public void actualitzaLlista(DefaultListModel model1) {
        jlstLlista.setModel(model1);
    }

    /**
     * Funció per actualitzar els valors de la taula de reserves
     *
     * @param model informacio de la llista
     */
    public void actualitzaTaula(DefaultTableModel model) {
        jtTaula.setModel(model);
    }

    /**
     * Funció per poder netejar el jtf desde el controlador
     */
    public void netejaJtf() {
        jtfNcomencals.setText("");
    }

}
