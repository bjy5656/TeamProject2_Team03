package UIAppMaker;

import java.util.Scanner;

import member.Member;
import serviceDeveloper.LibraryService;

public class LibraryApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Member member = new Member();
		ConsoleUI UI = new ConsoleUI(member);
		LibraryService library = new LibraryService();
		
		UI.loginMenu(sc);
		
		sc.close();
	}
}
