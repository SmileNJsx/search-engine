package search.engine.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class IndexFiles {
	public static void main(String args[])
	{
		String indexPath = "D:\\indexPath";  //1
		
		String[] DocID;
		
		IndexWriter writer;
		
		writer = new IndexWriter(indexPath,new SimpleAnalyzer(),false);  //2 
		
		for(int i=1;i<DocID.length;i++)
		{
			InputStream is = new FileInputStream(DocID[i]);  //3
			
			Document doc = new Document();
			
			doc.add(Field.UnIndexed("path",DocID[i]));  //4
			
			doc.add(Field.Text("body",(Reader)new InputStreamReader(is)));  //5
			
			writer.addDocument(doc);  //6
			
			is.close();
		}
	}
}
