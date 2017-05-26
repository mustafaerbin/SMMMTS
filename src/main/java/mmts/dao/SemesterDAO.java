package mmts.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mmts.model.Semester;
/**
 *  Created by sinan.ulgen on 10/05/2017.
 */
@Repository
public class SemesterDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addSemester(Semester semester)
	{
		getSessionFactory().getCurrentSession().save(semester);
	}
	
	public void deleteSemester(Semester semester)
	{
		getSessionFactory().getCurrentSession().delete(semester);
	}
	
	public void updateSemester(Semester semester)
	{
		getSessionFactory().getCurrentSession().update(semester);
	}
	
	@SuppressWarnings("unchecked")
	public List<Semester> getSemesterList()
	{
		List<Semester> semesterList = getSessionFactory().getCurrentSession().createCriteria(Semester.class).list();
		return semesterList;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
