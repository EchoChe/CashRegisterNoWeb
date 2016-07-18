package app.printer;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.model.Product;
import app.printer.ReceiptPrinter;


public class DiscountTest {
	
	private ReceiptPrinter receiptPrinter;
	
	@Before
	public void setUp(){
		receiptPrinter = new ReceiptPrinter();
	}
	
	private Product createProduct(String barcode, String name, double price, String unit) {
		return new Product(barcode,name,price, unit, "", "");
	}
	
	@Test
	public void printOneItemInReceiptItemsSectionWhenDiscount()
	{
		Assert.assertEquals("���ƣ�ƻ����������2����ۣ�5.50(Ԫ)��С�ƣ�10.45(Ԫ)����ʡ��0.55(Ԫ)",
				receiptPrinter.printOneItemInItemsSectionWhentDiscount(createProduct("ITEM0003","ƻ��", 5.50, "��"),2));
	}
	
	@Test
	public void printMuiltyItemReciptItemsSectionWhenHaveMultipleProductDiscountAndAnorherAsUsual()
	{
		LinkedHashMap<Product,Integer> productsWithNumbers = new LinkedHashMap<Product,Integer>();
		productsWithNumbers.put(createProduct("ITEM0002","�ɿڿ���", 3.00, "ƿ"),3);
		productsWithNumbers.put(createProduct("ITEM0001","��ë��", 1.00, "��"),5);
		productsWithNumbers.put(createProduct("ITEM0003","ƻ��", 5.50, "��"),2);
		Assert.assertEquals("���ƣ��ɿڿ��֣�������3ƿ�����ۣ�3.00(Ԫ)��С�ƣ�9.00(Ԫ)\n���ƣ���ë��������5�������ۣ�1.00(Ԫ)��С�ƣ�5.00(Ԫ)\n���ƣ�ƻ����������2����ۣ�5.50(Ԫ)��С�ƣ�10.45(Ԫ)����ʡ��0.55(Ԫ)\n",
							receiptPrinter.printMultipleItemsInItemSectionWhenHaveMutipleDiscountAndAnthorAsUsual(productsWithNumbers));
	}
}
