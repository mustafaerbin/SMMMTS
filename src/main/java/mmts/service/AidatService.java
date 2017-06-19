package mmts.service;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mmts.dao.AidatDAO;
import mmts.model.Aidat;

/**
 * Created by mustafa.erbin on 20/05/2017.
 */
@Service("AidatService")
@Transactional(readOnly = false)
public class AidatService {

	@Autowired
	AidatDAO aidatDAO;

	@ManagedProperty(value = "#{UserContextService}")
	UserContextService userContextService;

	/*** Annotation of applying method level Spring Security ***/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional(readOnly = false)
	public void addAidat(Aidat aidat) {
		getAidatDAO().addAidat(aidat);
	}

	@Transactional(readOnly = false)
	public void deleteAidat(Aidat aidat) {
		getAidatDAO().deleteAidat(aidat);
	}

	@Transactional(readOnly = false)
	public void updateAidat(Aidat aidat) {
		getAidatDAO().updateAidat(aidat);
	}

	public List<Aidat> getAidatlar() {

		// Long employeId = userContextService.getEmployeeId(); // login olan
		// kullanýcý id'si
		return getAidatDAO().getTumAidatlar();

	}

	public List<Aidat> getAidatlarWithUser(Long user_id) {
		// Long employeId = userContextService.getEmployeeId(); // login olan
		// kullanýcý id'si
		return getAidatDAO().getTumAidatlarWithUser(user_id);
	}
	
	public List<Aidat> getAidatWithEmployeeName(String name){
		return null;
	}

	public AidatDAO getAidatDAO() {
		return aidatDAO;
	}

	public void setAidatDAO(AidatDAO aidatDAO) {
		this.aidatDAO = aidatDAO;
	}

}
