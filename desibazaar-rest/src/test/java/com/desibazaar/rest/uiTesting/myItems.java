package com.desibazaar.rest.uiTesting;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class myItems {

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

		WebElement MyItems_element = driver.findElement(By
				.xpath("/html/body/nav/div/div[2]/ul/li[3]/a"));

		MyItems_element.click();

		WebElement name_element = driver.findElement(By
				.xpath("/html/body/div/ng-view/div[2]/div[1]/div/div[2]/h3/a"));

		name_element.sendKeys("ea");

		WebElement price_element = driver.findElement(By
				.xpath("/html/body/div/ng-view/div[2]/div[1]/div/div[3]/p"));

		price_element.sendKeys("3");

		WebElement desc_element = driver.findElement(By
				.xpath("/html/body/div/ng-view/div[2]/div[1]/div/div[4]/p"));

		desc_element.sendKeys("t");

		WebElement detail_element = driver.findElement(By
				.xpath("/html/body/div/ng-view/div[2]/div[1]/div/div[4]/p/a"));

		detail_element.click();

		WebElement edit_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/div[2]/div[2]/div[2]/a/button"));

		edit_element.click();

		WebElement edit_name_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[1]/div/input"));

		edit_name_element.sendKeys("aksh");

		WebElement edit_basep_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[2]/div/input"));

		edit_basep_element.sendKeys("987");

		WebElement edit_desc_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[3]/div/textarea"));

		edit_desc_element.sendKeys("like it.");

		WebElement save_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[4]/div[5]/div[2]/div/button"));

		save_element.click();

		System.out.println(driver.switchTo().alert().getText());

		driver.switchTo().alert().accept();

		WebElement check_name_element = driver.findElement(By
				.xpath("/html/body/div/ng-view/div/div[2]/div[2]/h3[1]"));

		check_name_element.sendKeys("eaksh");

		WebElement delete_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/div[2]/div[2]/div[2]/button"));

		delete_element.click();

		System.out.println(driver.switchTo().alert().getText());

		driver.switchTo().alert().accept();

		Assert.assertEquals("eaksh", "eaksh");

		driver.quit();
	}
}
