package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicUserController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MicUserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(path = "/micuser", method = RequestMethod.GET)
    public String showForm() {
        return "micuser";
    }

    @RequestMapping(path = "/micuser", method = RequestMethod.POST)
    public String processForm(String loginid, String password, Model model) {

        // 文字数制限のチェック
        if (loginid.length() > 10 || password.length() > 10) {
            model.addAttribute("error", "エラー：IDとパスワードはそれぞれ10文字以内で入力してください。");
            model.addAttribute("micuser", ""); // エラー時は空文字でクリア
        } else {
            try {
                // micloginテーブルに登録する処理
            	jdbcTemplate.update("INSERT INTO miclogin (loginid,password) VALUES(?,?);", loginid, password);

                // 登録成功時に登録情報を表示
                model.addAttribute("micuser", "ユーザID: " + loginid + " が登録されました。");
            } catch (Exception e) {
                // 登録失敗時のエラーメッセージ
                model.addAttribute("error", "エラー：ユーザの登録に失敗しました。");
                model.addAttribute("micuser", ""); // エラー時は空文字でクリア
                e.printStackTrace(); // エラー内容をコンソールに出力（本番環境では適切なログ設定を行うべき）
            }
        }

        return "micuser";
    }
}
