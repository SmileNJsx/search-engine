package search.engine.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import search.engine.util.Config;

public class DbConnector
{
    private static String URL;
    private static String USER_NAME;
    private static String PASS_WORD;
    private static String DRIVER_NAME;
    
    Config config = Config.getInstance();
    
    @SuppressWarnings("unused")
    private static Statement STATEMENT = null; 
    
    public static Connection getconnection()
    {	
    	URL = Config.URL;
    	USER_NAME = Config.USER_NAME;
    	PASS_WORD = Config.PASS_WORD;
    	DRIVER_NAME = Config.DRIVER_NAME;
    	
        Connection conn = null;
        
        try
        {
            Class.forName(DRIVER_NAME);
            
            conn = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
            
            STATEMENT = conn.createStatement();
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
