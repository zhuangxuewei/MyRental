package com.prudential.rental.common.util;


import org.apache.commons.lang.math.RandomUtils;

public class RentalUtil {
	/**
	 * 订单号生产方法
	 * @return
	 */
	public static String generateOrderId() {
		long time = System.currentTimeMillis();
		long random = RandomUtils.nextLong();
		String result = String.valueOf(time) + String.valueOf(random);
		return result;
	}
}
