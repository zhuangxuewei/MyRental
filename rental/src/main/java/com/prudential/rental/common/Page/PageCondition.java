package com.prudential.rental.common.Page;

import com.prudential.rental.model.entity.BaseEntity;

public class PageCondition extends BaseEntity {
	private static final long serialVersionUID = 1L;
	protected Page page;
	protected String orderByClause;

	protected PageCondition() {
		this.page = null;
		this.orderByClause = null;
	}

	public PageCondition(Page page) {
		this.page = page;
	}

	public PageCondition(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public PageCondition(Page page, String orderByClause) {
		this.page = page;
		this.orderByClause = orderByClause;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return this.orderByClause;
	}

	public void clear() {
		this.orderByClause = null;
		this.page = null;
	}
}