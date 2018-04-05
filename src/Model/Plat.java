package Model;

import java.io.Serializable;

public class Plat implements Serializable {


    private static final long serialVersionUID = 12345L;
    private String nomPlat;
    private float preu;


    public Plat(String nom, float preu) {                          //Creo la classe de preu amb el elemet de nomPlat i preu
        this.nomPlat = nom;
        this.preu = preu;
    }

    public String getNomPlat() {
        return nomPlat;
    }                        //Getter d'nomPlat

    public float getPreu() {
        return preu;
    }                        //Getter de preu


    @Override
    public String toString() {
        return nomPlat + " - " + preu;                                //Un override del tosting perque hem dongui la preu composada com la vui
    }

}

