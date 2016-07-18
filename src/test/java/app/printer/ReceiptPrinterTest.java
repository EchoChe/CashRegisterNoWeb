package app.printer;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.model.Product;


public class ReceiptPrinterTest {
	
	private ReceiptPrinter receiptPrinter;
	
	@Before
	public void setUp(){
		receiptPrinter = new ReceiptPrinter();
	}
	
	private Product createProduct(String barcode, String name, double price, String unit) {
		return new Product(barcode,name,price, unit, "", "");
	}

	@Test
	public void getReceiptHead()
	{
		assertEquals("***<ûǮ׬�̵�>�����嵥***",receiptPrinter.getReceiptHead());
	}

	@Test
	public void printOneItemInReceiptItemsSection()
	{
		Assert.assertEquals("���ƣ��ɿڿ��֣�������3ƿ�����ۣ�3.00(Ԫ)��С�ƣ�9.00(Ԫ)",receiptPrinter.printOneItemInItemsSection(createProduct("ITEM0002","�ɿڿ���", 3.00, "ƿ"),3));
		Assert.assertEquals("���ƣ��ɿڿ��֣�������2ƿ�����ۣ�3.00(Ԫ)��С�ƣ�6.00(Ԫ)",receiptPrinter.printOneItemInItemsSection(createProduct("ITEM0002","�ɿڿ���", 3.00, "ƿ"),2));
		Assert.assertEquals("���ƣ���ë��������5�������ۣ�1.00(Ԫ)��С�ƣ�5.00(Ԫ)",
                receiptPrinter.printOneItemInItemsSection(createProduct("ITEM0001","��ë��",1.00,"��"),5));
	}
	@Test
	public void printMutipleItemInReceiptItemsSection()
	{
		LinkedHashMap<Product,Integer> productsWithNumbers = new LinkedHashMap<Product,Integer>();
		productsWithNumbers.put(createProduct("ITEM0002","�ɿڿ���", 3.00, "ƿ"),3);
		productsWithNumbers.put(createProduct("ITEM0001","��ë��", 1.00, "��"),5);
		Assert.assertEquals("���ƣ��ɿڿ��֣�������3ƿ�����ۣ�3.00(Ԫ)��С�ƣ�9.00(Ԫ)\n���ƣ���ë��������5�������ۣ�1.00(Ԫ)��С�ƣ�5.00(Ԫ)\n",
							receiptPrinter.printMultipleItemsInItemSection(productsWithNumbers));
	}

	@Test
	public void getReceiptSum()
	{
		assertEquals("�ܼƣ�14.00(Ԫ)",receiptPrinter.getReceiptSum(14.00));
	}
}
