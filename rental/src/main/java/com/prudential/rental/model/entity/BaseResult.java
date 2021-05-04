package com.prudential.rental.model.entity;

import java.io.Serializable;

import com.prudential.rental.model.enums.GeneralResultTypeEnum;

/**
 * 返回结果
 * 
 * @author zhuangxuewei
 *
 */
public class BaseResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 返回码
	 */
	private int resultCode;
	/**
	 * 返回消息
	 */
	private String resultMsg;
	/**
	 * 返回数据信息
	 */
	private Object datas;

	public BaseResult() {
		resultCode = GeneralResultTypeEnum.SUCCESS.getCode();
		resultMsg = GeneralResultTypeEnum.SUCCESS.getMsg();
	}
	private void BaseResul(int resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}

}
