package functionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import userDTO.UserDTO;

public class UserDAO {

	// 필드
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	// 유저 회원가입 -> return void
	// 회원가입
	public void join(UserDTO memberDTO) {
		String query = "INSERT INTO TBL_USER" + "(" + "user_number, " + "user_name, " + "USER_id, " + "user_pw, "
				+ "user_phonenumber " + ") " + "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?)";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, memberDTO.getUserName());
			preparedStatement.setString(2, memberDTO.getUserId());
			preparedStatement.setString(3, memberDTO.getUserPw());
			preparedStatement.setString(4, memberDTO.getUserPhoneNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("join() 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("join 해제 오류");
				e.printStackTrace();
			}
		}
	} // 회원 가입 메소드 끝

	// 유저 로그인 -> return int -> 회원의 user_num값 가져와서 해당 행에 있는 값들로 새로운 멤버 만들어서 넣어주기
	public int login(String userId, String userPw) {
		String query = "SELECT USER_NUMBER " + "FROM TBL_USER " + "WHERE USER_id = ? AND user_pw = ?";

		int userNumber = -1;

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, userPw);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				userNumber = resultSet.getInt(1);
				System.out.println("로그인 성공 " + userNumber + " : 로그인 번호");
			} else
				System.out.println("잘못됨!");
		} catch (SQLException e) {
			System.out.println("로그인 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("로그인 닫기 오류");
				e.printStackTrace();
			}
		}

		return userNumber;
	} // 로그인 메소드 끝

	// 유저 로그아웃 -> Member만 다시 null로 -> 따로 만들필요 없음
	// 유저 찾기 -> return boolean
	public List<String> findID(String userName, String user_phonenumber) {
		String query = "SELECT USER_ID FROM TBL_USER " + "WHERE USER_NAME = ? AND user_phonenumber = ?";

		// userBirth 가 YYYY-MM-DD 형식인 경우 JAVA 코드에서
		// SQL 문에서
		// TO_DATE(?, 'YYYY-MM-DD')
		// JAVA 코드에서
		// java.sql.Date.valueOF(userBirth) 형식으로 작성한다.
		List<String> userIds = new ArrayList<>();
		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, user_phonenumber);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String userId = resultSet.getString("USER_ID");
				userIds.add(userId);
			}
			if (userIds.isEmpty())
				System.out.println("해당하는 사용자 아이디를 찾을 수 없습니다");
			else {
				System.out.println("찾은 사용자 아이디 : ");
				for (String userId : userIds) {
					System.out.println(userId);
				}
			}
		} catch (SQLException e) {
			System.out.println("아이디 찾기 SQL 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("아이디 찾기 닫기 오류");
				e.printStackTrace();
			}
		}
		return userIds;
	} // 유저 찾기 메소드 끝

	// 유저 아이디 중복확인 (회원가입시 확인) -> return boolean
	public boolean IdDuplicate(String userId) {
		String query = "SELECT COUNT(*) " + "FROM TBL_USER " + "WHERE USER_id = ?";

		boolean Duplicate = false;

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Duplicate = resultSet.getInt(1) > 0;
			}
		} catch (SQLException e) {
			System.out.println("중복확인 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("중복확인 닫기 오류");
				e.printStackTrace();
			}
		}

		return Duplicate;
	} // 아이디 중복확인 메소드 끝

	// 유저 정보 조회 -> return void
	public UserDTO userInfo(int userNumber) {
		String query = "SELECT USER_ID, USER_PW, user_phonenumber, USER_NAME " 
				+ "FROM TBL_USER "
				+ "WHERE USER_NUMBER = ?";

		UserDTO targetMember = null;

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			String userId = resultSet.getString("USER_ID");
			String userPw = resultSet.getString("USER_PW");
			String userPhoneNumber = resultSet.getString("user_phonenumber");
			String userName = resultSet.getString("USER_NAME");
			
			targetMember = new UserDTO(userName, userId, userPw, userPhoneNumber);

		} catch (SQLException e) {
			System.out.println("저장된 정보가 없습니다.");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(targetMember == null) System.out.println("정보 조회 실패");
		else 
		{
			System.out.println("정보 조회 완료");
		}
		
		return targetMember;
	}

	// 유저 정보 수정 -> return void\
	// USER_PW를 입력받아서 맞으면 수정 처리 진행
	public void update(UserDTO memberDTO, int userNumber) {
		String query = "UPDATE TBL_USER " + "SET USER_NAME = ? , " + "USER_PHONENUMBER = ? , "
				+ "USER_PW = ? " + "WHERE USER_NUMBER = ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, memberDTO.getUserName());
			preparedStatement.setString(2, memberDTO.getUserPhoneNumber());
			preparedStatement.setString(3, memberDTO.getUserPw());			
			preparedStatement.setInt(4, userNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("update() SQL오류!!!");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 회원 정보 삭제 -> return void
	public void delete(int userNumber) {
		String query = "DELETE FROM TBL_USER WHERE USER_NUMBER = ?";

		try {
			connection = DBConnecter.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete() SQL 오류!!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 담당자 : 김태현, 민경승, 강승훈
}
