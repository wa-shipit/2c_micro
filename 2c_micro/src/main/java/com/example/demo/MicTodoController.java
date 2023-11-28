package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicTodoController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String addGet() {
		return "mictodoadd";
	}
	
	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String addPost(String month, String day,String todo) {
		
		//(JdbcTemplate)
		jdbcTemplate.update("INSERT INTO todo(user_id,month, day, todo) VALUES('',?, ?, ?)",month,day,todo);

		return "mictodoadd";
	}
	
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String editGet() {
		return "mictodoedit";
	}
	
	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String editPost(String month, String day,String todo) {
		
		//(JdbcTemplate)
		jdbcTemplate.update("UPDATE todo SET todo = ? WHERE month = ? and day = ?",todo,month,day);

		return "mictodoedit";
	}
	
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String delGet() {
		return "mictododel";
	}
	
	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String delPost(String month, String day) {
		
		//(JdbcTemplate)
		jdbcTemplate.update("DELETE FROM todo WHERE month = ? and day = ?",month,day);

		return "mictododel";
	}
	
}
