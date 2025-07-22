package functionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookDTO.BookDTO;
import exceptionMaker.BookAlreadyException;
import exceptionMaker.BookNotAvailableException;
import exceptionMaker.MaxBorrowException;

public class RentalDAO {

	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	// 책 대여
	// 책 반납
	// 책 조회 : 책이름, 빌린사람, 빌린날짜, 반납날짜 띄우도록

	// 모든 책을 조회
	public List<BookDTO> checkAllBook() 
	{
		String query = "SELECT BOOK_NUMBER, BOOK_TITLE, BOOK_AUTHOR, BOOK_AVAILABLE FROM TBL_BOOKS";

		List<BookDTO> bookList = new ArrayList<>();
		
		try 
		{
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) 
			{
				// 책의 정보를 모두 가져와서 새로운 책 객체를 만들어 반환
				int bookNumber = resultSet.getInt("BOOK_NUMBER");
				String bookTitle = resultSet.getString("BOOK_TITLE");
				String bookAuthor = resultSet.getString("BOOK_AUTHOR");
				String bookAvailableString = resultSet.getString("BOOK_AVAILABLE");
				boolean isBookAvailable = bookAvailableString.equals("T") ? true : false;
				
				BookDTO newBook = new BookDTO(bookNumber, bookTitle, bookAuthor, isBookAvailable, -1);
				bookList.add(newBook);
			}
		} catch (SQLException e) {
			System.out.println("책들 조회 이상");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("책들 조회 닫기 이상");
				e.printStackTrace();
			}
		}

		return bookList;
	}
	
	// 책을 번호로 검색  -> 하나만 받기
	public BookDTO searchBookByNumber(int targetBookNumber) 
	{
		String query = "SELECT BOOK_NUMBER, BOOK_TITLE, BOOK_AUTHOR, BOOK_AVAILABLE "
					+ "FROM TBL_BOOKS "
					+ "WHERE BOOK_NUMBER = ?";
		
		BookDTO targetBook = null;
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, targetBookNumber);
			resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			
			int bookNumber = resultSet.getInt("BOOK_NUMBER");
			String bookTitle = resultSet.getString("BOOK_TITLE");
			String bookAuthor = resultSet.getString("BOOK_AUTHOR");
			String bookAvailableString = resultSet.getString("BOOK_AVAILABLE");
			boolean isBookAvailable = bookAvailableString.equals("T") ? true : false;
			
			targetBook = new BookDTO(bookNumber, bookTitle, bookAuthor, isBookAvailable, -1);
		} catch (SQLException e) {
			System.out.println("책 번호 찾기 문제");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("책 번호 찾기 닫기 문제");
				e.printStackTrace();
			}
		}
		
		return targetBook;
	}
	
	// 책을 이름으로 검색 -> arrayList로 받기
	
	public List<BookDTO> searchBookByTitle(String bookName) 
	{
		String query = "SELECT BOOK_NUMBER, BOOK_TITLE, BOOK_AUTHOR, BOOK_AVAILABLE "
				+ "FROM TBL_BOOKS "
				+ "WHERE BOOK_TITLE = ?";
		
		List<BookDTO> targetBookList = new ArrayList<>();
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bookName);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) 
			{
				int bookNumber = resultSet.getInt("BOOK_NUMBER");
				String bookTitle = resultSet.getString("BOOK_TITLE");
				String bookAuthor = resultSet.getString("BOOK_AUTHOR");
				String bookAvailableString = resultSet.getString("BOOK_AVAILABLE");
				boolean isBookAvailable = bookAvailableString.equals("T") ? true : false;
				
				BookDTO targetBook = new BookDTO(bookNumber, bookTitle, bookAuthor, isBookAvailable, -1);
				targetBookList.add(targetBook);
			}
			System.out.println(targetBookList);
			System.out.println("검색 결과 출력 완료");
			
		} catch (SQLException e) {
			System.out.println("책 이름 찾기 문제");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("책 이름 찾기 닫기 문제");
				e.printStackTrace();
			}
		}
		
		return targetBookList;
	} 

	// 유저가 빌린 책들을 조회 -> arrayList로 받기
	public List<BookDTO> checkBorrowedBook(int userNumber)
	{
		String query = "SELECT BOOK_NUMBER, BOOK_TITLE, BOOK_AUTHOR, BOOK_AVAILABLE "
				+ "FROM TBL_BOOKS "
				+ "WHERE USER_NUMBER = ?";
		
		List<BookDTO> bookList = new ArrayList<>();
		
		try 
		{
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) 
			{
				// 책의 정보를 모두 가져와서 새로운 책 객체를 만들어 반환
				int bookNumber = resultSet.getInt("BOOK_NUMBER");
				String bookTitle = resultSet.getString("BOOK_TITLE");
				String bookAuthor = resultSet.getString("BOOK_AUTHOR");
				String bookAvailableString = resultSet.getString("BOOK_AVAILABLE");
				boolean isBookAvailable = bookAvailableString.equals("T") ? true : false;
				
				BookDTO newBook = new BookDTO(bookNumber, bookTitle, bookAuthor, isBookAvailable, userNumber);
				bookList.add(newBook);
			}
		} catch (SQLException e) {
			System.out.println("유저가 빌린 책들 조회 이상");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("유저가 빌린 책들 조회 닫기 이상");
				e.printStackTrace();
			}
		}

		return bookList;
	}	
	
	// 대출이 안된 책을 검색 -> arrayList로 받기
	public List<BookDTO> checkAvailableBook()
	{
		String query = "SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_AVAILABLE "
				+ "FROM TBL_BOOKS "
				+ "WHERE USER_NUMBER IS NULL";
		
		List<BookDTO> bookList = new ArrayList<>();
		
		try 
		{
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) 
			{
				// 책의 정보를 모두 가져와서 새로운 책 객체를 만들어 반환
				String bookTitle = resultSet.getString("BOOK_TITLE");
				String bookAuthor = resultSet.getString("BOOK_AUTHOR");
				String bookAvailableString = resultSet.getString("BOOK_AVAILABLE");
				boolean isBookAvailable = bookAvailableString.equals("T") ? true : false;
				
				if(isBookAvailable = true) 
				{
					BookDTO newBook = new BookDTO(bookTitle, bookAuthor, isBookAvailable);
					bookList.add(newBook);
				}
			}
		} catch (SQLException e) {
			System.out.println("유저가 빌린 책들 조회 이상");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("유저가 빌린 책들 조회 닫기 이상");
				e.printStackTrace();
			}
		}

		return bookList;
	}
	
	// 책을 이름으로 검색
	public List<BookDTO> checkBookWithName(String targetBookTitle)
	{
		String query = "SELECT BOOK_NUMBER, BOOK_TITLE, BOOK_AUTHOR, BOOK_AVAILABLE, USER_NUMBER "
				+ "FROM TBL_BOOKS "
				+ "WHERE BOOK_TITLE LIKE ?";
		
		List<BookDTO> bookList = new ArrayList<>();
		
		try 
		{
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + targetBookTitle + "%");
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) 
			{
				// 책의 정보를 모두 가져와서 새로운 책 객체를 만들어 반환
				int bookNumber  = resultSet.getInt("BOOK_NUMBER");
				String bookTitle = resultSet.getString("BOOK_TITLE");
				String bookAuthor = resultSet.getString("BOOK_AUTHOR");
				String bookAvailableString = resultSet.getString("BOOK_AVAILABLE");
				int userNumber = resultSet.getInt("USER_NUMBER");
				boolean isBookAvailable = bookAvailableString.equals("T") ? true : false;
				
				BookDTO newBook = new BookDTO(bookNumber, bookTitle, bookAuthor, isBookAvailable, userNumber);
				bookList.add(newBook);
			}
		} catch (SQLException e) {
			System.out.println("유저가 빌린 책들 조회 이상");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("유저가 빌린 책들 조회 닫기 이상");
				e.printStackTrace();
			}
		}

		return bookList;
	}
	

	// 책을 번호로 빌리기 -> USER_NUMBER을 해당 유저로 변경
	public void borrowBookByNumber(int bookNumber, int userNumber) throws MaxBorrowException, BookNotAvailableException, BookAlreadyException
	{
		String query = "UPDATE TBL_BOOKS "
				+ "SET USER_NUMBER = ?, BOOK_AVAILABLE = 'T' "
				+ "WHERE BOOK_NUMBER = ?";
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			preparedStatement.setInt(2, bookNumber);
			preparedStatement.executeUpdate();
			
			System.out.println(searchBookByNumber(bookNumber).getBookTitle() + "을 대여했습니다.");
			
		} catch (SQLException e) {
			System.out.println("책을 번호로 빌리기 이상");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("책을 번호로 빌리기 닫기 이상");
				e.printStackTrace();
			}
		}
		
	}

	// 책을 이름으로 빌리기 -> USER_NUMBER을 해당 유저로 변경
	public void borrowBookByName(String bookTitle, int userNumber) throws MaxBorrowException, BookNotAvailableException, BookAlreadyException
	{
		List<BookDTO> books = checkAllBook();
		
		for(int i = 0; i < books.size(); i++) 
		{
			if(books.get(i).getBookTitle().equals(bookTitle)) 
			{
				if(books.get(i).isBookAvailable() == true) 
				{
					borrowBookByNumber(i, userNumber);					
					break;
				}
				else throw new BookNotAvailableException();				
			}
		}	
	}

	// 책을 번호로 반납하기 -> 해당 책의 USER_NUMBER를 NULL 로 변경
	public void returnBookByNumber(int bookNumber, int userNumber) 
	{
		String query = "UPDATE TBL_BOOKS "
				+ "SET USER_NUMBER = NULL, BOOK_AVAILABLE = 'F' "
				+ "WHERE BOOK_NUMBER = ? AND USER_NUMBER = ?";
		
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, bookNumber);
			preparedStatement.setInt(2, userNumber);
			preparedStatement.executeUpdate();
			
//			System.out.println(searchBookByNumber(bookNumber).getBookTitle() + "을 반납했습니다.");
			
		} catch (SQLException e) {
			System.out.println("책을 번호로 반납 이상");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("책을 번호로 반납 닫기 이상");
				e.printStackTrace();
			}
		}
		
	}

	// 책을 이름으로 반납하기 -> 해당 책의 USER_NUMBER를 NULL 로 변경
	// 책을 이름으로 빌리기 -> USER_NUMBER을 해당 유저로 변경
	public void returnBookByName(String bookTitle, int userNumber)
	{
		List<BookDTO> books = checkAllBook();
		
		for(int i = 0; i < books.size(); i++) 
		{
			if(books.get(i).getBookTitle().equals(bookTitle) && books.get(i).isBookAvailable() == false) 
			{
				returnBookByNumber(i, userNumber);
				break;
			}
		}	
	}
	

	// 담당자 : 이태호, 정훈
}