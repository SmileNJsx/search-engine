package search.engine.main;

import search.engine.spider.SpiderContainer;

public class Main {
	public static void main(String args[]){//
		SpiderContainer spiderContainer = new SpiderContainer();
		spiderContainer.retrivePage();
	}
}
