package Model;

import java.io.Serializable;
import java.util.ArrayList;


public class Carta implements Serializable {

    private ArrayList<Plat> plats;

    /**
     * Constructor que crea la carta
     */

    public Carta(){
        this.plats = new ArrayList<Plat>();
    }

    public Carta(ArrayList<Plat> plats){
        this.plats = new ArrayList<Plat>();
        this.plats = plats;
    }

    public int getNumPlats(){ return plats.size();}

    public Plat getPlat(int i){
        return plats.get(i);
    }

    public ArrayList<Plat> getPlats() {
        return plats;
    }

    public void afageixPlat(Plat plat){plats.add(plat);}

    public void setCarta(Carta carta){this.plats = carta.getPlats();}


}
