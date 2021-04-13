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
import com.hackernews.rest.entities.Comments;
import com.hackernews.rest.repo.ICommentsRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes= RestApplication.class)
public class CommentsServiceTest {
	
	@InjectMocks
	CommentsServiceImpl commentsService;
	
	@Mock
	RestClient restClient;
	
	@Mock
	CommentsDao commentsDao;
	
	@Mock
	StoryDao storyDao;
	
	@MockBean
	ICommentsRepository iCommentsRepository;
	
	@Test
	void getBestComments_Case1() {
		
		Integer storyId = 26755252;
		List<Integer> commentsList = Arrays.asList(26755925,26757780, 26756919, 26755626);
		
		List<Comments> comments = new ArrayList<>();
		Comments dto = new Comments();
		dto.setParent(26755252);
		dto.setText("This is a test");
		dto.setChildComments(5);
		dto.setCommentId(26755252);
		comments.add(dto);
		
		List<CommentsDto> commentsDto = new ArrayList<>();
		CommentsDto dto1 = new CommentsDto();
		dto1.setUserName("jl");
		dto1.setCommentText("This is a test");
		dto1.setUserBio("This is a test");
		dto1.setUserSinceInYrs(14L);
		commentsDto.add(dto1);
		
		Map<Integer,List<Integer>> storyMap = new HashMap<>();
		storyMap.put(storyId, commentsList);

		when(storyDao.getStoryCommentsMap()).thenReturn(storyMap);
		doNothing().when(commentsDao).createCommentsEntity(new LinkedHashMap<>());
		
		ResponseEntity re = new ResponseEntity(HttpStatus.ACCEPTED);
		when(restClient.callRestService(ArgumentMatchers.anyString())).thenReturn(re);
		
		doNothing().when(commentsDao).setUserData(new LinkedHashMap<>(), new CommentsDto());
		
		when(iCommentsRepository.findBestComments()).thenReturn(comments);
		
		when(commentsDao.getBestComments()).thenReturn(commentsDto);
		
		//calling service method
		List<CommentsDto> result = commentsService.getBestComments(storyId);
		assertEquals(result.size(), commentsDto.size());
		
	}
	
	@Test
	void getBestComments_Case2() {
		
		Integer storyId = 26755252;
		
		List<Comments> comments = new ArrayList<>();
		Comments dto = new Comments();
		dto.setParent(26755252);
		dto.setText("This is a test");
		dto.setChildComments(5);
		dto.setCommentId(26755252);
		comments.add(dto);
		
		List<CommentsDto> commentsDto = new ArrayList<>();
		CommentsDto dto1 = new CommentsDto();
		dto1.setUserName("jl");
		dto1.setCommentText("This is a test");
		dto1.setUserBio("This is a test");
		dto1.setUserSinceInYrs(14L);
		commentsDto.add(dto1);
		
		//setting empty map for case 2
		Map<Integer,List<Integer>> storyMap = new HashMap<>();

		when(storyDao.getStoryCommentsMap()).thenReturn(storyMap);
		doNothing().when(commentsDao).createCommentsEntity(new LinkedHashMap<>());
		
		ResponseEntity re = new ResponseEntity(HttpStatus.ACCEPTED);
		when(restClient.callRestService(ArgumentMatchers.anyString())).thenReturn(re);
		
		doNothing().when(commentsDao).setUserData(new LinkedHashMap<>(), new CommentsDto());
		
		when(iCommentsRepository.findBestComments()).thenReturn(comments);
		
		when(commentsDao.getBestComments()).thenReturn(commentsDto);
		
		//calling service method
		List<CommentsDto> result = commentsService.getBestComments(storyId);
		assertEquals(result.size(), commentsDto.size());
		
	}
	
	@Test
	void getBestComments_Case3() {
		
		//passing storyId which is not present in map for case 3
		Integer storyId = 1;
		List<Integer> commentsList = Arrays.asList(26755925,26757780, 26756919, 26755626);
		
		List<Comments> comments = new ArrayList<>();
		Comments dto = new Comments();
		dto.setParent(26755252);
		dto.setText("This is a test");
		dto.setChildComments(5);
		dto.setCommentId(26755252);
		comments.add(dto);
		
		List<CommentsDto> commentsDto = new ArrayList<>();
		CommentsDto dto1 = new CommentsDto();
		dto1.setUserName("jl");
		dto1.setCommentText("This is a test");
		dto1.setUserBio("This is a test");
		dto1.setUserSinceInYrs(14L);
		commentsDto.add(dto1);
		
		Map<Integer,List<Integer>> storyMap = new HashMap<>();
		storyMap.put(storyId, commentsList);

		when(storyDao.getStoryCommentsMap()).thenReturn(storyMap);
		doNothing().when(commentsDao).createCommentsEntity(new LinkedHashMap<>());
		
		ResponseEntity re = new ResponseEntity(HttpStatus.ACCEPTED);
		when(restClient.callRestService(ArgumentMatchers.anyString())).thenReturn(re);
		
		doNothing().when(commentsDao).setUserData(new LinkedHashMap<>(), new CommentsDto());
		
		when(iCommentsRepository.findBestComments()).thenReturn(comments);
		
		when(commentsDao.getBestComments()).thenReturn(commentsDto);
		
		//calling service method
		List<CommentsDto> result = commentsService.getBestComments(storyId);
		assertEquals(result.size(), commentsDto.size());
		
	}
	
	@Test
	void getBestComments_Case4() {
		
		Integer storyId = 26755252;
		List<Integer> commentsList = Arrays.asList(26755925,26757780, 26756919, 26755626);
		
		List<Comments> comments = new ArrayList<>();
		Comments dto = new Comments();
		dto.setParent(26755252);
		dto.setText("This is a test");
		dto.setChildComments(5);
		dto.setCommentId(26755252);
		comments.add(dto);
		
		Map<Integer,List<Integer>> storyMap = new HashMap<>();
		storyMap.put(storyId, commentsList);

		when(storyDao.getStoryCommentsMap()).thenReturn(storyMap);
		doNothing().when(commentsDao).createCommentsEntity(new LinkedHashMap<>());
		
		ResponseEntity re = new ResponseEntity(HttpStatus.ACCEPTED);
		when(restClient.callRestService(ArgumentMatchers.anyString())).thenReturn(re);
		
		doNothing().when(commentsDao).setUserData(new LinkedHashMap<>(), new CommentsDto());
		
		when(iCommentsRepository.findBestComments()).thenReturn(comments);
		
		//passing empty list for case 4
		when(commentsDao.getBestComments()).thenReturn(new ArrayList<>());
		
		//calling service method
		List<CommentsDto> result = commentsService.getBestComments(storyId);
		Assert.assertEquals(new ArrayList<>(), result);
		
	}
}
