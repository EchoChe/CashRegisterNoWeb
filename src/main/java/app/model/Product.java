package app.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.UtilFiles.ReadUtilFile;

public class Product {
	private String barcode = "";
	private double price = 0.0;
	private String name = "";
	private String unit = "";
	private String category = "";
	private String subCategory = "";

	
	public Product(String barcode, String name, double price, String unit, String catagory, String subCatagory) {
		this.barcode = barcode;
		this.price = price;
		this.name = name;
		this.unit = unit;
		this.category = catagory;
		this.subCategory = subCatagory;
	}
	
	public String getBarcode() {
		return barcode;
	}

	public String getCategory() {
		return category;
	}

	public String getSubCategory() {
		return subCategory;
	}	

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getUnit() {
		return unit ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		return true;
	}
	
	public static Map<String, Product> readProductItem()
	{
		String str = ReadUtilFile.readFile(".\\Data\\product.json");
//		String str = ReadUtilFile.readFile("classpath:Data/product.json");
		Map<String, Product> productMap = new HashMap<String, Product>();

		//json string product
		Gson gson = new Gson();
	    productMap = gson.fromJson(str, new TypeToken<Map<String, Product>>(){}.getType());
	    return productMap;
	}

}
