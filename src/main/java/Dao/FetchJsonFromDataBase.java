package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Author;
import Model.Book;
import Utility.DbConnect;

public class FetchJsonFromDataBase {
	public List<Book> getJsonFromDataBase() {
		List<Book> booklist = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		String sql = "select * from book";
		try {
			con = new DbConnect().ConnectToDB();
			stmt = con.createStatement();
			res= stmt.executeQuery(sql);
			int count = 0;
			int authCount = 1;
			while(res.next()) {
				
				if (booklist.size() != 0 && booklist.get(count-1).getBookTitle().equals(res.getString(2))) {
					List<Author> authorlist = booklist.get(count-1).getBookAuthors();
					
					Author a = new Author();
					a.setAuthorId(Integer.parseInt(res.getString(4)));
					a.setAuthorName(res.getString(5));
					
					authorlist.add(authCount,a);
					authCount++;
					
					
				}else {
					Book b = new Book();
					authCount=1;
					
					b.setBookId(Integer.parseInt(res.getString(1)));
					b.setBookTitle(res.getString(2));
					b.setBookPrice(Integer.parseInt(res.getString(3)));
					
					List<Author> authorlist = new ArrayList<Author>();
					Author a = new Author();
					a.setAuthorId(Integer.parseInt(res.getString(4)));
					a.setAuthorName(res.getString(5));
					authorlist.add(0,a);
					count++;
					b.setBookAuthors(authorlist);
					booklist.add(b);
					
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Book i : booklist) {
			System.out.println("in dao fetchingFromDatabase :"+i);
		}
		return booklist;
	}
}
