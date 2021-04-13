package com.hackernews.rest.service;

import java.util.List;

import com.hackernews.rest.dtos.StoryDto;

public interface IStoryService {
	
	public List<StoryDto> getBestStories();
	
	public List<StoryDto> getPastStories();
}
