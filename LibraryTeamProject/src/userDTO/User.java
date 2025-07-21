package userDTO;

import bookDTO.Book;

// 유저 추상클래스 생성
public abstract class User {
	
	// 추상 클래스 안에 필드값 선언
	private int userNum; // 유저 넘버
	private String userName; // 유저이름
	private String userId; // 유저id
	private String userPw; // 유저pw
	private String userPhoneNumber; // 유저 폰넘버
	
	
	// getter 메소드 선언

	public int getUserNum() {
		return userNum;
	}
	
	//생성자
	public User(int userNum, String userName, String userId, String userPw, String userPhoneNumber) {
		super();
		this.userNum = userNum;
		this.userName = userName;
		this.userId = userId;
		this.userPw = userPw;
		this.userPhoneNumber = userPhoneNumber;
	}	


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	// 로그인 추상메서드
	public abstract boolean login(String inputId, String inputPw);

	// 매개변수 유무, return 타입
	
	// 도서 검색 추상메서드
	public abstract boolean bookSerch(String serch);
	
	// 도서 대출 추상메서드
	public abstract void borrowBooks (Book book);

	// 도서 반납 추상메서드
	public abstract void bookReturn (int index);
	
	
}
