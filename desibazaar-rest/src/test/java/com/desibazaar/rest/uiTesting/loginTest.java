package com.desibazaar.rest.uiTesting;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class loginTest {

	@Test
	public void getLogin() {

		WebDriver driver = new FirefoxDriver();

		driver.get("http://localhost:8080/desibazaar-web/#/login");

		WebElement element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[1]/div/input"));

		element.sendKeys("xyz@gmail.com");

		WebElement pass_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[2]/div/input"));

		pass_element.sendKeys("password");

		WebElement go_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[3]/div/button[1]"));

		go_element.click();
		WebElement logout_element = driver.findElement(By
				.xpath("/html/body/nav/div/div[2]/ul/li[6]/a"));

		System.out.println();
		logout_element.getText();
		Assert.assertEquals("Logout", "Logout");

		driver.quit();
	}
}