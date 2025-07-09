package user;

// 유저 추상클래스 생성
public abstract class User {
	
	// 추상 클래스 안에 필드값 선언
	private String name; // 유저이름
	private String userid; // 유저id
	private String password; // 유저pw
	private String phoneNumber; // 유저 폰넘버
	private int userNum; // 유저 넘버
	
	
	// getter 메소드 선언
	public String getName() {
		return name;
	}
	public String getUserid() {
		return userid;
	}
	public String getPassword() {
		return password;
	}
	public String getPhone_number() {
		return phoneNumber;
	}
	public int getUserNum() {
		return userNum;
	}
	
	//생성자
	public User(String name, String userid, String password, String phone_number) {
		this.name = name;
		this.userid = userid;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
	// 로그인 추상메서드
	public abstract boolean login(String inputId, String inputPw);
	// 매개변수 유무, return 타입
	
	// 도서 검색 추상메서드
	public abstract boolean bookSerch(String serch);
	
	// 도서 대출 추상메서드
	public abstract void borrowBooks (String bookTitle);

	// 도서 반납 추상메서드
	public abstract void bookReturn (String bookTitle);
	
}
