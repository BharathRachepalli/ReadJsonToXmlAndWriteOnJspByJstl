package Services;

import org.json.JSONObject;
import org.json.XML;

public class jsonToXml {
	public void toxml(String json) {
		
		JSONObject jo1 = new JSONObject(json);
		
		String xml = XML.toString(jo1);

		System.out.println("print in xml ");
		System.out.println(xml);
		
	}
}
