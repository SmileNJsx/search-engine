package search.engine.util;

public class Url
{
    private long id;
    
    private String title;
    
    private String url;
    
    private int tag;

    private int hashcode;
    
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
    
    public void setId(long id)
    {
        this.id = id;
    }
    
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
