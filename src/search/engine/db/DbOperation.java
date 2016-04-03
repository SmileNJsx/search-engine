package search.engine.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbOperation
{
    private static Connection conn = DbConnector.getconnection();
    
    //public String insert = "insert into search_engine(id,url,tag,hashcode) values(?,?,?,?)";
    //public String select = "select url from search_engine where id=1";
    //public String delete = "delete from search_engine where id=1";
    //public String update = "update search_engine set tag=1 where id=1"; 
    
    public static void insert(String sql) throws SQLException
    {
       PreparedStatement preparedStatement = conn.prepareStatement(sql);
       
       preparedStatement.setLong(1,0);
       preparedStatement.setString(2,"http://www.njau.edu.cn");
       preparedStatement.setInt(3, 0);
       preparedStatement.setInt(4,0);
       
       int num = preparedStatement.executeUpdate();
       
       System.out.println(num + "rows records have been influence");
       
    }
    
    public static void update(String sql) throws SQLException
    {
    	PreparedStatement preparedStatement = conn.prepareStatement(sql);
    	
    	int i = preparedStatement.executeUpdate();
    	
    	System.out.println("result :"+ i);
    	
    }
    
    public static String select(String sql) throws SQLException
    {
    	String url=null;
    	
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        
        preparedStatement.setInt(1, 1);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next())
        {
		    url = resultSet.getString("url");
            
        }
        return url;
    }
    
    public static void delete(String sql) throws SQLException
    {
    	PreparedStatement preparedStatement = conn.prepareStatement(sql);
    	
    	int i = preparedStatement.executeUpdate();
    	
    	System.out.println("result"+i);
  
    }
}
