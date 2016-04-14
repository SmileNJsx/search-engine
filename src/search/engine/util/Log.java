package search.engine.util;

import java.util.Date;

public class Log {
	static boolean DEBUG = true;
	
	public static void Debug(String msg)
	{
		if(!DEBUG)
		{
			return;
		}
		
		System.out.println((new Date()).toString()+"DEBUG--:"+msg);
	}
	
	public static void Access(String msg)
	{
		System.out.println((new Date()).toString()+"ACCESS--:"+msg);
	}
	
	public static void Error(String msg)
	{
		System.err.println((new Date()).toString()+"ERROE--:"+msg);
	}
	
	public static void Exit(String msg)
	{
		System.err.println((new Date()).toString()+"EXITING--:"+msg);
		System.exit(0);
	}
}
