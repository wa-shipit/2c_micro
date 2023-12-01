package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicTodoController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/mictodo", method = RequestMethod.GET)
	public String copGet() {
		
		return "mictodo";
	}
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String copGet1() {
		
		return "micadd";
	}
	
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String copGet2() {
		
		return "micedit";
	}
	
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String copGet3() {
		
		return "micdel";
	}
	
	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String copPost(String id, String month, String day, String todo,Model model) {
	
		//DBに繋ぐならこんな感じ(JdbcTemplate)
			jdbcTemplate.update("INSERT INTO todo values(?,?,?)",month,day,todo);

		

		return "micadd";
	}
	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String cop(String month, String day, String todo,Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
			jdbcTemplate.update("INSERT INTO todo values(?,?,?)",month,day,todo);

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);

		return "micedit";
	}
	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String c(String month, String day, String todo,Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
			jdbcTemplate.update("INSERT INTO todo values(?,?,?)",month,day,todo);

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);

		return "todo";
	}
}
