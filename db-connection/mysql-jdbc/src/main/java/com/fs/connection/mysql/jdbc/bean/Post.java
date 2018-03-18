package com.fs.connection.mysql.jdbc.bean;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.util.Date;
import java.util.List;

/**
 * @author cnstonefang@gmail.com
 */
@Data
public class Post {
    private Long id;
    private Long userId;
    private String title;
    private String category;
    private String contents;
}
