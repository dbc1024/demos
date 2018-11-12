package com.ectrip.base.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class testSqlite {

	public void setUp() throws Exception {
	}
	
	public static void main(String[] args) {
		testSqlite  ts = new testSqlite();
		ts.testSqlite();
	}

	public void testSqlite() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:y");
			Statement stat = conn.createStatement();
			stat.executeUpdate("drop table if exists people;");
			stat.executeUpdate("create table people (name, occupation);");
			PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?);");

			prep.setString(1, "李进");
			prep.setString(2, "123456");
			prep.addBatch();
			prep.setString(1, "李四");
			prep.setString(2, "abcdef");
			prep.addBatch();
			prep.setString(1, "王五");
			prep.setString(2, "123456");
			prep.addBatch();

			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);

			ResultSet rs = stat.executeQuery("select * from people;");
			while (rs.next()) {
				System.out.println("name = " + rs.getString("name"));
				System.out.println("job = " + rs.getString("occupation"));
			}
			rs.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
