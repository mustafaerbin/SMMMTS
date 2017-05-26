package mmts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mmts.dao.UserDaoMust;
import mmts.model.User;;

/**
 * Created by mustafa.erbin on 10/05/2017.
 */
@Service("UserServiceMust")
@Transactional(readOnly = false)
public class UserServiceMust {

	@Autowired
	UserDaoMust userDAO;

	public void addUser(User user) {
		getUserDAO().addUser(user);
	}

	public User getUserById(Long id) {
		return getUserDAO().getUserById(id);
	}

	public UserDaoMust getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDaoMust userDAO) {
		this.userDAO = userDAO;
	}

}
