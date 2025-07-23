package userDTO;

public class UserDTO {
	// 추상 클래스 안에 필드값 선언
	private int userNum; // 유저 넘버
	private String userName; // 유저이름
	private String userId; // 유저id
	private String userPw; // 유저pw
	private String userPhoneNumber; // 유저 폰넘버
	
	//생성자
	public UserDTO(int userNum, String userName, String userId, String userPw, String userPhoneNumber) {
		this(userName, userId, userPw, userPhoneNumber);
		this.userNum = userNum;
	}
	
	// userNum 없는 생성자
	public UserDTO(String userName, String userId, String userPw, String userPhoneNumber) {
		this.userName = userName;
		this.userId = userId;
		this.userPw = userPw;
		this.userPhoneNumber = userPhoneNumber;
	}	
	
	
	
	public UserDTO(String userName, String userPw, String userPhoneNumber) {
		super();
		this.userName = userName;
		this.userPw = userPw;
		this.userPhoneNumber = userPhoneNumber;
	}

	// getter 메소드 선언

	public int getUserNum() {
		return userNum;
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
	
	
	
	@Override
	public String toString() {
		String info = "ID : " + userId + "\n" + 
						"PW : " + userPw + "\n" +
						"유저 이름 : " + userName + "\n" +
						"전화 번호 : " + userPhoneNumber;
						
		return info;
	}		
}
