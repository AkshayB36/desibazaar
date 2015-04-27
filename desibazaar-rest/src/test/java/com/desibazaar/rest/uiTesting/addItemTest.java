package com.desibazaar.rest.uiTesting;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class addItemTest {

	@Test
	public void postItem() {

		
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		
		WebElement MyItems_element = driver
				.findElement(By
						.xpath("/html/body/nav/div/div[2]/ul/li[3]/a"));

		MyItems_element.click();
		WebElement plus_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/a/button"));

		plus_element.click();
		

		WebElement name_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[1]/div/input"));

		name_element.sendKeys("galaxy");

		WebElement bprice_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[2]/div/input"));

		bprice_element.sendKeys("25");

		WebElement desc_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[3]/div/textarea"));

		desc_element.sendKeys("It is .");
		
		WebElement sdate_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[4]/div[1]/div/p/input"));

		sdate_element.sendKeys("29-April-2015");
		
		WebElement hour_stime_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[4]/div[2]/table/tbody/tr[2]/td[1]/input"));

		hour_stime_element.sendKeys("04");
		
		WebElement minute_stime_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[4]/div[2]/table/tbody/tr[2]/td[3]/input"));

		minute_stime_element.sendKeys("56");
		
		WebElement duration_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[4]/div[3]/div/input"));

		duration_element.sendKeys("10");
		
		WebElement category_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[4]/div[4]/div/select"));

		category_element.sendKeys("Electronics");
		
		WebElement image_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[5]/div[1]/div/input"));

		image_element.sendKeys("/Users/akshaybodhankar/Documents/desibazaar/desibazaar-web/src/main/webapp/img/abacus.jpg");
		

		WebElement submit = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[5]/div[2]/div/button[1]"));

		submit.click();
		
		WebElement itemName_element = driver
				.findElement(By
						.name(""));

		itemName_element.sendKeys("galaxy");

		WebElement price_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div[2]/div/div/div[3]/h3/a"));

		price_element.sendKeys("25");

		
		System.out.println(driver.switchTo().alert().getText());
		
		driver.switchTo().alert().accept();
		
		
		
	//	WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/ng-view/div[2]/div/div/div[2]/h3/a")));
		//System.out.println();
		
		WebElement title = driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[6]/a"));
		title.click();
		driver.get("http://localhost:8080/desibazaar-web/#/login");

		WebElement new_login_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[1]/div/input"));

		new_login_element.sendKeys("akshay@gmail.com");

		WebElement new_pass_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[2]/div/input"));

		new_pass_element.sendKeys("password");

		WebElement new_go_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/form/fieldset/div[3]/div/button[1]"));

		new_go_element.click();


		WebElement itemName_check_element = driver
				.findElement(By
						.name("/html/body/div/ng-view/div/div[2]/div[1]/div/div[2]/h3/a"));

		itemName_check_element.sendKeys("galaxy");

		WebElement price_check_element = driver
				.findElement(By
						.xpath("/html/body/div/ng-view/div/div[2]/div[1]/div/div[3]/p"));

		price_check_element.sendKeys("25");
		//Now the alert appears. 
		//Alert alert = driver.switchTo().alert();
		//alert.accept();
		
		

		driver.quit();
	}
}


