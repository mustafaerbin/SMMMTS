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
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import mmts.model.Employee;
import mmts.model.Role;
import mmts.model.User;
import mmts.service.EmployeeService;
import mmts.service.RoleServiceMust;
import mmts.service.UserContextService;
import mmts.service.UserServiceMust;
import mmts.util.MessageUtil;

/**
 * Created by mustafa.erbin on 18/05/2017.
 */
@ManagedBean(name = "employeeMB")
@ViewScoped
public class EmployeeManagedController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Java server faces EmployeeService objelerini inject ediyor
	 */
	@ManagedProperty(value = "#{EmployeeService}")
	EmployeeService employeeService;

	@ManagedProperty(value = "#{UserContextService}")
	UserContextService userContextService;

	@ManagedProperty(value = "#{UserServiceMust}")
	UserServiceMust userService;

	@ManagedProperty(value = "#{RoleServiceMust}")
	RoleServiceMust roleService;
	
	@Inject
	MessageUtil messageUtil;

	/**
	 * Java server faces ile etkileþime girecek deðiþkenler
	 */
	/*
	 * Datatable'ý doldurmak için kullanýlan liste
	 */
	private List<Employee> employeeList;
	/*
	 * Datatable ýn filter özelliðini kullanabilmek için oluþturulan liste
	 */
	private List<Employee> filteredEmployee;
	/*
	 * Ekrandan gönderilen deðiþlen
	 */
	private String name;

	private String ad;
	private String soyad;
	private String firma;
	private String tel;
	private String kullaniciAdi;
	private String sifre;
	private String adres;

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
	 * Ýlgili parametleri kullanarak Veri tabanýna Student objesini eklediðimiz
	 * metoddur
	 */
	@Transactional
	public void addEmployee() {
		try {

			Employee employee = new Employee();
			employee.setAd(ad);
			employee.setSoyad(soyad);
			employee.setFirma(firma);
			employee.seteMail(kullaniciAdi);
			employee.setAdres(adres);
			employee.setTelNo(tel);
			getEmployeeService().addEmployee(employee);

			Role rol = getRoleService().getRoleById(2L);
			User user = new User();
			user.setEmployee(employee);
			user.setPassword(sifre);
			user.setRole(rol);
			getUserService().addUser(user);

			/*
			 * Employee student = new Employee(); student.setSname(name);
			 * getStudentService().addStudent(student); refreshPage();
			 */
			//messageUtil.info("baþarýlý", null);
			refreshPage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Datatable üzeride seçmiþ olduðumu selectedStudent objesini
	 * güncellediðimiz metoddur SelectedStudent objesi datatable üzerinde
	 * seçildiði anda otomatik olarak seçtiðimiz Student kaydý ile set
	 * edilmektedir.
	 */
	public void updateEmployee() {
		try {
			Employee employee = selectedEmployee;
			getEmployeeService().updateEmployee(employee);
			/*
			 * getEmployeeService().updateStudent(selectedStudent);
			 * refreshPage();
			 */
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Kayýtlý bir öðrenciyi silmek için kullandýðýmýz metoddur
	 */
	public void deleteEmployee() {
		try {
			getEmployeeService().deleteEmployee(selectedEmployee);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Named Query kullanarak id sine göre öðrenci aradýðýmýz ve öðrenci
	 * listemizi güncellediðimiz medtoddur
	 */
	public void getEmployeeById() {
		try {
			if (employeeList == null) {
				employeeList = new ArrayList<Employee>();
				employeeList.addAll(getEmployeeService().getEmployees());
			} else {
				employeeList.clear();
				employeeList.addAll(getEmployeeService().getEmployees());
			}
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Kayýtlý bütün Student objelerini getirip studentList isimli listemizi
	 * doldurduðumuz metoddur
	 */
	public List<Employee> getEmployeeList() {
		// Long employeId = userContextService.getEmployeeId();

		if (employeeList == null) {
			employeeList = new ArrayList<Employee>();
			employeeList.addAll(getEmployeeService().getEmployees());
		}
		return employeeList;
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

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
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

	public UserContextService getUserContextService() {
		return userContextService;
	}

	public void setUserContextService(UserContextService userContextService) {
		this.userContextService = userContextService;
	}

	public List<Employee> getFilteredEmployee() {
		return filteredEmployee;
	}

	public void setFilteredEmployee(List<Employee> filteredEmployee) {
		this.filteredEmployee = filteredEmployee;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public UserServiceMust getUserService() {
		return userService;
	}

	public void setUserService(UserServiceMust userService) {
		this.userService = userService;
	}

	public RoleServiceMust getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleServiceMust roleService) {
		this.roleService = roleService;
	}
	
	public MessageUtil getMessageUtil() {
		return messageUtil;
	}
	
	public void setMessageUtil(MessageUtil messageUtil) {
		this.messageUtil = messageUtil;
	}

}
