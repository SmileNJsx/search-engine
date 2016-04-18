package search.engine.search;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class SearchFiles
{
	public String index = "index";
	public String field = "contents";
	public String queries = null;
	public int repeat = 0;
	public boolean raw = false;
	public String queryString = null;
	public int hitPerPage = 10;
	
	public SearchFiles() throws  IOException
	{
		IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer();
		
		BufferedReader in = null;
		
		if(queries != null)
		{
			in = Files.newBufferedReader(Paths.get(queries),StandardCharsets.UTF_8);
		}
		else
		{
			in = new BufferedReader(new InputStreamReader(System.in,StandardCharsets.UTF_8));
		}
		
		QueryParser parser = new QueryParser(field,analyzer);
		
		while(true)
		{
			if(queries == null && queryString == null)
			{
				System.out.println("Enter query :");
			}
			
			String line = queryString != null ? queryString : in.readLine();
			
			line = line.trim();
		}
	}
	
	public static void doPagingSearch(BufferedReader in,IndexSearcher searcher,Query query,int hitsPerPage,boolean raw,boolean interactive) throws IOException
	{
		TopDocs results = searcher.search(query, 5*hitsPerPage);
		ScoreDoc[] hits = results.scoreDocs;
		
		int numTotalHits = results.totalHits;
		System.out.println(numTotalHits+"total matching documnets");
		
		int start = 0;
		int end = Math.min(numTotalHits, hitsPerPage);
		
		while(true)
		{
			if(end>hits.length)
			{
				System.out.println("Only results "+hits.length+"of"+numTotalHits+"total matching documnets collected.");
				System.out.println("Collect more (y/n)?");
				String line = in.readLine();
				
				if(line.length() == 0 || line.charAt(0) == 'n')
				{
					break;
				}
				
				hits = searcher.search(query, numTotalHits).scoreDocs;
			}
			
			end = Math.min(hits.length, start+hitsPerPage);
			
			for(int i=start;i<end;i++)
			{
				if(raw)
				{
					System.out.println("doc ="+hits[i].doc+"score ="+hits[i].score);
					continue;
				}
				
				Document doc = searcher.doc(hits[i].doc);
				String path = doc.get("path");
				
				if(path != null)
				{
					System.out.println((i+1)+"."+path);
					String title = doc.get("title");
					if(title != null)
					{
						System.out.println("Title: "+doc.get("title"));
					}
				}
				else
				{
					System.out.println((i+1)+"."+"No path for this document");
				}
			}
			
			if(!interactive || end == 0)
			{
				break;
			}
			
			if(numTotalHits >= end)
			{
				boolean quit = false;
				
				while(true)
				{
					System.out.println("Press");
					if(start-hitsPerPage >= 0)
					{
						System.out.println("(p)revious page,");
					}
					
					if(start+hitsPerPage < numTotalHits)
					{
						System.out.println("(n)ext page");
					}
					
					System.out.println("(q)uit or enter number to jump to a page.");
					
					String line = in.readLine();
					
					if(line.length() == 0 || line.charAt(0) == 'q')
					{
						quit = true;
						break;
					}
		
					if(line.charAt(0) == 'p')
					{
						start = Math.min(0, start-hitsPerPage);
						break;
					}
					else if(line.charAt(0) == 'n')
					{
						if(start+hitsPerPage < numTotalHits)
						{
							start+=hitsPerPage;
						}
						break;
					}
					else
					{
						int page = Integer.parseInt(line);
						
						if((page - 1)*hitsPerPage < numTotalHits)
						{
							start = (page - 1) * hitsPerPage;
							break;
						}
						else
						{
							System.out.println("No such page");
						}
					}
				}
				
				if(quit)
				{
					break;
				}
				
				end = Math.min(numTotalHits, start+hitsPerPage);
			}
		}
	}
}

