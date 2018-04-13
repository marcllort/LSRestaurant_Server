package Model.Json;


public class ConfiguracioServer {

    private String portBBDD;            //  Port de connexio amb la base de dades

    private String ipBBDD;        // IP del servidor on es troba la base de dades

    private String nomBBDD;             // Nom de la base de dades

    private String usernameBBDD;            // Nom de l'usuari d'acces a la BBDD

    private String passwordBBDD;            // Nom de la contrassenya de l'usuari d'acces a la BBDD

    private String portEntrada;        // Port de communicacio amb el client Recepcio

    private String portReserva;         // Port de communicacio amb el client Reserva

    private StringBuilder sb;           // StringBuilder

    private String separator;           // Separador del sistema




    public String lectorIpBBDD() {
        return ipBBDD;
    }

    public String lectorPortBBDD() {
        return portBBDD;
    }

    public String lectorNomBBDD() {
        return nomBBDD;
    }

    public String lectorUsernameBBDD() {
        return usernameBBDD;
    }

    public String lectorPasswordBBDD() {
        return passwordBBDD;
    }

    public String lectorPortEntrada() {
        return portEntrada;
    }

    public String lectorPortReserva() {
        return portReserva ;
    }



    @Override
    public String toString() {
        if (sb == null) {
            sb = new StringBuilder();
            separator = System.lineSeparator();
        }
        sb.setLength(0);

        sb.append(this.portBBDD);

        sb.append(this.separator);
        sb.append(this.ipBBDD);

        sb.append(this.separator);
        sb.append(this.nomBBDD);

        sb.append(this.separator);
        sb.append(this.usernameBBDD);

        sb.append(this.separator);
        sb.append(this.passwordBBDD);

        sb.append(this.separator);
        sb.append(this.portEntrada);

        sb.append(this.separator);
        sb.append(this.portReserva);

        return sb.toString();
    }
}
