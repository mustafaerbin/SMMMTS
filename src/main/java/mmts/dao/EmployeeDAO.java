package mmts.dao;
 
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mmts.model.Employee;
import mmts.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Repository
public class EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEmployee(Employee employee) {
		getSessionFactory().getCurrentSession().save(employee);
	}

	public void deleteEmployee(Employee employee) {
		getSessionFactory().getCurrentSession().delete(employee);
	}

	public void updateCustomer(Employee employee) {

		User user = employee.getUser();
		getSessionFactory().getCurrentSession().update(user);
		getSessionFactory().getCurrentSession().update(employee);
	}

	@SuppressWarnings("rawtypes")
	public Employee getEmployeeById(Long id) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Employee where id=?").setParameter(0, id)
				.list();
		return (Employee) list.get(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Employee> getEmployeeByEducation(String education) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Employee where employeeEducation.description=?").setParameter(0, education).list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Employee> getEmployeeByActiveStatus(boolean active) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Employee where active=?")
				.setParameter(0, active).list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Employee> getEmployeeByBloodType(String bloodType) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Employee where bloodType=? and active=true").setParameter(0, bloodType).list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Employee> getEmployeeByAssignmentGrup(Long id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Employee where assignmentGrup.grupId=? and active=true").setParameter(0, id).list();
		return list;
	}

	@SuppressWarnings({"rawtypes", "unchecked" })
	public List<Employee> getEmployees() {

		//List<Employee> list = getSessionFactory().getCurrentSession().createQuery("from Employee where employeeId != 'id'");.list();
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Employee where employeeId != ?").setParameter(0, 1L).list();
		 return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Employee> getDeletedEmployees() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Employee where active=? and eMail != 'sistem'").setParameter(0, false).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public Employee getEmployeeByEmail(String userName) {
		List<Employee> employee = new ArrayList<Employee>();
		String queryString = "from Employee where eMail = :eMail";
		Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
		query.setString("eMail", userName);
		employee.addAll(query.list());
		return employee.get(0);
	}

}
