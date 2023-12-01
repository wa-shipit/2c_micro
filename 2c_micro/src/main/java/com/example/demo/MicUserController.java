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
	
	@RequestMapping(path = "/useradd", method = RequestMethod.GET)
    public String userGet() {
        return "micuser";
    }

	@RequestMapping(path = "/useradd", method = RequestMethod.POST)
	public String userPost(String userId,String password){
	// 入力検証やデータベースへの登録処理を追加
		jdbcTemplate.update("INSERT INTO miclogin (loginid, password) VALUES (?,?);",userId,password);
System.out.println("通ったよー");
            return "redirect:/useradd"; 
    }
	
	

}
