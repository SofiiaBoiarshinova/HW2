import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class jdbc_h2 extends jdbc{
    Connection connection = null;
    public jdbc_h2(String url, String user, String pass) throws SQLException {
        connection = DriverManager.getConnection(url, user, pass);
    }

}
