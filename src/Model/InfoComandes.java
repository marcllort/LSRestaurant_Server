package Model;

import java.sql.Time;
import java.util.Date;

/**
 * Classe que conte la informació de els plats demanats i pendents
 * Ens serveix per realitzar els gràfics
 */
public class InfoComandes {

    private String usuari;
    private int totalPlats;
    private int platsPendents;
    private Date date;
    private Time hora;

    /**
     * Constructor de infocomandes sense parametres
     */
    public InfoComandes() {

    }

    /**
     * Constructor de infoComandes amb els parametres per tenirl-lo ja inicialitzat
     *
     * @param usuari        nom de usuari
     * @param totalPlats    numero de plats totals
     * @param platsPendents numero de plats pendents
     * @param date          tipus data
     * @param hora          tipus time
     */
    public InfoComandes(String usuari, int totalPlats, int platsPendents, Date date, Time hora) {
        this.usuari = usuari;
        this.totalPlats = totalPlats;
        this.platsPendents = platsPendents;
        this.date = date;
        this.hora = hora;
    }

    /**
     * Setter de usuari
     *
     * @param usuari nom d'usuari
     */
    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    /**
     * Setter de el nombre total de plats
     *
     * @param totalPlats número de plats total
     */
    public void setTotalPlats(int totalPlats) {
        this.totalPlats = totalPlats;
    }

    /**
     * Setter de plats pendents
     *
     * @param platsPendents número de plats pendents
     */
    public void setPlatsPendents(int platsPendents) {
        this.platsPendents = platsPendents;
    }

    /**
     * Setter de la data
     *
     * @param date tipus data
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Setter de hora
     *
     * @param hora tipus hora
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * Getter de usuari
     *
     * @return usuari
     */
    public String getUsuari() {
        return usuari;
    }

    /**
     * Getter de plats totals
     *
     * @return tots els plats
     */
    public int getTotalPlats() {
        return totalPlats;
    }

    /**
     * Getter de plats pendents
     *
     * @return els plats pendents
     */
    public int getPlatsPendents() {
        return platsPendents;
    }

    /**
     * Getter de data
     *
     * @return la data
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter de hora
     *
     * @return la hora
     */
    public Time getHora() {
        return hora;
    }

}
