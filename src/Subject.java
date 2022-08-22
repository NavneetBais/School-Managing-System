
public class Subject {
	private String subName ; 
	private int subId ;
	public Subject(String subName, int subId) {
		super();
		this.subName = subName;
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	} 
	
	
}
