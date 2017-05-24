package spring.dao;

import java.util.List;

import spring.model.NonTechnicalStuff;
import spring.model.Stuff;
import spring.model.TechnicalStuff;
import spring.util.StuffPOJO;
/**
 *  Created by sinan.ulgen on 10/05/2017.
 */
public interface StuffDAO {

	public void addAnyStuff(Stuff stuff);

	public void addAnyStuff(TechnicalStuff technicalStuff);

	public void addAnyStuff(NonTechnicalStuff nonTechnicalStuff);

	public List<StuffPOJO> getStuffList();
}
