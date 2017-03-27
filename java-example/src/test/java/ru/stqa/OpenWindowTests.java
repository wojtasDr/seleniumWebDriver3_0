package ru.stqa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenWindowTests {

	private WebDriver driver;
	private WebDriverWait wait;

	private final String userName = "admin";
	private final String password = "admin";

	// ---Locators
	// Login Page
	private By userNameInputLocator = By.name("username");
	private By passwordInputLocator = By.name("password");
	private By loginButtonLocator = By.name("login");
	
	//Countries Page
	private By addNewCountryButtonLocator = By.cssSelector("a.button");
	
	//Add new country Page
	private By newCountryFormLinksLocator = By.cssSelector("td>a[target='_blank']");

	// ---WebElements
	// Login Page
	private WebElement userNameInput;
	private WebElement passwordInput;
	private WebElement loginButton;
	
	//Countries Page
	private WebElement addNewCountryButton;
	private List<WebElement> newCountryFormLinks = new ArrayList<WebElement>();

	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wojtas_dr\\.m2\\repository\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
	}

	@Test
	public void addRemoveProductsFromCart() throws InterruptedException {
		String mainPage;
		String newWindowHandle;
		Set<String>windowHandles;
		
		driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

		this.loginLiteCartWithCorrectCrendentials(userName, password);
		
		addNewCountryButton = wait.until(ExpectedConditions.presenceOfElementLocated(addNewCountryButtonLocator));
		addNewCountryButton.click();
		
		newCountryFormLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(newCountryFormLinksLocator)); 
		
		for(WebElement link : newCountryFormLinks){
			
			windowHandles = driver.getWindowHandles();
			
			mainPage = driver.getWindowHandle();
			
			link.click();
			
			newWindowHandle = this.anyWindoOtherThanExistingWindows(windowHandles);
			
			driver.switchTo().window(newWindowHandle);
			
			System.out.println("New: " + driver.getCurrentUrl());
			
			driver.close();
			
			driver.switchTo().window(mainPage);
			
			System.out.println("Main:" + driver.getCurrentUrl());
		}	
	}

	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
	
	public String anyWindoOtherThanExistingWindows(Set<String> existingWindows) throws InterruptedException{
		Integer counter = 0;
		String newWindowHandle = "";
		Set<String> currentlyExistingWindows;
	
		do {
			Thread.sleep(500);
			currentlyExistingWindows = driver.getWindowHandles();
			counter++;
			System.out.println("ExistingWindows: " + existingWindows.size());
			System.out.println("currentlyExistingWindows: " + currentlyExistingWindows.size());
		} while(existingWindows.size() == currentlyExistingWindows.size() && counter <= 10);
		
		currentlyExistingWindows.removeAll(existingWindows);
		
		try {
			newWindowHandle = currentlyExistingWindows.iterator().next().toString();
		} catch (NoSuchElementException ex){
			System.out.println("New Window was not opened within 5s.");
			ex.printStackTrace();
		}
		
		return newWindowHandle;
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
	}
}
