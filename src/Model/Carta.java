package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe qur conte els plats de la carta amb els sus getters i setters
 * Implementa Serializable per poder enviar-la per el servidor
 */
public class Carta implements Serializable {

    private ArrayList<Plat> plats;

    /**
     * Constructor que crea la carta
     */
    public Carta() {
        this.plats = new ArrayList<Plat>();
    }

    /**
     * Costructor amb parametre de arraylist per poder crear la carta ja inicialitzada
     *
     * @param plats plats a guardar a la bbdd
     */
    public Carta(ArrayList<Plat> plats) {
        this.plats = new ArrayList<Plat>();
        this.plats = plats;
    }

    /**
     * Getter de nombre de plats
     *
     * @return nombre de plats
     */
    public int getNumPlats() {
        return plats.size();
    }

    /**
     * Getter de un plat especific
     *
     * @param i index del plat a guardar
     * @return el plat
     */
    public Plat getPlat(int i) {
        return plats.get(i);
    }

    public Plat getPlat(String nom) {
        for (Plat p : plats) {
            if (p.getNomPlat().equals(nom)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Getter de tots els plats
     *
     * @return tots els plats
     */
    public ArrayList<Plat> getPlats() {
        return plats;
    }

    /**
     * Funció per afegir plat al arraylist de la carta
     *
     * @param plat plat a afegir
     */
    public void afegeixPlat(Plat plat) {
        plats.add(plat);
    }

    /**
     * Setter de una carta
     *
     * @param carta tipus carta
     */
    public void setCarta(Carta carta) {
        this.plats = carta.getPlats();
    }

}