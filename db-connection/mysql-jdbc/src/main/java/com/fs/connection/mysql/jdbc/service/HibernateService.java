package com.fs.connection.mysql.jdbc.service;

import com.fs.connection.mysql.jdbc.bean.Post;
import com.fs.connection.mysql.jdbc.bean.User;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cnstonefang@gmail.com
 */
@Service
public class HibernateService  implements  DbService{
    @Autowired
    @Qualifier("hibernateSessionFactory")
    private SessionFactory mSessionFactory;

    public void queryByUserId(long userId) {
        Session session = mSessionFactory.openSession();
        String hql = "FROM  Post2  WHERE userid = :user_id";
        Query query = session.createQuery(hql);
        Transaction tx = session.beginTransaction();
        query.setParameter("user_id",userId);
        //Post2 post = (Post2)session.get(Post2.class, userId);
        List result = query.list();
        session.close();
    }

    @Override
    public void insertUser(final User user) throws SQLException, ClassNotFoundException {
        Session session = mSessionFactory.openSession();
        session.getTransaction().begin();
        Post post = user.getPosts().get(0);
        post.setId(null);
        session.persist(post);
        session.persist(user);
        session.getTransaction().commit();

    }

    @Override
    public User getUserInfo(final long userId) throws ClassNotFoundException, SQLException {
        Session session = mSessionFactory.openSession();

        User user = session.get(User.class, userId);
        // Query query = session.createQuery( "SELECT u FROM User u WHERE u.userId=:userId" );
    	// query.setParameter( "userId", userId);
		// User user = (User) query.uniqueResult();

		//User user = (User) session.createCriteria( User.class )
		//		.add( Restrictions.eq( "userId", userId ) )
		//		.uniqueResult();
        Hibernate.initialize( user.getPosts());
        return user;
    }
}
