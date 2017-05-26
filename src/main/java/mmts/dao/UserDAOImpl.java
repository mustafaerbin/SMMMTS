package mmts.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mmts.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Repository
public class UserDAOImpl implements UserDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SessionFactory sessionFactory;

	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public User getUser(String login) {
		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.employee.eMail =:username");
		query.setParameter("username", login);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	public void addUser(User user) {
		getSessionFactory().getCurrentSession().save(user);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
