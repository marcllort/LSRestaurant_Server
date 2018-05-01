package Controlador;


import Model.BDD;
import Model.Gestionador;
import Model.Reserva;
import Vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Controlador implements ActionListener {

    private ServidorVista vista;
    private String card;
    private Gestionador gestionador;
    private BDD bdd;

    public Controlador(ServidorVista vista) {

        gestionador = new Gestionador(bdd);
        this.vista = vista;
        card = "TAULES";

    }

    public Controlador(ServidorVista vista, BDD bdd) {

        this.bdd = bdd;
        gestionador = new Gestionador(bdd);
        this.vista = vista;
        card = "TAULES";

    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
        handleLlista();
        vista.getVistaTaules().actualitzaTaula(creaModel(gestionador.mostraReseves(1)));
        int index = vista.getVistaTaules().getJlstLlista();
        switch (e.getActionCommand()) {
            case "AFEGIR":
                try {
                    int n = Integer.parseInt(vista.getVistaTaules().getJtfText());
                    System.out.println("AFEGIR" + n);
                    gestionador.creaTaula(n);
                } catch (Exception e1) {
                    vista.showError("Introdueixi un nombre!");
                }
                break;

            case "DELETE":

                if (index != -1) {
                    System.out.println("DELETE");
                } else {
                    vista.showError("No hi ha taules ha borrar!");
                }
                break;

            case "ACTUALITZA":
                index = vista.getVistaTaules().getJlstLlista();
                System.out.println(index);
                vista.getVistaTaules().actualitzaTaula(creaModel(gestionador.mostraReseves(index)));
                handleLlista();
                //System.out.println(gestionador.mostraReseves(1));

                break;
        }

    }

    private void handleLlista(){
        int[] llista = convertIntegers(gestionador.llistaTaules());
        DefaultListModel modelLlista = new DefaultListModel();
        for (int i : llista){
            modelLlista.addElement(i);
        }
        vista.getVistaTaules().actualitzaLlista(modelLlista);
    }



    private DefaultTableModel creaModel(ArrayList<Reserva> reserves){
        DefaultTableModel modelTaula = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        modelTaula.addColumn("Reserva");
        modelTaula.addColumn("N persones");
        modelTaula.addColumn("Data/Hora");


        for (Reserva r : reserves){
            String[] reservesArr = new String[]{r.getUsuari(),r.getnComencals().toString(), r.getHora().toString()+"//"+r.getData().toString()};
            modelTaula.addRow(reservesArr);
        }

        return modelTaula;
    }

    public static int[] convertIntegers(ArrayList<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

}