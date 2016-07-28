package app.utilsFiles;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Test;

import app.model.ShoppingCart;

public class ReadShoppingCartItemFromJsonFile {
	
	@SuppressWarnings("unchecked")
	@Test
	public void test()
	{		
		LinkedHashMap<String, Integer> map1= ShoppingCart.shoppingCartItem();
        
		LinkedHashMap<String, Integer> map2= new LinkedHashMap<String, Integer>();
		map2.put("ITEM000001", 5);
		map2.put("ITEM000003", 2);
		map2.put("ITEM000005", 3);
		
        Assert.assertEquals(map2,map1);
        
	}

}
