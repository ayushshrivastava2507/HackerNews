package com.hackernews.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.hackernews.rest.dtos.CommentsDto;
import com.hackernews.rest.dtos.StoryDto;
import com.hackernews.rest.service.ICommentsService;
import com.hackernews.rest.service.IStoryService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private IStoryService storyService;
	
	@Autowired
	private ICommentsService commentsService;
	
	@GetMapping(value = "/past-stories")
	public List<StoryDto> getPastStories(){
		return storyService.getPastStories();
	}
	
	@GetMapping(value = "/best-stories")
	public List<StoryDto> getBestStories(){
		return storyService.getBestStories();
	}
	
	@GetMapping(value = "/comments/{storyId}")
	public List<CommentsDto> getBestComments(@PathVariable Integer storyId){
		return commentsService.getBestComments(storyId);
	}
}
