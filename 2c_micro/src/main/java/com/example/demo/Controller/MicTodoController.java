package com.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class MicTodoController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//INSERT（登録）用メソッド
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String postIns() {
		
	
		return "micadd";
	}
	
	//INSERT（登録）用メソッド
	@RequestMapping(path = "/micedit", method = RequestMethod.GET)
	public String postUpd() {
		
	
		return "micedit";
	}
	
	//INSERT（登録）用メソッド
	@RequestMapping(path = "/micdel", method = RequestMethod.GET)
	public String PostDelete() {
		
	
		return "micdel";
	}
	

	
	//INSERT（登録）用メソッド
		@RequestMapping(path = "/micadd", method = RequestMethod.POST)
		public String postIns(int user_id, int month,int day,String todo) {
			
			jdbcTemplate.update("insert into todo (user_id,month,day,todo) value (?,?,?,?)",user_id,month,day,todo);
			

			return "redirect:/micadd";
		}
		//UPDATE(更新)用メソッド
		@RequestMapping(path = "/micedit", method = RequestMethod.POST)
		public String postUpd(int user_id, int month,int day,String todo) {
			
			jdbcTemplate.update("UPDATE todo SET month=?,day=?,todo=? WHERE user_id=?",month,day,todo,user_id);

			return "redirect:/micedit";
		}
		
		@RequestMapping(path = "/micdel", method = RequestMethod.POST)
		public String PostDelete(int user_id) throws IOException {

			//データ削除SQL実行
			jdbcTemplate.update("DELETE FROM todo WHERE user_id = ?", user_id);

			return "redirect:/micdel";
		}
		
}
