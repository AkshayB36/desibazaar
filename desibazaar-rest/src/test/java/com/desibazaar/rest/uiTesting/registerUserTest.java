package com.desibazaar.rest.uiTesting;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class registerUserTest {

	@Test
	public void user() {

		
	WebDriver driver = new FirefoxDriver();

	driver.get("http://localhost:8080/desibazaar-web/#/register");

	WebElement name_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[1]/div/input"));

	name_element.sendKeys("name");

	WebElement address_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[2]/div/textarea"));

	address_element.sendKeys("pass");

	WebElement phone_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[3]/div/input"));

	phone_element.sendKeys("6789");
	
	WebElement email_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[4]/div/input"));

	email_element.sendKeys("mandy@gmail.com");
	
	WebElement re_email_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[5]/div/input"));

	re_email_element.sendKeys("mandy@gmail.com");
	
	
	WebElement pass_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[6]/div/input"));

	pass_element.sendKeys("io");
	
	WebElement re_pass_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[7]/div/input"));

	re_pass_element.sendKeys("io");
	
	WebElement register_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[8]/div/button[1]"));

	register_element.click();
	
	System.out.println(driver.switchTo().alert().getText());
	
	driver.switchTo().alert().accept();

	
	WebElement element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[1]/div/input"));

	element.sendKeys("mandy@gmail.com");

	WebElement password_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[2]/div/input"));

	password_element.sendKeys("io");

	WebElement go_element = driver
			.findElement(By
					.xpath("/html/body/div/ng-view/div/form/fieldset/div[3]/div/button[1]"));

	go_element.click();
	
	WebElement logout_element = driver.findElement(By
			.xpath("/html/body/nav/div/div[2]/ul/li[6]/a"));

	
	logout_element.click();
	driver.quit();
}
}

	

