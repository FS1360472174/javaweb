package com.fs.connection.mysql.jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class MysqlJdbcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MysqlJdbcApplication.class, args);
	}

	@Override
	public void run(final String... strings) throws Exception {
		testJdbc();
	}

	public void testJdbc() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/test?"
				+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		String sql = "create table test(name char(20))";
		int result = stmt.executeUpdate(sql);
	}
}
