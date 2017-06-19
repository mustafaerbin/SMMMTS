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
 * Created by mustafa.erbin on 20/05/2017. kullan�c�lar i�in control s�n�fu,�
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
	 * Java server faces ile etkile�ime girecek de�i�kenler
	 */
	/*
	 * Datatable'� doldurmak i�in kullan�lan liste
	 */
	private List<Aidat> aidatList;
	/*
	 * Datatable �n filter �zelli�ini kullanabilmek i�in olu�turulan liste
	 */
	private List<Aidat> filteredAidat;
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
	private Aidat selectedAidat;

	private List<Aidat> selectedAidatlar;
	/*
	 * Sorgulama i�leminde kullan�lan id
	 */
	private int id;

	/**
	 * �lgili parametleri kullanarak Veri taban�na Aidat objesini ekledi�imiz
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
	 * Datatable �zeride se�mi� oldu�umu selectedAidat objesini g�ncelledi�imiz
	 * metoddur SelectedAidat objesi datatable �zerinde se�ildi�i anda otomatik
	 * olarak se�ti�imiz Student kayd� ile set edilmektedir.
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
	 * Kay�tl� bir ��renciyi silmek i�in kulland���m�z metoddur
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
	 * Kay�tl� b�t�n Aidat objelerini getirip aidatList isimli listemizi
	 * doldurdu�umuz metoddur
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
