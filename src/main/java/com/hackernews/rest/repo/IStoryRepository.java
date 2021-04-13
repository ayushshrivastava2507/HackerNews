package com.hackernews.rest.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hackernews.rest.entities.Stories;

@Repository
public interface IStoryRepository extends JpaRepository<Stories, Integer>{
	
	  @Query(value = "Select * from Stories s order by s.score desc limit 10", nativeQuery=true) 
	  public List<Stories> findBestStories();
	  
	  @Query(value = "Select * from Stories s where s.served = 'true'", nativeQuery=true) 
	  public List<Stories> findPastStories();
	  
	  @Modifying(clearAutomatically = true)
	  @Transactional
	  @Query(value = "Update Stories s Set s.served = TRUE where s.story_Id in :storyIds", nativeQuery=true)
	  public void updateServedFlag(@Param("storyIds") List<Integer> types);
	 
}
