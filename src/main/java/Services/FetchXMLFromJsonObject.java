package Services;

import java.util.List;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import Model.Book;

public class FetchXMLFromJsonObject {
	public void jsonToXml(List<Book> booklist) {
		
		Gson gson = new Gson();
		String toJson = gson.toJson(booklist);
		
		JsonArray conArr = new Gson().fromJson(toJson, JsonArray.class);
		
		JSONObject jo = new JSONObject();
		jo.put("root", conArr);
		
		String toJson1 = gson.toJson(jo);
//		System.out.println("toJson1"+toJson1);
		//convert to XML
		new jsonToXml().toxml(toJson1);
		
		
	}
}
