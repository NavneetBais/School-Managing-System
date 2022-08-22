
public class Class {
	private int noOfStudent ; 
	private Subject standard ; 
	private Teacher classTeacher ;
	public int getNoOfStudent() {
		return noOfStudent;
	}
	public Subject getStandard() {
		return standard;
	}
	public Teacher getClassTeacher() {
		return classTeacher;
	}
	public Class(int noOfStudent, Subject standard, Teacher classTeacher) {
		super();
		this.noOfStudent = noOfStudent;
		this.standard = standard;
		this.classTeacher = classTeacher;
	} 
	
	
}
