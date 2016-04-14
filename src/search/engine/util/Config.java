package search.engine.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	
	private static Config instance = new Config();
	
	private Properties properties = null;
	
	private String configPath;
	
	//DbUser
	 public String url;
	 public String user_name;
	 public String pass_word;
	 public String driver_name;
	
	private Config()
	{
		
	}
	
	private void initProperties()
	{
		properties = new Properties();
		
		String jarPath = Config.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		
		File jarFile = new File(jarPath);
		
		configPath = jarFile.getParent();
		
		try
		{
			InputStream in = new FileInputStream(new File(configPath+File.separator+"config.properties"));
			properties.load(in);
			
			if(properties == null)
			{
				Log.Exit("Config.properties cant't be empty !");
			}
		}
		catch(IOException e)
		{
			Log.Exit("Config load failed,Please check whether Config is exist!");
		}
	}
	
	private void initDbUser()
	{
		String key , value;
	}
	
	
	
	
	
	public static Config getInstance()
	{
		if(instance == null)
		{
			instance = new Config();
		}
		
		return instance;
	}
}
