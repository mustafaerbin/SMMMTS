package mmts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mmts.contex.UserContext; 

@Service("UserContextService")
@Transactional(readOnly = true)
public class UserContextService {

	public Long getEmployeeId() {
		return UserContext.getEmployeeId();
	}

}
