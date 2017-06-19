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

import mmts.model.Aidat;
import mmts.service.AidatService;
import mmts.service.UserContextService;

/**
 * Created by mustafa.erbin on 20/05/2017. kullanýcýlar için control sýnýfu,ý
 */
@ManagedBean(name = "aidatMB")
@ViewScoped
public class AidatManagedController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Java server faces EmployeeService objelerini inject ediyor
	 */
	@ManagedProperty(value = "#{AidatService}")
	AidatService aidatService;

	@ManagedProperty(value = "#{UserContextService}")
	UserContextService userContextService;

	/**
	 * Java server faces ile etkileþime girecek deðiþkenler
	 */
	/*
	 * Datatable'ý doldurmak için kullanýlan liste
	 */
	private List<Aidat> aidatList;
	/*
	 * Datatable ýn filter özelliðini kullanabilmek için oluþturulan liste
	 */
	private List<Aidat> filteredAidat;
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
	private Aidat selectedAidat;

	private List<Aidat> selectedAidatlar;
	/*
	 * Sorgulama iþleminde kullanýlan id
	 */
	private int id;

	/**
	 * Ýlgili parametleri kullanarak Veri tabanýna Aidat objesini eklediðimiz
	 * metoddur
	 */
	public void addAidat() {
		try {
			/*
			 * Employee student = new Employee(); student.setSname(name);
			 * getStudentService().addStudent(student); refreshPage();
			 */
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Datatable üzeride seçmiþ olduðumu selectedAidat objesini güncellediðimiz
	 * metoddur SelectedAidat objesi datatable üzerinde seçildiði anda otomatik
	 * olarak seçtiðimiz Student kaydý ile set edilmektedir.
	 */
	public void updateAidat() {
		try {
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
	public void deleteAidat() {
		try {
			getAidatService().deleteAidat(selectedAidat);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Kayýtlý bütün Aidat objelerini getirip aidatList isimli listemizi
	 * doldurduðumuz metoddur
	 */
	public List<Aidat> getAidatList() {
		Long id = userContextService.getEmployeeId();

		if (aidatList == null) {
			aidatList = new ArrayList<Aidat>();
			aidatList.addAll(getAidatService().getAidatlarWithUser(id));
			if (aidatList.size() > 0) {
				for (Aidat aidat : aidatList) {
					if (!aidat.isGoruldu()) {
						aidat.setGoruldu(true);
						aidatService.updateAidat(aidat);
					}
				}
			}
		}
		return aidatList;
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

	public AidatService getAidatService() {
		return aidatService;
	}

	public void setAidatService(AidatService aidatService) {
		this.aidatService = aidatService;
	}

	public List<Aidat> getFilteredAidat() {
		return filteredAidat;
	}

	public void setFilteredAidat(List<Aidat> filteredAidat) {
		this.filteredAidat = filteredAidat;
	}

	public Aidat getSelectedAidat() {
		return selectedAidat;
	}

	public void setSelectedAidat(Aidat selectedAidat) {
		this.selectedAidat = selectedAidat;
	}

	public void setAidatList(List<Aidat> aidatList) {
		this.aidatList = aidatList;
	}

	public List<Aidat> getSelectedAidatlar() {
		return selectedAidatlar;
	}

	public void setSelectedAidatlar(List<Aidat> selectedAidatlar) {
		this.selectedAidatlar = selectedAidatlar;
	}

}
