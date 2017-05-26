package mmts.dao;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mmts.model.Student;
/**
 *  Created by sinan.ulgen on 10/05/2017.
 */
@Repository
public class StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void addStudent(Student student)
	{
		getSessionFactory().getCurrentSession().save(student);
	}
	
	public void updateStudent(Student student)
	{
		getSessionFactory().getCurrentSession().update(student);
	}
	
	public void deleteStudent(Student student)
	{
		getSessionFactory().getCurrentSession().delete(student);
	}
	
	public List<Student> getStudentById(int id)
	{
		@SuppressWarnings("unchecked")
		List<Student> list= getSessionFactory().getCurrentSession().getNamedQuery("find by id").setParameter("id",id).list();
		return  list;
	}
	@SuppressWarnings("unchecked")
	public List<Student> getStudentList()
	{
		List<Student>list = getSessionFactory().getCurrentSession().createQuery("from Student").list();
		return list;
	}
	
	
	
}
