package Model;

import java.sql.*;

public class BDD {
    private Connection con;
    private Statement st;

    public BDD() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/LSRestaurant?useSSL=false";

        String username = "root";
        String password = "marcmarc12";

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
        st.executeUpdate("INSERT INTO Taula(num_cadires) " +
                "VALUES ("+i +")");
    }
    public void insereixPlat(String nom_plat, int preu, int unitats_disponibles, int  unitats_gastades) throws SQLException {

        st.executeUpdate("INSERT INTO Plat(nom_plat, preu, unitats_disponibles, unitats_gastades) " +
                "VALUES ('"+ nom_plat + "',"+preu +","+ unitats_disponibles+","+ unitats_gastades+")");


    }
    public void queriePlat(String querie) {
        try {
            ResultSet rss = st.executeQuery(querie);

            while (rss.next()) {
                String lastName = "NOM: " + rss.getString("nom_plat");
                lastName = lastName + "  Preu: " + rss.getString("preu");
                lastName = lastName + "  Unitats disponibles: " + rss.getString("unitats_disponibles");
                lastName = lastName + "  Unitats gastades: " + rss.getString("unitats_gastades");
                System.out.println(lastName );
            }
        }catch (Exception e){

        }
    }
    public void updatePlat(int id){

        try {
            String str ="Update Plat set unitats_gastades = ?, unitats_disponibles= ? where id_plat = "+id;
            PreparedStatement ps = con.prepareStatement(str);

            ResultSet rss = null;
            ResultSet rs = null;
            rs = st.executeQuery("SELECT unitats_gastades FROM Plat WHERE id_plat = "+id);
            if (rs.next() ){
                //System.out.println(rss.getString("unitats_gastades"));
                ps.setInt(1, rs.getInt("unitats_gastades") + 1);

            }
            rss = st.executeQuery("SELECT unitats_disponibles FROM Plat WHERE id_plat = "+id);
            if (rss.next() ) {

                ps.setInt(2, rss.getInt("unitats_disponibles") - 1);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROORR");
            e.printStackTrace();
        }

    }

}

