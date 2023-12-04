package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicTodoController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String addGet() {
		return "mictodoadd";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String addPost(String month,String day,String todo) {

	jdbcTemplate.update("insert into todo (user_id,month,day,todo) values (999999,?,?,?)",month,day,todo);


		return "redirect:/michome";
	}

	
	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String editGet() {
		return "mictodoedit";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micedit", method = RequestMethod.POST)
	public String editPost(String month,String day,String todo) {

	jdbcTemplate.update("update todo set todo = ? where month = ? and day = ?",todo,month,day);


		return "redirect:/michome";
	}
	

	
	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String delGet() {
		return "mictododel";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micdel", method = RequestMethod.POST)
	public String delPost(String month,String day) {

	jdbcTemplate.update("delete from todo where month = ? and day = ?",month,day);


		return "redirect:/michome";
	}
}
