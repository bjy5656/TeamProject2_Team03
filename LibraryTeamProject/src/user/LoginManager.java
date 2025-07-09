package user;

import java.util.Map;

import member.Member;

public class LoginManager
{
	// 필드
	Map<String, Member> userInfo; // ID, Member

	// 생성자 필드 초기화
	
	// 메소드
	// 로그인 메소드
	public boolean login(String id, String ps) 
	{
		if(!userInfo.containsKey(id)) 
		{
			System.out.println("아이디가 없습니다.");
			return false;
		}
		
		else 
		{
			// 여기에 Member 필드에 패스워드 값 추가되면 그다음에 작성
//			if(userInfo.get(id).getPs().equals(ps)) 
//			{
//				System.out.println("로그인 성공하였습니다.");
//				return true;
//			}
			
//			else 
//			{
//			
//			
//				sysout("패스워드가 틀립니다");
//				return false;
//			}
			return true;
		}
	}
	
	
	// getUser
	public Member getUser(String userId) 
	{
		return userInfo.get(userId);
	}
	
	
}
