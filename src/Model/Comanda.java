package Model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Comanda implements Serializable {

    private String usuari;              //cal enviar el usuari que ha fet la comanda
    private ArrayList<Plat> plats;      //cal que sigui el array dels plats que ha demanat la taula
    private Date data;                  //  quan fem una comanda cal enviar a la hora que sha fet
    private Time hora;

    /**
     * Constructor sense parametres.
     * Crea un llistat de plats buit.
     */

    public Comanda(ArrayList<Plat> plats, Date data, Time hora, String usuari) {
        this.plats = plats;
        this.data = data;
        this.hora = hora;
        this.usuari = usuari;
    }

    public  Comanda(){
        this.plats = new ArrayList<Plat>();

    }

    public ArrayList<Plat> getPlats() {
        return plats;
    }

    public String getUsuari() {
        return usuari;
    }

    public void addPlat(Plat plat) {
        plats.add(plat);
    }

    public Plat getPlat(int i) {
        return plats.get(i);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

}