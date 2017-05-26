package mmts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mmts.dao.UserDAO;
import mmts.dao.UserDAOImpl;
import mmts.model.User;

/**
 * Created by mustafa.erbin on 10/05/2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserDAOImpl UserDAOImpl;

	/*** Annotation of applying method level Spring Security ***/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional(readOnly = false)
	public void addUser(User user) {
		getUserDAOImpl().addUser(user);
	}

	public UserDAOImpl getUserDAOImpl() {
		return UserDAOImpl;
	}

	public void setUserDAOImpl(UserDAOImpl userDAOImpl) {
		UserDAOImpl = userDAOImpl;
	}

	public User getUser(String login) {
		return userDAO.getUser(login);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

}
