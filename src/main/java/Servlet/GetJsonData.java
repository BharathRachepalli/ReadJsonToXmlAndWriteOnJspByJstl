package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Book;
import Services.FetchJsonData;

/**
 * Servlet implementation class GetJsonData
 */
@WebServlet("/GetJsonData")
public class GetJsonData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		displayBook(request, response);

	}

	private static void displayBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

		String json = "";
		if (br != null) {
			json = br.readLine();
		}
//		System.out.println(json);

		List<Book> bookList = new FetchJsonData().getBookData(json);
		
		
//		response.setContentType("application/text");
//		request.setCharacterEncoding("UTF-8");
//		for(Book i : bookList) {
//			response.getWriter().println(i);
//			response.setStatus(200);
//		}
//		response.getWriter().flush();
		
//		request.setAttribute("book_list", bookList);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/CodeChallengeSendJson.jsp");
//		dispatcher.forward(request, response);
		
		HttpSession session = request.getSession();
		session.setAttribute("book_list", bookList);
		response.sendRedirect("PrintList.jsp");
	}
}
