package bookMaker;

import exceptionMaker.BookAlreadyException;
import exceptionMaker.BookNotAvailableException;
import exceptionMaker.MaxBorrowException;

public class Book implements Borrow{
	//필드 
	private String title; 
	private String author; 
	private boolean isAvailable = true;
	
	//생성자 
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	//메소드 오버라이딩 
	//도서대출
	@Override
	public void borrowBook() throws MaxBorrowException, BookNotAvailableException, BookAlreadyException {
		System.out.println("도서를 대출합니다!");
		printInfo();
		setAvailable(false);
	}
	//도서반납
	@Override
	public void returnBook() {
		System.out.println("도서를 반납합니다!");
		printInfo();
		setAvailable(true);
	}
	//도서정보 출력 메소드
	public void printInfo() {
		System.out.println("책 이름 : " + getTitle());
		System.out.println("저자 : " + getAuthor());
	}

	//getter, setter 
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}  
	
	
	
}
