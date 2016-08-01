package app;

public class CashRigisterMain {
	public static void main(String[] args) {
		
		CashRegister cashRegister = new CashRegister();
		String shoppingCartList = cashRegister.printReceipt();
		String buyTwoGetOneFreeList = cashRegister.getByeTwoGetFreeOneMessage();
		String sumList = cashRegister.sum();
		System.out.println(shoppingCartList);
		System.out.println(buyTwoGetOneFreeList);
		System.out.println(sumList);
	}
}
