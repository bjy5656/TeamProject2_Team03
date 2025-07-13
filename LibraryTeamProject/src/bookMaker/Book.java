package bookMaker;

import java.util.Objects;

import exceptionMaker.BookAlreadyException;
import exceptionMaker.BookNotAvailableException;
import exceptionMaker.MaxBorrowException;

public class Book implements Borrow {
	// 필드
	private String title;
	private String author;
	private String publisher;
	private boolean isAvailable = true;

	
	// 생성자
	public Book(String title, String author, String publisher) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
	
	
	// 메소드 오버라이딩
	// 도서대출 오버라이딩 -> 메시지 출력, 도서 정보 출력, 대출가능 여부 변경(false), 예외 던지기
	@Override
	public void borrowBook() throws BookNotAvailableException{
		if(!isAvailable) {
			throw new BookNotAvailableException();
		}
		System.out.println("도서를 대출합니다!");
//		printInfo();
		System.out.println(this);
		isAvailable = false;
	}

	// 도서반납 오버라이딩 -> 메시지 출력, 도서 정보 출력, 대출가능 여부 변경(true)
	@Override
	public void returnBook() {
		System.out.println("도서를 반납합니다!");
//		printInfo();
		System.out.println(this);
		isAvailable = true;
	}

	// hashCode 오버라이딩 -> 이름, 저자, 출판사를 이용해서 생성한 해시값 반환
	@Override
	public int hashCode() {
		return Objects.hash(title, author, publisher);
	}

	// equals 오버라이딩 -> 제목, 저자, 출판사가 같으면 같은 객체로 판단
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(obj instanceof Book) {
			Book book = (Book)obj;
	        return this.title.equals(book.title) && this.author.equals(book.author) && this.publisher.equals(book.publisher);
		}
		return false;
	}

	// toString 오버라이딩 -> 도서 정보 반환
	@Override
	public String toString() {
		return "[제목 : " + title + ", 저자 : " + author + ", 출판사 : " + publisher + ", 대출가능 여부 : "
				+ (isAvailable ? "대출가능" : "대출불가") + "]";
	}
	
//	//메소드
//	//도서정보 출력 메소드
//	public void printInfo() {
//		System.out.println("책 이름 : " + getTitle());
//		System.out.println("저자 : " + getAuthor());
//	}
	
	// getter, setter
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
	public String getPublisher() {
		return publisher;
	}

}
