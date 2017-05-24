package spring.contex;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
 
import spring.util.MediUser;

public class UserContext {
	
    public static Long getEmployeeId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();

            if (principal instanceof MediUser) {
                return ((MediUser) principal).getEmployeeId();
            }
        }

        return 0L;
    }

}
