package Model;

import java.sql.*;
import java.util.ArrayList;

public class BDD {

    private Connection con;
    private Statement st;
    private static String username = "root";
    private static String password = "alex";
    private static String url = "jdbc:mysql://localhost:3306/LSRestaurant?useSSL=false";


    public BDD() throws SQLException {
        System.out.println("Connecting database...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");
        con = connection;
        st = con.createStatement();

        /* MOSTRA EL NOM DE TOTES LES TAULES

         DatabaseMetaData md = connection.getMetaData();
         ResultSet rs = md.getTables(null, null, "%", null);
         while (rs.next()) {
                System.out.println(rs.getString(3));
         }
        */
    }

    public void createTable(int i) throws SQLException {
        st.executeUpdate("INSERT INTO Taula(num_cadires) " + "VALUES (" + i + ")");
    }

    public void insereixPlat(String nom_plat, int preu, int unitats_disponibles, int unitats_gastades) throws SQLException {

        st.executeUpdate("INSERT INTO Plat(nom_plat, preu, unitats_disponibles, unitats_gastades) " +
                "VALUES ('" + nom_plat + "'," + preu + "," + unitats_disponibles + "," + unitats_gastades + ")");

    }

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
            //Posar algo! enviar error o el que sigui
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

    public void eliminaPlat(String nom) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Plat WHERE Nom_plat = '" + nom + "'");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Plat> llistaPlats() {
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

    public void serveixPlat(String plat, String usuari) {
        String str = "Update Comanda set servit  = TRUE where nom_plat = '" + plat + "'" + " AND usuari = '" + usuari + "'";
        try {
            PreparedStatement ps = con.prepareStatement(str);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Comanda mostraPlatsComanda(String usuari) {

        ArrayList<Plat> array = new ArrayList<Plat>();
        ResultSet rs = null;       //Mirar si l'ordre va be
        ResultSet rss = null;
        try {
            rs = st.executeQuery("SELECT MAX(hora) AS hour, MAX(data) AS Date FROM Comanda WHERE usuari = '" + usuari + "'" +
                    "GROUP BY usuari ");
            Date data;
            Time hora;
            if (rs.next()) {
                data = rs.getDate("Date");
                hora = rs.getTime("hour");
            } else {
                data = new Date(12, 12, 12);
                hora = new Time(12, 12, 12);

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

    public void creaReserva(String usuari, String password, int comencals, Date data, Time hora, int id_taula) throws SQLException {

        st.executeUpdate("INSERT INTO Reserva(usuari, password, n_comensals, data, hora, id_taula) " +
                "VALUES ('" + usuari + "','" + password + "'," + comencals + ",'" + data + "','" + hora + "'," + id_taula + ")");

    }

    public void creaComanda(Comanda comanda) throws SQLException {

        for (Plat plat : comanda.getPlats()) {
            st.executeUpdate("INSERT INTO Comanda(usuari, nom_plat, data, hora,servit) " +
                    "VALUES ('" + comanda.getUsuari() + "','" + plat.getNomPlat() + "', '" + comanda.getData() + "','" + comanda.getHora() + "'," + plat.isServit() + ")");
        }
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

    public ArrayList<InfoComandes> LlistatComandes() throws SQLException {
        ArrayList<InfoComandes> array = new ArrayList<InfoComandes>();
        ResultSet rs = st.executeQuery("SELECT usuari, COUNT(nom_plat) AS num, SUM(!servit) AS sum, hora, data FROM Comanda\n" +
                "GROUP BY usuari \n" +
                "ORDER BY data, hora ASC; ");       //Mirar si l'ordre va be

        while (rs.next()) {
            InfoComandes comanda = new InfoComandes();
            comanda.setUsuari(rs.getString("usuari"));
            comanda.setTotal_plats(rs.getInt("num"));
            comanda.setPlatsPendents(rs.getInt("sum"));
            comanda.setHora(rs.getTime("hora"));
            comanda.setDate(rs.getDate("data"));
            array.add(comanda);

        }
        return array;
    }




    public ArrayList<Integer> getMinusArray(ArrayList array1, ArrayList array2) {

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

