package com.choremonger.test;

import com.choremonger.shared.User;

public class UserTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Driver is asking to get user");
		User user = UserImpl.getUser("agtjaG9yZW1vbmdlcnIgCxIKRmFtaWx5SW1wbBjxLgwLEghVc2VySW1wbBjRDww");
		if (user == null) {
			System.out.println("user was null!");
			System.exit(1);
		}

		System.out.println("\n\nGot back a user:");
		System.out.println(user.getName());
		System.out.println(user.getId());

	}

}
