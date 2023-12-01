package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//↓「name="xxx"」の「xxx」の部分に模倣したいテーブル名を書く
@Table(name = "todo")
public class Todo {

	//主キーには「@Id」を設定する！
		@Id
		//カラム名(列名)を書く。
		@Column(name = "user_id")
		private String user_id;

		@Column(name = "month")
		private String month;

		@Column(name = "day")
		private String day;

		@Column(name = "todo")
		private String todo;

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}

		public String getMonth() {
			return month;
		}

		public void setMonth(String month) {
			this.month = month;
		}

		public String getDay() {
			return day;
		}

		public void setDay(String day) {
			this.day = day;
		}

		public String getTodo() {
			return todo;
		}

		public void setTodo(String todo) {
			this.todo = todo;
		}
		
		
		
}
