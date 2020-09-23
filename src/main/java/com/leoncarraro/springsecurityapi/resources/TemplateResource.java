package com.leoncarraro.springsecurityapi.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class TemplateResource {

	@RequestMapping(method = RequestMethod.GET, value = "login")
	public String getLoginView() {
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "courses")
	public String getCoursesView() {
		return "courses";
	}
	
}
