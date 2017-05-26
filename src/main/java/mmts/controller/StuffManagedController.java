package mmts.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

import mmts.model.NonTechnicalStuff;
import mmts.model.Stuff;
import mmts.model.TechnicalStuff;
import mmts.service.StuffService;
import mmts.util.StuffPOJO;
/**
 *  Created by mustafa.erbin on 10/05/2017.
 */
@ManagedBean(name = "stuffMB")
@ViewScoped
public class StuffManagedController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{StuffService}")
	StuffService serviceImpl;

	private List<StuffPOJO> stuffList;
	private List<StuffPOJO> filteredStuff;
	private StuffPOJO selectedStuff;
	private String name;

	private String educationLevel;
	private String qualification;
	private String expertise;

	private boolean enableButton = true;

	public void addStuff() {
		Stuff stuff = new Stuff();
		stuff.setName(name);
		getServiceImpl().addAnyStuff(stuff);
	}

	public void addTechnicalStuff() {
		try {
			TechnicalStuff technicalStuff = new TechnicalStuff();
			technicalStuff.setName(name);
			technicalStuff.setEducationLevel(educationLevel);
			technicalStuff.setQualification(qualification);
			getServiceImpl().addAnyStuff(technicalStuff);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addNonTechnicalStuff() {
		try {
			NonTechnicalStuff nonTechnicalStuff = new NonTechnicalStuff();
			nonTechnicalStuff.setName(name);
			nonTechnicalStuff.setExpertise(expertise);
			getServiceImpl().addAnyStuff(nonTechnicalStuff);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<StuffPOJO> getStuffList() {
		if (stuffList == null) {
			stuffList = new ArrayList<StuffPOJO>();
			stuffList.addAll(getServiceImpl().getStuffList());
		}
		return stuffList;
	}

	public void refreshPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String refreshpage = fc.getViewRoot().getViewId();
		ViewHandler ViewH = fc.getApplication().getViewHandler();
		UIViewRoot UIV = ViewH.createView(fc, refreshpage);
		UIV.setViewId(refreshpage);
		fc.setViewRoot(UIV);
	}

	public void onRowSelect(SelectEvent event) {

		setEnableButton(false);
	}

	public StuffService getServiceImpl() {
		return serviceImpl;
	}

	public void setServiceImpl(StuffService serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public void setStuffList(List<StuffPOJO> stuffList) {
		this.stuffList = stuffList;
	}

	public List<StuffPOJO> getFilteredStuff() {
		return filteredStuff;
	}

	public void setFilteredStuff(List<StuffPOJO> filteredStuff) {
		this.filteredStuff = filteredStuff;
	}

	public StuffPOJO getSelectedStuff() {
		return selectedStuff;
	}

	public void setSelectedStuff(StuffPOJO selectedStuff) {
		this.selectedStuff = selectedStuff;
	}

	public boolean isEnableButton() {
		return enableButton;
	}

	public void setEnableButton(boolean enableButton) {
		this.enableButton = enableButton;
	}

}
