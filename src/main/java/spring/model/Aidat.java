package spring.model;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by mustafa.erbin on 20/05/2017.
 */
@Table(name = "AIDAT")
@ManagedBean(name = "aidat")
@Entity
public class Aidat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long aidatId;
	private int ay;
	private int yil;
	private BigDecimal miktar; // tahakkuk edilen miktar
	private BigDecimal miktarOdenen; // ödenen miktar
	private Date islemTarih; 
	private String islemTip; // manuel veya kredi kartý ile ödeme. M manuel, K kk
	private String flag; // aidat durumu F - ödenmedi ( borc durumunda ), I - iptal, T - tahsil edildi.
	private boolean goruldu; // tahakkuk edilen aidatýn mükellef tarafýndan görülme durumu. 1 - görüldü, 0 - görülmedi
	private boolean odendi; // aidatýn ödeme durumu. 1 - ödendi, 0 ödenmedi
	private String aciklama;

	@NotNull
	@JoinColumn(name = "MukellefId", nullable = false)
	@ManyToOne(cascade = { PERSIST, MERGE }, fetch = FetchType.EAGER)
	private Employee mukellef;

	public boolean isOdendi() {
		return odendi;
	}

	public void setOdendi(boolean odendi) {
		this.odendi = odendi;
	}

	public boolean isGoruldu() {
		return goruldu;
	}

	public void setGoruldu(boolean goruldu) {
		this.goruldu = goruldu;
	}

	public Long getAidatId() {
		return aidatId;
	}

	public void setAidatId(Long aidatId) {
		this.aidatId = aidatId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getAy() {
		return ay;
	}

	public void setAy(int ay) {
		this.ay = ay;
	}

	public int getYil() {
		return yil;
	}

	public void setYil(int yil) {
		this.yil = yil;
	}

	public BigDecimal getMiktar() {
		return miktar;
	}

	public void setMiktar(BigDecimal miktar) {
		this.miktar = miktar;
	}

	public BigDecimal getMiktarOdenen() {
		return miktarOdenen;
	}

	public void setMiktarOdenen(BigDecimal miktarOdenen) {
		this.miktarOdenen = miktarOdenen;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Date getIslemTarih() {
		return islemTarih;
	}

	public void setIslemTarih(Date islemTarih) {
		this.islemTarih = islemTarih;
	}

	public String getIslemTip() {
		return islemTip;
	}

	public void setIslemTip(String islemTip) {
		this.islemTip = islemTip;
	}

	public Employee getMukellef() {
		return mukellef;
	}

	public void setMukellef(Employee mukellef) {
		this.mukellef = mukellef;
	}

}
