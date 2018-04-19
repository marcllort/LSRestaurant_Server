package Model;

import java.io.Serializable;

public class Plat implements Serializable {


    private static final long serialVersionUID = 12345L;
    private String nomPlat;
    private float preu;
    private boolean servit;


    public Plat(String nom, float preu) {                          //Creo la classe de preu amb el elemet de nomPlat i preu
        this.nomPlat = nom;
        this.preu = preu;
        servit = false;
    }

    public Plat(String nom, float preu, boolean servit) {                          //Creo la classe de preu amb el elemet de nomPlat i preu
        this.nomPlat = nom;
        this.preu = preu;
        this.servit = servit;
    }

    public String getNomPlat() {
        return nomPlat;
    }                        //Getter d'nomPlat

    public float getPreu() {
        return preu;
    }                        //Getter de preu

    public boolean isServit() {
        return servit;
    }

    public void setServit(boolean servit) {
        this.servit = servit;
    }


    @Override
    public String toString() {
        return nomPlat + " - " + preu;                                //Un override del tosting perque hem dongui la preu composada com la vui
    }

}
