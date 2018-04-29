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
     * @param plats
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
     * @param i
     * @return el plat
     */
    public Plat getPlat(int i) {
        return plats.get(i);
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
     * @param plat
     */
    public void afageixPlat(Plat plat) {
        plats.add(plat);
    }

    /**
     * Setter de una carta
     *
     * @param carta
     */
    public void setCarta(Carta carta) {
        this.plats = carta.getPlats();
    }

}