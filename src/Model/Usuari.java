package Model;

import java.io.Serializable;

/**
 * Classe usuari que conte el nom d'usuari i password
 */
public class Usuari implements Serializable {

    private String user;
    private String password;

    /**
     * Constructor amb parametres de usuari
     *
     * @param user
     * @param password
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
     * @param user
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
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
