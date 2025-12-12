package com.example.request;



public class SearchRequest {
	
    private String planName;
    private String planstatus;
    private String gender;
    private String startDate;
    private String endDate;
    

    public String getPlanName() {
		return planName;
	}


	public void setPlanName(String planName) {
		this.planName = planName;
	}


	public String getPlanstatus() {
		return planstatus;
	}


	public void setPlanstatus(String planstatus) {
		this.planstatus = planstatus;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	@Override
    public String toString() {
        return "SearchRequest [planName=" + planName +
               ", planstatus=" + planstatus +
               ", gender=" + gender +
               ", startDate=" + startDate +
               ", endDate=" + endDate + "]";
    }
}
