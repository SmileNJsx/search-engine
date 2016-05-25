package search.engine.main;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.lucene.queryparser.classic.ParseException;

import search.engine.db.SearchDb;

public class Main {
	//TODO MOD SPIDER & INDEX & SEARCH
	
	public static void main(String args[]) throws IOException, ParseException, SQLException
	{
		/*
		
		SpiderContainer spiderContainer = new SpiderContainer();
		spiderContainer.retrivePageAndHtmlParser();
		*/
		
		/*IndexFiles index = new IndexFiles(true);
		SearchFiles search = new SearchFiles();*/
		
		SearchDb.search("南京农业大学");
		
	}
}
