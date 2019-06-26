package com.fs.connection.mysql.jdbc.bean;

import lombok.Data;
import org.hibernate.annotations.Parent;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author cnstonefang@gmail.com
 */
@Entity
@Table(name = "post")
@Data
@Embeddable
public class Post {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String category;
    private String contents;
}
