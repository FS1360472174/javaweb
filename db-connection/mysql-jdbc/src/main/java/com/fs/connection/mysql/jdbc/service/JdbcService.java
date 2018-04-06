package com.fs.connection.mysql.jdbc.service;

/**
 * @author cnstonefang@gmail.com
 */

import com.fs.connection.mysql.jdbc.bean.Post;
import com.fs.connection.mysql.jdbc.bean.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcService implements DbService{

    @Override
    public void insertUser(final User user) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO user VALUES(?,?)";
        stmt =  conn.prepareStatement(sql);
        stmt.setLong(1, user.getUserId());
        stmt.setString(2, user.getName());
        stmt.executeUpdate();
        stmt.close();

        for (Post post : user.getPosts()) {
            stmt = conn.prepareStatement( "INSERT INTO post VALUES(?,?,?,?,?)" );
            stmt.setLong(1, post.getId());
            stmt.setString( 2, post.getCategory() );
            stmt.setString( 3, post.getContents() );
            stmt.setString(4, post.getTitle());
            stmt.setLong(5, post.getUserId());
            stmt.executeUpdate();
            stmt.close();
        }
    }

    @Override
    public User getUserInfo(final long userId) throws ClassNotFoundException, SQLException {
        User user = new User();
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String userSql = "select userId,name from user where userId = ?" ;
        stmt= conn.prepareStatement(userSql);
        stmt.setLong(1,(int)userId);
        rs = stmt.executeQuery();
        rs.next();
        user.setUserId(rs.getLong(1));
        user.setName(rs.getString(2));
        String postSql = "select *from post where userId=?";
        stmt = conn.prepareStatement(postSql);
        stmt.setLong(1,userId);
        rs = stmt.executeQuery();
        List<Post> posts = new ArrayList<>();
        while (rs.next()) {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setTitle(rs.getString("title"));
            post.setContents(rs.getString("contents"));
            posts.add(post);
        }
        user.setPosts(posts);
        stmt.close();
        conn.close();
        return user;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
}
