package com.csit321g2.ppfp.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblProject")

public class ProjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private int pid;
	
	@Column(name = "user_id")
    private int userId;
	
	private String projectTitle;
	
	private String projectDesc;
	
	private String projectCode;
	
	private boolean isPending;
	
	private boolean isDeleted;

	private boolean isApproved;

	private boolean isRejected;

	public ProjectEntity() {
		super();
	}

	public ProjectEntity(int pid, int userId, String projectTitle, String projectDesc, String projectCode, boolean isPending,
	        boolean isDeleted, boolean isApproved, boolean isRejected) {
	    super();
	    this.pid = pid;
	    this.userId = userId;
	    this.projectTitle = projectTitle;
	    this.projectDesc = projectDesc;
	    this.projectCode = projectCode;
	    this.isPending = isPending;
	    this.isDeleted = isDeleted;
		this.isApproved = isApproved;
		this.isRejected = isRejected;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public boolean isPending() {
		return isPending;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public boolean isRejected() {
		return isRejected;
	}

	public void setRejected(boolean isRejected) {
		this.isRejected = isRejected;
	}
	
	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
