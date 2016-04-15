package search.engine.spider;

import java.sql.Connection;
import java.sql.SQLException;

import search.engine.db.DbConnector;

public class SpiderContainer
{
	//TODO READ FROM CONFIG
    public static final int MAX_SPIDER_COUNT = 1;
    
    public static Connection conn = DbConnector.getconnection();
    
    public void retrivePageAndHtmlParser() throws SQLException
    {
    	for(int i=0;i<MAX_SPIDER_COUNT;i++)
    	{
    		
    		SpiderThread spiderThread = new SpiderThread();
    		Thread thread = new Thread(spiderThread);
    		thread.start();
    	}
    }
}
