package com.prudential.rental.common.Page;

import java.io.Serializable;

import com.prudential.rental.model.entity.BaseEntity;



public class Page extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int begin;
	private int end;
	// 一页长度
	private int length;
	// 总记录数
	private int count;
	// 当前页码
	private int current;
	// 总页数
	private int total;
	private int offset;
	private boolean onlyCount;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	// 满页
	public Page(int begin, int length) {
		this.begin = begin;
		this.length = length;
		this.end = (this.begin + this.length);
		if (this.length != 0)
			this.current = ((int) Math.floor(this.begin * 1.0D / this.length) + 1);
		else
			this.current = 1;
		setOffset(length);
	}

	public Page(int begin, int length, int count) {
		this(begin, length);
		this.count = count;
		if ((count - begin) >= length) {
			this.offset = length;
		} else {
			this.offset = count - begin;
		}

	}

	public int getBegin() {
		return this.begin;
	}

	public int getEnd() {
		return this.end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setBegin(int begin) {
		this.begin = begin;
		if (this.length > 0)
			this.current = ((int) Math.floor(this.begin * 1.0D / this.length) + 1);
		else
			this.current = 1;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
		if (this.begin != 0)
			if (this.length > 0)
				this.current = ((int) Math.floor(this.begin * 1.0D
						/ this.length) + 1);
			else
				this.current = 1;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
		if (this.length > 0) {
			this.total = (int) Math.floor(this.count * 1.0D / this.length);
			if (this.count % this.length != 0)
				this.total += 1;
		} else {
			this.total = 1;
		}
	}

	public int getCurrent() {
		return this.current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		if (this.total == 0) {
			return 1;
		}
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isOnlyCount() {
		return this.onlyCount;
	}

	public void setOnlyCount(boolean onlyCount) {
		this.onlyCount = onlyCount;
	}
}