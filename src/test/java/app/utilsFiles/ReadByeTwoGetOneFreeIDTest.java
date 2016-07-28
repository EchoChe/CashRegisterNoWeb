package app.utilsFiles;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import app.model.ByeThreeGetOneFree;

public class ReadByeTwoGetOneFreeIDTest {

	@Test
	public void test() {
		
		ArrayList<String> actual = ByeThreeGetOneFree.readBuyTwoGetOneFreeID();

		ArrayList<String> list = new ArrayList<String>();
		list.add("ITEM000001");
		list.add("ITEM000002");
		list.add("ITEM000005");

        Assert.assertEquals(list,actual);
	}

}
