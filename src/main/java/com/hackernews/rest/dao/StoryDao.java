package com.hackernews.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hackernews.rest.dtos.StoryDto;
import com.hackernews.rest.entities.Stories;
import com.hackernews.rest.repo.IStoryRepository;
import com.hackernews.rest.utilities.EpochTimeConverter;

@Component
@SuppressWarnings("unchecked")
public class StoryDao {
	
	@Autowired
	IStoryRepository iStoryRepository;
	
	private Map<Integer, List<Integer>> storyCommentsMap = new HashMap<>();
	
	public Map<Integer, List<Integer>> getStoryCommentsMap() {
		return storyCommentsMap;
	}

	public void saveStoriesAndCommentsData(LinkedHashMap<Object, Object> storyDetails) {
			Stories stories = new Stories();
			stories.setStoryId((Integer) storyDetails.get("id"));
			stories.setTitle((String) storyDetails.get("title"));
			stories.setUrl((String) storyDetails.get("url"));
			stories.setSubmissionTime((Integer) storyDetails.get("time"));
			stories.setScore((Integer) storyDetails.get("score"));
			stories.setUserName((String) storyDetails.get("by"));
			stories.setDescendants((Integer) storyDetails.get("descendants"));
			storyCommentsMap.put(stories.getStoryId(),(List<Integer>) storyDetails.get("kids"));
			iStoryRepository.save(stories);
		}

	public List<StoryDto> getTopStoriesByScore() {
		List<Stories> allStories =  iStoryRepository.findBestStories();
		List<StoryDto> allStoriesDto = new ArrayList<>();
		List<Integer> storyIds = new ArrayList<>();
		for(Stories dto: allStories) {
			StoryDto stories = new StoryDto();
			stories.setTitle(dto.getTitle());
			stories.setUrl(dto.getUrl());
			stories.setSubmissionTime(EpochTimeConverter.convertEpochTime(dto.getSubmissionTime()));
			stories.setScore(dto.getScore());
			stories.setUserName(dto.getUserName());
			allStoriesDto.add(stories);
			storyIds.add(dto.getStoryId());
		}
		this.updateServedFlag(storyIds);
		return allStoriesDto;
	}

	public List<StoryDto> getPastStories() {
		List<Stories> allStories =  iStoryRepository.findPastStories();
		List<StoryDto> allStoriesDto = new ArrayList<>();
		for(Stories dto: allStories) {
			StoryDto stories = new StoryDto();
			stories.setTitle(dto.getTitle());
			stories.setUrl(dto.getUrl());
			stories.setScore(dto.getScore());
			stories.setUserName(dto.getUserName());
			allStoriesDto.add(stories);
		}
		return allStoriesDto;
	}

	public void updateServedFlag(List<Integer> storyIds) {
		iStoryRepository.updateServedFlag(storyIds);
	}

}
