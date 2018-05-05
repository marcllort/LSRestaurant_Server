package Controlador;


import Model.Gestionador;
import Model.Reserva;
import Network.ServerSocketReserva;
import Network.ServidorReserva;
import Vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;


public class Controlador implements ActionListener {

    private ServidorVista vista;
    private String card;
    private Gestionador gestionador;
    private int index;
    private VistaServirComandes panel;
    private ServerSocketReserva sReserva;
    private String boto;

    public Controlador(ServidorVista vista, Gestionador gestionador, ServerSocketReserva sReserva) {

        this.gestionador = gestionador;
        this.vista = vista;
        this.sReserva = sReserva;

        inicialitzaVistes();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        handleMenu(e);
        switch (card) {
            case "TAULES":
                handleTaules(e);
                break;
            case "CARTA":
                handleCarta(e);
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
                    vista.getVistaTaules().netejaJtf();
                    vista.showError("Taula afegida correctament!");
                } catch (Exception e1) {
                    vista.getVistaTaules().netejaJtf();
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

    private void handleCarta(ActionEvent e) {
        DialogAfegirPlat dialogAfegirPlat = vista.getVistaPlats().getDialogAfegirPlat();
        DialogUpdatePlat dialogUpdatePlat = vista.getVistaPlats().getDialogUpdatePlat();


        if (e.getActionCommand().equals("AFEGIR NOU PLAT")) {
            dialogAfegirPlat.setVisible(true);
        }
        if (e.getActionCommand().equals("AFEGIR PLAT")) {
            try {
                gestionador.insereixPlat(dialogAfegirPlat.getJtfNom(), dialogAfegirPlat.getJtfPreu(), dialogAfegirPlat.getJtfUnitats());
                vista.showError("Plat afegit!");
                dialogAfegirPlat.dispatchEvent(new WindowEvent(dialogAfegirPlat, WindowEvent.WINDOW_CLOSING));
                dialogAfegirPlat.netejaCamps();

            } catch (NumberFormatException ne) {
                vista.showError("Error! Caracters no permesos a preu i unitats!");
                dialogAfegirPlat.netejaCamps();
            }
        }
        if (e.getActionCommand().equals("ACTUALITZAR")) {
            System.out.println("actualizat " + boto);
            try {
                gestionador.updatePlat(boto, dialogUpdatePlat.getJtfUnitats());
                vista.showError("Unitats de" + boto + "actualitzades!");
                dialogUpdatePlat.dispatchEvent(new WindowEvent(dialogUpdatePlat, WindowEvent.WINDOW_CLOSING));
                dialogUpdatePlat.netejaCamps();

            } catch (NumberFormatException ne2) {
                vista.showError("Error! Caracters no permesos a unitats!");
                dialogUpdatePlat.netejaCamps();
            } catch (SQLException sqe) {
                vista.showError("Error al actualitzar el plat!");
                dialogUpdatePlat.dispatchEvent(new WindowEvent(dialogUpdatePlat, WindowEvent.WINDOW_CLOSING));
                dialogUpdatePlat.netejaCamps();
            }
        }
        if (e.getActionCommand().equals("SEGUENT")) {
            System.out.println("PAGINA SEGUENT");
            //Funcio canvi pagina
        }
        if (e.getActionCommand().equals("ANTERIOR")) {
            System.out.println("PAGINA ANTERIOR");
            //Funcio canvi pagina


        } else {
            boto = e.getActionCommand();     //per saber de quin plat haurem de actualizar
            System.out.println(boto);
            dialogUpdatePlat.setVisible(true);
            dialogUpdatePlat.setJlNom(boto);
            dialogUpdatePlat.setJlPreu("5");
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
                String plat = panel.platSeleccionat();
                String user = panel.usuariComanda();
                gestionador.serveixPlat(plat, user);
                panel.ferLlistes(gestionador.retornaComanda(user));
                ServidorReserva server = sReserva.getServerReserva(user);
                try {
                    System.out.println("Envai comanda");
                    server.enviaComanda();
                } catch (Exception ee) {
                    vista.showError("El client s'ha desconnectat!");
                }

            }
            if (e.getActionCommand().equals("SERVIR TAULA")) {
                String fila = vistaComanda.filaSeleccionada();
                if (!fila.equals("null")) {
                    panel.dispatchEvent(new WindowEvent(panel, WindowEvent.WINDOW_CLOSING));
                    panel = new VistaServirComandes(gestionador, fila);
                    panel.registraControladorDialog(this);
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

    private void inicialitzaVistes() {
        card = "TAULES";
        index = 1;
        handleLlista();
        vista.creaMenu(this);
        vista.getVistaTaules().actualitzaTaula(creaModel(gestionador.mostraReseves(1)));
        panel = new VistaServirComandes(gestionador, null);
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