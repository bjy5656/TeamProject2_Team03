package user;

import java.util.Map;

import member.Member;

public class LoginManager
{
	// 필드
	Map<String, Member> userInfo; // ID, Member

	// 생성자 필드 초기화
	public LoginManager(Map<String, Member> userInfo) {
		super();
		this.userInfo = userInfo;
	}
	
	// 메소드
	// 로그인 메소드
	public boolean login(String InputId, String InputPw) 
	{
//		id 입력을 잘못했을시
		if(!userInfo.containsKey(InputId)) 
		{
			System.out.println("아이디가 없습니다.");
			return false;
		}
		
		else 
		{
			if(!userInfo.containsValue(InputPw))
			{
				System.out.println("패스워드가 틀립니다");
				return false;
			}
			else 
			{
			if(userInfo.get(InputId).equals(InputPw)) 
			{	
				System.out.println("로그인 성공하였습니다.");
				return true;
			}
			}
			return true;
		}
	}

	// getUser
//	Member 클래스의 userId 가져오기
	public Member getUser(String userId) 
	{
		return userInfo.get(userId);
	}
	
	
}
