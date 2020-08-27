package com.alexander.maven.archetypes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/")
public class UrlViewController {
	
	//Used to convert page requests to views
	@RequestMapping(value="{page}")
	public String forward(@PathVariable String page){
		System.out.println("Request found for page "+page);
		return page;
	}
	
	@RequestMapping(value="{subfolder}/{page}")
	public String forward(@PathVariable String subfolder, @PathVariable String page){
		System.out.println("Request found for subfolder "+subfolder+" and page "+page);
		return subfolder+"/"+page;
	}	
	
	public UrlViewController(){
		System.out.println("In the url view controller constructor");
	}
}
