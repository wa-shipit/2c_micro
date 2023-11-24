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

    // ログイン画面表示用メソッド
    @RequestMapping(path = "/miclogin", method = RequestMethod.GET)
    public String showLoginPage() {
        return "miclogin";
    }

    // ログイン処理用メソッド
    @RequestMapping(path = "/miclogin", method = RequestMethod.POST)
    public String processLogin(String micloginid, String micpw, Model model) {
        // 入力文字数の制限チェック
        if (micloginid.length() > 16 || micpw.length() > 16) {
            model.addAttribute("message", "文字数が多すぎます");
            return "miclogin";
        }

        // データベースと照合（サンプルとしてqueryForListの使用）
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(
                "SELECT * FROM users WHERE username = ? AND password = ?", micloginid, micpw);

        if (!resultList.isEmpty()) {
            // ログイン成功//
            return "michome";
        } else {
            // ログイン失敗
            model.addAttribute("message", "ログインに失敗しました");
            return "miclogin";
        }
    }
}
