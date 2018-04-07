package com.fs.connection.mysql.jdbc.bean;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

/**
 * @author cnstonefang@gmail.com
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private Long userId;
    private String name;
    @OneToMany
    @Fetch(FetchMode.SUBSELECT)
    private List<Post> posts;
}
