package app.utilsFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.UtilFiles.ReadUtilFile;
import app.model.Product;
import junit.framework.Assert;

public class ReadProductItemFromJsonTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void test()
	{
		String str = ReadUtilFile.readFile(".\\Data\\product.json");
		//��json stringתΪproduct���� ����map���
		Gson gson = new Gson();
	    Map<String, Product> retMap = gson.fromJson(str, new TypeToken<Map<String, Product>>(){}.getType());
	    for(String barcode: retMap.keySet())
	    {
	    	Product product = retMap.get(barcode);
	    	//System.out.println("barcode:" + barcode + "    name��" + product.getName());
	    	if(barcode == "ITEM0001")
	    		Assert.assertEquals("�ɿڿ���", product.getName());
	    	if(barcode == "ITEM0004")
	    		Assert.assertEquals("���Ա�", product.getName());
	    }
	}
}
