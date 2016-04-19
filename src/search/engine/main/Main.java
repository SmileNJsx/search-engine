package search.engine.main;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import search.engine.index.IndexFiles;
import search.engine.search.SearchFiles;

public class Main {
	//TODO MOD SPIDER & INDEX & SEARCH
	@SuppressWarnings("unused")
	public static void main(String args[]) throws IOException, ParseException
	{
		/*
		SpiderContainer spiderContainer = new SpiderContainer();
		spiderContainer.retrivePageAndHtmlParser();
		*/
		
		IndexFiles index = new IndexFiles();
		SearchFiles search = new SearchFiles();
		
	}
}
