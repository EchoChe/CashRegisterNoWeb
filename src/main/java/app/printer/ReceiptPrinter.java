package app.printer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import app.UtilFiles.ReadUtilFile;
import app.model.ByeThreeGetOneFree;
import app.model.DiscountConvert;
import app.model.Product;

public class ReceiptPrinter {

	public static ReadUtilFile readUtilFile = new ReadUtilFile(); 
	public static LinkedHashMap<String, Integer> buyTwoFreeOneList = new LinkedHashMap<String, Integer>(); 
	public static double totalPrice = 0.0; 
	public static double totalDiscount = 0.0; 
	
	public String getReceiptHead() {
		return "***<没钱赚商店>购物清单***\n";
	}

	public String printOneItemInItemsSection(Product product, Integer number) {
		double itemPrice = product.getPrice() * number;
		totalPrice += itemPrice;
		return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)\n",
								product.getName(),
								number,
								product.getUnit(),
								product.getPrice(),
								itemPrice);
	}

	public String printOneItemInItemsSectionWhentDiscount(Product product, int number) {
		String discountType = "";
		//反向寻找折扣类型
		
		Map<String, DiscountConvert> discountProduct = DiscountConvert.discountBarcodeWithMessage();
		String barcodeTmp = product.getBarcode();
		double discount = discountProduct.get(barcodeTmp).getDiscount();
		double itemPrice = product.getPrice() * number * discount;
				
		return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)，节省：%1.2f(元)\n",
				product.getName(),
				number,product.getUnit(),
				product.getPrice(),
				itemPrice,
				product.getPrice() * number * (1-discount));
	}

	public String printOneItemInItemsSectionWhenBuyTwoGetOneFree(Product product, int number) {
		if (number < 3)
			return printOneItemInItemsSection(product, number);
		else {
			double itemPrice = product.getPrice() * (number - number/3);
			double itemDiscount = product.getPrice() * (number/3);
			totalPrice += itemPrice;
			totalDiscount += itemDiscount;
			
			return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)\n",
					product.getName(),
					number,product.getUnit(),
					product.getPrice(),
					itemPrice);
		}
	}
	
	public String threeChoseOne(LinkedHashMap<String, Integer> productsWithNumbers) {
		ArrayList<String> buyTwoGetOneFreeID = ByeThreeGetOneFree.readBuyTwoGetOneFreeID();
		Map<String, DiscountConvert> discountID = DiscountConvert.discountBarcodeWithMessage();
		ReceiptPrinter receiptPrinter = new ReceiptPrinter();
		LinkedHashMap<String, Integer> shoppingCart = productsWithNumbers;
		
		String str = ""; 
		
		for(String string : shoppingCart.keySet())
		{		
//			Product product = ReadUtilFile.productMap.get(string);
			Product product = Product.readProductItem().get(string);
			//买二赠一
			if(buyTwoGetOneFreeID.contains(product.getBarcode()))
			{
				str += receiptPrinter.printOneItemInItemsSectionWhenBuyTwoGetOneFree(product, shoppingCart.get(product.getBarcode()));
				buyTwoFreeOneList.put(string, shoppingCart.get(string));
			}
			//打折
			else if(discountID.get(product.getBarcode()) != null){
				str += receiptPrinter.printOneItemInItemsSectionWhentDiscount(product, shoppingCart.get(product.getBarcode()));
			}
			//正常
			else{
				str += receiptPrinter.printOneItemInItemsSection(product, shoppingCart.get(product.getBarcode()));
			}
		}
		return str;
	}
	
	public String getReceiptBuyTwoGetOneFreeHead() {
		return "买二赠一商品：\n";
	}

	public String printOneItemInItemsSectionWheBuyTwoGetOneFreeList() {
		String str = getReceiptBuyTwoGetOneFreeHead();
		for(String key : buyTwoFreeOneList.keySet()){ 
//			Product product = readUtilFile.productMap.get(key);
			Product product = Product.readProductItem().get(key);
			str += String.format("名称：%s，数量：%d%s\n",
							product.getName(),
							buyTwoFreeOneList.get(key)/3,
							product.getUnit());
		}
		return str;
	}

	public String getReceiptSum(LinkedHashMap<String, Integer> productsWithNumbers) {
		//totalPrice = 0.0; 
		//totalDiscount = 0.0; 
		//threeChoseOne(productsWithNumbers);
		if(totalDiscount==0.0)
			return String.format("总计：%1.2f(元)\n", totalPrice);
		else
			return String.format("总计：%1.2f(元)\n 节省：%1.2f(元)\n", totalPrice,totalDiscount);
	}

	public void clear() {
		totalPrice = 0.0; 
		totalDiscount = 0.0; ; 
		LinkedHashMap<String, Integer> buyTwoFreeOneList = new LinkedHashMap<String, Integer>();
	}
}
