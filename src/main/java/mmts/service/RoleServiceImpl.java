package mmts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mmts.dao.RoleDAOImpl;
import mmts.model.Role;

/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Service("RoleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAOImpl roleDAO;

    public Role getRole(int id) {
        return roleDAO.getRole(id);
    }

}
