package Model;

import java.sql.Time;
import java.util.Date;

public class InfoComandes {
    private String usuari;
    private int total_plats;
    private int platsPendents;
    private Date date;
    private Time hora;

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public void setTotal_plats(int total_plats) {
        this.total_plats = total_plats;
    }

    public void setPlatsPendents(int platsPendents) {
        this.platsPendents = platsPendents;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getUsuari() {

        return usuari;
    }

    public int getTotal_plats() {
        return total_plats;
    }

    public int getPlatsPendents() {
        return platsPendents;
    }

    public Date getDate() {
        return date;
    }

    public Time getHora() {
        return hora;
    }
}
