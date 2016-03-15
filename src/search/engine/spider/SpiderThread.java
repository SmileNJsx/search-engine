package search.engine.spider;

import java.util.HashMap;

public class SpiderThread implements Runnable
{

    public static String url;
    public String content;
    public HashMap<String,String> keyLinks; 
    
    SpiderThread(String url){
        SpiderThread.url=url;//TODO 从db获得url
    }
    
    
    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        
    }
    
}
