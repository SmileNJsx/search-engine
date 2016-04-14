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
	public static String URL;
	public static String USER_NAME;
	public static String PASS_WORD;
	public static String DRIVER_NAME;
	
	public static Config getInstance()
	{
		if(instance == null)
		{
			instance = new Config();
		}
		
		return instance;
	}
	
	private Config()
	{
		this.initProperties();
		this.initDbUser();
	}
	
	
	
	private String getConfig(String key)
	{
		return properties.getProperty(key);
	}
	
	private void errExit(String key)
	{
		Log.Exit("Invalid format of key :"+key+"! Please check the Config File!");
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
		
		key = "URL";
		value = getConfig(key);
		if(value == null || value.length() == 0)
		{
			errExit(key);
		}
		URL = value;
		
		key = "USER_NAME";
		value = getConfig(key);
		if(value == null || value.length() == 0)
		{
			errExit(key);
		}
		USER_NAME =value;
		
		key ="PASS_WORD";
		value = getConfig(key);
		if(value == null || value.length() == 0)
		{
			errExit(key);
		}
		PASS_WORD = value;
		
		key = "DRIVER_NAME";
		value = getConfig(key);
		if(value == null || value.length() == 0)
		{
			errExit(key);
		}
		DRIVER_NAME = value;
	}
	
}
