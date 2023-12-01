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

    // コピペ用サンプル(ページ表示用メソッド)
    @RequestMapping(path = "/miclogin", method = RequestMethod.GET)
    public String micGet() {
        return "miclogin";
    }

    @RequestMapping(path = "/miclogin", method = RequestMethod.POST)
    public String micPost(String micloginid, String micpw, Model model) {
        if (micloginid.length() > 16 || micpw.length() > 16) {
            model.addAttribute("mozierror","文字数が多すぎます");
        } else {
            List<Map<String, Object>> resultList = jdbcTemplate
                    .queryForList("SELECT * FROM miclogin WHERE loginid = ? AND password = ?", micloginid, micpw);
            if (!resultList.isEmpty()) {
                return "redirect:/michome";
            } else {
            	model.addAttribute("loginerror","ログインに失敗しました");
            }
        }

        return "miclogin";
    }
}
