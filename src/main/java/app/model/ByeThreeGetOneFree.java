package app.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.UtilFiles.ReadUtilFile;

public class ByeThreeGetOneFree {

	public static ArrayList<String> readBuyTwoGetOneFreeID()
	{
		String str = ReadUtilFile.readFile(".\\Data\\ByeTwoGetOneFreeID.json");
		//买二赠一
		Gson gson = new Gson();
		ArrayList<String> retArray = gson.fromJson(str,  new TypeToken<ArrayList<String>>(){}.getType()); 
		return retArray;
	}
}
