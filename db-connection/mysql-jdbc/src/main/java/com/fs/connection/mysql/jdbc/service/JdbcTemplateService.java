package com.fs.connection.mysql.jdbc.service;

import com.fs.connection.mysql.jdbc.bean.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author cnstonefang@gmail.com
 */
@Service
public class JdbcTemplateService {
    @Autowired
    private JdbcTemplate mJdbcTemplate;

    public void queryById(long userId) {
        String sql = "select * from post where user_id = " + userId;
        List<Post> result =mJdbcTemplate.query(sql, new PostRowMapper());
    }

    class PostRowMapper implements RowMapper<Post> {
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setTitle(rs.getString("title"));
            post.setContents(rs.getString("contents"));
            return post;
        }

    }
}
