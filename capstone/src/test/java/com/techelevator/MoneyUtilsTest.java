package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class MoneyUtilsTest {

	
	
	@Test
	public void test() {
		String result = MoneyUtils.getChange(new BigDecimal(9.85)); 
		Assert.assertEquals("\n(1) Five\n(4) Ones\n(3) Quarters\n(1) Dime", result);
		result = MoneyUtils.getChange(new BigDecimal(.05)); 
		Assert.assertEquals("\n(1) Nickel", result);
		result = MoneyUtils.getChange(new BigDecimal(45.85)); 
		Assert.assertEquals("\n(4) Tens\n(1) Five\n(3) Quarters\n(1) Dime", result);
		result = MoneyUtils.getChange(new BigDecimal(.10)); 
		Assert.assertEquals("\n(1) Dime", result);
		result = MoneyUtils.getChange(new BigDecimal(.25)); 
		Assert.assertEquals("\n(1) Quarter", result);
		
		
	}

}
