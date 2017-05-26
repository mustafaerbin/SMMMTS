package mmts.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

import mmts.model.Employee;
import mmts.service.EmployeeService;

@ManagedBean(name = "mukellefMB")
@ViewScoped
public class MukellefManagedController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Java server faces EmployeeService ve LectureServis objelerini inject
	 * ediyor
	 */
	@ManagedProperty(value = "#{EmployeeService}")
	EmployeeService mukellefService;

	/**
	 * Java server faces ile etkileþime girecek deðiþkenler
	 */
	/*
	 * Datatable'ý doldurmak için kullanýlan liste
	 */
	private List<Employee> mukellefList;

	/*
	 * Datatable ýn filter özelliðini kullanabilmek için oluþturulan liste
	 */
	private List<Employee> filteredEmployee;

	/*
	 * Ekrandan gönderilen deðiþlen
	 */
	private String name;

	/*
	 * Ekrandaki butonlarýn aktivasyonu için kullanýlan deðiþken
	 */
	private boolean enableButton = true;

	/*
	 * Seçilen Student objesi
	 */
	private Employee selectedEmployee;

	/*
	 * Sorgulama iþleminde kullanýlan id
	 */
	private int id;

	/**
	 * Seçilen derslerin sisteme eklendiði metoddur
	 */
	public void addOwnedLecture() {
		try {
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Ýlgili parametleri kullanarak Veri tabanýna Mukellef objesini eklediðimiz
	 * metoddur
	 */
	public void addMukellef() {
		try {

		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Datatable üzeride seçmiþ olduðumu selectedStudent objesini
	 * güncellediðimiz metoddur SelectedStudent objesi datatable üzerinde
	 * seçildiði anda otomatik olarak seçtiðimiz Student kaydý ile set
	 * edilmektedir.
	 */
	public void updateMukellef() {
		try {
			/*
			 * getStudentService().updateStudent(selectedStudent);
			 * refreshPage();
			 */
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Kayýtlý bir öðrenciyi silmek için kullandýðýmýz metoddur
	 */
	public void deleteMukellef() {
		try {
			/*
			 * getStudentService().deleteStudent(selectedStudent);
			 * refreshPage();
			 */
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Kayýtlý bütün Student objelerini getirip studentList isimli listemizi
	 * doldurduðumuz metoddur
	 */
	public List<Employee> getMukellefList() {
		// int employeId = userContextService.getEmployeeId();

		if (mukellefList == null) {
			mukellefList = new ArrayList<Employee>();
			mukellefList.addAll(getMukellefService().getEmployees());
		}
		return mukellefList;
	}

	/**
	 * Ekranlarda ajax kullandýðýmýz için her hangi bir crud iþlemi yapýldýðýnda
	 * view ý yenilemek için kullandýðýmýz metoddur.Farklý yöntemleri de mevcut
	 */
	public void refreshPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String refreshpage = fc.getViewRoot().getViewId();
		ViewHandler ViewH = fc.getApplication().getViewHandler();
		UIViewRoot UIV = ViewH.createView(fc, refreshpage);
		UIV.setViewId(refreshpage);
		fc.setViewRoot(UIV);
	}

	/**
	 * Datatable üzerinde seçim yapýldýðýnda çalýþan metod
	 */

	public void onRowSelect(SelectEvent event) {
		enableButton = false;
	}

	public List<Employee> getFilteredEmployee() {
		return filteredEmployee;
	}

	public void setFilteredEmployee(List<Employee> filteredEmployee) {
		this.filteredEmployee = filteredEmployee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnableButton() {
		return enableButton;
	}

	public void setEnableButton(boolean enableButton) {
		this.enableButton = enableButton;
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMukellefService(EmployeeService mukellefService) {
		this.mukellefService = mukellefService;
	}

	public void setMukellefList(List<Employee> mukellefList) {
		this.mukellefList = mukellefList;
	}

	public EmployeeService getMukellefService() {
		return mukellefService;
	}

}
