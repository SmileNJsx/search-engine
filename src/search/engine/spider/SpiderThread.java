package search.engine.spider;

import java.util.HashMap;
import java.util.Map.Entry;

import search.engine.db.DbOperation;
import search.engine.index.IndexFiles;
import search.engine.io.FilesIO;
import search.engine.util.Url;

public class SpiderThread implements Runnable
{
	//READ FROM MYSQL
	
	public static String title;
	
    public static String url;
    
    public static int tag;
    
    public static int hashcode;
    
    public static String content;
    
    //TODO READ FROM CONFIG
    public static String filePath = "D:\\search_engine";
    
    public HashMap<String,String> keyLinks; 
   
    @Override
    public void run()
    {
        // TODO Auto-generated method stub
    	try 
    	{
    		
    		String selectSql_1 = "select url from t_url where id="+Url.id;
        	
        	url = DbOperation.select(selectSql_1);
    		
    		//WRITE FILES
			content = RetrivePage.getContent(url);
			
			FilesIO.writeFile(filePath+"\\"+Url.id+".txt", content);
			
			@SuppressWarnings("unused")
			IndexFiles indexFiles = new IndexFiles(false);
			
			
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
				
				String selectSql_2 = "select url from t_url where hashcode="+hashcode;
				
				if(DbOperation.select(selectSql_2)==null)
				{
					String insertSql = "insert ignore into  t_url(title,url,hashcode) values("+"'"+title+"'"+","+"'"+url+"'"+","+hashcode+")";
					
					DbOperation.insert(insertSql);	
				}
			}
			
			String updateSql = "update t_url set tag=1 where id="+Url.id;
			
			DbOperation.update(updateSql);
			
		}catch(Exception e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		Url.id++;
    }
    
}
