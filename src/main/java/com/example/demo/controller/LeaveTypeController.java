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

import com.example.demo.model.LeaveType;
import com.example.demo.service.LeaveTypeService;


@Controller
public class LeaveTypeController {

	@Autowired 
	private LeaveTypeService service;
	
	@RequestMapping("/leavetype")
	public String ViewHieracyPage(Model model)
	{
		List<LeaveType> listLeaveTypes=service.listAll();
		model.addAttribute("listLeaveTypes", listLeaveTypes);
		
		return "leavelist";
	}
	
	@RequestMapping("/newleavetype")
	public String showNewEmployeeForm(Model model)
	{
		LeaveType leavetype= new LeaveType();
		model.addAttribute("leavetype",leavetype);
		
		return "newleavetype";
	}
	
	@RequestMapping(value="/saveleavetype",method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("leavetype") LeaveType leavetype)
	{
		service.save(leavetype);
		return "forward:/leavetype";
	}
	
	@RequestMapping(value="/editeleavetype/{typeId}",method=RequestMethod.GET)
	public ModelAndView showEditUserForm(@PathVariable("typeId") String typeId)
	{
		ModelAndView mav = new ModelAndView("edit_leavetype");
		LeaveType leavetype= service.get(typeId);
		mav.addObject("leavetype", leavetype);		
		return mav;
	}
	@RequestMapping(value="/editeleavetype/{typeId}",method=RequestMethod.POST)
	public ModelAndView editUser(@PathVariable("typeId") String typeId,@ModelAttribute @Valid LeaveType leavetype)
	{
		ModelAndView mav = new ModelAndView("forward:/leavetype");
		String message="employee was successfully updated";
		System.out.println(message);
		service.changeUser(leavetype);
		return mav;
	}
	
	
	@RequestMapping(value="/deleteleavetype/{typeId}",method=RequestMethod.GET)
	public String deleteUser(@PathVariable("typeId") String typeId)
	{
		service.delete(typeId);
		
		return "redirect:/leavetype";
	}

}
