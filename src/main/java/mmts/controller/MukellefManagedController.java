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
	 * Java server faces ile etkile�ime girecek de�i�kenler
	 */
	/*
	 * Datatable'� doldurmak i�in kullan�lan liste
	 */
	private List<Employee> mukellefList;

	/*
	 * Datatable �n filter �zelli�ini kullanabilmek i�in olu�turulan liste
	 */
	private List<Employee> filteredEmployee;

	/*
	 * Ekrandan g�nderilen de�i�len
	 */
	private String name;

	/*
	 * Ekrandaki butonlar�n aktivasyonu i�in kullan�lan de�i�ken
	 */
	private boolean enableButton = true;

	/*
	 * Se�ilen Student objesi
	 */
	private Employee selectedEmployee;

	/*
	 * Sorgulama i�leminde kullan�lan id
	 */
	private int id;

	/**
	 * Se�ilen derslerin sisteme eklendi�i metoddur
	 */
	public void addOwnedLecture() {
		try {
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * �lgili parametleri kullanarak Veri taban�na Mukellef objesini ekledi�imiz
	 * metoddur
	 */
	public void addMukellef() {
		try {

		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Datatable �zeride se�mi� oldu�umu selectedStudent objesini
	 * g�ncelledi�imiz metoddur SelectedStudent objesi datatable �zerinde
	 * se�ildi�i anda otomatik olarak se�ti�imiz Student kayd� ile set
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
	 * Kay�tl� bir ��renciyi silmek i�in kulland���m�z metoddur
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
	 * Kay�tl� b�t�n Student objelerini getirip studentList isimli listemizi
	 * doldurdu�umuz metoddur
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
	 * Ekranlarda ajax kulland���m�z i�in her hangi bir crud i�lemi yap�ld���nda
	 * view � yenilemek i�in kulland���m�z metoddur.Farkl� y�ntemleri de mevcut
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
	 * Datatable �zerinde se�im yap�ld���nda �al��an metod
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
