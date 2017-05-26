package mmts.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Entity
@Table(name="ROLES")
@ManagedBean(name="roles")
public class Role implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="pk_role_sequence",sequenceName="role_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String role;
   
    @OneToMany(cascade=CascadeType.ALL)
    private Set<User> userRoles;

	public Long getId() {
		
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<User> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<User> userRoles) {
		this.userRoles = userRoles;
	}


}
