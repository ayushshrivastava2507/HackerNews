package com.hackernews.rest.dtos;

public class CommentsDto {
	
	private String userName;
	private String userBio;
	private Long userSinceInYrs;
	private String commentText;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getUserBio() {
		return userBio;
	}
	public void setUserBio(String userBio) {
		this.userBio = userBio;
	}
	public Long getUserSinceInYrs() {
		return userSinceInYrs;
	}
	public void setUserSinceInYrs(Long userSinceInYrs) {
		this.userSinceInYrs = userSinceInYrs;
	}
	
}
