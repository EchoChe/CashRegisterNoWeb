package app.printer;

import java.util.LinkedHashMap;

import app.UtilFiles.ReadUtilFile;
import app.model.Product;

public class ReceiptPrinter {

	public static ReadUtilFile readUtilFile = new ReadUtilFile(); 
	
	
	public String getReceiptHead() {
		return "***<没钱赚商店>购物清单***\n";
	}

	public String printOneItemInItemsSection(Product product, Integer number, double itemPrice) {
		return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)\n",
								product.getName(),
								number,
								product.getUnit(),
								product.getPrice(),
								itemPrice);
	}

	public String printOneItemInItemsSectionWhentDiscount(Product product, int number, double itemPrice, double itemSave) {
		
		//反向寻找折扣类型
		return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)，节省：%1.2f(元)\n",
				product.getName(),
				number,product.getUnit(),
				product.getPrice(),
				itemPrice,
				itemSave);
	}

	public String printOneItemInItemsSectionWhenBuyTwoGetOneFree(Product product, int number, double itemPrice) {
		if (number < 3)
			return printOneItemInItemsSection(product, number, itemPrice);
		else 
		{
			return String.format("名称：%s，数量：%d%s，单价：%1.2f(元)，小计：%1.2f(元)\n",
					product.getName(),
					number,product.getUnit(),
					product.getPrice(),
					itemPrice);
		}
	}
	
	public String getReceiptBuyTwoGetOneFreeHead() {
		return "买二赠一商品：\n";
	}

	public String printOneItemInItemsSectionWheBuyTwoGetOneFreeList(LinkedHashMap<Product, Integer> buyTwoFreeOneList) {
		String str = "";
		for(Product product : buyTwoFreeOneList.keySet()){
			int number = buyTwoFreeOneList.get(product);
			str += String.format("名称：%s，数量：%d%s\n",
							product.getName(),
							number,
							product.getUnit());
		}
		return str;
	}

	public String getReceiptSum(double totalPrice, double totalSave) {
		if(totalSave==0.0)
			return String.format("总计：%1.2f(元)\n", totalPrice);
		else
			return String.format("总计：%1.2f(元)\n 节省：%1.2f(元)\n", totalPrice,totalSave);
	}

}
