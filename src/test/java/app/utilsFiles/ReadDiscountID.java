package app.utilsFiles;

import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import app.model.Discount;
import junit.framework.Assert;

public class ReadDiscountID {

	@Test
	public void test() {
		 Map<String, Discount> discountProduct = Discount.discountBarcodeWithMessage();
		 Iterator< String> it = discountProduct.keySet().iterator();
		 
		 while(it.hasNext())
		 {
		  	String key = it.next();
		   	Discount dConvert = (Discount)discountProduct.get(key);
		   	if(key == "ITEM000001")
		   	{
		   		Assert.assertEquals(0.95, dConvert.getDiscount());
		   		Assert.assertEquals("可口可乐", dConvert.getDiscountMessage());
		   	}
		   	if(key == "ITEM000003")
		   	{
		   		Assert.assertEquals(0.9, dConvert.getDiscount());
		   		Assert.assertEquals("苹果", dConvert.getDiscountMessage());
		   	}
		 }
	}
}
