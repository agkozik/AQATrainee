import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class JdbcSeparateMethods {
    static Connection conn;

    public static void main(String[] args) {
//        conn=initConnection();
//        createTableIfNotExist(conn);
//        addToBase(conn);
//        showCountRegionsAndCitiesInCountry(conn);
//        top5countriesWithTheBiggestAmountOfCities(conn);
//        top5countriesWithTheBiggestAmountOfRegions(conn);
    }

    public Connection initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Postgresql.Driver failed..." + e.getMessage());
        }
        Properties properties = new Properties();
        try (InputStream in = new FileInputStream("JDBC\\src\\main\\resources\\db.properties")) {
            properties.load(in);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла " + e.getMessage());
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Connection failed..." + e.getMessage());
        }
        return null;
    }

    public static void createTableIfNotExist(Connection conn) {
        try (PreparedStatement prItems =
                     conn.prepareStatement("CREATE TABLE IF NOT EXISTS items(id serial primary key,"
                             + "name varchar(200), description varchar(500), create_time timestamp);")) {
            prItems.execute();
        } catch (SQLException e) {
            System.out.println("Table create Error: " + e.getMessage());
        }
    }

    public static void addToBase(Connection conn) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input product id: ");
            int id = scanner.nextInt();

            System.out.print("Input product name: ");
            String name = scanner.next();

            System.out.print("Input product description: ");
            String desc = scanner.next();

            try {
                String sql = "INSERT INTO Items (id, name, description) Values (?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, desc);
                int rows = preparedStatement.executeUpdate();
                System.out.printf("%d rows added", rows);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public static void top5countriesWithTheBiggestAmountOfRegions(Connection conn) {
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery("SELECT country.name,\n" +
                        "Count(Distinct region.name)\n" +
                        "From country\n" +
                        "INNER JOIN region ON country.id = region.country_id\n" +
                        "GROUP BY country.name\n" +
                        "ORDER BY  Count(Distinct region.name) desc\n" +
                        "Limit 5");
                while (resultSet.next()) {
                    String name = resultSet.getString(1);
                    int regions = resultSet.getInt(2);
                    System.out.println( "Name= " + name + " Region= " + regions);
                }
            } catch (SQLException ex) {
                System.err.println("SQLException Message" + ex.getMessage());
                System.err.println("SQLStatus" + ex.getSQLState());
                System.err.println("SQLError" + ex.getErrorCode());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void top5countriesWithTheBiggestAmountOfCities(Connection conn) {
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery("SELECT country.name,\n" +
                        "Count (Distinct city.name) cities\n" +
                        "From country\n" +
                        "LEFT JOIN region ON country.id=region.country_id\n" +
                        "LEFT JOIN city ON city.region_id=region.id\n" +
                        "GROUP BY country.name\n" +
                        "ORDER BY cities desc\n" +
                        "limit 5");
                while (resultSet.next()) {
                    String name = resultSet.getString(1);
                    int cities = resultSet.getInt(2);
                    System.out.println( "Name= " + name + " Cities= "+cities);
                }
            } catch (SQLException ex) {
                System.err.println("SQLException Message" + ex.getMessage());
                System.err.println("SQLStatus" + ex.getSQLState());
                System.err.println("SQLError" + ex.getErrorCode());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showCountRegionsAndCitiesInCountry(Connection conn) {
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery("Select country.name,\n" +
                        "Count (Distinct region.name) Regions,\n" +
                        "Count (Distinct city.name) Cities\n" +
                        "From country\n" +
                        "Left Join region on country.id=region.country_id\n" +
                        "Left Join city on region.id=city.region_id\n" +
                        "Group By (country.name)\n" +
                        "Order by (country.name)");
                while (resultSet.next()) {
                    String name = resultSet.getString(1);
                    int regions = resultSet.getInt(2);
                    int cities = resultSet.getInt(3);
                    System.out.println( "Name= " + name + " Region= " + regions+" Cities= "+cities);
                }
            } catch (SQLException ex) {
                System.err.println("SQLException Message" + ex.getMessage());
                System.err.println("SQLStatus" + ex.getSQLState());
                System.err.println("SQLError" + ex.getErrorCode());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}