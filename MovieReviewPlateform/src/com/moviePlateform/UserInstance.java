package com.moviePlateform;

import java.util.HashMap;

public class UserInstance implements User {
	
	String userName;
	String userLevel;
	
	static HashMap<String, String> userMap = new HashMap<String, String>();
	
	
	@Override
	public void addUser(String userName) {
		this.userName = userName;
		this.userLevel = "Viewer";
		
		updateUserMap();
	}

	private void updateUserMap() {
		if (userMap.containsKey(userName) == false) {
			userMap.put(userName, userLevel);
		}
	}

	@Override
	public void promote(String userName) {
		if(userMap.containsKey(userName)) {
			userMap.put(userName, "Critic");
		}
	}

}
