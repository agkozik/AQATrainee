import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class JdbsPostgres {
    //Задаем параметры подключения к БД
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String BASENAME = "testbase";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "root";
    private static final String COMMANDSFILE = "C:\\DZ\\AQATrainee\\JDBC\\src\\main\\resources\\commands.sql";

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        //getConnection();
        getStatement();
    }

    public static void getConnection() throws ClassNotFoundException {
        //регистрация драйвера, используя стат. анализатор Class.forName
        Class.forName("org.postgresql.Driver");
        //Создаем объект класса Connection через DriverManager, который перебирает все зарегистрированные драйверы
        //которые соответствуют указанным параметрам: URL + BASENAME, LOGIN, PASSWORD
        //найдя драйвер, класс вызывает метод getConnection, который возвращает объект Connection
        try (Connection conn = DriverManager.getConnection(URL + BASENAME, LOGIN, PASSWORD)) {
            System.out.println("Connection to PostgresDB is successful installed");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
            System.out.println(e.getMessage());
        }
    }

    public static void getStatement() throws ClassNotFoundException, SQLException, IOException {

        Class.forName("org.postgresql.Driver");

        try (Connection conn = DriverManager.getConnection(URL + BASENAME, LOGIN, PASSWORD);
             BufferedReader sqlFile = new BufferedReader(new FileReader(COMMANDSFILE));
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery("SELECT * FROM city");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    int region_id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    String crt_date = resultSet.getString(4);
                    System.out.println("ID=" + id + " Region=" + region_id + " Name=" + name + " Date=" + crt_date);
                }
            } catch (SQLException ex) {
                System.err.println("SQLException Message" + ex.getMessage());
                //Статус
                System.err.println("SQLStatus" + ex.getSQLState());
                //номер Ошибки
                System.err.println("SQLEror" + ex.getErrorCode());
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                } else {
                    System.out.println("Ошибка чтения с Базы данных");
                }
            }
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}