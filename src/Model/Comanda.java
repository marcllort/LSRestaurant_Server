package Model;

import java.io.Serializable;

public class Comanda implements Serializable {

    private static final long serialVersionUID = 12345L;
    private String usuari;
    private String comanda;


    public Comanda(String user, String text) {                          //Creo la classe de comanda amb el elemet de usuari i comanda
        this.usuari = user;
        this.comanda = text;
    }

    public String getUsuari() {
        return usuari;
    }                        //Getter d'usuari

    public String getComanda() {
        return comanda;
    }                        //Getter de comanda


    @Override
    public String toString() {
        return usuari + " - " + comanda;                                //Un override del tosting perque hem dongui la comanda composada com la vui
    }

}

