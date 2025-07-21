package UIAppMaker;

import java.util.Scanner;

import functionDAO.UserDAO;
import serviceDeveloper.LibraryService;
import userDTO.LoginManager;
import userDTO.Member;

public class LibraryApp {
	public static LibraryService library = new LibraryService();
	public static ConsoleUI UI = new ConsoleUI(null);
	public static LoginManager server = new LoginManager();
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Member member = null; // 로그인 된 유저
		// 멤버를 하나 새로 만들어서 거기에 데이터 넣어주고 member = 해당 값으로
		
//		try {
//			Connection conn = DBConnecter.getConnection();
//			conn.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		UserDAO userDAO = new UserDAO();
		
		member = new Member(0,"이태호", "test", "1234", "010-8563-9121");
		
		userDAO.join(member);
		
		
		//UI.setCurLoginUser(member);
		//UI.loginMenu(sc);
		
		sc.close();

	}
}	
