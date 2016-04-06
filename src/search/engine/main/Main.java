package search.engine.main;

import java.sql.SQLException;

import search.engine.spider.SpiderContainer;

public class Main {
	public static void main(String args[]) throws SQLException{
		SpiderContainer spiderContainer = new SpiderContainer();
		spiderContainer.retrivePageAndHtmlParser();
	}
}
