package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.dao.SemesterDAO;
import spring.model.Semester;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Service("SemesterService")
@Transactional(readOnly=true)
public class SemesterService {
	
	@Autowired
	SemesterDAO semesterDAO;

	@Transactional(readOnly=false)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void addSemester(Semester semester)
	{
		getSemesterDAO().addSemester(semester);
	}
	@Transactional(readOnly=false)
	public void deleteSemester(Semester semester)
	{
		getSemesterDAO().deleteSemester(semester);
	}
	@Transactional(readOnly=false)
	public void updateSemester(Semester semester)
	{
		getSemesterDAO().updateSemester(semester);
	}
	
	public List<Semester> getSemesterList()
	{
		return getSemesterDAO().getSemesterList();
	}
	
	public SemesterDAO getSemesterDAO() {
		return semesterDAO;
	}

	public void setSemesterDAO(SemesterDAO semesterDAO) {
		this.semesterDAO = semesterDAO;
	}

}
