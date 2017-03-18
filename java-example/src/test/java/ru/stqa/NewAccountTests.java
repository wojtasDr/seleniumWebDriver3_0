package ru.stqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewAccountTests {
	private WebDriver driver;
	private WebDriverWait wait;
	
	private final String homePageTitle = "Online Store | My Store";
	private final String loginPageTitle = "Online Store | My Store";
	
	private String[] newAccountData = { "PLdddddddddd", "testCompany", "testFirstName", "testLastName", "testAddress1",
			"testAddress2", "00-111", "testCity", "test12@Email", "+48123",
			"testPassword1", "testPassword1", "Poland" };

	//---Locators
	//Login Page
	private By userEmailInputLocator = By.name("email");
	private By passwordInputLocator = By.name("password");
	private By loginButtonLocator = By.name("login");
	
	//Create Account Page Locators
	private By taxIdInputLocator = By.cssSelector("td input[name='tax_id']");
	private By companyInputLocator = By.cssSelector("td input[name='company']");
	private By firstNameInputLocator = By.cssSelector("td input[name='firstname']");
	private By lastNameInputLocator = By.cssSelector("td input[name='lastname']");
	private By address1InputLocator = By.cssSelector("td input[name='address1']");
	private By address2InputLocator = By.cssSelector("td input[name='address2']");
	private By postcodeNameInputLocator = By.cssSelector("td input[name='postcode']");
	private By cityInputLocator = By.cssSelector("td input[name='city']");
	private By emailInputLocator = By.cssSelector("td input[name='email']");
	private By phoneInputLocator = By.cssSelector("td input[name='phone']");
	private By desiredPasswordInputLocator = By.cssSelector("td input[name='password']");
	private By confirmPasswordInputLocator = By.cssSelector("td input[name='confirmed_password']");
	private By countrySelectLocator = By.cssSelector("td select[name='country_code']");
	private By newsletterCheckBoxLocator = By.cssSelector("td input[type='checkbox'][name='newsletter']");
	private By createAccountButtonLocator = By.cssSelector("td button[name='create_account']");
			
	//MainMenu Page
	private By logoutButtonLocator = By.xpath("//div[@class='content']//a[text()='Logout']");
			
	//---WebElements
	//Login Page
	private WebElement userNameInput;
	private WebElement passwordInput;
	private WebElement loginButton;
		
	//Create Account Page WebElements
	private WebElement taxIdInput;
	private WebElement companyInput;
	private WebElement firstNameInput;
	private WebElement lastNameIput;
	private WebElement address1Input;
	private WebElement address2Input;
	private WebElement postcodeNameInput;
	private WebElement cityInput;
	private WebElement emailInput;
	private WebElement phoneInput;
	private WebElement desiredPasswordInput;
	private WebElement confirmPasswordInput;
	private WebElement countrySelect;
	private WebElement newsletterCheckBox;
	private WebElement createAccountButton;
		
	//MainMenu Page
	private WebElement logoutButton;
	
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wojtas_dr\\.m2\\repository\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void createNewAccountTest() throws InterruptedException{
		driver.get("http://localhost/litecart/en/create_account");
		
		this.createNewAccount(newAccountData);
		this.logout();
		this.loginWithEmailCorrectCredentials(newAccountData[8], newAccountData[10]);
		this.logout();
	}
	
	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
	
	public void loginWithEmailCorrectCredentials(String userName, String password) {
		// Type password
		userNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(userEmailInputLocator));
		userNameInput.clear();
		userNameInput.sendKeys(userName);

		// Type userName
		passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(passwordInputLocator));
		passwordInput.clear();
		passwordInput.sendKeys(password);

		// Login
		loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(loginButtonLocator));
		loginButton.click();
		wait.until(ExpectedConditions.titleIs(homePageTitle));
	}
	
	public void logout() {
		logoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(logoutButtonLocator));
		logoutButton.click();
		wait.until(ExpectedConditions.titleIs(loginPageTitle));
	}
	
	public void createNewAccount(String[] newAccountData) {
		taxIdInput = driver.findElement(taxIdInputLocator);
		taxIdInput.sendKeys(newAccountData[0]);
		
		companyInput = driver.findElement(companyInputLocator);
		companyInput.sendKeys(newAccountData[1]);
		
		firstNameInput = driver.findElement(firstNameInputLocator);
		firstNameInput.sendKeys(newAccountData[2]);
		
		lastNameIput = driver.findElement(lastNameInputLocator);
		lastNameIput.sendKeys(newAccountData[3]);
		
		address1Input = driver.findElement(address1InputLocator);
		address1Input.sendKeys(newAccountData[4]);
		
		address2Input = driver.findElement(address2InputLocator);
		address2Input.sendKeys(newAccountData[5]);
		
		postcodeNameInput = driver.findElement(postcodeNameInputLocator);
		postcodeNameInput.sendKeys(newAccountData[6]);
		
		cityInput = driver.findElement(cityInputLocator);
		cityInput.sendKeys(newAccountData[7]);
		
		emailInput = driver.findElement(emailInputLocator);
		emailInput.sendKeys(newAccountData[8]);
		
		phoneInput = driver.findElement(phoneInputLocator);
		phoneInput.sendKeys(newAccountData[9]);
		
		desiredPasswordInput = driver.findElement(desiredPasswordInputLocator);
		desiredPasswordInput.sendKeys(newAccountData[10]);
		
		confirmPasswordInput = driver.findElement(confirmPasswordInputLocator);
		confirmPasswordInput.sendKeys(newAccountData[11]);
		
		newsletterCheckBox = driver.findElement(newsletterCheckBoxLocator);
		if(newsletterCheckBox.isSelected()){
			newsletterCheckBox.click();
		}
		
		countrySelect = driver.findElement(countrySelectLocator);
		Select cS = new Select(countrySelect);
		cS.selectByVisibleText(newAccountData[12]);
		
		createAccountButton = driver.findElement(createAccountButtonLocator);
		createAccountButton.click();
	}
}
