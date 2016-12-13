package com.wabu.health.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {

private static final Integer DEFAULT_PAGE_SIZE = 10;
	
	public static Pageable buildPageRequest(int pageNum, int pageSize, Sort sort){
		return new PageRequest(pageNum, pageSize, sort);
	}
	
	public static Pageable buildPageRequest(int pageNum, int pageSize){
		return new PageRequest(pageNum, pageSize);
	}
	
	public static Pageable buildPageRequest(int pageNum){
		return buildPageRequest(pageNum, DEFAULT_PAGE_SIZE);
	}
	
	/*public static <T> PageWrapper<T> buildPageWrapper(Page<T> page, String url){
		return new PageWrapper<T>(page, url);
	}
	*/
	
}
