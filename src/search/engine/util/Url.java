package search.engine.util;

public class Url
{
    private long id;
    private String url;
    private int tag;
    
    public long getId()
    {
        return id;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public int getTag()
    {
        return tag;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public void setTag(int tag)
    {
        this.tag = tag;
    }
}
