package com.myrestassured.basepage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class BasePage {

	static Properties OR;
	protected static String baseUri;
	protected static String userName;
	protected static String password;

	public static List<String> getRequest() throws IOException {
		loadData();
		 baseUri=OR.getProperty("Get");
		 userName=OR.getProperty("UserName");
		 password=OR.getProperty("password");
		return Arrays.asList(baseUri,userName,password);
		
	}

	public void postRequest() {

	}

	public static void loadData() throws IOException {
		OR = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty(
				"user.dir") +"\\src\\main\\java\\com\\myrestassured\\config\\config.properties");
		OR.load(file);
		
		
	}

}
