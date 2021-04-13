package com.hackernews.rest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hackernews.rest.entities.Comments;

public interface ICommentsRepository extends JpaRepository<Comments, Integer>{
	  
	  @Query(value = "Select * from Comments s order by s.child_comments desc limit 10", nativeQuery=true) 
	  public List<Comments> findBestComments();
}	
