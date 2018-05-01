package Controlador;


import Model.BDD;
import Model.Gestionador;
import Vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Controlador implements ActionListener {

    private ServidorVista vista;
    private String card;
    private Gestionador gestionador;
    private BDD bdd;

    public Controlador(ServidorVista vista) {
        try {
            bdd = new BDD();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        if (e.getActionCommand().equals("AFEGIR")) {

            try {
                int n = Integer.parseInt(vista.getVistaTaules().getJtfText());
                System.out.println("AFEGIR");
                System.out.println(vista.getVistaTaules().getJtfText());
                gestionador.creaTaula(n);
            } catch (Exception e1) {
                vista.showError("Introdueixi un nombre!");
            }
        } else {
            System.out.println("DELETE");
            try {
                System.out.println(vista.getVistaTaules().getJlstLlista());
            } catch (Exception ex) {
                vista.showError("No hi ha taules ha borrar!");
            }

        }
    }

}