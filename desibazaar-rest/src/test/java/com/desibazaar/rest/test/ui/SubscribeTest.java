package com.desibazaar.rest.test.ui;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SubscribeTest {

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
		WebElement subscribe_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/div[2]/div[1]/div/div[2]/h3/a"));

		subscribe_element.click();

		WebElement heading_element = driver.findElement(By
				.xpath("/html/body/div/ng-view/div/div[1]/div/h1"));

		heading_element.sendKeys("g");

		driver.quit();
	}
}
