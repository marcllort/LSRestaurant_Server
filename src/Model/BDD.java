package Model;

import Model.Json.ConfiguracioServer;
import Model.Json.LectorJson;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BDD {
    /**
     * Classe que s'encarrega de realitzar totes les queries i actualitzacions d'informació de la base de ad
     */

    private static String username;
    private static String password;
    private static String ipBBDD;
    private static int portBBDD;
    private static String nomBBDD;
    private static String url;
    private Connection con;
    private Statement st;
    private LectorJson lectorJSON;

    /**
     * Constructor de la bdd que fa la conexió amb bdd
     *
     * @throws SQLException error en cas de fallar la conexió amb la bdd
     */
    public BDD() throws SQLException {
        lectorJSON = new LectorJson();
        ConfiguracioServer config = LectorJson.llegeixConfiguracioServer();

        ipBBDD = config.lectorIpBBDD();
        portBBDD = config.lectorPortBBDD();
        nomBBDD = config.lectorNomBBDD();
        username = config.lectorUsernameBBDD();
        password = config.lectorPasswordBBDD();

        url = "jdbc:mysql://" + ipBBDD + ":" + portBBDD + "/" + nomBBDD + "?useSSL=false";

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("BBDD: Base de dades connectada");
        con = connection;
        st = con.createStatement();

    }


    //Taula

    /**
     * Funció que crea una nova taula a la bdd
     *
     * @param i numero de cadires amb que crearem la taula
     * @throws SQLException
     */
    public void createTable(int i) throws SQLException {
        st.executeUpdate("INSERT INTO Taula(num_cadires) " + "VALUES (" + i + ")");
    }

    /**
     * Funcio que retorna un arraylist amb tots els id's de les taules que hi ha
     *
     * @return arraylist de id ed les taules
     * @throws SQLException si no troba taules
     */
    public ArrayList<Integer> mostraTaules() throws SQLException {
        ArrayList<Integer> result = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT id_taula FROM Taula");
        while (rs.next()) {
            result.add(rs.getInt("id_taula"));
        }
        return result;
    }

    /**
     * Funcio que mostra el id de una taula lliure per un dia i una hora concretes
     *
     * @param comensals numero de persones per les que es fara la reserva
     * @param data      data de la reserva
     * @param hora      hora de la reserva
     * @return retorna el id de la taula lliure i en cas que no s'haigi pogut trobar un -1
     */
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

    /**
     * funcio que mostra les reserves que te una taula concreta
     *
     * @param idTaula id de la taula ed la que es solicita la informació
     * @return retorna un arraylist de reserves
     */
    public ArrayList<Reserva> mostraReservesTaula(int idTaula) {
        ArrayList<Reserva> result = new ArrayList<>();
        try {
            ResultSet rs = st.executeQuery("SELECT usuari, n_comensals, data, hora FROM Reserva WHERE id_taula = " + idTaula);
            while (rs.next()) {
                String usr = rs.getString("usuari");
                int com = rs.getInt("n_comensals");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                Reserva res = new Reserva(usr, com, data, hora);
                result.add(res);


            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }


    }

    /**
     * funcio que elimina una taula
     *
     * @param idTaula id de la taula a eliminar
     * @throws Exception en cas que no es trobi la taula o que tingui reserves pendents
     */
    public void eliminaTaula(int idTaula) throws Exception {
        ArrayList<Reserva> result = new ArrayList<>();
        result = mostraReservesTaula(idTaula);
        if (result.size() == 0) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Taula WHERE id_taula = " + idTaula);
            ps.executeUpdate();
        } else {
            Exception e = new Exception("La taula no es pot eliminar perque te reserves");
            throw e;
        }

    }


    //Plat

    /**
     * funció que realitza una cerca a la taula plat
     *
     * @param querie querie a realitzar
     */
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

    /**
     * funcio que insereix un nou plat a la taula plat
     *
     * @param nom_plat            nom del plat
     * @param preu                preu
     * @param unitats_disponibles unitats disponibles
     * @param unitats_gastades    unitats gastades
     * @throws SQLException error al inserir plat
     */
    public void insereixPlat(String nom_plat, float preu, int unitats_disponibles, int unitats_gastades) throws SQLException {
        nom_plat = nom_plat.replace("'", "\'");
        st.executeUpdate("INSERT INTO Plat(nom_plat, preu, unitats_disponibles, unitats_gastades) " +
                "VALUES (" + '"' + nom_plat + '"' + "," + preu + "," + unitats_disponibles + "," + unitats_gastades + ")");
    }

    /**
     * funcio que elimina un plat de la taula plat
     *
     * @param nom nom del plat a eliminar
     */
    public void eliminaPlat(String nom) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Plat WHERE Nom_plat = " + '"' + nom + '"');
        ps.executeUpdate();
    }

    /**
     * funcio que gasta una unitta de les disponibles i la afegeix a les gastades
     *
     * @param nom     plat que s'ha servit
     * @param unitats unitats que s'han gastat
     * @return retorna utrue o fals sewgons s'hagi pogut fer o no
     */
    public boolean updatePlat(String nom, int unitats) throws SQLException {

        boolean ok = true;

        String str = "Update Plat set unitats_gastades = ?, unitats_disponibles= ? where nom_plat = " + '"' + nom + '"';
        PreparedStatement ps = con.prepareStatement(str);

        ResultSet rss = null;
        ResultSet rs = null;

        rss = st.executeQuery("SELECT unitats_disponibles FROM Plat where nom_plat = " + '"' + nom + '"');
        if (rss.next()) {

            if (rss.getInt("unitats_disponibles") - unitats >= 0) {
                ps.setInt(2, rss.getInt("unitats_disponibles") - unitats);
            } else {
                ok = false;
                ps.setInt(2, rss.getInt("unitats_disponibles"));
            }

        }

        rs = st.executeQuery("SELECT  unitats_gastades  FROM Plat where nom_plat = " + '"' + nom + '"');
        if (rs.next()) {
            if (ok == true) {
                ps.setInt(1, rs.getInt("unitats_gastades") + unitats);
            } else {
                ps.setInt(1, rs.getInt("unitats_gastades"));
            }
        }
        ps.executeUpdate();
        return ok;


    }

    /**
     * funcio que gasta una unitta de les disponibles i la afegeix a les gastades
     *
     * @param nom     plat que s'ha servit
     * @param unitats unitats que s'han gastat
     * @return retorna utrue o fals sewgons s'hagi pogut fer o no
     */
    public boolean afegeixUnitats(String nom, int unitats) throws SQLException {

        boolean ok = true;

        String str = "Update Plat set unitats_disponibles= ? where nom_plat = " + '"' + nom + '"';
        PreparedStatement ps = con.prepareStatement(str);

        ResultSet rss = null;


        rss = st.executeQuery("SELECT unitats_disponibles FROM Plat where nom_plat = " + '"' + nom + '"');
        if (rss.next()) {


            ps.setInt(1, unitats);
            ps.executeUpdate();
            return ok;

        } else {
            return false;
        }

    }

    /**
     * funcio que fa que una comanda pasi de estar per servir a servida
     *
     * @param plat   nom del plat a servir
     * @param usuari nom del usuari al que es serveix
     */
    public void serveixPlat(String plat, String usuari) {
        int id = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT id_comanda FROM Comanda where nom_plat = " + '"' + plat + '"' + " AND usuari = '" + usuari + "' AND servit = false");
            if (rs.next()) {
                id = rs.getInt("id_comanda");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String str = "Update Comanda set servit  = TRUE where nom_plat = " + '"' + plat + '"' + " AND usuari = '" + usuari + "' AND id_comanda =" + id;
        try {
            PreparedStatement ps = con.prepareStatement(str);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * funcio que retorna un array amb tots els plats que hi ha disponibles
     *
     * @return array de plats
     */
    public ArrayList<Plat> llistaPlatsDisponibles() {
        try {
            ResultSet rs = st.executeQuery("SELECT nom_plat, preu, unitats_disponibles FROM Plat");
            ArrayList<Plat> plats = new ArrayList<>();

            while (rs.next()) {
                String nom = rs.getString("nom_plat");
                float preu = rs.getFloat("preu");
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

    /**
     * funcio que retorna un array amb tots els plats que hi ha a la bdd
     *
     * @return array de plats
     */
    public ArrayList<Plat> llistaPlats() {
        try {
            ResultSet rs = st.executeQuery("SELECT nom_plat, preu, unitats_disponibles FROM Plat");
            ArrayList<Plat> plats = new ArrayList<>();

            while (rs.next()) {
                String nom = rs.getString("nom_plat");

                float preu = rs.getFloat("preu");
                int unitats = rs.getInt("unitats_disponibles");
                Plat plat = new Plat(nom, preu);
                plats.add(plat);


            }
            return plats;

        } catch (SQLException s) {
            s.printStackTrace();
            return null;
        }
    }

    public int getUnitatsPlat(String plat) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery("SELECT  unitats_disponibles FROM Plat WHERE nom_plat = " + '"' + plat + '"');

            int unitats = 0;
            if (rs.next()) {
                unitats = rs.getInt("unitats_disponibles");
            }
            return unitats;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * funcio a la que li passem una comanda i ens torna un array dels plats que s'han acabat de la comanda
     *
     * @param comanda tipus comanda
     * @return retorna un array amb els plats que s'han acabat o null en cas que tots estiguin disponibles
     */
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

    /**
     * funcio que retorna els 5 plats més demanats
     *
     * @return arraylist dels plats
     */
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

    /**
     * funcio que retorna els plats més demanats de la ùltima setmana
     *
     * @return array de top 5 de plats
     */
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
                // Ordenar
                Collections.sort(comandes, new Comparator<InfoComandes>() {
                    @Override
                    public int compare(InfoComandes com1, InfoComandes com2) {
                        Integer a = com1.getTotalPlats();
                        Integer b = com2.getTotalPlats();
                        return b.compareTo(a);
                    }
                });
            }
            if (comandes.size() < 4) {
                return comandes;
            }else {
                ArrayList<InfoComandes> com = new ArrayList<>(comandes.subList(0, 4));
                return com;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    //Comanda

    /**
     * funcio que afegeix una comanda a la bdd
     *
     * @param comanda comanda a afegir a la bdd
     * @throws SQLException error al crear comanda
     */
    public void creaComanda(Comanda comanda) throws SQLException {
        for (Plat plat : comanda.getPlats()) {
            st.executeUpdate("INSERT INTO Comanda(usuari, nom_plat, data, hora,servit) " +
                    "VALUES ('" + comanda.getUsuari() + "'," + '"' + plat.getNomPlat() + '"' + ", '" + comanda.getData() + "','" + comanda.getHora() + "'," + plat.isServit() + ")");
        }
    }

    /**
     * funcio que mostra els plats que hi ha a una comanda, si estan servits o no i la hora del ultim plat que ha demanat de la bdd
     *
     * @param usuari usuari que ha realitzat la comanda
     * @return retorna
     */
    public Comanda mostraPlatsComanda(String usuari) {

        ArrayList<Plat> array = new ArrayList<Plat>();
        ResultSet rs = null;       //Mirar si l'ordre va be
        ResultSet rss = null;
        try {
            rs = st.executeQuery("SELECT MAX(hora) AS hour, MAX(data) AS Date FROM Comanda WHERE usuari = " + '"' + usuari + '"' +
                    "GROUP BY usuari ");
            Date data = new Date(12, 12, 12);
            Time hora = new Time(12, 12, 12);
            if (rs.next()) {
                data = rs.getDate("Date");
                hora = rs.getTime("hour");
            } else {
                Date dat = new Date(12, 12, 12);
                if (data.equals(dat)) {
                    rs = st.executeQuery("SELECT data, hora FROM Reserva WHERE usuari = '" + usuari + "'");

                    if (rs.next()) {
                        data = rs.getDate("data");
                        hora = rs.getTime("hora");

                    }
                }

            }
            rss = st.executeQuery("SELECT nom_plat, servit, preu, id_comanda  FROM Comanda NATURAL JOIN Plat WHERE usuari = '" + usuari + "'");

            while (rss.next()) {
                String nom = rss.getString("nom_plat");
                Boolean servit = rss.getBoolean("servit");
                float preu = rss.getFloat("preu");
                int id_comanda = rss.getInt("id_comanda");
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

    /**
     * funcio que mostra quanta plate tenen cada una de les comandes i quants queden per servir a cada una ordenades per dia i hora
     *
     * @return llista amb la informacio de les comandes
     * @throws SQLException error al retornar la llista de comandes
     */
    public ArrayList<InfoComandes> llistaComandes() throws SQLException {

        ArrayList<InfoComandes> array = new ArrayList<InfoComandes>();
        ResultSet rs = st.executeQuery("SELECT usuari, COUNT(nom_plat) AS num, SUM(!servit) AS sum, MAX(hora) AS hora, MAX(data) AS data FROM Comanda " + //WHERE SUM(!servit) > 0 AND MAX(data) > CURRENT_DATE " +
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

    /**
     * Funció que serveix tots els plats d'un usuari, quan paguen i marxen
     *
     * @param usuari nom de usuari del que volem servir els plats
     */
    public void serveixPlatsUsuari(String usuari) {
        String str = "Update Comanda set Servit= true where usuari = '" + usuari + "'";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(str);
            ResultSet rss = null;
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    //Reserva

    /**
     * Funció que comprova que la contrasenya introduida sigui correcta
     *
     * @param usuari   usuari
     * @param password contrasenya
     * @return
     */
    public Boolean comprovaPassword(String usuari, String password) {
        try {

            ResultSet rs = st.executeQuery("SELECT usuari, password, data FROM Reserva WHERE usuari = '" + usuari + "' ");
            String contrasenya = "";
            String usr = "";
            java.util.Date dat = new java.util.Date();

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            java.util.Date dataNow = cal.getTime();

            if (rs.next()) {
                usr = rs.getString("usuari");
                contrasenya = rs.getString("password");
                dat = rs.getDate("data");
            }
            if (usr.equals(usuari) && !dat.before(dataNow)) {
                return contrasenya.equals(password);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("ERROR");

            return false;
        }
    }

    /**
     * Funció que crea una reserva a una taula per un usuari
     *
     * @param usuari
     * @param password
     * @param comencals
     * @param data
     * @param hora
     * @param id_taula
     * @throws SQLException
     */
    public void creaReserva(String usuari, String password, int comencals, Date data, Time hora, int id_taula) throws SQLException {
        st.executeUpdate("INSERT INTO Reserva(usuari, password, n_comensals, data, hora, id_taula) " +
                "VALUES ('" + usuari + "','" + password + "'," + comencals + ",'" + data + "','" + hora + "'," + id_taula + ")");
    }


    //Altres

    /**
     * Funció que resta dos arrays, usada en diferentes funcions de la bbdd
     *
     * @param array1
     * @param array2
     * @return
     */
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

