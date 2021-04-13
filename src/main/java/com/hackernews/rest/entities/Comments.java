package com.hackernews.rest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "comments")
public class Comments {
	
	@Id
	@Column(name="COMMENT_ID")
	private Integer commentId;
	
	@Column(name="PARENT")
	private Integer parent;
	
	@Column(name="TEXT")
	private String text;
	
	@Column(name="COMMENT_USER")
	private String commentUser;
	
	@Column(name="CHILD_COMMENTS")
	private Integer childComments;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}

	public Integer getChildComments() {
		return childComments;
	}

	public void setChildComments(Integer childComments) {
		this.childComments = childComments;
	}
	
}
