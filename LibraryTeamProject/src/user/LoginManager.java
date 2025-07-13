package user;

import java.util.HashMap;
import java.util.Map; 
import member.Member;

public class LoginManager
{
	
	// private 필드
	private Map<String, Member> userInfo; // ID, Member

	// 생성자 필드 초기화
	public LoginManager() {
		super();
		userInfo = new HashMap<String, Member>();
	}

	// 회원가입 메소드
	public boolean register( String name, String InputId, String InputPw, String phoneNumber)
	{
//		id가 이미 존재하는 경우 회원가입 불가능
		if(userInfo.containsKey(InputId))
		{
			System.out.println("이미 사용중인 아이디 입니다.");
			return false;
		}
		{
			System.out.println("회원가입에 성공했습니다!");
			Member newMember = new Member(name, InputId, InputPw, phoneNumber, userInfo.size());
			userInfo.put(InputId, newMember);
			return true;
		}
	}
	
	// 로그인 메소드
	public boolean login(String InputId, String InputPw) 
	{
//		id / pw 잘못 입력
		if(!userInfo.containsKey(InputId)) 
		{
			System.out.println("아이디가 없습니다.");
			return false;
		}
		
		else 
		{
			// InputId에 대응하는 InputPw 확인
			if (!userInfo.get(InputId).getPassword().equals(InputPw)) 
			{
				System.out.println("패스워드가 틀립니다.");
				return false;
			}
			else 
			{
			if(userInfo.get(InputId).getPassword().equals(InputPw)) 
			{	
				System.out.println("로그인 성공하였습니다.");
				return true;
			}
			}
			return true;
		}
	}

	// Override
//	   toString 메소드 오버라이딩
	@Override
	public String toString() {
		return "현재 유저리스트는 : " + userInfo + "입니다";
	}
	
	// getUser
//	Member 클래스의 userId 가져오기
	public Member getUser(String userId) 
	{
		return userInfo.get(userId);
	}
}
