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
            DatabaseMetaData m = connection.getMetaData();
            ResultSet tables = m.getTables(connection.getCatalog(), null, "TAB_%", null);
            for (int i = 1; i < tables.getMetaData().getColumnCount(); i++) {
                System.out.println("table = " + tables.getMetaData().getTableName(i));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }    }

    //funciona

}
