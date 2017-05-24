package spring.controller;

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

import spring.model.Semester;
import spring.service.SemesterService;
/**
 *  Created by sinan.ulgen on 10/05/2017.
 */
@ManagedBean(name = "semesterMB")
@ViewScoped
public class SemesterManagedController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{SemesterService}")
	SemesterService semesterService;

	private String name;
	private List<Semester> semesterList;
	private List<Semester> filteredSemesterList;
	private Semester selectedSemester;
	private boolean enabled = true;

	public List<Semester> getSemesterList() {
		if (semesterList == null) {
			semesterList = new ArrayList<Semester>();
			semesterList.addAll(getSemesterService().getSemesterList());
		}
		return semesterList;
	}

	public void addSemester() {
		try {
			Semester sm = new Semester();
			sm.setName(name);
			getSemesterService().addSemester(sm);
			refreshPage();

		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateSemester() {
		try {
			if (selectedSemester != null) {
				getSemesterService().updateSemester(selectedSemester);
				refreshPage();
			}
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteSemester() {
		try {
			if (selectedSemester != null) {
				getSemesterService().deleteSemester(selectedSemester);
				refreshPage();
			}
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
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
		enabled = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSemesterList(List<Semester> semesterList) {
		this.semesterList = semesterList;
	}

	public List<Semester> getFilteredSemesterList() {
		return filteredSemesterList;
	}

	public void setFilteredSemesterList(List<Semester> filteredSemesterList) {
		this.filteredSemesterList = filteredSemesterList;
	}

	public Semester getSelectedSemester() {
		return selectedSemester;
	}

	public void setSelectedSemester(Semester selectedSemester) {
		this.selectedSemester = selectedSemester;
	}

	public SemesterService getSemesterService() {
		return semesterService;
	}

	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
