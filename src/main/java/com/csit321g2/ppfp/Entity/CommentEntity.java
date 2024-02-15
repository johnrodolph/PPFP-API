package com.csit321g2.ppfp.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblComment")

public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comment_id")
	private int cid;

    @Column(name = "user_id")
    private int userId;
	
    @Column(name = "project_id")
    private int projectId;
	
	private String comment;
	
	private boolean isPending;
	
	private boolean isDeleted;

	private boolean isRejected;

	private boolean isApproved;

	public CommentEntity() {
		super();
	}

	public CommentEntity(int cid, int projectId, int userId, String comment, boolean isPending, boolean isDeleted, boolean isRejected ,boolean isApproved) {
		super();
		this.cid = cid;
		this.projectId = projectId;
		this.userId = userId;
		this.comment = comment;
		this.isPending = isPending;
		this.isDeleted = isDeleted;
		this.isRejected = isRejected;
		this.isApproved = isApproved;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
	public boolean isRejected() {
		return isRejected;
	}

	public void setRejected(boolean isRejected) {
		this.isRejected= isRejected;
	}
	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
