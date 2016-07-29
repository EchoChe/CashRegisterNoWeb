package app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import app.model.ByeThreeGetOneFree;
import app.model.Discount;
import app.model.Product;
import app.model.ShoppingCart;
import app.printer.ReceiptPrinter;

public class CashRigisterMain {
	public static void main(String[] args) {
		
		CashRegister cashRegister = new CashRegister();
		ReceiptPrinter receiptPrinter = new ReceiptPrinter();
		
		ArrayList<String> byeTwoGetOneFree = ByeThreeGetOneFree.readBuyTwoGetOneFreeID();
		Map<String, Discount> discountProduct = Discount.discountBarcodeWithMessage();
	    Map<String, Product> products = Product.readProductItem();
	    LinkedHashMap<String, Integer> shoppingCart= ShoppingCart.shoppingCartItem();
		
	    receiptPrinter.clear();
	    String productsItem = receiptPrinter.getReceiptHead();
	    String byeTwoGetOneFreeItem = receiptPrinter.getReceiptBuyTwoGetOneFreeHead();
	    
	    productsItem += receiptPrinter.threeChoseOne(shoppingCart);
	    
	    System.out.println(productsItem);
	    if(!byeTwoGetOneFree.isEmpty())
	    	System.out.println(receiptPrinter.printOneItemInItemsSectionWheBuyTwoGetOneFreeList());
	 
	    String str = cashRegister.printReceipt();
	    System.out.println(str);
	}
}
