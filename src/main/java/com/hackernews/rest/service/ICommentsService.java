package com.hackernews.rest.service;

import java.util.List;

import com.hackernews.rest.dtos.CommentsDto;

public interface ICommentsService {
	
	public List<CommentsDto> getBestComments(Integer storyId) ;
}
