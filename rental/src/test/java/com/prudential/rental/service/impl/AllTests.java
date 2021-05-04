package com.prudential.rental.service.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RunWith(Suite.class)
@SuiteClasses({ CarRentalServiceImplTest.class, CarStockServiceImplTest.class })

public class AllTests {
	protected static ClassPathXmlApplicationContext context = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		// context = new ClassPathXmlApplicationContext(new String[] {
//		// "classpath:spring/applicationContext.xml",
//		// "classpath:spring/applicationContext-mybatis.xml",
//		// "classpath:spring/applicationContext-web.xml" });
//		context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
//		context.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
//		context.stop();
	}

}
