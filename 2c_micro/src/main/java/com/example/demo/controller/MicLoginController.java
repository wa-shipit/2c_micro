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
	public String copPost(String micloginid, String micpw, Model model) {

	
		String query = "SELECT * FROM miclogin WHERE loginid = ? AND password = ?";
		   if (micloginid.length() >= 16 || micpw.length() >= 16) {
	            model.addAttribute("loginFailedMessage2", "IDまたはパスワードが長すぎます");
	            return "miclogin";
	        }
		
	        List<Map<String, Object>> res = jdbcTemplate.queryForList(query, micloginid, micpw);
		
	       if (!res.isEmpty()) {
            //ログイン成功
            return "redirect:/michome";
            
        } else {
            // ログイン失敗
           model.addAttribute("loginFailedMessage", "ログイン失敗～～～残念！！！");
           

		
		return "miclogin";
        }
	
	}
}
