package com.hackernews.rest.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hackernews.rest.client.RestClient;
import com.hackernews.rest.dao.CommentsDao;
import com.hackernews.rest.dao.StoryDao;
import com.hackernews.rest.dtos.CommentsDto;
import com.hackernews.rest.utilities.HackerNewsConstants;

@Service
@SuppressWarnings("unchecked")
public class CommentsServiceImpl implements ICommentsService{
	
	@Autowired
	RestClient restClient;
	
	@Autowired
	StoryDao storyDao;
	
	@Autowired
	CommentsDao commentsDao;
	
	@Override
	public List<CommentsDto> getBestComments(Integer storyId) {
		ResponseEntity<?> rs;
		this.storeCommentsForStory(storyId);
		
		//fetching top 10 comments based on child comments
		List<CommentsDto> comments = commentsDao.getBestComments(); 
		
		for (CommentsDto dto : comments) {
			//fetching user data for every comment
			rs = restClient
					.callRestService(HackerNewsConstants.USER_BASE_URL + dto.getUserName() + HackerNewsConstants.API_END_URL);
			LinkedHashMap<Object, Object> userDetails = (LinkedHashMap<Object, Object>) rs.getBody();
			commentsDao.setUserData(userDetails, dto);
		}
		return comments;
	  }
	
	public void storeCommentsForStory(Integer storyId){
		ResponseEntity<?> rs;
		Map<Integer,List<Integer>> commentIds = storyDao.getStoryCommentsMap();
		if (commentIds != null && !commentIds.isEmpty()) {
			
			//fetching all comments for the given storyID
			for (Integer commentId : commentIds.get(storyId)) {
				rs = restClient.callRestService(
						HackerNewsConstants.COMMENTS_STORY_BASE_URL + commentId + HackerNewsConstants.API_END_URL);
				LinkedHashMap<Object, Object> commentDetails = (LinkedHashMap<Object, Object>) rs.getBody();
				
				//storing comments data in database
				commentsDao.createCommentsEntity(commentDetails);
			}
		}
	}
}
