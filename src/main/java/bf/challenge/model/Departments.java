package bf.challenge.model;

public class Departments {
	private int departmentId;
	private String deparmentName;
	
	
	public Departments(int departmentId, String deparmentName) {
		super();
		this.departmentId = departmentId;
		this.deparmentName = deparmentName;
	}

	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public String getDeparmentName() {
		return deparmentName;
	}


	public void setDeparmentName(String deparmentName) {
		this.deparmentName = deparmentName;
	}	
}