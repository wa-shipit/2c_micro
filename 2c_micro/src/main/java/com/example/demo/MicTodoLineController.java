package com.example.demo;

import java.util.Collections;
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
public class MicTodoLineController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	//ここにチャンネルアクセストークンを貼る！
	String channelAccessToken = "gEzCVUV9QDtNvoJjninYmbAr2TScRUBEfHNjK7q0coTT5O1OAz2yGJtSWcSMqppBKjTERg2bwzvnV6SlGcSZCznEvXFiQ0RZg1IBcAN3YL7suDTk0bkm6mvnXzCdpBXwH7HV7LPoNKhqsTCbi3IaVQdB04t89/1O/w1cDnyilFU=";

	@PostMapping("/micline")
	@CrossOrigin(origins = "*")
	public void postApidataaa(@RequestBody LineData webhookData) {
		for (Event event : webhookData.getEvents()) {

			String replyToken = event.getReplyToken();
			String userId = event.getSource().getUserId();
			
			List<Map<String, Object>> resultList;
		    
			resultList = jdbcTemplate.queryForList("select month,day,todo from todo");
			
			
			 for (Map<String, Object> entry : resultList) {
		            String month = (String) entry.get("month");
		            String day = (String) entry.get("day");
		            String todo = (String) entry.get("todo");
		            System.out.println("Month: " + month + ", Day: " + day + ", Todo: " + todo);
		            String hensin= month+"月"+day+"日"+"やること:"+todo;
		            pushMessage(userId,hensin);
		        }
			 
			
		}
	}

	/*******************************************************************:
	 * ここから↓は今は気にしないでOK!
	 *******************************************************************/
	
	private void pushMessage(String to, String pushText) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/push";
		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);
		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		message.put("text", pushText);
		// リクエストボディを設定
		Map<String, Object> body = new HashMap<>();
		body.put("to", to);
		body.put("messages", Collections.singletonList(message));
		// HTTPリクエストを送信
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(url, new HttpEntity<>(body, headers), String.class);
		}

	//文字を送りたい場合はこのメソッドを呼び出す。
	//呼び出す際、第二引数に送りたい文字列を渡す。
	private void replyMessage(String replyToken, String replyText) {
		// LINE APIのエンドポイント
		String url = "https://api.line.me/v2/bot/message/reply";

		// HTTPヘッダーにChannel Access Tokenを設定
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + channelAccessToken);

		// 送信するメッセージを設定
		Map<String, Object> message = new HashMap<>();
		message.put("type", "text");
		message.put("text", replyText);

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

