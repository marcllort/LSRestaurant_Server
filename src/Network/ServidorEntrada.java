package Network;

import Model.Gestionador;
import Model.Reserva;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServidorEntrada extends Thread {

    private final Gestionador gestionador;
    private Socket sClient;
    private ObjectOutputStream ooStream;

    /**
     * Construcor amb parametres del servidor per el client d'entrada
     *
     * @param sClient
     * @param gestionador
     */
    public ServidorEntrada(Socket sClient, Gestionador gestionador) {
        this.sClient = sClient;
        this.gestionador = gestionador;
    }


    /**
     * Override de run encarregat de generar reserves amb el usuari escrit, i genera un password aleatori
     */
    @Override

    public void run() {

        try {
            DataOutputStream doStream = new DataOutputStream(sClient.getOutputStream());
            ObjectInputStream diStream = new ObjectInputStream(sClient.getInputStream());
            ooStream = new ObjectOutputStream(sClient.getOutputStream());

            while (true) {
                Reserva reserva = (Reserva) diStream.readObject();

                String password = gestionador.generatePass();

                String estatReserva = gestionador.creaReserva(reserva, password);

                if (estatReserva.equals("true")) {
                    doStream.writeUTF(password);
                } else {
                    doStream.writeUTF(estatReserva);            //preparar networkReserva per rebre un string
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Entrada: Client desconnectat");
        }
    }

}
