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
public class MicUserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // ページ表示用メソッド
    @RequestMapping(path = "/micuser", method = RequestMethod.GET)
    public String copGet() {
        return "micuser";
    }

    // 画面からの入力処理用メソッド
    @RequestMapping(path = "/micuser", method = RequestMethod.POST)
    public String copPost(String loginId, String password, Model model) {

        List<Map<String, Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM miclogin WHERE login_id = ? AND password = ?", loginId, password);

        model.addAttribute("loginId", loginId);
        model.addAttribute("password", password);
        model.addAttribute("resultList", resultList);

        return "micuser";
    }
}
