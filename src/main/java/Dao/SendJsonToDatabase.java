package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import Model.Author;
import Model.Book;
import Utility.DbConnect;

public class SendJsonToDatabase {
	public void addToDataBase(List<Book> booklist) {

		Connection con = null;
		PreparedStatement stmt = null;

		String sql = "insert into book values(?,?,?,?,?)";

		try {
			con = new DbConnect().ConnectToDB();
			stmt = con.prepareStatement(sql);
			
			for(Book i:booklist) {
				List<Author> author = i.getBookAuthors();
				
				for(Author j : author) {
					stmt.setInt(1, i.getBookId());
					stmt.setString(2, i.getBookTitle());
					stmt.setInt(3, i.getBookPrice());
					stmt.setInt(4, j.getAuthorId());
					stmt.setString(5, j.getAuthorName());
					stmt.executeUpdate();
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
