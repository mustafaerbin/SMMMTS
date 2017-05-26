package mmts.contex;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import mmts.model.Employee;

public class UserWithId extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Employee employee;

	int id;

	/**
	 * Login olan kullanýcýnin id'sini almak için kullanýlan metod
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 * @param id
	 * @param userId
	 */
	public UserWithId(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			int id, int userId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
