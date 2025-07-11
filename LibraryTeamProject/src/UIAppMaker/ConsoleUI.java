package UIAppMaker;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import bookMaker.Book;
import exceptionMaker.BookAlreadyException;
import exceptionMaker.BookNotAvailableException;
import exceptionMaker.MaxBorrowException;
import member.Member;
import serviceDeveloper.LibraryService;
import user.LoginManager;

public class ConsoleUI {
	// 필드
	Member curLoginUser;

	public ConsoleUI(Member curLoginUser) {
		this.curLoginUser = null;
	}

	static public void menuPrinter(String... menuNames) {
		int count = 1;
		System.out.println("=================================");
		for (String menu : menuNames) {
			System.out.println("=\t\t\t\t=");
			System.out.println("=\t" + count + ". " + menu);
			System.out.println("=\t\t\t\t");
			count++;
		}
		System.out.println("=================================");
	}

	static public void printBookList(ArrayList<Book> targetList) {
		Iterator<Book> list = targetList.iterator();
		int count = 1;
		System.out.println("=================================");
		while (list.hasNext()) {
			System.out.printf("%d. ", count);
			Book book = list.next();
			System.out.println(book.toString());
			count++;
		}
		System.out.println("=================================");
	}

	void loginMenu(Scanner sc) {
		int choose = 0;
		while (choose != 2) {
			try {
				menuPrinter("로그인", "종료");
				choose = sc.nextInt();
				sc.nextLine();

				switch (choose) {
				case 1:
					// 로그인 메소드출력
					System.out.println("ID : ");
					String id = sc.next();
					sc.nextLine();
					System.out.println("Pw : ");
					String pw = sc.next();
					sc.nextLine();
					LibraryApp.server.login(id, pw);
					// 성공하면 행동하는 while문 메소드 호출
					menu(sc);
					break;
				case 2:
				default:
					choose = 2;
					break;
				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
			}
		}
	}

	void menu(Scanner sc) {

		while (curLoginUser != null) {
			System.out.println("도서 대출 프로그램");
			menuPrinter("대출할 도서목록", "반납할 도서 목록", "도서 목록 검색", "로그아웃");
			System.out.print("사용할 메뉴 입력 : ");

			int choose;
			String targetName = "";

			try {
				choose = sc.nextInt();

				sc.nextLine();

				switch (choose) {
				case 1:
					System.out.println("대출가능한 도서 목록");
					printBookList(LibraryApp.library.getBookList());
					System.out.println("어떤 책을 대출하시게습니까?");
					targetName = sc.next();
					LibraryApp.library.borrowBookService(curLoginUser, targetName);
					break;

				case 2:
					System.out.println("반납가능한 도서 목록");
					printBookList((ArrayList<Book>) curLoginUser.getBorrowBook());
					System.out.println("어떤 책을 반납하시게습니까?");
					targetName = sc.next();
					LibraryApp.library.returnBookService(curLoginUser, targetName);
					break;

				case 3:
					System.out.println("도서 목록을 출력합니다");
					printBookList(LibraryApp.library.getBookList());
					break;

				case 4:
					System.out.println("로그아웃");
					curLoginUser = null;
					break;

				default:
					System.out.println("메뉴 번호를 다시 입력하세요");
					break;

				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
			} catch (MaxBorrowException e) {
				e.printStackTrace();
			} catch (BookNotAvailableException e) {
				e.printStackTrace();
			} catch (BookAlreadyException e) {
				e.printStackTrace();
			} finally {
				sc.nextLine();
			}

		}
	}
}
