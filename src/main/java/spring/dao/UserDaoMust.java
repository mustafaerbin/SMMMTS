package spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import spring.model.User;

/**
 * Created by mustafa.erbin on 23/05/2017.
 */
@Repository
public class UserDaoMust {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addUser(User user) {
		getSessionFactory().getCurrentSession().save(user);
	}
	
	@SuppressWarnings("rawtypes")
	public User getUserById(Long id) {
		List list = getSessionFactory().getCurrentSession().createQuery("from User where id=?").setParameter(0, id)
				.list();
		return (User) list.get(0);
	}

}
