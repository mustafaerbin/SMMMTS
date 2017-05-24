package spring.util;

import java.io.Serializable;
import java.util.Collection; 

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import spring.model.Employee;
/**
 * Created by mustafa.erbin on 10/05/2017.
 */
public class MediUser extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long employeeId;    
	private String eMail;
	private Long roleId;
	
	private Employee employee;

	public MediUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			Long employeeId, String eMail, Long roleId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.employeeId = employeeId;   
		this.eMail = eMail;
		this.roleId = roleId;

	}
	
	public Long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
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

}
