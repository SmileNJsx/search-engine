package search.engine.spider;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	
	Document doc;
	
	HtmlParser(String url) throws IOException{
		doc=Jsoup.connect(url).get();
	}
	
	public HashMap<String,String> getParserMedia(){
		HashMap<String,String> mapMedia=new HashMap<>();
		Elements media=doc.select("[src]");
		for(Element src:media){
			mapMedia.put(src.tagName(),src.attr("abs:src"));
		}
		return mapMedia;
	}
	
	public HashMap<String,String> getParserImports(){
		HashMap<String,String> mapImports=new HashMap<>();
		Elements imports=doc.select("link[href]");
		for(Element link:imports){
			mapImports.put(link.attr("rel"),link.attr("abs:href"));
		}
		return mapImports;
	}
	
	public HashMap<String,String> getParserKeylinks(){
		HashMap<String,String> mapLinks=new HashMap<>();
		Elements links=doc.select("a[href]");
		for(Element link:links){
			mapLinks.put(trim(link.text(),35),link.attr("abs:href"));
		}
		return mapLinks;
	}
	
	private static String trim(String s,int width){
		
		if(s.length()>width){
			return s.substring(0,width-1)+".";
		}else{
			return s;
		}
	}
	
	//TODO DELETE
	public static void main(String args[]) throws IOException{
	}
}
