package member;

import java.util.ArrayList;
import java.util.List;

import user.User;

public class Member extends User{
	private List<User> user = new ArrayList<>();
	
	
	
	public List<User> getUser() {
		return user;
	}


	public void setUser(List<User> user) {
		this.user = user;
	}

	

	public void checklist() {
		
	}
	
}
