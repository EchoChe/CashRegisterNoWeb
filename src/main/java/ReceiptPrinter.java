import java.math.BigInteger;

public class ReceiptPrinter {

	public String getReceiptHead() {
		return "ûǮ׬�̵��嵥";
	}

	public String oneItemInReceiptItem(Product product, int number) {
		String str = String.format("���ƣ��ɿڿ��֣�������%dƿ�����ۣ�%1.2f��Ԫ����С�ƣ�%1.2f��Ԫ��",number,product.getPrice().doubleValue(),product.getPrice().doubleValue() * number);
		return str;
	}
}
