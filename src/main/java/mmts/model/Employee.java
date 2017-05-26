package mmts.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Table(name = "EMPLOYEE")
@ManagedBean(name = "employee")
@Entity
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;
	private String ad;
	private String soyad;
	private String telNo;
	private String adres;
	private String firma;
	@Column(unique = true)
	private String eMail; // kullanýcý adý

	@OneToOne(mappedBy = "employee", cascade = CascadeType.REMOVE)
	private User user;

	@OneToMany(mappedBy = "mukellef", cascade = CascadeType.REMOVE)
	private List<Aidat> aidatlar;

	public List<Aidat> getAidatlar() {
		return aidatlar;
	}

	public void setAidatlar(List<Aidat> aidatlar) {
		this.aidatlar = aidatlar;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	/*
	 * 
	 * public String getHireDateFormat() {
	 * 
	 * SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); String
	 * format = formatter.format(getHireDate()); return format; }
	 */

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

}
