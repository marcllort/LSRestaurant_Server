package Model;

import java.sql.Date;
import java.util.ArrayList;

public class Comanda {

    private String usuari;              //cal enviar el usuari que ha fet la comanda
    private ArrayList<Plat> plats;      //cal que sigui el array dels plats que ha demanat la taula
    //private boolean servit;
    private Date data;                  //  quan fem una comanda cal enviar a la hora que sha fet

    /**
     * Constructor sense parametres.
     * Crea un llistat de plats buit.
     */

    //public String getNomPlat(){return plat.getNomPlat();}
    //public boolean esServit(){return servit;}
    //public float getPreuPlat(){return plat.getPreu();}

    public void addPlat(Plat plat){
        plats.add(plat);
    }

    public Plat getPlat(int i){
        return plats.get(i);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}