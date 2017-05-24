package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.StuffDAO;
import spring.model.NonTechnicalStuff;
import spring.model.Stuff;
import spring.model.TechnicalStuff;
import spring.util.StuffPOJO;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Service("StuffService")
@Transactional(readOnly = true)
public class StuffServiceImpl implements StuffService
{
	@Autowired
	private StuffDAO stuffDAO;
	@Transactional(readOnly = false)
	public void addAnyStuff(Stuff stuff)
	{
		getStuffDAO().addAnyStuff(stuff);
	}
	@Transactional(readOnly = false)
	public void addAnyStuff(TechnicalStuff stuff)
	{
		getStuffDAO().addAnyStuff(stuff);
	}
	@Transactional(readOnly = false)
	public void addAnyStuff(NonTechnicalStuff stuff)
	{
		getStuffDAO().addAnyStuff(stuff);
	}
	
	public List<StuffPOJO> getStuffList()
	{
		return getStuffDAO().getStuffList();
	}
	
	public StuffDAO getStuffDAO() {
		return stuffDAO;
	}

	public void setStuffDAO(StuffDAO stuffDAO) {
		this.stuffDAO = stuffDAO;
	}

	
	
	
}
