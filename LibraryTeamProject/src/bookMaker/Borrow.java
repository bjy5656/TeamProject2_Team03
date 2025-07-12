package bookMaker;
import exceptionMaker.BookNotAvailableException;
import exceptionMaker.MaxBorrowException;

public interface Borrow {
	void borrowBook() throws BookNotAvailableException; //throws MaxBorrowException, BookNotAvailableException, BookAlreadyException
	void returnBook() ;
}
