package search.engine.spider;

public class SpiderContainer
{
    public static final int MAX_SPIDER_COUNT = 3;
    
    public void retrivePage()
    {
    	for(int i=0;i<MAX_SPIDER_COUNT;i++)
    	{
    		SpiderThread spiderThread = new SpiderThread();
    		Thread thread = new Thread(spiderThread);
    		thread.start();
    	}
    }
}
