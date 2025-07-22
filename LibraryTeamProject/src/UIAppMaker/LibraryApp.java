package UIAppMaker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import bookDTO.BookDTO;
import functionDAO.BookDAO;
import functionDAO.DBConnecter;

public class LibraryApp {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ConsoleUI ui = new ConsoleUI(null);
		// 멤버를 하나 새로 만들어서 거기에 데이터 넣어주고 member = 해당 값으로
		
		try {
			Connection conn = DBConnecter.getConnection();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		BookDAO bookDAO = new BookDAO();
//		Book newBook = new Book("무인도", "뇽", true);
//		bookDAO.addBook(newBook);
		
		ui.loginMenu(sc);
		
		sc.close();

	}
}	
