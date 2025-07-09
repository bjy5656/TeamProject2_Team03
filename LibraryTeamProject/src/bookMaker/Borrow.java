package bookMaker;

import exceptionMaker.BookAlreadyException;
import exceptionMaker.BookNotAvailableException;
import exceptionMaker.MaxBorrowException;

public interface Borrow {
	void borrowBook() throws MaxBorrowException, BookNotAvailableException, BookAlreadyException; //throws MaxBorrowException, BookNotAvailableException, BookAlreadyException
	void returnBook() ;
}
