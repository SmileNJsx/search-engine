package search.engine.spider;

import java.util.HashMap;

import search.engine.io.FilesIO;

public class SpiderThread implements Runnable
{

    public static String url = "http://www.njau.edu.cn";
    
    public static String content;
    
    public static String filePath = "D:\\search_engine";
    
    public HashMap<String,String> keyLinks; 
    
   /* SpiderThread(String url)
    {
        
    	SpiderThread.url=url;//TODO 从db获得url
    }
    */
    
    @Override
    public void run()
    {
        // TODO Auto-generated method stub
    	try 
    	{
			content = RetrivePage.getContent(url);
			
			FilesIO.writeFile(filePath+"\\"+RetrivePage.ID+".txt", content);
			
		} catch (Exception e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
    }
    
}
