package spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.Lecture;
/**
 *  Created by sinan.ulgen on 10/05/2017.
 */
@Repository
public class LectureDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addLecture(Lecture lecture)
	{
		getSessionFactory().getCurrentSession().save(lecture);
	}
	public void deleteLecture(Lecture lecture)
	{
		getSessionFactory().getCurrentSession().delete(lecture);
	}
	
	public void updateLecture(Lecture lecture)
	{
		getSessionFactory().getCurrentSession().update(lecture);
	}
	
	@SuppressWarnings("unchecked")
	public List<Lecture> getLectureList()
	{
		List<Lecture> lectureList=getSessionFactory().getCurrentSession().createCriteria(Lecture.class).list();
		return lectureList;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
