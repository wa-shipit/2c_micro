package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicUserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // ページ表示用メソッド
    @RequestMapping(path = "/micuser", method = RequestMethod.GET)
    public String copGet() {
        return "micuser";
    }

    // 画面からの入力処理用メソッド
    @RequestMapping(path = "/micuser/register", method = RequestMethod.POST)
    public String registerUser(String loginId, String password, Model model) {
        String sql = "INSERT INTO miclogin (loginid, password) VALUES (?, ?)";
        
        jdbcTemplate.update(sql, loginId, password);

        // 登録が完了したら、micuser.html ページにリダイレクト
        return "redirect:/micuser";
    }
}