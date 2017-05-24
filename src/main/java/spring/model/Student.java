package spring.model;

import java.io.Serializable;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

/**
 * Created by sinan.ulgen on 10/05/2017.
 */

@ManagedBean(name="student")
@Table(name="STUDENT")
@Entity
@NamedQuery(query = "Select s from Student s where s.sid = :id", 
name = "find by id")
@SuppressWarnings("rawtypes")
public class Student implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO) 	
	private int sid;
	private String sname;
	
	
	@ManyToMany(targetEntity=Lecture.class,fetch=FetchType.EAGER)
	private Set lectureSet;
	
	
	public Set getLectureSet() {
		return lectureSet;
	}

	public void setLectureSet(Set lectureSet) {
		this.lectureSet = lectureSet;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}
