package com.myrestassured.basepage;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BasePage {

	static Properties OR;
	
	protected static String userName;
	protected static String password;
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;

	public static void inIt() throws IOException {
		loadData();
		selectBaseUri(OR.getProperty("BaseUri"));
		userName = OR.getProperty("UserName");
		password = OR.getProperty("password");

	}

	
	public static void loadData() throws IOException {
		OR = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir")
						+ "\\src\\main\\java\\com\\myrestassured\\config\\config.properties");
		OR.load(file);

	}

	public static void selectBaseUri(String URI) {
		requestSpec = new RequestSpecBuilder().setBaseUri(URI).build();

		responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
	}

}
