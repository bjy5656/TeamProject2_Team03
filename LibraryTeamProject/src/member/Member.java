package member;

import java.util.ArrayList;
import java.util.List;

import bookMaker.Book;
import user.User;

public class Member extends User{
	//필드
	private List<User> user;	// 유저 정보를 담기
	private List<Book> borrowBook; // 유저에 빌린 책 정보를 담기 위함

	//생성자 : user에 회원정보와 빌린 책 리스트를 ArrayList에 추가하려 함
	public Member(String name, String userid, String password, String phoneNumber, int userNum, ArrayList<User> user) {
		super(name, userid, password, phoneNumber, userNum);
		this.user = user;
		this.borrowBook = new ArrayList<>();
	}


	// getter, setter
	public List<Book> getBorrowBook() {
		return borrowBook;
	}



	public void setBorrowBook(List<Book> borrowBook) {
		this.borrowBook = borrowBook;
	}



	public List<User> getUser() {
		return user;
	}



	public void setUser(List<User> user) {
		this.user = user;
	}


	//메소드 오버라이딩
	@Override
	public void borrowBooks(Book book) {
		if(book.isAvailable()) {
			borrowBook.add(book);
			book.setAvailable(false);
			System.out.println(book.getTitle()+" 대출을 완료했습니다!");
		}else {
			System.out.println(book.getTitle()+"은/는 이미 대출 중입니다.");
		}
	}


	@Override
	public void bookReturn(int index) {
		if (index >= 0 && index < borrowBook.size()) {
			Book book = borrowBook.get(index);
			borrowBook.remove(index);
			book.setAvailable(true);
			System.out.println(book.getTitle()+" 반납을 완료했습니다!");
		}else {
			System.out.println("반납을 실패했습니다.");
		}
	}


	@Override
	public boolean login(String inputId, String inputPw) {
		if(super.getUserid().equals(inputId) && super.getPassword().equals(inputPw)) {
			System.out.println("로그인 성공!" + getName() + "님 환영합니다!");
			return true;
		}else {
			System.out.println("로그인에 실패하였습니다");
			return false;
		}
	}



	@Override
	public boolean bookSerch(String serch) {
		boolean found = false;
		for (Book book : borrowBook) {
			if(book.getTitle().contains(serch)) {
				System.out.println("검색결과: "+ book.getTitle());
				found = true;
			}
		}
		
		if (!found) {
			System.out.println("대출 중인 책 중에 검색결과가 없습니다.");
		}
		return found;
	}
	
}
