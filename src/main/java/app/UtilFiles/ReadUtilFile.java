package app.UtilFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class ReadUtilFile {

	public static String readFile(String filePath)
	{
		String str = "";
		File file = new File(filePath);
		// �ж��ļ��Ƿ���ڣ����������Ƿ�Ϊfile
		if(file.exists() && file.isFile()){ 
			BufferedReader bufferedReader = null;
			String lineData = "";
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
				// ���д��ļ��ж�ȡ��������
				while((lineData = bufferedReader.readLine()) != null){
					str += lineData;
				}
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				CloseUtil.close(bufferedReader);
			}
		}
	return str;
	}
}
