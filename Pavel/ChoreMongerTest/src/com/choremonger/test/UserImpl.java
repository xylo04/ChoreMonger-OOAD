package com.choremonger.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import com.choremonger.shared.Chore;
import com.choremonger.shared.Family;
import com.choremonger.shared.Reward;
import com.choremonger.shared.User;

public class UserImpl implements User {

	public static User createUser() {
		User retval = null;

		HttpPost request = new HttpPost(HttpRequestExecutor.RESOURCE_ROOT
				+ "user");
		try {
			request.setEntity(new StringEntity(
					"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user />",
					"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setHeader("Content-Type", "application/xml");

		System.out
				.println("UserImpl is building request for new User from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}

		if (response.getStatusLine().getStatusCode() == 200) {
			retval = parseUser(response, retval);
		}

		// parse response XML into a UserImpl object
		// and return that
		return retval;
	}

	public static User getUser(String id) {
		User retval = null;

		HttpGet request = new HttpGet(HttpRequestExecutor.RESOURCE_ROOT
				+ "User/1");

		System.out
				.println("UserImpl is building request to get User from the server");
		HttpResponse response = HttpRequestExecutor.executeRequest(request);
		if (response != null) {
			System.out.println("Got a response, code "
					+ response.getStatusLine().getStatusCode());
		}

		if (response.getStatusLine().getStatusCode() == 200) {
			retval = parseUser(response, retval);
		}

		// parse response XML into a UserImpl object
		// and return that
		return retval;
	}

	private static User parseUser(HttpResponse response, User retval) {
		System.out.println("Going to try and parse out a User");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			UserSaxHandler handler = new UserSaxHandler();
			saxParser.parse(response.getEntity().getContent(), handler);
			retval = handler.getUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retval;
	}

	private List<Chore> chores = new ArrayList<Chore>();
	private String id;
	private double RewardPoints;
	private Date Dob;
	private String email;

	private String name;

	public UserImpl() {
	}

	@Override
	public void addChore(Chore toAdd) {
		chores.add(toAdd);
		// send update to server
	}

	@Override
	public void addRewardPoints(double amountToAdd) {
		RewardPoints += amountToAdd;
		// send update to server
	}

	@Override
	public List<Chore> getChores() {
		return chores;
	}

	@Override
	public Date getDob() {
		return Dob;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getRewardPoints() {
		return RewardPoints;
	}

	@Override
	public void redeemReward(Reward toRedeem) {
		// do stuff
	}

	@Override
	public boolean removeChore(Chore toRemove) {
		return chores.remove(toRemove);
		// send update to server
	}

	@Override
	public void setDob(Date newDateOfBirth) {
		Dob = newDateOfBirth;
		// send update to server
	}

	@Override
	public void setEmail(String newEmail) {
		email = newEmail;
		// send update to server
	}

	@Override
	public void setId(String newId) {
		id = newId;
		// send update to server
	}

	@Override
	public void setName(String newName) {
		name = newName;
		// send update to server
	}

	@Override
	public void subtractRewardPoints(double amountToSubtract) {
		RewardPoints -= amountToSubstract;
		// send update to server
	}

}