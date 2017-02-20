package ru.stqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginLiteCartTest {
	private WebDriver driver;
	private WebDriverWait wait;
	
	//Login Page WebElements
	private WebElement userNameInput;
	private WebElement passwordInput;
	private WebElement loginButton;
	
	//Login credentials
	private final String userName = "admin";
	private final String password = "admin";	
	
	private final String homePageTitle = "My Store";

	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wojtas_dr\\.m2\\repository\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void loginLiteCartWithCorrectCrendentials() {
		driver.get("http://localhost/litecart/admin/login.php");
		
		//Type password
		userNameInput = driver.findElement(By.name("usernae"));
		userNameInput.clear();
		userNameInput.sendKeys(userName);
		
		//Type userName
		passwordInput = driver.findElement(By.name("password"));
		passwordInput.clear();
		passwordInput.sendKeys(password);
		
		//Type userName
		loginButton = driver.findElement(By.name("login"));
		loginButton.click();
		
		wait.until(ExpectedConditions.titleIs(homePageTitle));
	}

	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
}
