package spring.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Entity
@DiscriminatorValue(value="TS")
public class TechnicalStuff extends Stuff implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String educationLevel;
	private String qualification;
	
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
	
	

}
