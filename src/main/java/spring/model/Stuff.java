package spring.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */

@Entity
@Table(name="STUFF")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(  name="type")
@ManagedBean(name="stuff")
public class Stuff implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	//@SequenceGenerator(name="pk_stuff_sequence",sequenceName="stuff_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int sid;
	
	private String name;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
