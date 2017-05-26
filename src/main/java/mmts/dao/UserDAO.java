package mmts.dao;

import mmts.model.User;

/**
 *  Created by sinan.ulgen on 10/05/2017.
 */
public interface UserDAO {
    public User getUser(String login);
}
