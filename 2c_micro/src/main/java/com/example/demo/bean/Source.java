package com.example.demo.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Source {
	private String type;
	private String userId;
	private String userName;
	private String displayName;

	// デフォルトコンストラクタ
	public Source() {
	}

	// getters and setters
}