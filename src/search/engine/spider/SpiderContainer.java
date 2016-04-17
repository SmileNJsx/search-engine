package search.engine.spider;

import java.sql.Connection;
import java.sql.SQLException;
import search.engine.db.DbConnector;

public class SpiderContainer
{
	//TODO READ FROM CONFIG
    public static final int MAX_SPIDER_COUNT = 16;
    
    public static Connection conn = DbConnector.getconnection();
    
    public void retrivePageAndHtmlParser() throws SQLException, InterruptedException
    {
    	SpiderThread spiderThread = new SpiderThread();
    	
    	while(true)
    	{
    		Thread thread = new Thread(spiderThread);
        	
        	thread.start();
        	
        	thread.join();
    	}
    }
}
