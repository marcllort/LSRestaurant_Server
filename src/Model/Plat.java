package Model;

import java.io.Serializable;

/**
 * Classe que conte la informació del plat, implementa Serializable per poder enviarlo per el servidor
 */
public class Plat implements Serializable {

    private String nomPlat;
    private float preu;
    private boolean servit;

    /**
     * Constructor de plat amb parametres sense el boolea servit
     * @param nom
     * @param preu
     */
    public Plat(String nom, float preu) {
        this.nomPlat = nom;
        this.preu = preu;
        servit = false;
    }

    /**
     * Constructor de plat amb tots els parametres
     * @param nom
     * @param preu
     */
    public Plat(String nom, float preu, boolean servit) {
        this.nomPlat = nom;
        this.preu = preu;
        this.servit = servit;
    }

    /**
     * Getter de nom del plat
     * @return nom de plat
     */
    public String getNomPlat() {
        return nomPlat;
    }

    /**
     * Getter de el preu del plat
     * @return
     */
    public float getPreu() {
        return preu;
    }

    /**
     * Getter del boolea de servit
     * @return
     */
    public boolean isServit() {
        return servit;
    }

    /**
     * Setter de servit
     * @param servit
     */
    public void setServit(boolean servit) {
        this.servit = servit;
    }

    /**
     * Override de toString perque ens retorni el string com el volem
     * @return String
     */
    @Override
    public String toString() {
        return nomPlat + " - " + preu;
    }

}
