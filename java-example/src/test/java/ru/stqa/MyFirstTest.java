package ru.stqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyFirstTest {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeTest
	public void start(){
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver,10);
	}
	
	@Test
	public void myFirstTest(){
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("webdriver");
		driver.findElement(By.name("btnG")).click();
		wait.until(ExpectedConditions.titleIs("webdriver - Szukaj w Google"));
	}
	
	@AfterTest
	public void stop(){
		driver.quit();
		driver = null;
	}
}
