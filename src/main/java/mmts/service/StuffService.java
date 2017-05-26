package mmts.service;

import java.util.List;

import mmts.model.NonTechnicalStuff;
import mmts.model.Stuff;
import mmts.model.TechnicalStuff;
import mmts.util.StuffPOJO;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */
public interface StuffService {

	public void addAnyStuff(Stuff stuff);
	public void addAnyStuff(TechnicalStuff technicalStuff);
	public void addAnyStuff(NonTechnicalStuff nonTechnicalStuff);
	public List<StuffPOJO> getStuffList();
	
}
