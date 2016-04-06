package search.engine.spider;

import java.sql.SQLException;

public class SpiderContainer
{
	//TODO READ FROM CONFIG
    public static final int MAX_SPIDER_COUNT = 1;
    
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
