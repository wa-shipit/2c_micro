package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/micuser")
public class MicUserController {

    @GetMapping
    public String showForm() {
        return "micuser"; // HTMLファイル名（micuser.html）と対応
    }

    @PostMapping
    public ModelAndView processForm(@RequestParam String userId, @RequestParam String password) {
        ModelAndView modelAndView = new ModelAndView();

        // 文字数制限のチェック
        if (userId.length() > 10 || password.length() > 10) {
            modelAndView.addObject("error", "IDとパスワードはそれぞれ10文字以内で入力してください。");
            modelAndView.setViewName("micuser"); // 入力エラーがある場合は再びフォームを表示
        } else {
            // micloginテーブルに登録する処理（データベースへの保存など）
            // ここでは省略
            modelAndView.addObject("success", "ユーザ登録が完了しました。");
            modelAndView.setViewName("success"); // 登録成功時の画面を表示
        }

        return modelAndView;
    }
}
