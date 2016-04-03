package search.engine.main;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import search.engine.db.DbConnector;
import search.engine.db.DbOperation;
//import search.engine.spider.SpiderContainer;

public class Main {
	public static void main(String args[]) throws SQLException{//
		//SpiderContainer spiderContainer = new SpiderContainer();
		//spiderContainer.retrivePage();
		String sql = "insert ignore into urlQueue(id,url,tag) values(?,?,?)";
		//String update = "update urlQueue set tag=1 where id=1";
		
		Connection conn = DbConnector.getconnection();
		Statement statement = conn.createStatement();
		DbOperation.insert(sql);
		//DbOperation.update(update);
		DbConnector.close(statement,conn);
	}
}
