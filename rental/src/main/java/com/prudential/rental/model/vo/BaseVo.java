package com.prudential.rental.model.vo;

import java.io.Serializable;

public class BaseVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * @Fields id :ID
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}