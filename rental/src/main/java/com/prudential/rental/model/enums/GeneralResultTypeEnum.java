package com.prudential.rental.model.enums;

/**
 * 通用返回类型枚举
 * 
 * @author zhuangxuewei
 *
 */
public enum GeneralResultTypeEnum {
	SUCCESS(0, "请求成功"), FAIL(555, "请求失败"), EXCEPTION(555, "请求异常");
	/**
	 * 返回码
	 */
	private int code;
	/**
	 * 返回信息
	 */
	private String msg;

	GeneralResultTypeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;

	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
