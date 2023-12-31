package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicHomeController {
	@Autowired
	JdbcTemplate jdbcTemplate;
    @RequestMapping(path = "/michome", method = RequestMethod.GET)
    public String third(Model model) {
    	List<Map<String, Object>> resultList;
    	resultList = jdbcTemplate.queryForList("select * from todo");
    	model.addAttribute("selectResult", resultList);
        return "michome";
    }

    
    }

   

