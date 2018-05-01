package Model;

import Model.Json.ConfiguracioServer;
import Model.Json.LectorJson;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BDD {

    private static String username ;
    private static String password ;
    private static String ipBBDD;
    private static int portBBDD;
    private static String nomBBDD;
    private static String url;
    private Connection con;
    private Statement st;
    private LectorJson lectorJSON;

    public BDD() throws SQLException {
        lectorJSON = new LectorJson();
        ConfiguracioServer config = LectorJson.llegeixConfiguracioServer();

        ipBBDD = config.lectorIpBBDD();
        portBBDD = config.lectorPortBBDD();
        nomBBDD = config.lectorNomBBDD();
        username = config.lectorUsernameBBDD();
        password = config.lectorPasswordBBDD();

        url= "jdbc:mysql://" + ipBBDD + ":" + portBBDD + "/" + nomBBDD + "?useSSL=false";

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("BBDD: Base de dades connectada");
        con = connection;
        st = con.createStatement();
    }


    //Taula

    public void createTable(int i) throws SQLException {
        st.executeUpdate("INSERT INTO Taula(num_cadires) " + "VALUES (" + i + ")");
    }

    public ArrayList<Integer> mostraTaules() throws SQLException {
        ArrayList<Integer> result = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT id_taula FROM Taula");
        while (rs.next()){
           result.add(rs.getInt("id_taula"));
        }
        return result;
    }

    public int reservaTaula(int comensals, java.sql.Date data, Time hora) {

        ArrayList taules = new ArrayList();
        ArrayList reserves = new ArrayList();

        try {
            ResultSet rs = st.executeQuery("SELECT id_taula FROM Taula WHERE num_cadires = " + comensals);
            while (rs.next()) {
                taules.add(rs.getInt("id_taula"));
            }

            ResultSet rss = st.executeQuery("SELECT id_taula FROM Reserva NATURAL JOIN Taula WHERE data = '" + data + "'AND hora ='" + hora + "'AND num_cadires = " + comensals);

            while (rss.next()) {
                reserves.add(rss.getInt("id_taula"));
            }

            ArrayList lliures = getMinusArray(taules, reserves);
            //System.out.println("ID TAULA LLIURE: " + lliures.get(0));

            return (int) lliures.get(0);
        } catch (Exception e) {
            return -1;
        }

    }

    public ArrayList<Reserva> mostraReservesTaula(int idTaula){
        ArrayList<Reserva> result = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("SELECT usuari, n_comensals, data, hora FROM Reserva WHERE id_taula = "+idTaula);
            while (rs.next()){
                String usr = rs.getString("usuari");
                int com = rs.getInt("n_comensals");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                Reserva res = new Reserva(usr,com,data,hora);
                result.add(res);


            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }


    }
    public void eliminaTaula(int idTaula)throws Exception{
        ArrayList<Reserva> result = new ArrayList<>();
        result = mostraReservesTaula(idTaula);
        if(result.size() == 0){
            PreparedStatement ps = con.prepareStatement("DELETE FROM Taula WHERE id_taula = "+idTaula);
            ps.executeUpdate();
        }
        else {
          Exception e =  new Exception("La taula no es pot eliminar perque te reserves");
            throw e;
        }

    }


    //FALTA UNA FUNCIO QUE BORRI TAULA I LES RESERVES QUE HAVIA PER AQUELL TAULA
    //


    //Plat

    public void queriePlat(String querie) {
        try {
            ResultSet rss = st.executeQuery(querie);
            while (rss.next()) {
                String lastName = "NOM: " + rss.getString("nom_plat");
                lastName = lastName + "  Preu: " + rss.getString("preu");
                lastName = lastName + "  Unitats disponibles: " + rss.getString("unitats_disponibles");
                lastName = lastName + "  Unitats gastades: " + rss.getString("unitats_gastades");
                System.out.println(lastName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Posar algo! enviar error o el que sigui
        }
    }

    public void insereixPlat(String nom_plat, int preu, int unitats_disponibles, int unitats_gastades) throws SQLException {
        st.executeUpdate("INSERT INTO Plat(nom_plat, preu, unitats_disponibles, unitats_gastades) " +
                "VALUES ('" + nom_plat + "'," + preu + "," + unitats_disponibles + "," + unitats_gastades + ")");
    }

    public void eliminaPlat(String nom) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Plat WHERE Nom_plat = '" + nom + "'");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updatePlat(String nom, int unitats) {

        boolean ok = true;
        try {
            String str = "Update Plat set unitats_gastades = ?, unitats_disponibles= ? where nom_plat = '" + nom + "'";
            PreparedStatement ps = con.prepareStatement(str);

            ResultSet rss = null;
            ResultSet rs = null;

            rss = st.executeQuery("SELECT unitats_disponibles FROM Plat where nom_plat = '" + nom + "';");
            if (rss.next()) {

                if (rss.getInt("unitats_disponibles") - unitats >= 0) {
                    ps.setInt(2, rss.getInt("unitats_disponibles") - unitats);
                } else {
                    ok = false;
                    ps.setInt(2, rss.getInt("unitats_disponibles"));
                }

            }

            rs = st.executeQuery("SELECT  unitats_gastades  FROM Plat where nom_plat = '" + nom + "';");
            if (rs.next()) {
                if (ok == true) {
                    ps.setInt(1, rs.getInt("unitats_gastades") + unitats);
                } else {
                    ps.setInt(1, rs.getInt("unitats_gastades"));
                }
            }
            ps.executeUpdate();
            return ok;

        } catch (SQLException e) {
            System.out.println("ERROORR");
            e.printStackTrace();
            return false;
        }
    }

    public void serveixPlat(String plat, String usuari) {
        String str = "Update Comanda set servit  = TRUE where nom_plat = '" + plat + "'" + " AND usuari = '" + usuari + "'";
        try {
            PreparedStatement ps = con.prepareStatement(str);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Plat> llistaPlatsDisponibles() {
        try {
            ResultSet rs = st.executeQuery("SELECT nom_plat, preu, unitats_disponibles FROM Plat");
            ArrayList<Plat> plats = new ArrayList<>();

            while (rs.next()) {
                String nom = rs.getString("nom_plat");
                int preu = rs.getInt("preu");
                int unitats = rs.getInt("unitats_disponibles");
                if (unitats > 0) {
                    Plat plat = new Plat(nom, preu);
                    plats.add(plat);

                }
            }
            return plats;

        } catch (SQLException s) {
            s.printStackTrace();
            return null;
        }
    }

    public ArrayList<Plat> llistaPlatsNoDisponibles(Comanda comanda) {
        try {
            ResultSet rs = st.executeQuery("SELECT nom_plat FROM Plat WHERE unitats_disponibles = 0");
            ArrayList<Plat> platscomanda = comanda.getPlats();
            ArrayList<String> platsacabat = new ArrayList<>();
            while (rs.next()) {
                String nom = rs.getString("nom_plat");
                platsacabat.add(nom);
            }
            ArrayList<Plat> acabat = new ArrayList<>();
            for (Plat plat : platscomanda) {
                for (String nom : platsacabat) {
                    if (plat.getNomPlat().equals(nom)) {
                        acabat.add(plat);
                    }
                }
            }
            return acabat;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<InfoComandes> top5Plats() {
        try {
            ResultSet rs = st.executeQuery("SELECT nom_plat, unitats_gastades FROM Plat ORDER BY unitats_gastades DESC");
            ArrayList<InfoComandes> comandes = new ArrayList<>();
            while (rs.next() && comandes.size() < 5) {
                InfoComandes info = new InfoComandes();
                info.setUsuari(rs.getString("nom_plat"));
                info.setTotalPlats(rs.getInt("unitats_gastades"));
                comandes.add(info);
            }
            return comandes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<InfoComandes> top5PlatsSemanals() {
        try {
            java.util.Date date = Calendar.getInstance().getTime();
            ResultSet rs = st.executeQuery("SELECT * FROM   Comanda WHERE  YEARWEEK(`data`, 1) = YEARWEEK(CURDATE(), 1)");
            ArrayList<InfoComandes> comandes = new ArrayList<>();
            while (rs.next()) {
                InfoComandes info = new InfoComandes();
                info.setUsuari(rs.getString("nom_plat"));
                info.setTotalPlats(1);
                int p = 0;
                for (InfoComandes a : comandes) {
                    if (info.getUsuari().equals(a.getUsuari())) {
                        p = a.getTotalPlats() + 1;
                        a.setTotalPlats(p);
                    }
                }
                if (p == 0) {
                    comandes.add(info);
                }
                // Sorting
                Collections.sort(comandes, new Comparator<InfoComandes>() {
                    @Override
                    public int compare(InfoComandes com1, InfoComandes com2) {
                        Integer a = com1.getTotalPlats();
                        Integer b = com2.getTotalPlats();
                        return b.compareTo(a);
                    }
                });
            }
            return comandes;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    //Comanda

    public void creaComanda(Comanda comanda) throws SQLException {
        for (Plat plat : comanda.getPlats()) {
            System.out.println(comanda.getUsuari() + plat.getNomPlat());
            st.executeUpdate("INSERT INTO Comanda(usuari, nom_plat, data, hora,servit) " +
                    "VALUES ('" + comanda.getUsuari() + "','" + plat.getNomPlat() + "', '" + comanda.getData() + "','" + comanda.getHora() + "'," + plat.isServit() + ")");
        }
    }

    public Comanda mostraPlatsComanda(String usuari) {

        ArrayList<Plat> array = new ArrayList<Plat>();
        ResultSet rs = null;       //Mirar si l'ordre va be
        ResultSet rss = null;
        try {
            rs = st.executeQuery("SELECT MAX(hora) AS hour, MAX(data) AS Date FROM Comanda WHERE usuari = '" + usuari + "'" +
                    "GROUP BY usuari ");
            Date data = new Date(12,12,12);
            Time hora = new Time(12,12,12);
            if (rs.next()) {
                data = rs.getDate("Date");
                hora = rs.getTime("hour");
            } else {
                Date dat = new Date(12,12,12);
                if (data.equals(dat) ){
                    rs = st.executeQuery("SELECT data, hora FROM Reserva WHERE usuari = '" + usuari + "'");

                    if (rs.next()) {
                        data = rs.getDate("data");
                        hora = rs.getTime("hora");

                    }
                }


            }
            rss = st.executeQuery("SELECT nom_plat, servit, preu  FROM Comanda NATURAL JOIN Plat WHERE usuari = '" + usuari + "'");

            while (rss.next()) {
                String nom = rss.getString("nom_plat");
                Boolean servit = rss.getBoolean("servit");
                int preu = rss.getInt("preu");
                Plat plat = new Plat(nom, preu, servit);
                array.add(plat);
            }
            Comanda comanda = new Comanda(array, data, hora, usuari);
            return comanda;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<InfoComandes> llistaComandes() throws SQLException {

        ArrayList<InfoComandes> array = new ArrayList<InfoComandes>();
        ResultSet rs = st.executeQuery("SELECT usuari, COUNT(nom_plat) AS num, SUM(!servit) AS sum, hora, data FROM Comanda\n" +
                "GROUP BY usuari \n" +
                "ORDER BY data, hora ASC; ");       //Mirar si l'ordre va be

        while (rs.next()) {
            InfoComandes comanda = new InfoComandes();
            comanda.setUsuari(rs.getString("usuari"));
            comanda.setTotalPlats(rs.getInt("num"));
            comanda.setPlatsPendents(rs.getInt("sum"));
            comanda.setHora(rs.getTime("hora"));
            comanda.setDate(rs.getDate("data"));
            array.add(comanda);

        }
        return array;
    }


    //Reserva

    public Boolean comprovaPassword(String usuari, String password) {
        try {
            ResultSet rs = st.executeQuery("SELECT usuari, password FROM Reserva WHERE usuari = '" + usuari + "'");
            String contrasenya = "";
            if (rs.next()) {
                contrasenya = rs.getString("password");
            }

            return contrasenya.equals(password);
        } catch (Exception e) {
            System.out.println("ERROR");
            return false;
        }
    }

    public void creaReserva(String usuari, String password, int comencals, Date data, Time hora, int id_taula) throws SQLException {
        st.executeUpdate("INSERT INTO Reserva(usuari, password, n_comensals, data, hora, id_taula) " +
                "VALUES ('" + usuari + "','" + password + "'," + comencals + ",'" + data + "','" + hora + "'," + id_taula + ")");
    }


    //Altres

    private ArrayList<Integer> getMinusArray(ArrayList array1, ArrayList array2) {

        ArrayList<Integer> minusArray = new ArrayList<Integer>();

        minusArray.addAll(array1);

        for (int i = 0; i < minusArray.size(); i++) {
            for (int j = 0; j < array2.size(); j++) {
                if (minusArray.get(i).equals(array2.get(j))) {
                    minusArray.remove(i);
                    if (i == 0) {
                    } else if (j == 0) {
                    } else {
                        i = 0;
                        j = 0;
                    }
                } else {
                }
            }
        }
        return minusArray;
    }


}

