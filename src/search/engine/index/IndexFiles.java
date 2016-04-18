package search.engine.index;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import search.engine.util.Log;

public class IndexFiles
{
      /*
       * [-index INDEX_PATH]
       * [-docs DOCS_PATH]
       * [-update]
       * */
    
    String indexPath = "D:\\search_engine_INDEX_PATH";
    
    String docsPath = "D:\\search_engine";
    
    boolean create = false;
    
    final Path docDir = Paths.get(docsPath);
    
    IndexFiles()
    {
    	if(!Files.isReadable(docDir))
    	{
    		Log.Error("Document directory : "+docDir.toAbsolutePath()+"does not exist or is not readable, please check the path");
    		System.exit(1);
    	}
    	
    	try
    	{
    		System.out.println("Indexing to directory "+indexPath+"...");
    		
    		Directory dir = FSDirectory.open(Paths.get(indexPath));
    		Analyzer analyzer = new StandardAnalyzer();
    		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
    		
    		if(create)
    		{
    			iwc.setOpenMode(OpenMode.CREATE);
    		}
    		else
    		{
    			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
    		}
    		
    		IndexWriter writer = new IndexWriter(dir,iwc);
    		IndexFiles.indexDocs(writer,docDir);
    		
    	}catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    static void indexDocs(final IndexWriter writer,Path path) throws IOException
    {
    	if(!Files.isDirectory(path))
    	{
    		Files.walkFileTree(path, new SimpleFileVisitor<Path>()
    		{
    			@Override
    			public FileVisitResult visitFile(Path file,BasicFileAttributes attrs) throws IOException
    			{
    				try
    				{
    					indexDoc(writer,file,attrs.lastModifiedTime().toMillis());
    				}catch(IOException ignore)
    				{
    					
    				}
    				return FileVisitResult.CONTINUE;
    			}
    		}
    				);
    	}
    	else
    	{
    		indexDoc(writer,path,Files.getLastModifiedTime(path).toMillis());
    	}
    }
    
    static void indexDoc(IndexWriter writer,Path file,long lastModified) throws IOException
    {
    	try(InputStream stream = Files.newInputStream(file))
    	{
    		Document doc = new Document();
    		Field pathField = new StringField("path",file.toString(),Field.Store.YES);
    		doc.add(pathField);
    		
    		doc.add(new LongField("modified",lastModified,Field.Store.NO));
    		
    		if(writer.getConfig().getOpenMode() == OpenMode.CREATE)
    		{
    			System.out.println("adding "+file);
    			writer.addDocument(doc);
    		}
    		else
    		{
    			System.out.println("updating "+file);
    			writer.updateDocument(new Term("path", file.toString()),doc);
    		}
    	}
    }
}
