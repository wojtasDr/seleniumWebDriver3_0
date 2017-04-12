package ru.stqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.FindElementsMethods;
import utils.ManipulateFormElements;

public class SingleProductPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	// Locators
	private By addToCartButtonLocator = By.cssSelector("td>button[name='add_cart_product']");
	private By homePageButtonLocator = By.xpath("//a/i[@title='Home']/..");
	private By sizeSelectLocator = By.cssSelector("select[name='options[Size]']");
	private By cartNumberOfProductsLocator = By.cssSelector("div#cart a.content span.quantity");
	private By cartLinkLocator = By.cssSelector("div#cart a.content");
	
	// WebElements
	private WebElement addToCartButton;
	private WebElement homePageButton;
	private WebElement cart;
	
	public SingleProductPage(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;	
	}
	
	public CartPage openCart(){
		cart = wait.until(ExpectedConditions.visibilityOfElementLocated(cartLinkLocator));
		cart.click();
		
		return new CartPage(driver,wait);
	}
	
	public SingleProductPage addProductToCart(){
		addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonLocator));
		addToCartButton.click();
		

		return this;
	}
	
	public SingleProductPage checkIfCartUpdated(int expectedNumberOfProducts){
		wait.until(ExpectedConditions.textToBePresentInElementLocated(cartNumberOfProductsLocator, String.valueOf(expectedNumberOfProducts)));
		
		return this;
	}
	
	public SingleProductPage setSizeSelect(String selectOption){
		ManipulateFormElements.manipulateSelectText(wait, sizeSelectLocator, selectOption);
		
		return this;
	}
	
	public ProductsPage returnHomePage(){
		homePageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(homePageButtonLocator));
		homePageButton.click();
		
		return new ProductsPage(driver,wait);
	}
	
	public boolean isSizeSelectPresent() {
		return FindElementsMethods.isElementPresent(driver, sizeSelectLocator);
	}
}