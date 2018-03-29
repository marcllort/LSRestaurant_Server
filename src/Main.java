import java.sql.*;

public class Main {


    //prova

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/LSRestaurant";

        String username = "root";
        String password = "alex";

        System.out.println("Connecting database...");


        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                System.out.println(rs.getString(3));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }    }

    //funciona

}
