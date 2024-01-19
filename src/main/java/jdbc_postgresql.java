import java.sql.*;
import java.util.concurrent.TimeUnit;

public class jdbc_postgresql extends jdbc{
    Connection connection = null;
    public jdbc_postgresql(String url, String user, String pass) throws SQLException, InterruptedException {
        //Подключение драйвера к PostgreSQL
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        //Подключение к БД

        connection = DriverManager.getConnection(url, user, pass);
    }

}
