package com.example.hapiness.model.entity.post;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.hapiness.model.pk.HapinessPostCreatePK;
import com.sun.istack.NotNull;

@Entity
@Table(name = "hppost")
public class HapinessPostCreateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	private HapinessPostCreatePK hapinessPostCreatePK;

	@Column(name = "body")
	@NotNull
	//@Size(min = 1, max = 500)
	//@Min(0)
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
 }

