package Model;

import java.sql.*;

public class BDD {
    private Connection con;
    private Statement st;

    public BDD() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/LSRestaurant";

        String username = "root";
        String password = "alex";

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

}

