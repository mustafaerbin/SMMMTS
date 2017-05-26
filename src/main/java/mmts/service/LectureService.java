package mmts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mmts.dao.LectureDAO;
import mmts.model.Lecture;
/**
 * Created by sinan.ulgen on 10/05/2017.
 */
@Service("LectureService")
@Transactional(readOnly=true)
public class LectureService {
	
	@Autowired
	LectureDAO lectureDAO;

	@Transactional(readOnly=false)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void addLecture(Lecture lecture)
	{
		getLectureDAO().addLecture(lecture);
	}
	@Transactional(readOnly=false)
	public void deleteLecture(Lecture lecture)
	{
		getLectureDAO().deleteLecture(lecture);
	}
	@Transactional(readOnly=false)
	public void updateLecture(Lecture lecture)
	{
		getLectureDAO().updateLecture(lecture);
	}
	
	public List<Lecture> getLectureList()
	{
		return getLectureDAO().getLectureList();
	}
	
	public LectureDAO getLectureDAO() {
		return lectureDAO;
	}

	public void setLectureDAO(LectureDAO lectureDAO) {
		this.lectureDAO = lectureDAO;
	}
	
	

}
