package mmts.service;

import mmts.model.User;

/**
 * Created by sinan.ulgen on 10/05/2017.
 */
public interface UserService {
    public User getUser(String login);
}
