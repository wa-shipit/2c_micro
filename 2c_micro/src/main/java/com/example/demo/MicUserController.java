package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicUserController {

	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//HTML表示
	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String Userget() {
		return "micuser";
	}

	//SQL
	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	
	public String Userpost(String loginid, String password, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("insert into miclogin values(?,?)",loginid,password);

		return "micuser";
	}
}
