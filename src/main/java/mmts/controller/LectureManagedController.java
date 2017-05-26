package mmts.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;

import mmts.model.Lecture;
import mmts.model.Semester;
import mmts.service.LectureService;
import mmts.service.SemesterService;
/**
 *  Created by mustafa.erbin on 10/05/2017.
 */
@ManagedBean(name = "lectureMB")
@ViewScoped
public class LectureManagedController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{LectureService}")
	LectureService lectureService;

	@ManagedProperty(value = "#{SemesterService}")
	SemesterService semesterService;

	private String lecture;
	private int semesterId;
	private List<Lecture> lectureList;
	private List<Lecture> filteredLectureList;
	private Lecture selectedLecture;
	private List<Semester> semesterList;
	private boolean enabled = true;

	public List<Semester> getSemesterList() {
		if (semesterList == null) {
			semesterList = new ArrayList<Semester>();
			semesterList.addAll(getSemesterService().getSemesterList());
		}
		return semesterList;
	}

	public List<Lecture> getLectureList() {
		if (lectureList == null) {
			lectureList = new ArrayList<Lecture>();
			lectureList.addAll(getLectureService().getLectureList());
		}
		return lectureList;
	}

	public void addLecture() {
		try {
			Lecture lc = new Lecture();
			lc.setLecture(lecture);
			Semester sm = new Semester();
			sm.setId(semesterId);
			lc.setSemester(sm);
			getLectureService().addLecture(lc);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateLecture() {
		try {
			getLectureService().updateLecture(selectedLecture);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteLecture() {
		try {
			getLectureService().deleteLecture(selectedLecture);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	public void refreshPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String refreshpage = fc.getViewRoot().getViewId();
		ViewHandler ViewH = fc.getApplication().getViewHandler();
		UIViewRoot UIV = ViewH.createView(fc, refreshpage);
		UIV.setViewId(refreshpage);
		fc.setViewRoot(UIV);
	}

	public void onRowSelect(SelectEvent event) {
		enabled = false;
	}

	public LectureService getLectureService() {
		return lectureService;
	}

	public void setLectureService(LectureService lectureService) {
		this.lectureService = lectureService;
	}

	public int getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public void setLectureList(List<Lecture> lectureList) {
		this.lectureList = lectureList;
	}

	public List<Lecture> getFilteredLectureList() {
		return filteredLectureList;
	}

	public void setFilteredLectureList(List<Lecture> filteredLectureList) {
		this.filteredLectureList = filteredLectureList;
	}

	public Lecture getSelectedLecture() {
		return selectedLecture;
	}

	public void setSelectedLecture(Lecture selectedLecture) {
		this.selectedLecture = selectedLecture;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public SemesterService getSemesterService() {
		return semesterService;
	}

	public void setSemesterService(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	public void setSemesterList(List<Semester> semesterList) {
		this.semesterList = semesterList;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
