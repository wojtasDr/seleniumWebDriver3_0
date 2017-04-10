package ru.stqa;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrowserLogsTests {
	
	// ---Locators
	// Login Page
	private By userNameInputLocator = By.name("username");
	private By passwordInputLocator = By.name("password");
	private By loginButtonLocator = By.name("login");
	
	//Catalog page
	private By productsLocator = By.xpath("//a[contains(@href,'product_id')][not(@title)]");
	//---WebElements
	//Login Page
	private WebElement userNameInput;
	private WebElement passwordInput;
	private WebElement loginButton;
	
	//Catalog page
	private List<WebElement> products;
	
	private final String userName = "admin";
	private final String password = "admin";	
	private final String homePageTitle = "Catalog | My Store";
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wojtas_dr\\.m2\\repository\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void createNewAccountTest() throws InterruptedException{
		driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=2");
		
		this.loginLiteCartWithCorrectCrendentials(userName, password);
		
		products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productsLocator));
		
		for (int i = 0; i < products.size(); i++){
			products.get(i).click();
			driver.navigate().back();
			products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productsLocator));
		}
	}
	
	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
	
	public void loginLiteCartWithCorrectCrendentials(String userName, String password) {
		userNameInput = driver.findElement(userNameInputLocator);
		userNameInput.clear();
		userNameInput.sendKeys(userName);
		
		passwordInput = driver.findElement(passwordInputLocator);
		passwordInput.clear();
		passwordInput.sendKeys(password);

		loginButton = driver.findElement(loginButtonLocator);
		loginButton.click();
		
		wait.until(ExpectedConditions.titleIs(homePageTitle));
	}

}
