package exceptionMaker;

public class MaxBorrowException extends Exception {

	public MaxBorrowException() {
		super("20권까지만 빌릴수있습니다");
	}
	
	
}
