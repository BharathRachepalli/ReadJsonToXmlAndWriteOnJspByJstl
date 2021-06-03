package Services;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Dao.FetchJsonFromDataBase;
import Dao.SendJsonToDatabase;
import Model.Author;
import Model.Book;

public class FetchJsonData {


public List<Book> getBookData(String json){
	
	List<Book> bookList = new ArrayList<Book>();
	JSONArray jsonArray = null;
	try {
		JSONParser jsonParser = new JSONParser();
		jsonArray = (JSONArray) jsonParser.parse(json);
		for (Object objBook : jsonArray) {
			JSONObject book = (JSONObject) objBook;
			
			Book b = new Book();
			
//			System.out.println(book.get("id"));
//			System.out.println(book.get("title"));
//			System.out.println(book.get("price"));
			
			b.setBookId(Integer.parseInt(book.get("id").toString()));
			b.setBookTitle(book.get("title").toString());
			b.setBookPrice(Integer.parseInt(book.get("price").toString()));
			
			
//			System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
			List<Author> authorList = new ArrayList<Author>();
			
			JSONArray authors = (JSONArray) book.get("authors");
			
			for(Object objAuthor : authors) {
				JSONObject author = (JSONObject) objAuthor;
				
				Author a = new Author();
				
//				System.out.println(author.get("id"));
//				System.out.println(author.get("name"));
				
				a.setAuthorId(Integer.parseInt(author.get("id").toString()));
				a.setAuthorName(author.get("name").toString());
				
				authorList.add(a);
			}
			b.setBookAuthors(authorList);
			bookList.add(b);
		}
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	new SendJsonToDatabase().addToDataBase(bookList);

	List<Book> booklistfromDatabase = new FetchJsonFromDataBase().getJsonFromDataBase();
	
	new FetchXMLFromJsonObject().jsonToXml(booklistfromDatabase);
	
//	for(Book b : bookList) {
//		System.out.println(b);
//	}
	
	return bookList;
}
}
