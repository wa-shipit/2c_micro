package com.example.demo;

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

	@RequestMapping(path = "/micadd", method = RequestMethod.GET)
	public String copGet() {
		return "mictodocadd";
	}

	@RequestMapping(path = "/micadd", method = RequestMethod.POST)
	public String copPost(String month, String day, String todo, Model model) {
	    jdbcTemplate.update("INSERT INTO todo (user_id, month, day, todo) VALUES (999999,?, ?, ?)", month, day, todo);

	    

	    
	    return "redirect:/micadd"; 
	}
		
		//削除ページ表示用メソッド
		@RequestMapping(path = "/micdel", method = RequestMethod.GET)
		public String cGet() {
			return "mictododel";
		}

		//コピペ用サンプル（画面から何か入力をした時用）
		@RequestMapping(path = "/micdel", method = RequestMethod.POST)
		public String cPost(String month, String day) {
		    // DBに繋ぐならこんな感じ(JdbcTemplate)
		    jdbcTemplate.update("DELETE FROM todo WHERE month = ? AND day = ?", month, day);

		    return "redirect:/micdel";
		}
		
		//編集ページ表示用メソッド
				@RequestMapping(path = "/micedit", method = RequestMethod.GET)
				public String cGe() {
					return "mictodoedit";
				}

				//コピペ用サンプル（画面から何か入力をした時用）
				@RequestMapping(path = "/micedit", method = RequestMethod.POST)
				public String cPo(String month, String day, String todo) {
				    // DBに繋ぐならこんな感じ(JdbcTemplate)
				    jdbcTemplate.update("UPDATE todo SET todo = ? WHERE month = ? AND day = ?", todo, month, day);

				    return "redirect:/micedit";
				}
				
		}
		


