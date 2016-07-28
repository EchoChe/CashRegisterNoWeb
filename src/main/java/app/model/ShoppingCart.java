package app.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.UtilFiles.ReadUtilFile;

public class ShoppingCart {

	private static ArrayList<String> readShoppingCartItem()
	{
		String str = ReadUtilFile.readFile(".\\Data\\shoppingCart.json");
		//买二赠一
		Gson gson = new Gson();
		ArrayList<String> retArray = gson.fromJson(str,  new TypeToken<ArrayList<String>>(){}.getType()); 
		return retArray;
	}
	

	public static LinkedHashMap<String, Integer> shoppingCartItem()
	{
		LinkedHashMap<String, Integer> productsWithNumbers = new LinkedHashMap<String, Integer>();
		List<String> shoppingList = readShoppingCartItem();
		
        for(int i = 0; i < shoppingList.size(); i++)
        {
        	if(shoppingList.get(i).contains("-"))
            	{
            		String[] temp = shoppingList.get(i).split("-");
            		if(productsWithNumbers.containsKey(temp[0]))
            			productsWithNumbers.put(temp[0], productsWithNumbers.get(temp[0])+Integer.valueOf(temp[1]));
            		else
            			productsWithNumbers.put(temp[0], Integer.valueOf(temp[1]));
            	}
        	else{
        		if(productsWithNumbers.containsKey(shoppingList.get(i)))
        			productsWithNumbers.put(shoppingList.get(i), productsWithNumbers.get(shoppingList.get(i))+Integer.valueOf(1));  
        		else
        			productsWithNumbers.put(shoppingList.get(i), Integer.valueOf(1));
        	}	
        }
        return productsWithNumbers;
	}
}
