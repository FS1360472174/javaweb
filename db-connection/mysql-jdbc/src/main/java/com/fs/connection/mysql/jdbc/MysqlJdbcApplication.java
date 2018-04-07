package com.fs.connection.mysql.jdbc;

import com.fs.connection.mysql.jdbc.bean.Post;
import com.fs.connection.mysql.jdbc.bean.User;
import com.fs.connection.mysql.jdbc.service.HibernateService;
import com.fs.connection.mysql.jdbc.service.JdbcService;
import com.fs.connection.mysql.jdbc.service.JdbcTemplateService;
import com.fs.connection.mysql.jdbc.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.fs.connection.mysql.jdbc")
public class MysqlJdbcApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplateService mJdbcTemplateService;

	@Autowired
	private MybatisService mybatisService;

	@Autowired
	private HibernateService hibernateService;

	@Autowired
	private JdbcService mJdbcService;

	public static void main(String[] args) {
		SpringApplication.run(MysqlJdbcApplication.class, args);
	}

	@Override
	public void run(final String... strings) throws Exception {
		long userId = 1L;
		User originUser1 = generateUserInfo(userId);
		mJdbcService.insertUser(originUser1);
		User user = mJdbcService.getUserInfo(userId);
		System.out.print(user.toString());
		hibernateService.insertUser(generateUserInfo(2L));
		User user2 = hibernateService.getUserInfo(2L);
		System.out.print(user2.toString());
		//testJdbc();
		// mJdbcTemplateService.queryById(1);
		// mybatisService.queryByUserId(1);
		//hibernateService.queryByUserId(1);
	}

	private User generateUserInfo(long userId) {
		User user = new User();
		user.setUserId(userId);
		user.setName("name");
		List<Post> post = new ArrayList<>();
		Post post1 = new Post();
		post1.setId(1L);
		post1.setContents("content1");
		post1.setCategory("cate1");
		post1.setTitle("title1");
		post1.setUserId(userId);
		post.add(post1);
		Post post2 = new Post();
		post2.setId(2L);
		post2.setContents("content1");
		post2.setCategory("cate1");
		post2.setTitle("title1");
		post2.setUserId(userId);
		post.add(post2);
		user.setPosts(post);
		return user;
	}
	public void testJdbc() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/test?"
				+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		String sql = "select *from post where user_id=1";
		ResultSet resultSet = stmt.executeQuery(sql);
		List<Post> posts = new ArrayList<>();
		while (resultSet.next()) {
			Post post = new Post();
			post.setId(resultSet.getLong("id"));
			post.setTitle(resultSet.getString("title"));
			post.setContents(resultSet.getString("contents"));
			posts.add(post);
		}
		resultSet.close();
		stmt.close();
		conn.close();
	}
}
