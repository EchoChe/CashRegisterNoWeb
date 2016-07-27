package app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.UtilFiles.DiscountConvert;
import app.UtilFiles.ReadUtilFile;
import app.model.Product;

public class CashRigisterMain {
	public static void main(String[] args) {
		
		CashRegister cashRegister;
		Product product1 = null;
		
		ArrayList<String> actual = ReadUtilFile.readBuyTwoGetOneFreeID();
		Map<String, DiscountConvert> discountProduct = ReadUtilFile.MessageConvertCountNumber();
	    Map<String, Product> retMap = ReadUtilFile.readProductItem();
	    LinkedHashMap<String, Integer> map1= new LinkedHashMap<String, Integer>();
        List<String> list3 = new ArrayList<>();
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000001");
        list3.add("ITEM000003-2");
        list3.add("ITEM000003");
        list3.add("ITEM000005");
        list3.add("ITEM000005");
        list3.add("ITEM000005");
        map1 = ReadUtilFile.shoppingCartItem();
		
		System.out.println("Hello");
	}
}
