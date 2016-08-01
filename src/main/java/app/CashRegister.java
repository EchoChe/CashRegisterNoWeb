package app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import app.model.ByeThreeGetOneFree;
import app.model.Discount;
import app.model.Product;
import app.model.ShoppingCart;
import app.printer.ReceiptPrinter;

public class CashRegister {
	private LinkedHashMap<Product,Integer> productMap = new LinkedHashMap<Product,Integer>();
	private ReceiptPrinter receiptPrinter = new ReceiptPrinter();
	public static LinkedHashMap<Product, Integer> buyTwoFreeOneList = new LinkedHashMap<Product, Integer>();
	public static double totalPrice = 0.0; 
	public static double totalSave = 0.0;
    
	public void add(Product product, int number) {
		if(productMap.containsKey(product)){
			productMap.put(product, productMap.get(product)+number);
		} else {
			productMap.put(product,number);
		}
	}

	public void InitCashRegister()
	{
		LinkedHashMap<String, Integer> shoppingCartBarcode= ShoppingCart.shoppingCartItem();
		Map<String, Product> prodects = Product.readProductItem();
		for(String barcode: shoppingCartBarcode.keySet())
	    {
			Integer number = shoppingCartBarcode.get(barcode);
	    	Product product = prodects.get(barcode);
	    	add(product, number);
	    }
	}
	
	public void setReceiptPrinter(ReceiptPrinter receiptPrinter) {
		this.receiptPrinter  = receiptPrinter;
		
	}

	public LinkedHashMap<Product, Integer> getProductMap() {
		InitCashRegister();
		return productMap;
	}
	
	public ReceiptPrinter getReceiptPrinter() {
		return receiptPrinter;
	}
	
	public String threeChoseOne() 
	{
		Map<String, Discount> discountProduct = Discount.discountBarcodeWithMessage();
		ArrayList<String> byeThreeGetOneFreeProduct = ByeThreeGetOneFree.readBuyTwoGetOneFreeID();
		String str = "";
		productMap = getProductMap();
		
		for(Product product : productMap.keySet())
		{	
			double price = product.getPrice();
			String barcode = product.getBarcode();
			int number = productMap.get(product);
			double itemPrice = 0.0;
			double itemSave = 0.0;
			
			if(byeThreeGetOneFreeProduct.contains(barcode))//买二赠一
			{
				itemPrice = number * price - number / 3 * price;
				itemSave = number / 3 * price;
				
				str += receiptPrinter.printOneItemInItemsSectionWhenBuyTwoGetOneFree(product, number, itemPrice);
				totalSave += itemSave;
				
				buyTwoFreeOneList.put(product, number/3);
			}
			else if(discountProduct.get(barcode) != null)//打折
			{
				double discount = discountProduct.get(barcode).getDiscount();
				itemPrice = number * price * discount;
				itemSave = number * price * (1 - discount);
				totalSave += itemSave;
				str += receiptPrinter.printOneItemInItemsSectionWhentDiscount(product, number, itemPrice, itemSave);
			}
			else//正常
			{
				itemPrice = price * number;
				str += receiptPrinter.printOneItemInItemsSection(product, number, itemPrice);
			}
			totalPrice += itemPrice;
		}
		
		return str;
	}

	public  String sum() {
		return receiptPrinter.getReceiptSum(totalPrice, totalSave);
	}
	
	public String printReceipt() {
		
		String receiptString = "";
		receiptString += receiptPrinter.getReceiptHead();
		receiptString += threeChoseOne();
		
		return receiptString;
	}

	public String getByeTwoGetFreeOneMessage() {
		
		String byeTwoGetOneFreeString = receiptPrinter.getReceiptBuyTwoGetOneFreeHead();
		byeTwoGetOneFreeString += receiptPrinter.printOneItemInItemsSectionWheBuyTwoGetOneFreeList(buyTwoFreeOneList);
		return byeTwoGetOneFreeString;
	}
	
}
