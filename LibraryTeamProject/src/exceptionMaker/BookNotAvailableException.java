package exceptionMaker;

public class BookNotAvailableException  extends Exception{

	public BookNotAvailableException() {
		super("대출이 안되는 도서입니다");
	}

	
	
}
