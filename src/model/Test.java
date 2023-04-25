package model;

public class Test {

	private String idTest;
	private String titleTest;
	private String nameSubject;
	private int releaseYear;
	private int numberOfQuestions;
	private String defaultTime;
	private String school;
	private String idAdmin;
	
	public String getIdTest() {
		return idTest;
	}
	
	public void setIdTest(String idTest) {
		this.idTest = idTest;
	}
	
	public String getTitleTest() {
		return titleTest;
	}
	
	public void setTitleTest(String titleTest) {
		this.titleTest = titleTest;
	}
	
	public String getNameSubject() {
		return nameSubject;
	}
	
	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}
	
	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}
	
	
	public String getDefaultTime() {
		return defaultTime;
	}

	public void setDefaultTime(String defaultTime) {
		this.defaultTime = defaultTime;
	}

	public String getSchool() {
		return school;
	}
	
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getIdAdmin() {
		return idAdmin;
	}
	
	public void setIdAdmin(String idAdmin) {
		this.idAdmin = idAdmin;
	}

	@Override
	public String toString() {
		return "Test [idTest=" + idTest + ", titleTest=" + titleTest + ", nameSubject=" + nameSubject + ", releaseYear="
				+ releaseYear + ", numberOfQuestions=" + numberOfQuestions + ", defaultTime=" + defaultTime
				+ ", school=" + school + ", idAdmin=" + idAdmin + "]";
	}
}
