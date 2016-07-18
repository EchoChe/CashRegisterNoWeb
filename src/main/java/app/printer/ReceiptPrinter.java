package app.printer;

import java.util.LinkedHashMap;

import app.model.Product;

public class ReceiptPrinter {

	public String getReceiptHead() {
		return "***<ûǮ׬�̵�>�����嵥***";
	}

	public String printOneItemInItemsSection(Product product, int number) {
		return String.format("���ƣ�%s��������%d%s�����ۣ�%1.2f(Ԫ)��С�ƣ�%1.2f(Ԫ)",
								product.getName(),
								number,product.getUnit(),
								product.getPrice(),
								product.getPrice() * number);
	}

	public String printMultipleItemsInItemSection(LinkedHashMap<Product, Integer> productsWithNumbers) {
		String str = "";
		for(Product product : productsWithNumbers.keySet()){
			str += printOneItemInItemsSection(product, productsWithNumbers.get(product));
			str += "\n";
		}
		return str;
	}

	public String getReceiptSum(double totalPrice) {
		return String.format("�ܼƣ�%1.2f(Ԫ)", totalPrice);
	}

	public String printOneItemInItemsSectionWhentDiscount(Product product, int number) {
		return String.format("���ƣ�%s��������%d%s�����ۣ�%1.2f(Ԫ)��С�ƣ�%1.2f(Ԫ)����ʡ��%1.2f(Ԫ)",
				product.getName(),
				number,product.getUnit(),
				product.getPrice(),
				product.getPrice() * number * 0.95,
				product.getPrice() * number * (1-0.95));
	}

	public String printMultipleItemsInItemSectionWhenHaveMutipleDiscountAndAnthorAsUsual(
			LinkedHashMap<Product, Integer> productsWithNumbers) {
		String str = "";
		for(Product product : productsWithNumbers.keySet()){
			if(product.getBarcode() != "ITEM0003")
			{
				str += printOneItemInItemsSection(product, productsWithNumbers.get(product));
			}
			else
			{
				str += printOneItemInItemsSectionWhentDiscount(product, productsWithNumbers.get(product));
			}
			str += "\n";
		}
		return str;
	}

	public String printOneItemInItemsSectionWhenByeTwoGetOneFree(Product product, int number) {
		if (number < 3)
			return printOneItemInItemsSection(product, number);
		else 
			return String.format("���ƣ�%s��������%d%s�����ۣ�%1.2f(Ԫ)��С�ƣ�%1.2f(Ԫ)",
					product.getName(),
					number,product.getUnit(),
					product.getPrice(),
					product.getPrice() * (number - number/3));
	}

	public String getReceiptByeTwoGetOneFreeHead() {
		return "�����һ��Ʒ:\n";
	}
	
	public String printMultipleItemsInItemSectionWhenHaveMutipleByeTwoGetOneFreeAndAnthorAsUsual(
			LinkedHashMap<Product, Integer> productsWithNumbers) {
		String str = "";
		String str2 = "";
		for(Product product : productsWithNumbers.keySet()){
			if((product.getBarcode() != "ITEM0001") && (product.getBarcode() != "ITEM0002"))
			{
				str += printOneItemInItemsSection(product, productsWithNumbers.get(product));
			}
			else
			{
				str += printOneItemInItemsSectionWhenByeTwoGetOneFree(product, productsWithNumbers.get(product));
				str2 += printOneItemInItemsSectionWheByeTwoGetOneFreeList(product, productsWithNumbers.get(product));
				str2 += "\n";
			}
			str += "\n";
		}
		return str +"-----------\n" + getReceiptByeTwoGetOneFreeHead() + str2;
	}

	private String printOneItemInItemsSectionWheByeTwoGetOneFreeList(Product product, int number) {
		if (number < 3)
			return printOneItemInItemsSection(product, number);
		else 
			return String.format("���ƣ�%s��������%d%s",
					product.getName(),
					number/3,
					product.getUnit());
	}
}
