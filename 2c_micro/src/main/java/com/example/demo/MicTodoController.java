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
	public String copPost( String month,String day,String todo,Model model) {

		String sql = "INSERT INTO todo (month, day, todo) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, month, day, todo);

		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("todo", todo);

		return "mictodocadd";
	}
		
		//削除ページ表示用メソッド
		@RequestMapping(path = "/micdel", method = RequestMethod.GET)
		public String cGet() {
			return "mictododel";
		}

		//コピペ用サンプル（画面から何か入力をした時用）
		@RequestMapping(path = "/micdel", method = RequestMethod.POST)
		public String cPost(String example1, String example2, Model model) {

			//DBに繋ぐならこんな感じ(JdbcTemplate)
			//		List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM honyarara WHERE honyarara");

			model.addAttribute("example1", example1);
			model.addAttribute("example2", example2);

			return "mictododel";
		}
}
