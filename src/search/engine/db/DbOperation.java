package search.engine.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbOperation
{
    private static Connection conn = DbConnector.getconnection();
    
    public static void insert(String sql) throws SQLException
    {
       PreparedStatement preparedStatement = conn.prepareStatement(sql);
       
       preparedStatement.setLong(1,0);
       preparedStatement.setString(2,"http://www.njau.edu.cn");
       preparedStatement.setInt(3, 0);
       
       int num = preparedStatement.executeUpdate();
       
       System.out.println(num + "rows records have been influence");
       
       DbConnector.close(preparedStatement, conn);
    }
    
    public static void update(){}
    
    public static void select(String sql) throws SQLException
    {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        
        preparedStatement.setInt(1, 1);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next())
        {
            String url = resultSet.getString("url");
        }
    }
    
    public static void delete(){}
}
