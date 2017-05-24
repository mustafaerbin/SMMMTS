package spring.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Entity
@DiscriminatorValue(value="NS")
public class NonTechnicalStuff extends Stuff implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String expertise;

	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
}
