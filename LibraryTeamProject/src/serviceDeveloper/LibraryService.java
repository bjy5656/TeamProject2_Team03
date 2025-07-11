package serviceDeveloper;

import java.util.ArrayList;

import UIAppMaker.ConsoleUI;
import bookMaker.Book;
import member.Member;

public class LibraryService {	
	ArrayList<Book> bookList = new ArrayList<>();
	
	String[] nameList = {"자바", "데이터베이스", "보안", "부자아빠", "리눅스마스터", "SQLD"};
	String[] author = {"이정훈", "민경승", "백정이", "이태호", "이재빈", "김태현"};
	String[] publisher = {"잉", "엥", "옹"};
	

	public LibraryService() {
		this.bookList = new ArrayList<Book>();
		randomBookList();
	}

	void printBookList() 
	{		
		ConsoleUI.printBookList(bookList);
	}
	
	void borrowBookService(Member user, String bookName) 
	{
		int index = findBookIndex(bookList, bookName);
		boolean isFound = index != -1 ? true : false;
		
		if(isFound) 
		{
			user.borrowBook(bookList.get(index));
			bookList.get(index).borrowBook();
		}
	}
	
	// 유저가 빌린 책을 반납하는 메소드
	// 만약 유저가 빌린 책 목록에 같은 이름을 가진 책이 있다면
	// 해당 책을 리스트에서 remove 해주고
	// 해당 책과 같은 이름의 도서관 책 목록에 있는 책의 대출 가능 상태를 true로 바꿔줌
	
	// returnbook 에서는 책에 index로 받아서 index에 있는 값을 제거
	void returnBookService(Member user, String bookName) 
	{
		int index = findBookIndex(user.getBookList(), bookName);
		boolean isFound = index != -1 ? true : false;
		
		if(isFound) 
		{
			user.returnBook(index);
		}
	}
	
	private void randomBookList() 
	{
		for(int i = 0; i < 5; i++) 
		{
			int randomName = (int)(Math.random() * nameList.length);
			int randomAuthor = (int)(Math.random() * author.length);
			
			bookList.add(new Book(nameList[randomName], author[randomAuthor]));
		}
	}
	
	public void addBook(String name, String author) 
	{
		Book newBook = new Book(name, author);
		bookList.add(newBook);
	}
	
	// 책리스트 내에서 책의 이름을 기준으로 인덱스를 찾아주는 메소드
	int findBookIndex(ArrayList<Book> targetList, String targetName) 
	{
		int index = 0;;
		for(Book target : targetList) 
		{
			if(target.getTitle().equals(targetName)) 
			{
				return index;
			}			
			index ++;
		}
		
		return -1;
	}
	
	// 대출 받은 책을 출력할지, 혹은 대출 안된 책을 검사할지 정해주기 위해 오버로딩
	int findBookIndex(ArrayList<Book> targetList, String targetName, boolean checkReturn) 
	{
		int index = 0;;
		for(Book target : targetList) 
		{
			if(target.getTitle().equals(targetName)) 
			{
				if(target.isAvailable() == checkReturn) return index;
			}			
			index ++;
		}
		
		return -1;
	}
	
}
