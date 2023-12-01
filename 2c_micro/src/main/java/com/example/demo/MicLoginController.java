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
public class MicLoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path = "/miclogin", method = RequestMethod.GET)
	public String copGet(Model model) {
		return "miclogin";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/miclogin", method = RequestMethod.POST)
	public String copPost(String micloginid, String micpw, Model model) {

		int k = micloginid.length();
		int a = micpw.length();
		if(k>=16||a>=16) {
			//エラーメッセージをmodelにしまう
			String message="文字数が多すぎます";
			model.addAttribute("over",message);
			//文字数制限超えてたらログイン画面にreturn
			return "miclogin";
		}
		//DBに繋ぐならこんな感じ(JdbcTemplate)
		List<Map<String, Object>> resultList;
		resultList= jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid=? and password=?",micloginid,micpw);
		
		int x = resultList.size();
		if(1<=x) {
			return "redirect:/michome";
			
		}else {
			String message="ログインに失敗しました";
			model.addAttribute("kekka",message);
			return "miclogin";
			
		}
	

	}
}
