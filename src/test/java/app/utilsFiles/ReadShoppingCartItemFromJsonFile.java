package app.utilsFiles;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import app.UtilFiles.ReadUtilFile;

public class ReadShoppingCartItemFromJsonFile {
	
	@SuppressWarnings("unchecked")
	@Test
	public void test()
	{		
		LinkedHashMap<String, Integer> map1= ReadUtilFile.shoppingCartItem();
        
		LinkedHashMap<String, Integer> map2= new LinkedHashMap<String, Integer>();
		map2.put("ITEM000001", 5);
		map2.put("ITEM000003", 2);
		map2.put("ITEM000005", 3);
		
        Assert.assertEquals(map2,map1);
        
	}

}
