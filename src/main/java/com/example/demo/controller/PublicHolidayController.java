package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.PublicHoliday;
import com.example.demo.service.PublicHolidayService;


@Controller
public class PublicHolidayController {

	@Autowired 
	private PublicHolidayService service;
	
	@RequestMapping("/publicholiday")
	public String Viewpublicholidaypage(Model model)
	{
		List<PublicHoliday> listPublicHolidays=service.listAll();
		model.addAttribute("listPublicHolidays", listPublicHolidays);
		
		return "publicholidaylist";
	}
	
	@RequestMapping("/newpublicholiday")
	public String showNewphForm(Model model)
	{
		PublicHoliday publicholday= new PublicHoliday();
		model.addAttribute("publicholday",publicholday);
		
		return "newpublicholiday";
	}
	
	@RequestMapping(value="/savepublicholiday",method = RequestMethod.POST)
	public String savepublicholiday(@ModelAttribute("publicholday") PublicHoliday publicholday)
	{
		service.save(publicholday);
		return "forward:/publicholiday";
	}
	
	@RequestMapping(value="/editpublicholiday/{name}",method=RequestMethod.GET)
	public ModelAndView showEditPHForm(@PathVariable("name") String name)
	{
		ModelAndView mav = new ModelAndView("edit_publicholiday");
		PublicHoliday publicholiday= service.get(name);
		mav.addObject("publicholiday", publicholiday);		
		return mav;
	}
	@RequestMapping(value="/editpublicholiday/{name}",method=RequestMethod.POST)
	public ModelAndView editPH(@PathVariable("name") String name,@ModelAttribute @Valid PublicHoliday publicholiday)
	{
		ModelAndView mav = new ModelAndView("forward:/publicholiday");
		String message="Public Holiday was successfully updated";
		System.out.println(message);
		service.changeUser(publicholiday);
		return mav;
	}
	
	
	@RequestMapping(value="/deletepublicholiday/{name}",method=RequestMethod.GET)
	public String deletePH(@PathVariable("name") String name)
	{
		service.delete(name);
		
		return "redirect:/publicholiday";
	}

}
