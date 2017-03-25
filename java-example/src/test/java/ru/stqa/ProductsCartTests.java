package ru.stqa;

import java.util.ArrayList;
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

import utils.FindElementsMethods;
import utils.ManipulateFormElements;

public class ProductsCartTests {
	private WebDriver driver;
	private WebDriverWait wait;

	// ---Locators
	// Products Page
	private By cartNumberOfProductsLocator = By.cssSelector("div#cart a.content span.quantity");
	private By cartLinkLocator = By.cssSelector("div#cart a.content");
	private By mostPopularProductsLocator = By.cssSelector("div#box-most-popular li");

	// Product Page
	private By addToCartButtonLocator = By.cssSelector("td>button[name='add_cart_product']");
	private By homePageButtonLocator = By.xpath("//a/i[@title='Home']/..");
	private By sizeSelectLocator = By.cssSelector("select[name='options[Size]']");

	// Cart Page
	private By removeButtonLocator = By.cssSelector("button[name='remove_cart_item']");
	private By productsSummaryTableLocator = By.cssSelector("div#box-checkout-summary");

	// ---WebElements
	// Products Page
	private WebElement cart;
	private List<WebElement> mostPopularProducts = new ArrayList<WebElement>();

	// Product Page
	private WebElement addToCartButton;
	private WebElement homePageButton;

	// Cart Page
	private WebElement removeButton;
	private WebElement productsSummaryTable;

	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wojtas_dr\\.m2\\repository\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
	}

	@Test
	public void addRemoveProductsFromCart() {
		driver.get("http://localhost/litecart/en/");

		this.addProductsToCart(3);
		
		this.removeAllProductsFromCart();
	}

	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
	
	//helper methods
	private void addProductsToCart(int numberOfProducts){
		mostPopularProducts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(mostPopularProductsLocator));

		for (int i = 1; i <= numberOfProducts; i++) {
			mostPopularProducts.get(i).click();

			cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartNumberOfProductsLocator));

			if (FindElementsMethods.isElementPresent(driver, sizeSelectLocator)) {
				ManipulateFormElements.manipulateSelectText(wait, sizeSelectLocator, "Small");
			}

			addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonLocator));
			addToCartButton.click();

			wait.until(ExpectedConditions.textToBePresentInElementLocated(cartNumberOfProductsLocator, String.valueOf(i)));

			//cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartNumberOfProductsLocator));
			
			homePageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(homePageButtonLocator));
			homePageButton.click();

			mostPopularProducts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(mostPopularProductsLocator));
		}
	}
	
	private void removeAllProductsFromCart(){
		Boolean isProductsTableVisible = false;
		
		cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartLinkLocator));
		cart.click();

		do {
			isProductsTableVisible = FindElementsMethods.isElementPresent(driver, productsSummaryTableLocator);
			if (isProductsTableVisible) {
				productsSummaryTable = wait.until(ExpectedConditions.visibilityOfElementLocated(productsSummaryTableLocator));
				
				removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(removeButtonLocator));
				removeButton.click();
				
				wait.until(ExpectedConditions.stalenessOf(productsSummaryTable));
			}
		} while (isProductsTableVisible);
	}
}
