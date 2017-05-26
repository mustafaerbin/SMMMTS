package mmts.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Entity
@Table(name="LECTURE")
@ManagedBean(name="lecture")
public class Lecture  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String lecture;
	
	@ManyToOne
	private Semester semester;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	

}
