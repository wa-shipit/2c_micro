package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicUserController {
		@Autowired
		JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/micuser", method = RequestMethod.GET)
	public String viewPage() {
			return "micuser";
			}
	
	@RequestMapping(path = "/micuser", method = RequestMethod.POST)
	public String viewPage(String loginid, String password) {
		jdbcTemplate.update("INSERT INTO miclogin VALUES (?,?);", loginid, password);
			return "redirect:/micuser";
		}
				} 