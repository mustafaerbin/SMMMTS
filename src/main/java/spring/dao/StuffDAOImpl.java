package spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.NonTechnicalStuff;
import spring.model.Stuff;
import spring.model.TechnicalStuff;
import spring.util.StuffPOJO;
/**
 *  Created by sinan.ulgen on 10/05/2017.
 */
@Repository
public class StuffDAOImpl implements StuffDAO{

	@Autowired
	private SessionFactory sessionFactory;

	
	public void addAnyStuff(Stuff stuff) 
	{
		getSessionFactory().getCurrentSession().save(stuff);
	}
	
	public void addAnyStuff(TechnicalStuff technicalStuff)
	{
		getSessionFactory().getCurrentSession().save(technicalStuff);
	}
	
	public void addAnyStuff(NonTechnicalStuff nonTechnicalStuff)
	{
		getSessionFactory().getCurrentSession().save(nonTechnicalStuff);
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<StuffPOJO> getStuffList() {
		List<StuffPOJO> pojoList = new ArrayList<StuffPOJO>();
		List<Object[]> list = getSessionFactory().getCurrentSession()
				.createQuery("Select s.sid,s.name,s.educationLevel,s.expertise,s.qualification from Stuff s").list();
		for (int i = 0; i < list.size(); i++) {
			StuffPOJO stuffPOJO = new StuffPOJO();

			if (list.get(i)[0] != null) {
				stuffPOJO.setId((Integer) list.get(i)[0]);
			}

			if (list.get(i)[1] != null) {
				stuffPOJO.setName((String) list.get(i)[1]);
			}
			if (list.get(i)[2] != null) {
				stuffPOJO.setEducationLevel((String) list.get(i)[2]);
			}
			if (list.get(i)[3] != null) {
				stuffPOJO.setExpertise((String) list.get(i)[3]);
			}
			if (list.get(i)[4] != null) {
				stuffPOJO.setQualification(((String) list.get(i)[4]));
			}
			pojoList.add(stuffPOJO);

		}
		return pojoList;
	}
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
}
