package member;

import java.util.ArrayList;
import java.util.List;

import bookMaker.Book;
import exceptionMaker.BookAlreadyException;
import exceptionMaker.BookNotAvailableException;
import exceptionMaker.MaxBorrowException;
import user.User;

public class Member extends User {
	// 필드
	final int MAX_BORROW_COUNT = 20;
	private List<Book> borrowBook; // 유저에 빌린 책 정보를 담기 위함

	// 생성자 : user에 회원정보와 빌린 책 리스트를 ArrayList에 추가하려 함
	public Member() {
		super(null, null, null, null, 0);
		this.borrowBook = new ArrayList<>();
	}

	public Member(String name, String userid, String password, String phoneNumber, int userNum) {
		super(name, userid, password, phoneNumber, userNum);
		this.borrowBook = new ArrayList<>();
	}

	// getter, setter
	public List<Book> getBorrowBook() {
		return borrowBook;
	}

	public void setBorrowBook(List<Book> borrowBook) {
		this.borrowBook = borrowBook;
	}

	// 메소드 오버라이딩
	
	
	@Override
	public void borrowBooks(Book book) {
		try {
			if (book.isAvailable()) {
				if(borrowBook.size() > MAX_BORROW_COUNT) 
				{
					throw new MaxBorrowException();
				}
				
				else 
				{
					book.borrowBook();
					borrowBook.add(book);
				}
			} else {
				throw new BookAlreadyException();
			}

		} catch (MaxBorrowException e) {
			System.out.println("더이상 빌릴 수 없습니다.");
		} catch (BookNotAvailableException e) {
			System.out.println("해당 책은 대출이 불가능한 책입니다.");
		} catch (BookAlreadyException e) {
			System.out.println(book.getTitle() + "은/는 이미 대출 중입니다.");
		}

		System.out.println(book.getTitle() + " 대출을 완료했습니다!");
	}

	@Override
	public String toString() {
		super.toString();
		return getName() + "님의 빌린책은" + borrowBook + "입니다";
	}

	@Override
	public void bookReturn(int index) {
		borrowBook.get(index).returnBook();
		borrowBook.remove(index);
	}

	@Override
	public boolean login(String inputId, String inputPw) {
		if (super.getUserid().equals(inputId) && super.getPassword().equals(inputPw)) {
			System.out.println("로그인 성공!" + getName() + "님 환영합니다!");
			return true;
		} else {
			System.out.println("로그인에 실패하였습니다");
			return false;
		}
	}

	@Override
	public boolean bookSerch(String serch) {
		boolean found = false;
		for (Book book : borrowBook) {
			if (book.getTitle().contains(serch)) {
				System.out.println("검색결과: " + book.getTitle());
				found = true;
			}
		}

		if (!found) {
			System.out.println("대출 중인 책 중에 검색결과가 없습니다.");
		}
		return found;
	}

}
