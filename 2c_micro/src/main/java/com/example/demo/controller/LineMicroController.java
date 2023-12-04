package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.Event;
import com.example.demo.bean.LineData;

@RestController
public class LineMicroController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//ここにチャンネルアクセストークンを貼る！
	String channelAccessToken = "FiD/RvdMmf1VbsXAufCEXO2qGGPrmgqcrKQ4zkU+JyCUg8/JtErMFvZATRpMIB2EPbDI67LVRiZLoxJazC13j/UFe+Iz3T9aSv+LLqnGNJ3bQdwznex5WuPdgddMy7M566EXIW0QLv0F8o9Y5bTzLAdB04t89/1O/w1cDnyilFU=";

	@PostMapping("/lineApi")
	@CrossOrigin(origins = "*")
	public void postApidata(@RequestBody LineData webhookData) {
		for (Event event : webhookData.getEvents()) {

			//メッセージを送ってきたアカウント情報を変数「replyToken」に格納する。
			String replyToken = event.getReplyToken();

			///////////////☆☆重要☆☆///////////////////
			/////////////変数「replyText」に送られてきたメッセージが格納されている
			String replyText = event.getMessage().getText();
			//////////////////////////////////////////////
			
			if(replyText.equals("予定")) {
				replyMessage(replyToken);
			}
			//////////////ここまでを見てね。///////////////////////
		}
	}

	//文字を送りたい場合はこのメソッドを呼び出す。
	//呼び出す際、第二引数に送りたい文字列を渡す。
	private void replyMessage(String replyToken) {
		
		String todo = "";
		
	    Date d = new Date();
        SimpleDateFormat d2 = new SimpleDateFormat("MM");
        SimpleDateFormat d3 = new SimpleDateFormat("dd");
        String c2 = d2.format(d);
        String c3 = d3.format(d);
        int month = Integer.parseInt(c2);
        int day = Integer.parseInt(c3);
        
        
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT todo FROM todo WHERE month = ? AND day = ?", month, day);

        List<String> todos = new ArrayList<>();

        for (Map<String, Object> row : result) {
            Object todoObject = row.get("todo");

            if (todoObject != null) {
                todo = todoObject.toString();
                todos.add(todo);
            }
        }
        
        if(todo.equals("")) {
        	todo = "予定がありません。";
        }

		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		message.put("text", todo);

		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("replyToken", replyToken);
		body.put("messages", Collections.singletonList(message));

		System.out.println("test");

		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
	}

}