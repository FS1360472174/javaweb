package com.fs.connection.mysql.jdbc.service;

import com.fs.connection.mysql.jdbc.bean.Post;
import com.fs.connection.mysql.jdbc.bean.User;

import java.sql.SQLException;

/**
 * @author cnstonefang@gmail.com
 */
public interface DbService {
    void insertUser(User user) throws SQLException, ClassNotFoundException;

    User getUserInfo(long userId) throws ClassNotFoundException, SQLException;
}
