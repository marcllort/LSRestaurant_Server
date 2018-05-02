package Controlador;


import Model.Gestionador;
import Model.Reserva;
import Vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Controlador implements ActionListener {

    private ServidorVista vista;
    private String card;
    private Gestionador gestionador;
    private int index;
    private DialogServirComandes panel;


    public Controlador(ServidorVista vista, Gestionador gestionador) {

        this.gestionador = gestionador;
        this.vista = vista;
        card = "TAULES";
        index = 1;
        handleLlista();
        vista.creaMenu(this);
        vista.getVistaTaules().actualitzaTaula(creaModel(gestionador.mostraReseves(1)));
        panel = new DialogServirComandes(gestionador, null);
        panel.registraControladorDialog(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        handleMenu(e);

        switch (card) {
            case "TAULES":
                handleTaules(e);
                break;
            case "CARTA":

                break;
            case "COMANDES":
                handleComandes(e);
                break;
            case "TOP5":
                handleTop5(e);
                break;
        }

    }


    private void handleMenu(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "TAULES":
                vista.activaTaula(this);
                card = "TAULES";
                break;
            case "CARTA":
                vista.activaCarta();
                card = "CARTA";
                break;
            case "COMANDES":
                vista.activaComanda();
                card = "COMANDES";
                break;
            case "TOP5":
                vista.activaTop5();
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
                    handleLlista();
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
                    handleLlista();
                } catch (Exception e1) {
                    vista.showError("Cap taula seleccionada!");
                }
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
    }

    private void handleComandes(ActionEvent e) {
        try {
            VistaComandes vistaComanda = vista.getVistaComandes();
            if (e.getActionCommand().equals("SERVIR")) {
                System.out.println("entra");
                String plat = panel.platSeleccionat();
                String user = panel.usuariComanda();
                gestionador.serveixPlat(plat, user);
                System.out.println(gestionador.retornaComanda(user).getPlats());
                panel.ferLlistes(gestionador.retornaComanda(user));
                panel.repaint();
                panel.revalidate();
            }
            if (e.getActionCommand().equals("SERVIR TAULA")) {
                String fila = vistaComanda.filaSeleccionada();
                if (!fila.equals("null")) {
                    panel = new DialogServirComandes(gestionador, fila);
                    panel.setVisible(true);
                }
            } else {
                vistaComanda.setModelTaula(gestionador.llistaComandes());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            vista.showError("No hi ha reserves!");
        }
    }


    //Altres Funcions

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

    private void handleTop5(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Semanal":
                int[] a = {1, 2, 3, 4, 5};
                vista.getVistaTop5().grSemanal(a);
                break;

            case "Total":
                int[] b = {5, 4, 3, 2, 2};
                vista.getVistaTop5().grSemanal(b);
                break;
        }

    }

}