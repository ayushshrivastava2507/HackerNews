package com.hackernews.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hackernews.rest.RestApplication;
import com.hackernews.rest.client.RestClient;
import com.hackernews.rest.dao.CommentsDao;
import com.hackernews.rest.dao.StoryDao;
import com.hackernews.rest.dtos.CommentsDto;
import com.hackernews.rest.dtos.StoryDto;
import com.hackernews.rest.entities.Comments;
import com.hackernews.rest.repo.ICommentsRepository;
import com.hackernews.rest.repo.IStoryRepository;
import com.hackernews.rest.utilities.HackerNewsConstants;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes= RestApplication.class)
public class StoriesServiceTest {
	
	@InjectMocks
	StoryServiceImpl storyService;
	
	@Mock
	RestClient restClient;
	
	@Mock
	StoryDao storyDao;
	
	@MockBean
	IStoryRepository iStoryRepository;
	
	@Test
	void getBestComments_Case1() {
		
		String url = HackerNewsConstants.GET_BEST_STORIES_URL;
		List<StoryDto> storyDto = new ArrayList<>();
		StoryDto dto = new StoryDto();
		dto.setScore(20);
		dto.setSubmissionTime("13/04/2021 18:05:05");
		dto.setTitle("Hi Test");
		storyDto.add(dto);
		
		List<Integer> commentsList = Arrays.asList(26755925);
		ResponseEntity re = new ResponseEntity(commentsList, HttpStatus.ACCEPTED);
		when(restClient.callRestService(url)).thenReturn(re);
		
		ResponseEntity re1 = new ResponseEntity(HttpStatus.ACCEPTED);
		when(restClient.callRestService("https://hacker-news.firebaseio.com/v0/item/26755925.json?print=pretty")).thenReturn(re1);
		
		doNothing().when(storyDao).saveStoriesAndCommentsData(new LinkedHashMap<>());
		
		when(storyDao.getTopStoriesByScore()).thenReturn(storyDto);
		
		List<StoryDto> result = storyService.getBestStories();
		Assert.assertEquals(storyDto, result);
		
	}
	
	@Test
	void getBestComments_Case2() {
		
		String url = HackerNewsConstants.GET_BEST_STORIES_URL;
		List<StoryDto> storyDto = new ArrayList<>();
		StoryDto dto = new StoryDto();
		dto.setScore(20);
		dto.setSubmissionTime("13/04/2021 18:05:05");
		dto.setTitle("Hi Test");
		storyDto.add(dto);
		
		//passing empty list for case 2
		ResponseEntity re = new ResponseEntity(new ArrayList<>(), HttpStatus.ACCEPTED);
		when(restClient.callRestService(url)).thenReturn(re);
		
		ResponseEntity re1 = new ResponseEntity(HttpStatus.ACCEPTED);
		when(restClient.callRestService("https://hacker-news.firebaseio.com/v0/item/26755925.json?print=pretty")).thenReturn(re1);
		
		doNothing().when(storyDao).saveStoriesAndCommentsData(new LinkedHashMap<>());
		
		when(storyDao.getTopStoriesByScore()).thenReturn(storyDto);
		
		List<StoryDto> result = storyService.getBestStories();
		Assert.assertEquals(storyDto, result);
		
	}
}
