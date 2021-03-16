package com.example.hapiness.model.entity.post;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.hapiness.model.pk.HapinessPostPK;



@Entity
@Table(name = "hppost")
public class HapinessPostEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	public HapinessPostPK hapinessPostPK;

	@Column(name = "body")
	private String body;

	//こいつは使い道なくなる？
	public HapinessPostPK getHapinessPostPK() {
		return this.hapinessPostPK;
		}

	public void setHapinessPostPK(HapinessPostPK hapinessPostPK) {
		this.hapinessPostPK = hapinessPostPK;
		}

//	public void setHapinessPostPK(HapinessPostPK userId, HapinessPostPK createDateTime) {
//		this.hapinessPostPK = new HapinessPostPK(userId, createDateTime);
//		}

	//hapinessPostPKメソッドを呼び出す
	public void setHapinessPostPK(int userId) {
		//hapinessPostPK.setUserId(userId);
		}


//	public void setHapinessPostPK(String createDateTime) {
//		hapinessPostPK.setCreateDateTime(createDateTime);
//		}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}



}
