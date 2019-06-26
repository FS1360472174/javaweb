package com.fs.connection.mysql.jdbc.service;

import com.fs.connection.mysql.jdbc.bean.Post;
import com.fs.connection.mysql.jdbc.mapper.PostMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cnstonefang@gmail.com
 */
@Service
public class MybatisService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    public void queryByUserId(long userId) {
        SqlSession session = sqlSessionFactory.openSession();
        PostMapper postMapper = session.getMapper(PostMapper.class);
        Post post = postMapper.findPostByUserId(userId);
        session.close();
    }
}
