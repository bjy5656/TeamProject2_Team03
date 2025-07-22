package bookDTO;

public class BookDTO {
	//sql
//	CREATE TABLE TBL_BOOKS(
//			BOOK_NUMBER NUMBER,
//			BOOK_TITLE varchar2(100) NOT NULL,
//			BOOK_AUTHOR varchar2(100) NOT NULL,
//			BOOK_AVAILABLE Char(1) DEFAULT 'T' NOT NULL,
//			USER_NUMBER NUMBER,
//			CONSTRAINT PK_BOOK_NUMBER PRIMARY KEY(book_number),
//			CONSTRAINT FK_USER_NUMBER FOREIGN KEY(USER_NUMBER) REFERENCES TBL_USER (USER_NUMBER),
//			CHECK(BOOK_AVAILABLE IN ('T', 'F'))
//		);
	
	//필드
	private int bookNumber;
	private String bookTitle;
	private String bookAuthor;
	private boolean bookAvailable;
	private int userNumber;
	
	//getter, setter
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public boolean isBookAvailable() {
		return bookAvailable;
	}
	public void setBookAvailable(boolean bookAvailable) {
		this.bookAvailable = bookAvailable;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	
	//toString
	@Override
	public String toString() {
		return "[도서번호 : " + bookNumber
				+ ", 제목 : " + bookTitle 
				+ ", 저자 : " + bookAuthor 
				+ ", 대출가능 여부 : " + (bookAvailable ? "가능" : "불가") 
				+ ", 회원번호 : " + userNumber + "]";
	}
	
}
