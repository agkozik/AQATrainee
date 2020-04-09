import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class JdbsPostgres {
    //Задаем параметры подключения к БД
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String BASENAME = "testbase";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "root";
    private static final String COMMANDSFILE = "C:\\AND\\AQATrainee\\JDBC\\src\\main\\resources\\commands.sql";

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        //getConnection();
        //getStatement();
        executePreparedStatement();
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
                    int id = resultSet.getInt(1);//обращение по индексу: 1 это первое поле (колонка) и т.д
                    int region_id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    String crt_date = resultSet.getString(4);
                    System.out.println("ID=" + id + " Region=" + region_id + " Name=" + name + " Date=" + crt_date);
                }
            } catch (SQLException ex) {
                System.err.println("SQLException Message" + ex.getMessage());
                System.err.println("SQLStatus" + ex.getSQLState());
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

    //Подготовленный запрос PreparedStatement
    public static void executePreparedStatement() throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL + BASENAME, LOGIN, PASSWORD);
             PreparedStatement ps = connection
                     .prepareStatement("INSERT INTO transact (id, age, firstname, lastname, phone) VALUES (?,?,?,?,?)")) {
            ps.setInt(1, 3);
            ps.setInt(2, 60);
            ps.setString(3, "Galya");
            ps.setString(4, "Lukashen");
            ps.setString(5, "+375297777773");
            ps.executeUpdate();

            ResultSet resultSet = null;
            try (PreparedStatement pr = connection.prepareStatement("SELECT * FROM transact")) {
                resultSet = pr.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    int age = resultSet.getInt(2);
                    String fName = resultSet.getString(3);
                    String lName = resultSet.getString(4);
                    String phone = resultSet.getString(5);
                    System.out.println("id =" + id + " age =" + age + " fName =" + fName + " lName =" + lName + " Phone =" + phone);
                }
            }
            CallableStatement cs = null;
            try {
                cs = connection.prepareCall("{ ? = call showCountOfTransact() }");
                cs.registerOutParameter(1, Types.BIGINT);
                cs.execute();
                System.out.println("Количество строк в таблице: "+cs.getInt(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                cs.close();
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
    }}