package com.hackernews.rest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STORIES")
public class Stories {
	
	@Id
	@Column(name="STORY_ID")
	private Integer storyId;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="SUBMISSION_TIME")
	private Integer submissionTime;
	
	@Column(name="SCORE")
	private Integer score;
	
	@Column(name="USER")
	private String userName;
	
	@Column(name="DESCENDANTS")
	private Integer descendants;
	
	@Column(name="SERVED")
	private Boolean servedFlag;

	public Integer getStoryId() {
		return storyId;
	}

	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Integer submissionTime) {
		this.submissionTime = submissionTime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDescendants() {
		return descendants;
	}

	public void setDescendants(Integer descendants) {
		this.descendants = descendants;
	}

	public Boolean getServedFlag() {
		return servedFlag;
	}

	public void setServedFlag(Boolean servedFlag) {
		this.servedFlag = servedFlag;
	}
	
}
