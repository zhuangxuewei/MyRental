package com.prudential.rental.service.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@SuiteClasses({ CarRentalServiceImplTest.class, CarStockServiceImplTest.class })
@SpringBootTest
public class AllTests {

}
