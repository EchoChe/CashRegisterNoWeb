package app.utilsFiles;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.xml.internal.txw2.IllegalAnnotationException;

import app.model.ByeThreeGetOneFree;
import app.model.Discount;

public class SaleTest {

	@SuppressWarnings("unchecked")
	@Test
	public <Sale> void test() {
		Sale sale;
		
		switch(2)
		{
		case 1:
			sale = (Sale)new ByeThreeGetOneFree();
			break;
		case 2:
			sale = (Sale)new Discount();
			break;
		default:
			throw new IllegalAnnotationException("Incorrect Sale Code");
		}
		System.out.println(sale.getClass());
		
	}

}
