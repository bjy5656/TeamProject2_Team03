package serviceDeveloper;

import java.util.ArrayList;
import java.util.Iterator;

import UIAppMaker.ConsoleUI;
import bookMaker.Book;
import exceptionMaker.BookAlreadyException;
import exceptionMaker.BookNotAvailableException;
import exceptionMaker.MaxBorrowException;
import member.Member;

public class LibraryService {	
	private ArrayList<Book> bookList = new ArrayList<>();
	
	private String[] nameList = {"자바", "데이터베이스", "보안", "부자아빠", "리눅스마스터", "SQLD"};
	private String[] author = {"이정훈", "민경승", "백정이", "이태호", "이재빈", "김태현"};
	private String[] publisher = {"잉", "엥", "옹"};
	

	public LibraryService() {
		this.bookList = new ArrayList<Book>();
		randomBookList();
	}

	// 현재 책목록을 모두 출력하는 메소드
	public void printBookList() 
	{		
		ConsoleUI.printBookList(bookList);
	}
	
	// getter
	public ArrayList<Book> getBookList() {
		return bookList;
	}
	
	// 유저가 책을 빌리는 메소드
	// 만약 유저가 빌린 책 목록에 해당 인덱스의 책을 add 해준다.
	// 해당 책과 같은 이름의 도서관 책 목록에 있는 책의 대출 가능 상태를 false로 바꿔줌
		
	// borrowBook 에서는 해당 
	
	public void borrowBookService(Member user, String bookName) throws MaxBorrowException, BookNotAvailableException, BookAlreadyException 
	{
		int index = findBookIndex(bookList, bookName, true);
		boolean isFound = index != -1 ? true : false;
		
		if(isFound) 
		{
			user.borrowBooks(bookList.get(index));
		}
	}
	
	// 책의 인덱스로 빌리는 메소드
	public void borrowBookService(Member user, int index) throws MaxBorrowException, BookNotAvailableException, BookAlreadyException 
	{		
		user.borrowBooks(bookList.get(index));
	}

	// 유저가 빌린 책을 반납하는 메소드
	// 만약 유저가 빌린 책 목록에 같은 이름을 가진 책이 있다면
	// 해당 책을 리스트에서 remove 해주고
	// 해당 책과 같은 이름의 도서관 책 목록에 있는 책의 대출 가능 상태를 true로 바꿔줌
	// returnbook 에서는 책에 index로 받아서 index에 있는 값을 제거
	public void returnBookService(Member user, String bookName) 
	{		
		if(user.bookSerch(bookName)) 
		{
			int index = findBookIndex((ArrayList<Book>)user.getBorrowBook(), bookName);
			user.bookReturn(index);
		}
	}
	
	// 번호로 반납
	public void returnBookService(Member user, int index) 
	{		
		user.bookReturn(index);
	}
	
	// 랜덤으로 책을 5개 생성 해주는 메소드
	private void randomBookList() 
	{
		for(int i = 0; i < 5; i++) 
		{
			int randomName = (int)(Math.random() * nameList.length);
			int randomAuthor = (int)(Math.random() * author.length);
			int randomPublisher = (int)(Math.random() * publisher.length);
			
			addBook(nameList[randomName], author[randomAuthor], publisher[randomPublisher]);
		}
	}
	
	// 책을 추가해주는 메소드
	public void addBook(String name, String author, String publisher) 
	{
		Book newBook = new Book(name, author, publisher);
		bookList.add(newBook);
	}
	
	// 책리스트 내에서 책의 이름을 기준으로 인덱스를 찾아주는 메소드
	private int findBookIndex(ArrayList<Book> targetList, String targetName) 
	{
		int index = 0;;
		for(Book target : targetList) 
		{
			if(target.getTitle().equals(targetName)) 
			{
				return index;
			}			
			index ++;
		}
		
		return -1;
	}
	
	// 대출이 가능한 책 혹은 대출이 불가능한 책을 정해서 인덱스를 반환 하도록 오버로딩
	private int findBookIndex(ArrayList<Book> targetList, String targetName, boolean checkReturn) 
	{
		int index = 0;;
		for(Book target : targetList) 
		{
			if(target.getTitle().equals(targetName)) 
			{
				if(target.isAvailable() == checkReturn) return index;
			}			
			index ++;
		}
		
		return -1;
	}
	
	public ArrayList<Book> findBookList(String targetName)
	{
		ArrayList<Book> result = new ArrayList<Book>();
		Iterator<Book> iter = bookList.iterator();
		while(iter.hasNext()) 
		{
			Book checkBook = iter.next();
			if(checkBook.getTitle().equals(targetName)) 
			{
				result.add(checkBook);
			}
		}
		
		return result;
	}
	
	// toString 메소드 오버라이딩
		@Override
		public String toString() {
			String info = "현재 도서관에 있는 책목록은 " + bookList + "입니다";
			return info;
		}
	
}
