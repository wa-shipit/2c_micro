package com.example.demo;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class MicLoginController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
//    @Autowired
//    private UserRepository userRepository;
    @RequestMapping(path = "/miclogin", method = RequestMethod.GET)
    public String login() {
        return "/miclogin";
    }
    @RequestMapping(path = "//miclogin", method = RequestMethod.POST)
    public String login1(String micloginid, String micpw) {
//      DBに繋ぐならこんな感じ(JdbcTemplate)
      	List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE loginid = ? AND password = ?",micloginid,micpw);
        if (resultList.isEmpty()) {
            // 一致するユーザーが見つからない場合、ログインエラーページにリダイレクト
        	System.out.println("ログインに失敗しました");
        	return "/miclogin";
        } else {
            // 一致するユーザーが見つかった場合、ホームページにリダイレクト
            return "/michome";
        }
    }
}