package search.engine.example;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.queryparser.surround.parser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

public class Search {
	public static void main(String args[])
	{
		String indexPath ="D://indexPath";  //1
		
		String[] WordID;
		
		queryString = WordID[];  //2
		
		Searcher searcher = new IndexSearcher(indexPath);  //3
		
		Query query = QueryParser.parse(queryString,"KeyWord",new SimpleAnalyzer());  //4
		
		Hits hits = searcher.search(query);  //5
		
		for(int i=0;i<WordID.length;i++)
		{
			getResult(hits.doc(i).get("path"));
		}
	}
}
