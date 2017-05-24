package spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Role;

/**
 * Created by mustafa.erbin on 23/05/2017.
 */
@Repository
public class RoleDaoMust {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings({ "rawtypes" })
    public Role getRoleById(Long id) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Role where id=?").setParameter(0, id)
				.list();
		return (Role) list.get(0);
    }

}
