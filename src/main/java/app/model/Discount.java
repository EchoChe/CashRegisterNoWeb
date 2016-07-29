package app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.UtilFiles.ReadUtilFile;

public class Discount extends Sale{

	public List<String> barcodeList()
	{
		String str = ReadUtilFile.readFile(".\\Data\\Discount.json");
		//打折  都是95折的商品
		Gson gson = new Gson();
		ArrayList<String> retArray = gson.fromJson(str,  new TypeToken<ArrayList<String>>(){}.getType()); 
		return retArray;
	}
	
	private double discount = 0.0;
	private String discountMessage = "";
	public static Map<String, String> discountMap = new HashMap<String, String>();
	public static Map<String, Discount> discountConvertMap = new HashMap<String, Discount>();
	
	public Discount( double discount, String discountMessage) {
		this.discount = discount;
		this.discountMessage = discountMessage;
	}

	public double getDiscount() {
		return discount;
	}

	public String getDiscountMessage() {
		return discountMessage;
	}
	 
	
	private static Map<String, String> readDiscountItem()
	{
		String str = ReadUtilFile.readFile(".\\Data\\discountID.json");
		//折扣信息
		Gson gson = new Gson();
		discountMap = gson.fromJson(str, new TypeToken<Map<String, String>>(){}.getType());
	    
	    return discountMap;
	}

	private static Map<String, Discount> readDiscountConvertItem()
	{
		String str2 = ReadUtilFile.readFile(".\\Data\\discountConvert.json");
		//折扣转换
		Gson gson2 = new Gson();
		discountConvertMap = gson2.fromJson(str2, new TypeToken<Map<String, Discount>>(){}.getType());
	    
	    return discountConvertMap;
	}
	
	public static Map<String, Discount> discountBarcodeWithMessage() {
		Map<String, String> discountIDMap = readDiscountItem();
		Map<String, Discount> discountConvertMap = readDiscountConvertItem();
		Map<String, Discount> discountProduct = new LinkedHashMap<String, Discount>();
		
		for(String discountMessageInretMap : discountIDMap.keySet())
			for(String discountMessageInretMap2 : discountConvertMap.keySet())
		   	{
		   		String barcode = (String)discountIDMap.get(discountMessageInretMap);
		   		if(discountMessageInretMap.equals(discountMessageInretMap2) )
		   		{
			   		discountProduct.put(barcode, discountConvertMap.get(discountMessageInretMap2));
		   		}
		    }
		return discountProduct;
	}
}
