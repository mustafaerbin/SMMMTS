package mmts.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mmts.model.Aidat;
import mmts.model.Employee;

import java.util.List;

/**
 * Created by mustafa.erbin on 20/05/2017.
 */
@Repository
public class AidatDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addAidat(Aidat aidat) {
		getSessionFactory().getCurrentSession().save(aidat);
	}

	public void deleteAidat(Aidat aidat) {
		getSessionFactory().getCurrentSession().delete(aidat);
	}

	public void updateAidat(Aidat aidat) {
		getSessionFactory().getCurrentSession().update(aidat);
	}

	@SuppressWarnings("rawtypes")
	public Employee getEmployeeById(int id) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Aidat where id=?").setParameter(0, id)
				.list();
		return (Employee) list.get(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Employee> getEmployeeByActiveStatus(boolean active) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Aidat where active=?")
				.setParameter(0, active).list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Employee> getEmployeeByBloodType(String bloodType) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Aidat where bloodType=? and active=true")
				.setParameter(0, bloodType).list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Employee> getEmployeeByAssignmentGrup(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Aidat where assignmentGrup.grupId=? and active=true").setParameter(0, id).list();
		return list;
	}

	@SuppressWarnings({ "unchecked" })
	public List<Aidat> getTumAidatlar() {

		List<Aidat> list = getSessionFactory().getCurrentSession().createCriteria(Aidat.class).list();

		
		
		return list;
	}

	@SuppressWarnings({ "unchecked" })
	public List<Aidat> getTumAidatlarWithUser(Long user_id) {

		List<Aidat> list = getSessionFactory().getCurrentSession().createQuery("from Aidat where MukellefId=?")
				.setParameter(0, user_id).list();
		
		 return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Aidat> getDeletedEmployees() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Aidat where active=? and eMail != 'sistem'").setParameter(0, false).list();
		return list;
	}

}
