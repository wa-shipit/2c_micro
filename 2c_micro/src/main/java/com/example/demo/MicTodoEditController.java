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
public class MicTodoEditController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//コピペ用サンプル(ページ表示用メソッド)
	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String addGet() {
		return "mictodoadd";
	}

	//コピペ用サンプル（画面から何か入力をした時用）
	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String addPost(int month, int date, String todo, Model model) {
	    // DBに繋ぐならこんな感じ(JdbcTemplate)
	    List<Map<String, Object>> resultList;
	    resultList = jdbcTemplate.queryForList("SELECT * FROM todo WHERE month = ? AND day = ?", month, date);

	    
	    model.addAttribute("resultList",resultList);
	    if (!resultList.isEmpty()) {
	        model.addAttribute("errorMessage", "予定がすでに入っています");
	        return "mictodoadd";
	    }

	    // DBに繋ぐならこんな感じ(JdbcTemplate)
	    jdbcTemplate.update("INSERT INTO todo (user_id,month, day, todo) VALUES (1,?, ?, ?)", month, date, todo);

	    return "mictodoadd";
	}
	
	
	    //コピペ用サンプル(ページ表示用メソッド)
		@RequestMapping(path = "/micedit", method = RequestMethod.GET)
		public String editGet() {
			return "mictodoedit";
		}

		//コピペ用サンプル（画面から何か入力をした時用）
		@RequestMapping(path = "/micedit", method = RequestMethod.POST)
		public String editPost(int month, int date, String todo) {

			//DBに繋ぐならこんな感じ(JdbcTemplate)
			jdbcTemplate.update("UPDATE todo SET todo = ? WHERE month = ? AND day = ?", todo, month, date);



			return "mictodoedit";
		}
		
		
		//コピペ用サンプル(ページ表示用メソッド)
		@RequestMapping(path = "/micdel", method = RequestMethod.GET)
		public String delGet() {
			return "mictododel";
		}

		//コピペ用サンプル（画面から何か入力をした時用）
		@RequestMapping(path = "/micdel", method = RequestMethod.POST)
		public String delPost(int month, int date, String todo) {

			//DBに繋ぐならこんな感じ(JdbcTemplate)
			jdbcTemplate.update("DELETE FROM todo WHERE month = ? AND day = ?",month, date);

			return "mictododel";
		}
}
