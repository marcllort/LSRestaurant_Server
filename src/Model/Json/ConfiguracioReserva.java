package Model.Json;

public class ConfiguracioReserva {
    private String portServer;            //  Port de connexio amb el servidor

    private String ipServer;        // IP del servidor

    private StringBuilder sb;           // StringBuilder

    private String separator;           // Separador del sistema



    public String lectorPortServer() {
        return portServer;
    }

    public String lectorIpServer() {
        return ipServer;
    }

    @Override
    public String toString() {
        if (sb == null) {
            sb = new StringBuilder();
            separator = System.lineSeparator();
        }
        sb.setLength(0);

        sb.append(this.portServer);

        sb.append(this.separator);
        sb.append(this.ipServer);

        return sb.toString();
    }

}
