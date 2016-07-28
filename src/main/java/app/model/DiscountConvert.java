package app.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.UtilFiles.ReadUtilFile;

public class DiscountConvert {

	private double discount = 0.0;
	private String discountMessage = "";
	public static Map<String, String> discountMap = new HashMap<String, String>();
	public static Map<String, DiscountConvert> discountConvertMap = new HashMap<String, DiscountConvert>();
	
	public DiscountConvert( double discount, String discountMessage) {
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

	private static Map<String, DiscountConvert> readDiscountConvertItem()
	{
		String str2 = ReadUtilFile.readFile(".\\Data\\discountConvert.json");
		//折扣转换
		Gson gson2 = new Gson();
		discountConvertMap = gson2.fromJson(str2, new TypeToken<Map<String, DiscountConvert>>(){}.getType());
	    
	    return discountConvertMap;
	}
	
	public static Map<String, DiscountConvert> discountBarcodeWithMessage() {
		Map<String, String> discountIDMap = readDiscountItem();
		Map<String, DiscountConvert> discountConvertMap = readDiscountConvertItem();
		Map<String, DiscountConvert> discountProduct = new LinkedHashMap<String, DiscountConvert>();
		
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
