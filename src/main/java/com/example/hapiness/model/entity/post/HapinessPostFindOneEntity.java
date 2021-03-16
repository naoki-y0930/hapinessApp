package com.example.hapiness.model.entity.post;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hppost")
public class HapinessPostFindOneEntity implements Serializable {
	private static final long serialVersionUID = 1L;

		private int userId;

		private String body;

		private String createDateTime;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getCreateDateTime() {
			return (String) createDateTime;
		}

		public void setCreateDateTime(String createDateTime) {
			this.createDateTime = createDateTime;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}
	}