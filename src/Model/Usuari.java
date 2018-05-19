package Model;

import java.io.Serializable;

/**
 * Classe usuari que conte el nom d'usuari i password
 */
public class Usuari implements Serializable {

    private String user;
    private String password;

    /**
     * Constructor amb parámetres de usuari
     *
     * @param user     nom usuari
     * @param password string de password
     */
    public Usuari(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * Getter de el nom d'usuari
     *
     * @return nom d'usuari
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter de nom d'usuari
     *
     * @param user nom d'usuari
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Getter de constrasenya
     *
     * @return constrasenya
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter de password
     *
     * @param password string de password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
