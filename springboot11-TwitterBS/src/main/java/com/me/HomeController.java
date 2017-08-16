package com.me;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	 @Autowired
	 CourseRepository courseRepository;
	 
	 @RequestMapping("/")
	    public String listJobs(Model model){
		 model.addAttribute("courses", courseRepository.findAll());
	        return"list";
	 }
	 
	 @RequestMapping("/add")
	    public String jobForm(Model model){
	        model.addAttribute("course", new Course());
	        return"courseform";
	 }
	 
	 @RequestMapping("/process")
	    public String processForm(@Valid Course course, BindingResult result){
		
		 if (result.hasErrors()) {
			 return "coureform";
		 }
		 courseRepository.save(course);
	     return"redirect:/";
	 }
	 
	 @RequestMapping("/detail/{id}")
	    public String showJob(@PathVariable("id") long id, Model model){
	        model.addAttribute("course", courseRepository.findOne(id));
	        return "show";
	 }
	 
	 @RequestMapping("/update/{id}")
	    public String updateCourse(@PathVariable("id") long id, Model model){
	        model.addAttribute("course", courseRepository.findOne(id));
	        return "courseform";
	 }
	 	 
	 @RequestMapping("/delete/{id}")
	    public String delJob(@PathVariable("id") long id, Model model){
		 courseRepository.delete(id);
	        return"redirect:/";
	 }

}
