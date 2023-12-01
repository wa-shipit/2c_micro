package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicTodoController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 新規登録画面表示
    @RequestMapping(path = "/micadd", method = RequestMethod.GET)
    public String showAddPage() {
        return "mictodo-add"; // 新規登録画面のView名を指定
    }

    // 新規登録
    @RequestMapping(path = "/micadd", method = RequestMethod.POST)
    public String addTodo(String month,String day, String todo) {

        jdbcTemplate.update("INSERT INTO todo (user_id, month, day, todo) VALUES (?, ?, ?, ?)","00000", month, day, todo);

        return "redirect:/micadd";
    }

    // 編集画面表示
    @RequestMapping(path = "/micedit", method = RequestMethod.GET)
    public String showEditPage() {
        return "mictodo-edit"; // 編集画面のView名を指定
    }

    // 編集
    @RequestMapping(path = "/micedit", method = RequestMethod.POST)
    public String editTodo(String month,String day,String todo) {

        jdbcTemplate.update("UPDATE todo SET todo=? WHERE user_id=? AND month=? AND day=?;",todo, 00000, month, day);

        return "redirect:/micedit";
    }

    // 削除画面表示
    @RequestMapping(path = "/micdel", method = RequestMethod.GET)
    public String showDeletePage() {
        return "mictodo-delete"; // 削除画面のView名を指定
    }

    // 削除
    @RequestMapping(path = "/micdel", method = RequestMethod.POST)
    public String deleteTodo(String deleteMonth,String deleteDay) {

        jdbcTemplate.update("DELETE FROM todo WHERE user_id=? AND month=? AND day=?;",00000, deleteMonth, deleteDay);

        return "redirect:/micdel";
        
        
        
    }
}
