package com.fs.connection.mysql.jdbc;

import com.fs.connection.mysql.jdbc.bean.Post;
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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication(scanBasePackages = "com.fs.connection.mysql.jdbc")
public class MysqlJdbcApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplateService mJdbcTemplateService;

    @Autowired
    private MybatisService mybatisService;

    public static void main(final String[] args) {
        SpringApplication.run(MysqlJdbcApplication.class, args);
    }

    @Override
    public void run(final String... strings) throws Exception {
        testJdbc();
        //mJdbcTemplateService.queryById(1);
        //mybatisService.queryByUserId(1);
    }

    public void testJdbc() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        final String url = "jdbc:mysql://rds-kujiale-alpha.qunhequnhe.com:3306/fenshua123?"
                +
                "user=kujialealpha2017&password=zcJ4sNaVJ63l9lKk&useUnicode=true&characterEncoding=UTF8";
        conn = DriverManager.getConnection(url);
        final Statement stmt = conn.createStatement();
        final String sql = "select *from fstest where userId=1";
        final ResultSet resultSet = stmt.executeQuery(sql);
        final List<Post> posts = new ArrayList<>();
        while (resultSet.next()) {
            final Post post = new Post();
            post.setId(resultSet.getLong("id"));
            post.setTitle(resultSet.getString("title"));
            post.setContents(resultSet.getString("contents"));
            posts.add(post);
        }
        stmt.close();
        conn.close();
    }

}
