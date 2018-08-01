package test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysqlcon {
	private static String url;
	private static String user;
	private static String password;
	private static java.sql.Connection con;
	private static Statement stmt;
	private static ResultSet rs;
	String com;
	int beta;

	Mysqlcon(String url1, String user1, String password1) {
		url = url1;
		user = user1;
		password = password1;
	}

	public String[][] Select(String query, int st) {//Select
		String[][] result = null;
		com = query;
		beta = st;
		int x = 0;
		try {
			// opening database connection to MySQL server

			con = DriverManager.getConnection(url, user, password);

			// getting Statement object to execute query
			stmt = con.createStatement();

			// executing SELECT query
			rs = stmt.executeQuery(query);
			if (rs != null) {//Мучался 5 дней :)

				if (!con.isClosed()) {

					while (rs.next()) {
						x++;
					}
					if(x==0){
						return null;
					}
				}
			}
		} catch (SQLException sqlEx) {
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

		try {
			// opening database connection to MySQL server

			con = DriverManager.getConnection(url, user, password);

			// getting Statement object to execute query
			stmt = con.createStatement();

			// executing SELECT query
			rs = stmt.executeQuery(query);
			if (rs != null) {
				result = new String[x][st];
				x = 0;
				int y;
				int v;

				while (rs.next()) {

					v = 1;
					y = 0;
					while ((y < st) && (v <= st)) {
						rs.clearWarnings();
						result[x][y] = rs.getString(v);
						v++;
						y++;
					}
					x++;
				}

			}
		} catch (SQLException sqlEx) {
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

		return result;
	}

	public void Other(String query) {
		try {
			// opening database connection to MySQL server
			con = DriverManager.getConnection(url, user, password);

			// getting Statement object to execute query
			stmt = con.createStatement();

			// executing SELECT query
			stmt.executeUpdate(query);

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			// close connection ,stmt and resultset here
			try {
				con.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				stmt.close();
			} catch (SQLException se) {
				/* can't do anything */ }

		}
	}
}
