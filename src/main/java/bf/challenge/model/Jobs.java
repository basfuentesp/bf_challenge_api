package bf.challenge.model;

public class Jobs {
	private int jobId;
	private String jobName;
	
	public Jobs(int jobId, String jobName) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
	}

	public Jobs() {
		// TODO Auto-generated constructor stub
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
}