package search.engine.util;

public class Url
{
    public static long id=1;
    
    public String title;
    
    public String url;
    
    public int tag;

    public int hashcode;
    
    public long getId()
    {
        return id;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public String getTitle()
    {
    	return title;
    }
    
    public int getTag()
    {
        return tag;
    }
    
    public int getHashCode()
    {
    	return hashcode;
    }
    
   /* public void setId(long id)
    {
        this.id = id;
    } */
    
    public void setTitle(String title)
    {
    	this.title = title;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public void setTag(int tag)
    {
        this.tag = tag;
    }
    
    public void setHashCode(int hashcode)
    {
    	this.hashcode = hashcode;
    }
}
