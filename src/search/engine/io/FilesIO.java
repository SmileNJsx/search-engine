package search.engine.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesIO
{
	public static String readFile(String filePath) throws IOException
	{
		
		Path path = Paths.get(filePath);
		
	    byte[] encoded = Files.readAllBytes(path);
	    
	    return new String(encoded , "UTF-8");
	}
	
	public static void writeFile(String filePath , String content) throws IOException
	{
		Path path = Paths.get(filePath);
		
		byte[] contentByte = content.getBytes("UTF-8");
		
		Files.write(path, contentByte);
	}
	
	//TODO
	public static void main(String[] args) throws IOException
	{
	}
}
