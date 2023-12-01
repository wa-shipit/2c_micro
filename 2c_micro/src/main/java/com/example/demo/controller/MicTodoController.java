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
		public String copPost(String month, String day, String todo,Model model) {
	 String user_id = "1";
	 //DBに繋ぐならこんな感じ(JdbcTemplate)
	 jdbcTemplate.update("INSERT INTO todo values(?,?,?,?)",user_id,month,day,todo);
	 model.addAttribute("month", month);
	 model.addAttribute("day", day);
	 model.addAttribute("todo", todo);
	 return "micadd";
		}
	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String cop(String month, String day, String todo,Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
	       String updateQuery = "UPDATE todo SET todo = ? WHERE month = ? AND day = ?";
	       int updatedRows =  jdbcTemplate.update(updateQuery, todo, month, day);

	        if (updatedRows > 0) {
	            // 更新成功
	        	model.addAttribute("loginFailedMessage", "変更完了！！！");
	        } else {
	            // 更新失敗
	        	model.addAttribute("loginFailedMessage", "変更失敗！！！");
	        }
		
		
		return "micedit";
	}
			@RequestMapping(path = "/micdel", method = RequestMethod.POST)
			public String c(String month, String day, String todo,Model model) {
		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("DELETE FROM todo WHERE month=? AND day=? AND todo=?",month,day,todo);
		
		return "micdel";
		}
}
