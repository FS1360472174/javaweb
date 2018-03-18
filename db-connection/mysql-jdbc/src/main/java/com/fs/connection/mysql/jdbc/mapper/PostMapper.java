package com.fs.connection.mysql.jdbc.mapper;

import com.fs.connection.mysql.jdbc.bean.Post;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cnstonefang@gmail.com
 */
@Mapper
public interface PostMapper {
    Post findPostByUserId(long userId);
}
