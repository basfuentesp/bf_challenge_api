package bf.challenge.model;

public class HiredEmployees {
	private int hiredId;
	private String hiredName;
	private String hiredDate;
	private int departmentId;
	private int jobId;
	
	public HiredEmployees(int hiredId, String hiredName, String hiredDate, int departmentId, int jobId) {
		super();
		this.hiredId = hiredId;
		this.hiredName = hiredName;
		this.hiredDate = hiredDate;
		this.departmentId = departmentId;
		this.jobId = jobId;
	}

	public int getHiredId() {
		return hiredId;
	}

	public void setHiredId(int hiredId) {
		this.hiredId = hiredId;
	}

	public String getHiredName() {
		return hiredName;
	}

	public void setHiredName(String hiredName) {
		this.hiredName = hiredName;
	}

	public String getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(String hiredDate) {
		this.hiredDate = hiredDate;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}	
}