package mmts.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.dao.DataAccessException;
 
import mmts.model.Lecture;
import mmts.model.Student;
import mmts.service.LectureService;
import mmts.service.StudentService;
import mmts.service.UserContextService;
/**
 *  Created by mustafa.erbin on 10/05/2017.
 */
@ManagedBean(name = "studentMB")
@ViewScoped
public class StudentManagedController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Java server faces StudentServis ve LectureServis objelerini inject ediyor
	 */
	@ManagedProperty(value = "#{StudentService}")
	StudentService studentService;

	@ManagedProperty(value = "#{LectureService}")
	LectureService lectureService;
	
	@ManagedProperty(value = "#{UserContextService}")
	UserContextService userContextService;

	/**
	 * Java server faces ile etkile�ime girecek de�i�kenler
	 */
	/*
	 * Datatable'� doldurmak i�in kullan�lan liste
	 */
	private List<Student> studentList;
	/*
	 * Datatable �n filter �zelli�ini kullanabilmek i�in olu�turulan liste
	 */
	private List<Student> filteredStudent;
	/*
	 * Ekrandan g�nderilen de�i�len
	 */
	private String name;
	/*
	 * Ekrandaki butonlar�n aktivasyonu i�in kullan�lan de�i�ken
	 */
	private boolean enableButton = true;
	/*
	 * Se�ilen Student objesi
	 */
	private Student selectedStudent;
	/*
	 * Sorgulama i�leminde kullan�lan id
	 */
	private int id;

	private List<Lecture> lectureList;
	private List<Lecture> selectedLectures;

	/**
	 * Se�ilen derslerin sisteme eklendi�i metoddur
	 */
	public void addOwnedLecture() {
		try {
			Set<Lecture> lectureCollection = new HashSet<Lecture>();
			for (Lecture lc : selectedLectures) {
				lectureCollection.add(lc);
			}
			selectedStudent.setLectureSet(lectureCollection);
			getStudentService().updateStudent(selectedStudent);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * �lgili parametleri kullanarak Veri taban�na Student objesini ekledi�imiz
	 * metoddur
	 */
	public void addStudent() {
		try {
			Student student = new Student();
			student.setSname(name);
			getStudentService().addStudent(student);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Datatable �zeride se�mi� oldu�umu selectedStudent objesini
	 * g�ncelledi�imiz metoddur SelectedStudent objesi datatable �zerinde
	 * se�ildi�i anda otomatik olarak se�ti�imiz Student kayd� ile set
	 * edilmektedir.
	 */
	public void updateStudent() {
		try {
			getStudentService().updateStudent(selectedStudent);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Kay�tl� bir ��renciyi silmek i�in kulland���m�z metoddur
	 */
	public void deleteStudent() {
		try {
			getStudentService().deleteStudent(selectedStudent);
			refreshPage();
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Named Query kullanarak id sine g�re ��renci arad���m�z ve ��renci
	 * listemizi g�ncelledi�imiz medtoddur
	 */ 
	public void getStudentById() {
		try {
			if (studentList == null) {
				studentList = new ArrayList<Student>();
				studentList.addAll(getStudentService().getStudentById(id));
			} else {
				studentList.clear();
				studentList.addAll(getStudentService().getStudentById(id));
			}
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Kay�tl� b�t�n Student objelerini getirip studentList isimli listemizi
	 * doldurdu�umuz metoddur
	 */
	public List<Student> getStudentList() {
		 //int employeId = userContextService.getEmployeeId();
		
		if (studentList == null) {
			studentList = new ArrayList<Student>();
			studentList.addAll(getStudentService().getStudentList());
		}
		return studentList;
	}

	/**
	 * Ekranlarda ajax kulland���m�z i�in her hangi bir crud i�lemi yap�ld���nda
	 * view � yenilemek i�in kulland���m�z metoddur.Farkl� y�ntemleri de mevcut
	 */
	public void refreshPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String refreshpage = fc.getViewRoot().getViewId();
		ViewHandler ViewH = fc.getApplication().getViewHandler();
		UIViewRoot UIV = ViewH.createView(fc, refreshpage);
		UIV.setViewId(refreshpage);
		fc.setViewRoot(UIV);
	}

	/**
	 * Datatable �zerinde se�im yap�ld���nda �al��an metod
	 */

	public void onRowSelect(SelectEvent event) {
		enableButton = false;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setStundentList(List<Student> stundentList) {
		this.studentList = stundentList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnableButton() {
		return enableButton;
	}

	public void setEnableButton(boolean enableButton) {
		this.enableButton = enableButton;
	}

	public Student getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(Student selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Student> getFilteredStudent() {
		return filteredStudent;
	}

	public void setFilteredStudent(List<Student> filteredStudent) {
		this.filteredStudent = filteredStudent;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public LectureService getLectureService() {
		return lectureService;
	}

	public void setLectureService(LectureService lectureService) {
		this.lectureService = lectureService;
	}
	
	public UserContextService getUserContextService() {
		return userContextService;
	}
	
	public void setUserContextService(UserContextService userContextService) {
		this.userContextService = userContextService;
	}

	public List<Lecture> getLectureList() {
		if (lectureList == null) {
			lectureList = new ArrayList<Lecture>();
			lectureList.addAll(lectureService.getLectureList());
		}
		return lectureList;
	}

	public void setLectureList(List<Lecture> lectureList) {
		this.lectureList = lectureList;
	}

	public List<Lecture> getSelectedLectures() {
		return selectedLectures;
	}

	public void setSelectedLectures(List<Lecture> selectedLectures) {
		this.selectedLectures = selectedLectures;
	}

}
