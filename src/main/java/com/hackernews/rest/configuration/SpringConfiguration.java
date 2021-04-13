package com.hackernews.rest.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.hackernews.rest.client.RestClient;
import com.hackernews.rest.utilities.HackerNewsConstants;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
@ComponentScan(basePackages = {"com.hackernews.rest"})
public class SpringConfiguration extends CachingConfigurerSupport {
	
	@Bean
	public RestClient getRestClient() {
		return new RestClient();
	}
	
	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}
	
	/*
	 * Configuring custom cache for specific time
	 */
	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager(){
		CacheConfiguration ehConfig = new CacheConfiguration();
		ehConfig.setName(HackerNewsConstants.STORY_CACHE);
		ehConfig.setMaxEntriesLocalHeap(1000);
		ehConfig.setTimeToLiveSeconds(900);
		
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(ehConfig);
		return net.sf.ehcache.CacheManager.newInstance(config);
	}
}
