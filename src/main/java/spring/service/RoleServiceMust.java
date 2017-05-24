package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.RoleDaoMust; 
import spring.model.Role;

/**
 * Created by mustafa.erbin on 23/05/2017.
 */
@Service("RoleServiceMust")
@Transactional(readOnly=false)
public class RoleServiceMust {
	
	@Autowired
	RoleDaoMust roleDaoMust;
	
	public Role getRoleById(Long id) {
		return getRoleDaoMust().getRoleById(id);
	}
	
	public RoleDaoMust getRoleDaoMust() {
		return roleDaoMust;
	}
	
	public void setRoleDaoMust(RoleDaoMust roleDaoMust) {
		this.roleDaoMust = roleDaoMust;
	}

}
