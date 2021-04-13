package com.hackernews.rest.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hackernews.rest.dtos.CommentsDto;
import com.hackernews.rest.entities.Comments;
import com.hackernews.rest.repo.ICommentsRepository;
import com.hackernews.rest.utilities.EpochTimeConverter;

@Component
@SuppressWarnings("unchecked")
public class CommentsDao {
	
	@Autowired
	ICommentsRepository iCommentsRepository;
	
	public void createCommentsEntity(LinkedHashMap<Object, Object> commentDetails) {
		Comments comments = new Comments();
		comments.setCommentId((Integer) commentDetails.get("id"));
		comments.setParent((Integer) commentDetails.get("parent"));
		comments.setText((String) commentDetails.get("text"));
		comments.setCommentUser((String) commentDetails.get("by"));
		if(commentDetails.get("kids")!=null)
			comments.setChildComments((Integer) ((List<Integer>) commentDetails.get("kids")).size());
		iCommentsRepository.save(comments);
	}
	
	public List<CommentsDto> getBestComments() {
		List<Comments> allComments = iCommentsRepository.findBestComments();
		List<CommentsDto> allCommentsDto = new ArrayList<>();
		for(Comments dto: allComments) {
			CommentsDto  comments = new CommentsDto();
			comments.setUserName(dto.getCommentUser());
			comments.setCommentText(dto.getText());
			allCommentsDto.add(comments);
		}
		return allCommentsDto;
	}
	
	public void setUserData(LinkedHashMap<Object, Object> userDetails, CommentsDto dto){
		dto.setUserBio((String) userDetails.get("about"));
		dto.setUserSinceInYrs(EpochTimeConverter.calcUserProfileTime((Integer) userDetails.get("created")));
	}
}
