package mmts.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mmts.dao.UserDAO;
import mmts.util.MediUser;

/**
 * Created by mustafa.erbin on 10/05/2017.
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		mmts.model.User domainUser = userDAO.getUser(login);

		return new MediUser(domainUser.getEmployee().geteMail(), domainUser.getPassword(), true, true, true, true,
				getAuthorities(domainUser.getRole().getId()), domainUser.getEmployee().getEmployeeId(),
				domainUser.getEmployee().geteMail(), domainUser.getRole().getId());

		// Orjinal Spring user kýsmý
		// @Autowired
		// private UserDAO userDAO;
		// @Override
		// public UserDetails loadUserByUsername(String login) throws
		// UsernameNotFoundException {
		//
		// spring.model.User domainUser = userDAO.getUser(login);
		//
		// boolean enabled = true;
		// boolean accountNonExpired = true;
		// boolean credentialsNonExpired = true;
		// boolean accountNonLocked = true;
		//
		// return new User(
		// domainUser.getEmployee().geteMail(),
		// domainUser.getPassword(),
		// enabled,
		// accountNonExpired,
		// credentialsNonExpired,
		// accountNonLocked,
		// getAuthorities(domainUser.getRole().getId())
		// );
	}

	public Collection<? extends GrantedAuthority> getAuthorities(Long role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	/**
	 * getirilen rolleri id lerine göre yetkilendiriyoruz
	 */
	public List<String> getRoles(Long role) {

		List<String> roles = new ArrayList<String>();

		if (role.intValue() == 1) { 
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
		}

		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
