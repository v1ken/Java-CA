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

import com.example.demo.model.LeaveBalance;
import com.example.demo.service.LeaveBalanceService;


@Controller
public class LeaveBalanceController {

	@Autowired 
	private LeaveBalanceService service;
	
	@RequestMapping("/leavebalance")
	public String ViewBalancePage(Model model)
	{
		List<LeaveBalance> listLeaveBalances=service.listAll();
		model.addAttribute("listLeaveBalances", listLeaveBalances);
		
		return "leavebalancelist";
	}
	
	
	@RequestMapping("/newleavebalance")
	public String showNewLeaveBalanceForm(Model model) {
		LeaveBalance leavebalance = new LeaveBalance();
		model.addAttribute("leavebalance", leavebalance);

		return "newleavebalance";
	}

	@RequestMapping(value = "/saveleavebalance", method = RequestMethod.POST)
	public String saveLeaveBalance(@ModelAttribute("leavebalance") LeaveBalance leavebalance) {
		service.save(leavebalance);
		return "forward:/leavebalance";
	}
	
	@RequestMapping(value="/editeleavebalance/{userleaveid}",method=RequestMethod.GET)
	public ModelAndView showEditLeaveBalanceForm(@PathVariable("userlevaid") Integer userleaveid)
	{
		ModelAndView mav = new ModelAndView("edit_leavebalance");
		LeaveBalance leavebalance= service.get(userleaveid);
		mav.addObject("leavebalance", leavebalance);		
		return mav;
	}
	@RequestMapping(value="/editeleavebalance/{userleaveid}",method=RequestMethod.POST)
	public ModelAndView editLeaveBalance(@PathVariable("userleaveid") Integer userleaveid,
			@ModelAttribute @Valid LeaveBalance leavebalance)
	{
		ModelAndView mav = new ModelAndView("forward:/leavebalance");
		String message="Leave Balance was successfully updated";
		System.out.println(message);
		service.changeUser(leavebalance);
		return mav;
	}
	
	
	@RequestMapping(value="/deleteleavebalance/{userleaveid}",method=RequestMethod.GET)
	public String deleteLeaveBalance(@PathVariable("userleaveid") Integer userleaveid)
	{
		service.delete(userleaveid);
		
		return "redirect:/leavebalance";
	}

}
