package Model.Json;

/**
 * Classe que conte les classes que agafa del fitxer json
 */
public class ConfiguracioServer {

    private int portBBDD;            //  Port de connexio amb la base de dades

    private String ipBBDD;        // IP del servidor on es troba la base de dades

    private String nomBBDD;             // Nom de la base de dades

    private String usernameBBDD;            // Nom de l'usuari d'acces a la BBDD

    private String passwordBBDD;            // Nom de la contrassenya de l'usuari d'acces a la BBDD

    private int portEntrada;        // Port de communicacio amb el client Recepcio

    private int portReserva;         // Port de communicacio amb el client Reserva


    /**
     * Getter de la ip de bbdd
     *
     * @return ip BBDD
     */
    public String lectorIpBBDD() {
        return ipBBDD;
    }

    /**
     * Getter del port de la bbdd
     *
     * @return port BBDD
     */
    public int lectorPortBBDD() {
        return portBBDD;
    }

    /**
     * Getter de nom de la bbdd
     *
     * @return nom de la BBDD
     */
    public String lectorNomBBDD() {
        return nomBBDD;
    }

    /**
     * Getter de el nom d'usuari de la bbdd
     *
     * @return nom d'usuari BBDD
     */
    public String lectorUsernameBBDD() {
        return usernameBBDD;
    }

    /**
     * Getter de el password de la bbdd
     *
     * @return password BBDD
     */
    public String lectorPasswordBBDD() {
        return passwordBBDD;
    }

    /**
     * Getter de el port del client entrada
     *
     * @return port client entrada
     */
    public int lectorPortEntrada() {
        return portEntrada;
    }

    /**
     * Getter de el port del client de reserva
     *
     * @return port client reserva
     */
    public int lectorPortReserva() {
        return portReserva;
    }

}
