package test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.telegram.telegrambots.api.objects.Update;

class AutoAdverts extends Thread {
	 Update update;
	 private static String url = "jdbc:mysql://localhost:3306/chkpzbot";
		private static String user = "user";
		private static String password = "admin";
		private static java.sql.Connection con;
		private static Statement stmt;
		private static ResultSet rs;
		String query;
	 AutoAdverts() {
		
		}
	@Override
	public void run() // Этот метод будет выполняться в побочном потоке
	{
	
		String message_text = null;
		int b = 0;
		long chatid;
		
		while (true) {
			try {
				Thread.sleep(500);
		
			 message_text = "";
			
			
			
			query = "select Advert,BeginDate,EndDate,SLEEP(3) from Adverts where NoShowen = '1' ";
			try{
			con = DriverManager.getConnection(url, user, password);
			
			// getting Statement object to execute query
			stmt = con.createStatement();

			// executing SELECT query
			rs = stmt.executeQuery(query);
			
			
		
			String one;
			String two;
			while (rs.next()) {
				one = rs.getString(2);
				one.substring(0, one.length()-2);
				two = rs.getString(3);
				two.substring(0, two.length()-2);
				message_text = message_text + String.format(one+ "%n" + '"' + rs.getString(1) + '"'
						+ "%n" + "Актуально до: " + two+ "%n" + "%n");
		
			}
			
			}
			catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
				
			} finally {
				// close connection ,stmt and resultset here
			       try {
		                
		                if (rs != null) {
		                    rs.close();
		                }
		                
		                if (stmt != null) {
		                    stmt.close();
		                }
		                
		                if (con != null) {
		                    con.close();
		                }
			       } catch (SQLException ex) {
		             
		                    }
			}
			if(message_text != "" && message_text != null){
				
					query = "select chatid from botmembers where (registered = '1') and (subscrube = '1')";
					String[][] resul = new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "user", "admin").Select(query, 1);
					
				
					
					 b = 0;
					while (b < resul.length) {
						chatid = Integer.parseInt(resul[b][0]);
						new ShowAdverts().SendMessage(chatid,message_text);
					b++;
					}
					
					
					query = "UPDATE Adverts SET NoShowen = '0' ";
					new Mysqlcon("jdbc:mysql://localhost:3306/chkpzbot", "user", "admin").Other(query);
			
			
			}
			
			
			}
		 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}