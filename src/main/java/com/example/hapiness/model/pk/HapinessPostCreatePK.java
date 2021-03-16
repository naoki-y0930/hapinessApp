package com.example.hapiness.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.sun.istack.NotNull;

@Embeddable
public class HapinessPostCreatePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "userId")
	private int userId;

	@NotNull
	@Column(name = "createDateTime")
	private String createDateTime;

	public void getUserId(int userId) {
		this.userId = userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void getCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HapinessPostCreatePK other = (HapinessPostCreatePK) obj;
		if (createDateTime == null) {
			if (other.createDateTime != null)
				return false;
		} else if (!createDateTime.equals(other.createDateTime))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}


}
