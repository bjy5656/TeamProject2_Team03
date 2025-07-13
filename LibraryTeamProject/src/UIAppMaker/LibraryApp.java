package UIAppMaker;

import java.util.Scanner;

import member.Member;
import serviceDeveloper.LibraryService;
import user.LoginManager;

public class LibraryApp {
	public static LibraryService library = new LibraryService();
	public static ConsoleUI UI = new ConsoleUI(null);
	public static LoginManager server = new LoginManager();
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Member member = null;
		
		UI.setCurLoginUser(member);
		UI.loginMenu(sc);
		
		sc.close();

	}
}	
