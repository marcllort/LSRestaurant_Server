package Network;

import Model.*;
import Vista.VistaComandes;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServidorReserva extends Thread {

    private final Gestionador gestionador;
    private Socket sClient;
    private Usuari user;
    private ArrayList<ServidorReserva> servers;
    private ObjectOutputStream ooStream;
    private DataOutputStream doStream;
    private DataInputStream diStream;
    private ObjectInputStream oiStream;
    private VistaComandes vistaComandes;


    /**
     * Constructor amb parametres per crear el servidor dedicat
     *
     * @param sClient
     * @param servers
     * @param gestionador
     */
    public ServidorReserva(Socket sClient, ArrayList<ServidorReserva> servers, Gestionador gestionador, VistaComandes vistaComandes) {
        this.sClient = sClient;
        this.servers = servers;
        this.gestionador = gestionador;
        this.vistaComandes = vistaComandes;
    }

    /**
     * Override de run del server que s'encarrega d'interactuar amb el client
     * Comprova que el usuari password siguin correctes
     * Comprova les comandes que envien i retorna al usuari si sha realizat correctament
     */
    @Override

    public void run() {

        try {
            diStream = new DataInputStream(sClient.getInputStream());
            doStream = new DataOutputStream(sClient.getOutputStream());
            ooStream = new ObjectOutputStream(sClient.getOutputStream());
            oiStream = new ObjectInputStream(sClient.getInputStream());

            while (true) {

                user = (Usuari) oiStream.readObject();
                System.out.println("Reserva: " + user.getUser() + " - " + user.getPassword());

                if (gestionador.comprovaUserPass(user.getUser(), user.getPassword())) {
                    doStream.writeUTF("true");                                                  //enviem true en cas de haver entrat correctaemnt
                    ooStream.writeObject(gestionador.retornaCarta());                               //enviem la carta amb plats disponibles

                    while (true) {
                        ooStream.writeObject(gestionador.retornaComanda(user.getUser()));
                        Comanda com = (Comanda) oiStream.readObject();                          //Rebem la comanda enviada pel usuari

                        String analisi = gestionador.analitzarComanda(com);
                        if (analisi.equals("true")) {
                            gestionador.addComanda(com);                                                //Guardo la comanda

                            try {
                                ArrayList<InfoComandes> model = gestionador.llistaComandes();
                                vistaComandes.setModelTaula(model);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            String missatge = "true";
                            ooStream.writeObject(missatge);
                            //actualitzar vista de gestionar comandes
                        } else if (analisi.equals("Pagat")) {
                            ArrayList<Plat> plats = gestionador.retornaComanda(user.getUser()).getPlats();
                            gestionador.serveixPlatsUsuari(user.getUser());
                            for (Plat p : plats) {
                                if (!p.isServit()) {
                                    gestionador.updatePlat(p.getNomPlat(), 1);
                                    p.setServit(true);
                                }
                            }
                            try {                                                                       //Actaulitzo vista de les comandes
                                ArrayList<InfoComandes> model = gestionador.llistaComandes();
                                vistaComandes.setModelTaula(model);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }else if(analisi.substring(0,6).equals("Falten")){
                            ooStream.writeObject(analisi);
                        }
                        else if (!analisi.equals("Pagat") || !analisi.equals("true")) {
                            String missatge = "No queden unitats de:" + analisi;
                            ooStream.writeObject(missatge);//enviar error
                        }
                    }
                } else {
                    doStream.writeUTF("Usuari o Password incorrectes!");            //preparar networkReserva per rebre un string
                    System.out.println("Reserva: Usuari o Password incorrectes!");
                }
            }

        } catch (MySQLIntegrityConstraintViolationException r) {
            try {
                doStream.writeUTF("El plat no existeix");
                System.out.println("Error mysql");
            } catch (IOException e) {
                r.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            servers.remove(this);
            //En cas de que es desconnecti el client o hi hagi algun error tanco el server dedicat
            System.out.println("Client Desonnectat");
        }
    }


    //Funcions

    /**
     * Funció per enviar comanda al client del servidor dedicat
     */
    public void enviaComanda() {
        try {
            ooStream.writeObject(gestionador.retornaComanda(user.getUser()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funció per enviar la carta al client del servidor dedicat
     */
    public void enviaCarta() {
        try {
            ooStream.writeObject(gestionador.retornaCarta());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter de user del servidor dedicat
     *
     * @return string de usuari
     */
    public String getUser() {
        return user.getUser();
    }
}
