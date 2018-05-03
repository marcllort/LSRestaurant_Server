package Vista;

import Model.InfoComandes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaComandes extends JPanel{

    private JTable jtTaula;
    private DefaultTableModel modelTaula;
    private JScrollPane scroll;
    private JButton jbServir;

    /**
     * Constructor de la vista del panel que mostra la taula de comandes
     */
    public VistaComandes() {

        this.setLayout(new BorderLayout(0, 0));

        //Creem taula
        modelTaula = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        modelTaula.addColumn("Reserva");
        modelTaula.addColumn("Plats Totals");
        modelTaula.addColumn("Plats Pendents");
        modelTaula.addColumn("Data/Hora");

        //Posem informacio



        //Acabada
        jtTaula = new JTable(modelTaula);

        scroll = new JScrollPane(jtTaula);

        //Afegim taula i boto als seus llocs
        this.add(scroll, BorderLayout.CENTER);
        jbServir = new JButton("Mirar comandes");
        this.add(jbServir, BorderLayout.SOUTH);

    }



    /**
     * Funcio per registrar el controlador de aquest panel
     * @param controller
     */
    public void registraControlador(ActionListener controller) {                            //Registro el boto serve amb un action comand de send, declarat en una constant

        jbServir.addActionListener(controller);
        jbServir.setActionCommand("SERVIR TAULA");



    }

    /**
     * Funció per donar valor a cada fila de la taula
     * @param modelTaulas
     */
    public void setModelTaula(ArrayList<InfoComandes> modelTaulas){
       int r =modelTaula.getRowCount();
       for (int p = 0 ; p<r; p++){
           modelTaula.removeRow(0);
       }
        for (InfoComandes info : modelTaulas){
            Object rowData[] = {info.getUsuari(), info.getTotalPlats(), info.getPlatsPendents(), info.getDate()+"//"+info.getHora()};
            modelTaula.addRow(rowData);
        }

        scroll.repaint();
        scroll.revalidate();
        System.out.println(modelTaula.getRowCount());
    }

    /**
     * Getter per saber quina fila de la taula ha estat seleccionada
     * @return usuari de la fila seleccionada
     */
    public String filaSeleccionada(){
        try {
            return (String) modelTaula.getValueAt(jtTaula.getSelectedRow(), 0);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Cap comanda seleccionada!");
            return "null";
        }
    }



}
