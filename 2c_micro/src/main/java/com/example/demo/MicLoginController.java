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

	public String third() {

		return "miclogin";
}
	@RequestMapping(path = "/micloginresult", method = RequestMethod.POST)

	public String third(Model model,String micloginid,String micpw) {
		List<Map<String, Object>> resultList;
		
		//SELECT文の実行
		resultList = jdbcTemplate.queryForList("select * from miclogin where loginid=? and password=?",micloginid,micpw);
		
		String errormessage ="ログインに失敗しました";;
		
		if(micloginid.length()>=16||micpw.length()>=16) {
			errormessage = "文字数が多すぎます";
		}
		if(!resultList.isEmpty()) {
			return "redirect:michome";
		}
		model.addAttribute("micloginid",errormessage);

		return "miclogin";
}
}

