package app.utilsFiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.UtilFiles.ReadUtilFile;

public class ReadByeTwoGetOneFreeIDTest {

	@Test
	public void test() {
		
		ArrayList<String> actual = ReadUtilFile.readBuyTwoGetOneFreeID();

		ArrayList<String> list = new ArrayList<String>();
		list.add("ITEM000001");
		list.add("ITEM000002");
		list.add("ITEM000005");

        Assert.assertEquals(list,actual);
	}

}
