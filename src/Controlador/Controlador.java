package Controlador;


import Model.Gestionador;
import Model.Reserva;
import Vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controlador implements ActionListener {

    private ServidorVista vista;
    private String card;
    private Gestionador gestionador;
    private int index;


    public Controlador(ServidorVista vista, Gestionador gestionador) {

        this.gestionador = gestionador;
        this.vista = vista;
        card = "TAULES";
        index = 1;
        handleLlista();
        vista.creaMenu(this);
        vista.getVistaTaules().actualitzaTaula(creaModel(gestionador.mostraReseves(1)));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //vista.creaMenu(this);
        if (e.getSource() instanceof JMenuItem) {
            handleMenu(e);
        } else {
            switch (card) {
                case "TAULES":
                    handleTaules(e);
                    break;
                case "CARTA":

                    break;
                case "COMANDES":

                    break;
                case "TOP5":

                    break;
            }

        }

    }


    private void handleMenu(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "TAULES":
                vista.activaTaula(this);
                card = "TAULES";
                break;
            case "CARTA":
                vista.activaCarta(this);
                card = "CARTA";
                break;
            case "COMANDES":
                vista.activaComanda(this);
                card = "COMANDES";
                break;
            case "TOP5":
                vista.activaTop5(this);
                card = "TOP5";
                break;
        }
    }

    private void handleTaules(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "AFEGIR":
                try {
                    int n = Integer.parseInt(vista.getVistaTaules().getJtfText());
                    gestionador.creaTaula(n);
                } catch (Exception e1) {
                    vista.showError("Introdueixi un nombre!");
                }
                break;

            case "DELETE":

                try {
                    gestionador.eliminaTaula(Integer.parseInt(vista.getVistaTaules().getJlstLlista()));
                    handleLlista();

                } catch (NullPointerException e2) {
                    vista.showError("Cap taula seleccionada!");
                } catch (Exception e1) {
                    vista.showError(e1.getMessage());
                }
                break;

            case "ACTUALITZA":
                try {
                    index = Integer.parseInt(vista.getVistaTaules().getJlstLlista());
                } catch (Exception e1) {
                    vista.showError("Cap taula seleccionada!");
                }
                handleLlista();
                //System.out.println(gestionador.mostraReseves(1));

                break;
        }

    }

    private void handleLlista() {

        vista.getVistaTaules().actualitzaTaula(creaModel(gestionador.mostraReseves(index)));
        int[] llista = convertIntegers(gestionador.llistaTaules());
        DefaultListModel modelLlista = new DefaultListModel();
        for (int f : llista) {
            modelLlista.addElement(f);
        }
        vista.getVistaTaules().actualitzaLlista(modelLlista);
        //System.out.println("sdds: "+vista.getVistaTaules().getJlstLlista());
        //index = Integer.parseInt(vista.getVistaTaules().getJlstLlista());
        System.out.println(index);

    }


    private DefaultTableModel creaModel(ArrayList<Reserva> reserves) {
        DefaultTableModel modelTaula = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        modelTaula.addColumn("Reserva");
        modelTaula.addColumn("N persones");
        modelTaula.addColumn("Data/Hora");


        for (Reserva r : reserves) {
            String[] reservesArr = new String[]{r.getUsuari(), r.getnComencals().toString(), r.getHora().toString() + "//" + r.getData().toString()};
            modelTaula.addRow(reservesArr);
        }

        return modelTaula;
    }

    public static int[] convertIntegers(ArrayList<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

}