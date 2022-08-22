
public class Teacher {
	private String name ; 
	private Subject subject ; 
	private int empId;
	public String getName() {
		return name;
	}
	public Subject getSubject() {
		return subject;
	}
	public int getEmpId() {
		return empId;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Teacher(String name, Subject subject, int empId) {
		super();
		this.name = name;
		this.subject = subject;
		this.empId = empId;
	}
	
	
	
	
}
