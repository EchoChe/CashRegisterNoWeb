package app.UtilFiles;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadUtilFile {

	public static String readFile(String filePath)
	{
		String str = "";
		File file = new File(filePath);
		// 判断文件是否存在，且其属性是否为file
		if(file.exists() && file.isFile()){ 
			BufferedReader bufferedReader = null;
			String lineData = "";
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				// 逐行从文件中读取输入数据
				while((lineData = bufferedReader.readLine()) != null){
					str += lineData;
				}
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				close(bufferedReader);
			}
		}
	return str;
	}
	
	public static void close(Closeable closeable) {
		if(null != closeable){
			try {
				closeable.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	/*
	public static void clear(){
		discountMap = new HashMap<String, String>();
		discountConvertMap = new HashMap<String, DiscountConvert>();
		productMap = new HashMap<String, Product>();
		productsWithNumbers = new LinkedHashMap<String, Integer>();
	}
	*/
}
