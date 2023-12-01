package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicLoginController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/miclogin", method = RequestMethod.GET)
	public String copGet() {
		return "miclogin";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/miclogin", method = RequestMethod.POST)
	public String copPost(String micloginid, String micpw,String error,String loger, Model model) {
		
	int x = micloginid.length();
	int y = micpw.length();
	error = "文字数が越えました";
	loger = "ログイン画面に戻ります";
	
	if(x >= 16 && y >= 16) {
		model.addAttribute("error", error);
	}
		
	List<Map<String, Object>> resultList;
	List<Map<String, Object>> resultList2;
		//DBに繋ぐならこんな感じ(JdbcTemplate)
	resultList = jdbcTemplate.queryForList
			("SELECT loginid FROM miclogin WHERE loginid = ?", micloginid);
	
	resultList2 = jdbcTemplate.queryForList
			("SELECT password FROM miclogin WHERE password = ?", micpw);
	
	System.out.print(resultList);
	if((resultList.equals(micloginid)) && (resultList2.equals(micpw))) {

		return "redirect:/michome";
	}else {
		model.addAttribute("loger", loger);
		return "miclogin";
	}
	

	
	}
}
