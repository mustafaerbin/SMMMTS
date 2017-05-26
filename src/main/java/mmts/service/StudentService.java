package mmts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mmts.dao.StudentDAO;
import mmts.model.Student;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Service("StudentService")
@Transactional(readOnly=true)
public class StudentService {

	@Autowired
	StudentDAO studentDAO;

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	@Transactional(readOnly=false)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void addStudent(Student student)
	{
		getStudentDAO().addStudent(student);
	}
	@Transactional(readOnly=false)
	public void deleteStudent(Student student)
	{
		getStudentDAO().deleteStudent(student);
	}
	@Transactional(readOnly=false)
	public void updateStudent(Student student)
	{
		getStudentDAO().updateStudent(student);
	}
	
	public List<Student> getStudentById(int id)
	{
		return getStudentDAO().getStudentById(id);
	}
	public List<Student> getStudentList()
	{
		return getStudentDAO().getStudentList();
	}
}
