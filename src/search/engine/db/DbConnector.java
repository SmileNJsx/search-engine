package search.engine.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnector
{
    private static final String URL = "jdbc:mysql://192.168.56.17:3306/search_engine";
    private static final String USER_NAME = "root";
    private static final String PASS_WORD = "Sx930622";
    private static final String Driver_Name = "com.mysql.jdbc.Driver";
    
    public static Connection getconnection()
    {
        Connection conn = null;
        
        try
        {
            Class.forName(Driver_Name);
            
            conn = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
            
            @SuppressWarnings("unused")
            Statement statement = conn.createStatement();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void close(Statement statement,Connection conn)
    {
        try
        {
            if(statement != null)
            {
                statement.close();
            }
            if(conn != null)
            {
                conn.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
