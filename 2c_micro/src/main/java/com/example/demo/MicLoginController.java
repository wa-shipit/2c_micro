package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MicLoginController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/miclogin")
	public String viewPage() {
		return "miclogin";
	}

	@PostMapping("/miclogin")
	public String loginPost(
			@RequestParam String micloginid,
			@RequestParam String micpw,
			Model model, HttpSession session) {
		
		int x = micloginid.length();
		int y = micpw.length();
		if(x>= 16 || y >=16) {
			//多いならログイン画面に戻す。
			String mojiseigen = "文字数が多すぎます";
			model.addAttribute("mojiseigen", mojiseigen);
				}

		// ログイン検証
		String query = "SELECT * FROM miclogin WHERE loginid = ? AND password = ?";
		boolean loginSuccess = jdbcTemplate.queryForList(query, micloginid, micpw).isEmpty();

		if (!loginSuccess) {
			// ログイン成功の場合
			session.setAttribute("micloginid", micloginid);
			return "redirect:/michome";
		} else {
			// ログイン失敗の場合
			model.addAttribute("loginError", "ログインに失敗しました");
			return "miclogin";
		}
	}
}