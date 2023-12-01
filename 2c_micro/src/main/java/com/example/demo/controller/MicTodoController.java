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

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String insG() {
		return "mictodo";
	}
	
	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String insP(String user_id,String month, String day,String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		 jdbcTemplate.update("insert into todo values(?,?,?,?)",  "000000",month, day, todo);

		 
//		model.addAttribute("example1", user_id);
//		model.addAttribute("example2", month);
//		model.addAttribute("example3", day);
//		model.addAttribute("example4", todo);

		return "mictodo";
	}
	
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String editG() {
		return "mictodo_edit";
	}

	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String editP(String month, String day,String todo, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		 jdbcTemplate.update("UPDATE todo SET todo = ? WHERE month = ? and day = ? ",todo,month,day);

		 

		return "redirect:/micedit";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String delG() {
		return "mictodo_del";
	}
	
	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String delP( String month,String day, Model model) {

		//DBに繋ぐならこんな感じ(JdbcTemplate)
		jdbcTemplate.update("delete from todo where month = ? and day = ?",month,day);

		

		return "redirect:/micdel";
	}

}
