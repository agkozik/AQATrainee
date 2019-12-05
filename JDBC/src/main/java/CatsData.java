import java.sql.*;

/*
1) Взять класс сущности и унаследоваться от нее, создать класс Кот с неким набором характеристик
2) Через джава сгенерить таблицу для этой сущности
3) Сделать 3 инсерта (будет 3 записи в таблице)
4) Сделать 2 апдейта в транзакции - результат коммит
5) Сделать 2 апдейта в транзакции - результат ролбек
6) Очистить базу
7) Каждую операцию с базой выводить на экран
 */
public class CatsData {
    static Connection conn;
    static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        JdbcSeparateMethods jdbcSeparateMethods = new JdbcSeparateMethods();
        conn = jdbcSeparateMethods.initConnection();
        createTableWithCats(conn);
        cleanTable(conn);
        showAllInfoFromCatsTable();
        addCatToTableCats(conn, "Tom", "Yellow", 3);
        showAllInfoFromCatsTable();
        addCatToTableCats(conn, "Boris", "Black", 1);
        showAllInfoFromCatsTable();
        addCatToTableCats(conn, "Alica", "White", 5);
        showAllInfoFromCatsTable();
        updateTransactionCommitAsResultAutoCommitTrueBatch(conn, 1, 3);
        showAllInfoFromCatsTable();
        updateTransactionCommitAsResultAutoCommitFalse(conn, 1, 3);
        showAllInfoFromCatsTable();
        updateTransactionRollbackAsResult(conn, 1, 3);
        showAllInfoFromCatsTable();
    }

    public static void createTableWithCats(Connection conn) {
        try (PreparedStatement prS = conn.prepareStatement("CREATE TABLE IF NOT EXISTS cats(id SERIAL PRIMARY KEY,"
                + "name varchar(50), color varchar(50),age bigint);")) {
            prS.execute();
        } catch (SQLException e) {
            System.out.println("Table create Error: " + e.getMessage());
        }
    }

    public static void showAllInfoFromCatsTable() {
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = null;
            System.out.println("\n->Table cats begin:_______________________________");
            try {
                resultSet = statement.executeQuery("select * FROM viewcats");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String color = resultSet.getString(3);
                    int age = resultSet.getInt(4);
                    System.out.println("Id= " + id + "\tName= " + name + "\tColor= " + color + "\tAge= " + age);
                }
                System.out.println("<-Table cats end__________________________________");
            } catch (SQLException ex) {
                System.err.println("SQLException Message" + ex.getMessage());
                System.err.println("SQLStatus" + ex.getSQLState());
                System.err.println("SQLError" + ex.getErrorCode());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCatToTableCats(Connection conn, String name, String color, int age) {
        try {
            String sql = "INSERT INTO cats ( name, color, age) Values (?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, color);
            preparedStatement.setInt(3, age);
            int rows = preparedStatement.executeUpdate();
            System.out.printf("\n%d rows added to table", rows);
        } catch (SQLException e) {
            System.out.println("Table INSERT Error: " + e.getMessage());
        }
    }

    public static void updateTransactionCommitAsResultAutoCommitFalse(Connection conn, int idItemToUpdate1, int idItemToUpdate2) {
        try (Statement statement = conn.createStatement()) {
            String sql1 = "UPDATE cats SET name='Matroskin1_Updated', color='Striped:White-Blue',age=5 WHERE id=1";
            String sql3 = "UPDATE cats SET name='Matroskin3_Updated', color='Striped:White-Blue',age=4 WHERE id=3";
            conn.setAutoCommit(false);
            statement.executeUpdate(sql1);
            System.out.printf("\nrow %d Updated in table", idItemToUpdate1);
            statement.executeUpdate(sql3);
            System.out.printf("\nrow %d Updated in table", idItemToUpdate2);
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Table UPDATE Error: " + e.getMessage());
        }
    }

    public static void updateTransactionCommitAsResultAutoCommitTrueBatch(Connection conn, int idItemToUpdate1, int idItemToUpdate2) {
        try (Statement statement = conn.createStatement()) {
            String sql1 = "UPDATE cats SET name='Matroskin1_BATCH_Updated', color='Striped:White-Blue',age=5 WHERE id=1";
            String sql3 = "UPDATE cats SET name='Matroskin1_BATCH_Updated', color='Striped:White-Blue',age=4 WHERE id=3";
            conn.setAutoCommit(true);
            statement.addBatch(sql1);
            statement.addBatch(sql3);
            statement.executeBatch();
            System.out.println("\nTable Updated!");
        } catch (SQLException e) {
            System.out.println("Table UPDATE Error: " + e.getMessage());
        }
    }

    public static void updateTransactionRollbackAsResult(Connection conn, int idItemToUpdate1, int idItemToUpdate2) {
        try (Statement statement = conn.createStatement()) {
            String sql1 = "UPDATE cats SET name='Rollback', color='White-Blue',age=5 WHERE id=1";
            String sql3 = "UPDATE cats SET name='Rollback', color='White-Blue',age=4 WHERE id=3";
            conn.setAutoCommit(false);
            statement.executeUpdate(sql1);
            System.out.printf("\nrow %d Updated in table", idItemToUpdate1);
            statement.executeUpdate(sql3);
            System.out.printf("\nrow %d Updated in table", idItemToUpdate2);
            conn.rollback();
            System.out.println("\nRollback changes!");
        } catch (SQLException e) {
            System.out.println("Table UPDATE Error: " + e.getMessage());
        }
    }

    public static void simpleUpdate(Connection conn, String name, String color, int age, int idItemToUpdate) {
        try {
            String sql = "UPDATE cats SET name=?, color=?,age=? WHERE id=?";
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, color);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, idItemToUpdate);
            int rows = preparedStatement.executeUpdate();
            System.out.printf("%d rows added to table", rows);
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Table INSERT Error: " + e.getMessage());
        }
    }

    public static void cleanTable(Connection conn) {
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery("select clearTable();");
                while (resultSet.next()) {
                    String count = resultSet.getString(1);
                    System.out.println("Таблица очищена. Количество удаленных строк = " + count);
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