package mmts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mmts.dao.EmployeeDAO;
import mmts.model.Employee;

/**
 * Created by mustafa.erbin on 10/05/2017.
 */
@Service("EmployeeService")
@Transactional(readOnly = false)
public class EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	/*** Annotation of applying method level Spring Security ***/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional(readOnly = false)
	public void addEmployee(Employee employee) {
		getEmployeeDAO().addEmployee(employee);
	}

	@Transactional(readOnly = false)
	public void deleteEmployee(Employee employee) {
		getEmployeeDAO().deleteEmployee(employee);
	}

	@Transactional(readOnly = false)
	public void updateEmployee(Employee employee) {
		getEmployeeDAO().updateCustomer(employee);
	}

	public Employee getEmployeeById(Long id) {
		return getEmployeeDAO().getEmployeeById(id);
	}

	public List<Employee> getEmployeeByEducation(String education) {
		return getEmployeeDAO().getEmployeeByEducation(education);
	}

	public List<Employee> getEmployeeByActiveStatus(boolean active) {
		return getEmployeeDAO().getEmployeeByActiveStatus(active);
	}

	public List<Employee> getEmployeeByBloodType(String bloodType) {
		return getEmployeeDAO().getEmployeeByBloodType(bloodType);
	}

	public List<Employee> getEmployeeByAssignmentGrup(Long id) {
		return getEmployeeDAO().getEmployeeByAssignmentGrup(id);
	}

	public List<Employee> getEmployees() {
		return getEmployeeDAO().getEmployees();
	}

	public List<Employee> getDeletedEmployees() {
		return getEmployeeDAO().getDeletedEmployees();
	}

	public Employee getEmployeeByEmail(String userName) {
		return getEmployeeDAO().getEmployeeByEmail(userName);
	}

}
