package com.example.demo.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

@Service
public class AdminAddBookImageService {
	
	public List<String> findAllImages() throws IOException{
		ResourcePatternResolver resolver =
				new PathMatchingResourcePatternResolver();
		
		Resource[] resources = resolver.getResources("classpath*:/static/images/*");
		
		return Arrays.stream(resources)
				.map(Resource::getFilename)
				.filter(name -> name != null)
				.filter(name -> !name.equalsIgnoreCase("Noimage.png"))
				.filter(name -> name.matches("(?i).+\\.(png|jpg|jpeg|gif|webp)"))
				.sorted()
				.toList();
		
	}
	

}
