import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReceiptPrinterTest {
	
	private ReceiptPrinter receiptPrinter;
	
	@Before
	public void setUp(){
		receiptPrinter = new ReceiptPrinter();
	}
	
	private Product createProduct(double price) {
		return new Product(price);
	}

	@Test
	public void getReceiptHead()
	{
		assertEquals("ûǮ׬�̵��嵥",receiptPrinter.getReceiptHead());
	}

	@Test
	public void getReceiptItemsSectionJustOneItem()
	{
		Assert.assertEquals("���ƣ��ɿڿ��֣�������3ƿ�����ۣ�3.00��Ԫ����С�ƣ�9.00��Ԫ��",receiptPrinter.oneItemInReceiptItem(createProduct(3.00),3));
		Assert.assertEquals("���ƣ��ɿڿ��֣�������2ƿ�����ۣ�3.00��Ԫ����С�ƣ�6.00��Ԫ��",receiptPrinter.oneItemInReceiptItem(createProduct(3.00),2));
		
	}
}
