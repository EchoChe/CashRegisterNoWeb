package app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

import app.model.Product;
import app.model.ShoppingCart;
import app.printer.ReceiptPrinter;
import ch.qos.logback.core.net.SyslogOutputStream;

public class CashRegisterTest {

	private CashRegister cashRegister;
	
	private Product product1 = null;

	@Before
	public void setUp(){
		cashRegister = new CashRegister();
		product1 = createProduct("ITEM000001",10.50);
		CashRegister cashRegister = new CashRegister();
	}
	
	private Product createProduct(String barcode, double price) {
		return new Product(barcode, "AAAA", price, "GE", "", "");
	}
	
	@Test
	public void getTotalPriceWhenJustHaveOneProduct() {
		cashRegister.add(product1, 1);
//		assertEquals(10.50,cashRegister.getTotalPrice(),0.0000001);
	}
	
	@Test
	public void getTotalPriceWhenHaveMultipleProducts() {
		cashRegister.add(product1, 1);
		cashRegister.add(product1, 1);
		cashRegister.add(createProduct("ITEM000002",20.50), 1);
//		assertEquals(41.50,cashRegister.getTotalPrice(),0.00000001);
	}
	
	/*@Test
	public void getProductNumber() {
		cashRegister.add(product1, 1);
		cashRegister.add(product1, 1);
		cashRegister.add(createProduct("ITEM000002",20.50), 1);
		assertEquals(2,cashRegister.getProductNumber(product1));
		assertEquals(1,cashRegister.getProductNumber(createProduct("ITEM000002",20.50)));
	}*/
	
	@SuppressWarnings("unchecked")
	@Test
	public void printReceipt(){
		ReceiptPrinter receiptPrinter = mock(ReceiptPrinter.class);
		cashRegister.setReceiptPrinter(receiptPrinter);
		cashRegister.add(product1, 1);
		cashRegister.add(product1, 1);
		cashRegister.add(createProduct("ITEM000002",20.50), 1);
		
//		cashRegister.printReceipt();
				
//		verify(receiptPrinter,times(1)).getReceiptHead();
//		verify(receiptPrinter,times(1)).printMultipleItemsInItemSection(any(LinkedHashMap.class));
//		verify(receiptPrinter,times(1)).getReceiptSum(ShoppingCart.shoppingCartItem());
	}
	
	@Test
	public void initCashRegisterTest()
	{
		CashRegister cashRegister = new CashRegister();
		LinkedHashMap<Product, Integer> retMap = cashRegister.getProductMap();
		
		assertEquals(5, retMap.get(product1).intValue());
		assertEquals(2, retMap.get(createProduct("ITEM000003",20.50)).intValue());
		assertEquals(3, retMap.get(createProduct("ITEM000005",10.50)).intValue());
	}
	
	@Test
	public void printTotal()
	{
		
		String str = cashRegister.printReceipt();
		assertEquals("***<没钱赚商店>购物清单***\n名称：可口可乐，数量：5瓶，单价：3.00(元)，小计：12.00(元)\n名称：苹果，数量：2斤，单价：5.00(元)，小计：9.00(元)，节省：1.00(元)\n名称：羽毛球，数量：3个，单价：1.00(元)，小计：3.00(元)\n", str);
	}
	
	@Test
	public void printByeTwoGetOneFreeList()
	{
		String str = cashRegister.getByeTwoGetFreeOneMessage();
		assertEquals("买二赠一商品：\n名称：可口可乐，数量：1瓶\n", str);
	}
	
	@Test
	public void sumTest()
	{
		String str = cashRegister.sum();
		assertEquals("总计：0.00(元)\n", str);
	}
}
