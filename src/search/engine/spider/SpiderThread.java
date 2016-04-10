package search.engine.spider;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;

import search.engine.db.DbConnector;
import search.engine.db.DbOperation;
import search.engine.io.FilesIO;

public class SpiderThread implements Runnable
{
	//READ FROM MYSQL
	
	public static long ID = 1;
	
	public static String title;
	
    public static String url;
    
    public static int tag;
    
    public static int hashcode;
    
    public static String content;
    
    //TODO READ FROM CONFIG
    public static String filePath = "D:\\search_engine";
    
    public HashMap<String,String> keyLinks; 
    
    SpiderThread(Connection conn) throws SQLException
    {
    	
    	String sql = "select url from urlQueue where id="+ID;
    	
    	//conn =DbConnector.getconnection();
    	//Connection conn = DbConnector.getconnection();
    	//Statement statement = conn.createStatement();
    	
    	url = DbOperation.select(sql);
    	
    	//DbConnector.close(statement,conn);
    }
    
    @Override
    public void run()
    {
        // TODO Auto-generated method stub
    	try 
    	{
    		//Connection conn =DbConnector.getconnection();
    		
    		//WRITE FILES
			content = RetrivePage.getContent(url);
			
			FilesIO.writeFile(filePath+"\\"+ID+".txt", content);
			
			
			//GET URL AND TITLE
			HtmlParser htmlParser = new HtmlParser(url);
			
			keyLinks = htmlParser.getParserKeylinks();
			
			for(Entry<String,String> entry:keyLinks.entrySet())
			{
				String title;
				String url;
				int hashcode;
				
				title = entry.getKey();
				url = entry.getValue();
				hashcode = url.hashCode();
				
				String sql = "insert ignore into  urlQueue(title,url,hashcode) values("+"'"+title+"'"+","+"'"+url+"'"+","+hashcode+")";
				
				DbOperation.insert(sql);
				
				SpiderThread spiderThread = new SpiderThread(SpiderContainer.conn);
				Thread thread = new Thread(spiderThread);
				thread.start();
				
			}	
			
		}catch(Exception e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	synchronized(this)
    	{
    		ID++;
    	}
    }
    
}
