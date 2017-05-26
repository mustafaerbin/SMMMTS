package mmts.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne; 
import javax.persistence.Table;

/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Entity
@Table(name = "USERS")
@ManagedBean(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	// @SequenceGenerator(name="pk_user_sequenceAUTP,sequenceName="user_id_seq",
	// allocationSize=1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String password;
	// private String username;
	@OneToOne(cascade = CascadeType.REMOVE)
	private Employee employee; 

	@OneToOne
	@JoinColumn(name = "fk_role")
	private Role role;
	// @JoinTable(name="USER_ROLES",
	// joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
	// inverseJoinColumns = {@JoinColumn(name="role_id",
	// referencedColumnName="id")}
	// )
	// private Role role; 

	public Long getId() {
		return id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
