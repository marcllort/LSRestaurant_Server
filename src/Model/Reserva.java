package Model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * Classe Reserva que conté la informació (usuari, nombre de començals, data i hora) de la reserva
 */
public class Reserva implements Serializable {

    private String usuari;
    private Integer nComencals;
    private Date data;
    private Time hora;

    /**
     * Constructor amb parametres del tipus reserva
     *
     * @param nom
     * @param nComencals
     * @param data
     * @param hora
     */
    public Reserva(String nom, int nComencals, Date data, Time hora) {
        this.usuari = nom;
        this.nComencals = nComencals;
        this.data = data;
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
     * Getter de nombre començals
     *
     * @return nombre de començals
     */
    public Integer getnComencals() {
        return nComencals;
    }

    /**
     * Getter de data
     *
     * @return data
     */
    public Date getData() {
        return data;
    }

    /**
     * Getter de hora
     *
     * @return hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * Override de toString perque ens retorni el string com el volem
     *
     * @return String
     */
    @Override
    public String toString() {
        return usuari + " - " + nComencals + " - " + data + " - " + hora;
    }


}
