package com.hackernews.rest.service;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hackernews.rest.client.RestClient;
import com.hackernews.rest.dao.StoryDao;
import com.hackernews.rest.dtos.StoryDto;
import com.hackernews.rest.utilities.HackerNewsConstants;

@Service
@SuppressWarnings("unchecked")
public class StoryServiceImpl implements IStoryService{
	
	@Autowired
	RestClient restClient;
	
	@Autowired
	StoryDao storyDao;
	
	@Override
	@Cacheable(value=HackerNewsConstants.STORY_CACHE,key="'getall'")
	public List<StoryDto> getBestStories(){
		ResponseEntity<?> rs;
		
		//fetching best stories from Hacker News API
		rs = restClient.callRestService(HackerNewsConstants.GET_BEST_STORIES_URL);
		
		List<Integer> storyIds = (List<Integer>) rs.getBody();
		
		//fetching story data for each story
		for(Integer storyId : storyIds) {
			rs = restClient.callRestService(HackerNewsConstants.COMMENTS_STORY_BASE_URL + storyId + HackerNewsConstants.API_END_URL);
			LinkedHashMap<Object, Object> storyDetails = (LinkedHashMap<Object, Object>) rs.getBody();

			//saving each story data in database
			storyDao.saveStoriesAndCommentsData(storyDetails);
		}
		
		//fetching top 10 stories based on score
		return storyDao.getTopStoriesByScore();
	}
	
	@Override
	public List<StoryDto> getPastStories() {
		
		//fetching all stories that were served in past
		return storyDao.getPastStories();
	}	
	 
}
