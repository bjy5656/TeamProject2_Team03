package UIAppMaker;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import bookDTO.BookDTO;
import exceptionMaker.BookAlreadyException;
import exceptionMaker.BookNotAvailableException;
import exceptionMaker.MaxBorrowException;
import functionDAO.BookDAO;
import functionDAO.RentalDAO;
import functionDAO.UserDAO;
import userDTO.UserDTO;

public class ConsoleUI {
	// 필드
	private int curLoginUser;
	UserDAO userDAO = new UserDAO();
	BookDAO bookDAO = new BookDAO();
	RentalDAO rentalDAO = new RentalDAO();

	public ConsoleUI(UserDTO curLoginUser) {
		this.curLoginUser = -1;
	}

	public void menuPrinter(String... menuNames) {
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

	public void printBookList(List<BookDTO> targetList) {
		Iterator<BookDTO> list = targetList.iterator();
		System.out.println("=================================");
		while (list.hasNext()) {
			BookDTO book = list.next();
			System.out.printf("%d. ", book.getBookNumber() + 1);
			System.out.println(book.toString());
		}
		System.out.println("=================================");
	}

	void loginMenu(Scanner sc) {
		int choose = 0;
		String userId = "";
		String userPw = "";
		String userName = "";
		String userPhoneNumber = "";
		while (choose != 4) {
			try {

				menuPrinter("로그인", "회원 가입", "아이디 찾기","종료");

				choose = sc.nextInt();
				sc.nextLine();

				switch (choose) {
				case 1:
					// 로그인 메소드출력
					System.out.println("ID : ");
					userId = sc.next();
					sc.nextLine();
					System.out.println("Pw : ");
					userPw = sc.next();
					sc.nextLine();

					// 성공하면 행동하는 while문 메소드 호출
					// 로그인 DAO
					if(userDAO.login(userId, userPw) != -1) 
					{
						curLoginUser = userDAO.login(userId, userPw);
						menu(sc);
					}
					
					else 
					{
						System.out.println("로그인 실패");
					}

					break;
				case 2:					
					UserDTO newMember = makeNewMember(sc);
					if(newMember != null) 
					{
						userDAO.join(newMember);
					}					

					break;
				case 3:
					System.out.println("찾을 아이디 사용자 이름 : ");
					userName = sc.next();
					sc.nextLine();
					System.out.println("찾을 아이디 사용자 전화번호 : ");
					userPhoneNumber = sc.next();
					sc.nextLine();
					
					if(userDAO.findID(userName, userPhoneNumber) != null)
					{
						System.out.println(userDAO.findID(userName, userPhoneNumber));
					}					
					
					break;
					
				case 4:
					System.out.println("종료");
					break;
					
				default:
					System.out.println("올바른 값을 입력해주세요");

					break;
				}
			} catch (InputMismatchException e) {
				// e.printStackTrace();
				System.out.println("올바른 값을 입력해주세요");
				sc.nextLine();
			}
		}
	}

	void menu(Scanner sc) {

		while (curLoginUser != -1) {
			System.out.println("도서 대출 프로그램");
			menuPrinter("대출할 도서목록", "반납할 도서 목록", "도서 목록 검색", "회원 정보 확인", "회원 정보 수정", "회원 탈퇴","로그아웃");
			System.out.print("사용할 메뉴 입력 : ");

			int choose;
			int num;
			String targetName = "";

			try {
				choose = sc.nextInt();
				sc.nextLine();

				switch (choose) {
				case 1:
					System.out.println("대출가능한 도서 목록");
					// 도서 출력 DAO
					printBookList(rentalDAO.checkAllBook());
					
					System.out.println("어떤 책을 대출하시겠습니까?");
					targetName = sc.next();
					num = stringToInt(targetName);
					// 도서 대출 DAO
					if(num > -1) 
					{
						rentalDAO.borrowBookByNumber(num, curLoginUser);
					}
					else 
					{
						rentalDAO.borrowBookByName(targetName, curLoginUser);
					}
					
					break;

				case 2:
					System.out.println("반납가능한 도서 목록");
					printBookList(rentalDAO.checkBorrowedBook(curLoginUser));
					System.out.println("어떤 책을 반납하시겠습니까?");
					targetName = sc.next();
					num = stringToInt(targetName);
					// 도서 반납 DAO
					if(num > -1) 
					{
						rentalDAO.returnBookByNumber(num, curLoginUser);
					}
					else 
					{
						rentalDAO.returnBookByName(targetName, curLoginUser);
					}
					
					
					break;

				case 3:
					System.out.println("도서 이름을 작성해주세요");
					targetName = sc.next();
					sc.nextLine();
					// 도서 찾기 DAO
					printBookList(rentalDAO.checkBookWithName(targetName));;
					break;
					
				case 4:
					System.out.println("회원 정보 확인");
					System.out.println(userDAO.userInfo(curLoginUser).toString());
					break;
					
				case 5 :
					System.out.println("비밀 번호를 입력하세요");
					String userPw = sc.next();
					sc.nextLine();
					
					if(userDAO.userInfo(curLoginUser).getUserPw().equals(userPw)) 
					{
						UserDTO targetMember = makeNewMember(sc);
						if(targetMember != null) 
						{
							userDAO.update(makeNewMember(sc), curLoginUser);							
						}
						
					}
					
					else 
					{
						System.out.println("비밀번호가 일치하지 않습니다.");
					}
					
					break;
					
				case 6:
					userDAO.delete(curLoginUser);
					curLoginUser = -1;
					System.out.println("자동으로 로그아웃 됩니다.");
					break;

				case 7:
					System.out.println("로그아웃");
					curLoginUser = -1;
					break;

				default:
					System.out.println("메뉴 번호를 다시 입력하세요");
					break;

				}
			} catch (InputMismatchException e) {
				System.out.println("올바른 값을 입력해주세요");
				// e.printStackTrace();
			} 
			catch (MaxBorrowException e) {
				e.printStackTrace();
			} catch (BookNotAvailableException e) {
				e.printStackTrace();
			} catch (BookAlreadyException e) {
				e.printStackTrace();
			} 
			finally {
				sc.nextLine();
			}

		}
	}
	
	UserDTO makeNewMember(Scanner sc) 
	{
		String userName;
		String userPhoneNumber;
		String userId;
		String userPw;
		
		System.out.println("이름 : ");
		userName = sc.next();
		sc.nextLine();

		System.out.println("전화번호 : ");
		userPhoneNumber = sc.next();
		sc.nextLine();

		System.out.println("ID : ");
		userId = sc.next();
		sc.nextLine();
		
		if(userDAO.IdDuplicate(userId)) 
		{
			System.out.println("중복아이디가 있습니다.");
			return null;
		}

		System.out.println("Pw : ");
		userPw = sc.next();
		sc.nextLine();
		
		return new UserDTO(userName, userId, userPw, userPhoneNumber);
	}

	public int stringToInt(String target) {
		try {
			int num = Integer.parseInt(target);
			return num;

		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
