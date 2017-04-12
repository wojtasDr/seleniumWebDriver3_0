package ru.stqa.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	// Locators
	private By cartNumberOfProductsLocator = By.cssSelector("div#cart a.content span.quantity");
	private By mostPopularProductsLocator = By.cssSelector("div#box-most-popular li");
	
	//WebElements
	WebElement cart;

	public ProductsPage(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;	
	}
	
	public ProductsPage openProductsPage(){
		driver.get("http://localhost/litecart/en/");
		
		return new ProductsPage(driver,wait);
	}
	
	public SingleProductPage chooseProduct(WebElement product){
		product.click();
		
		return new SingleProductPage(driver,wait);
	}
	
	public List<WebElement> getMostPopularProducts(){
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(mostPopularProductsLocator));
	}
	
	public WebElement getCart(){
		return  wait.until(ExpectedConditions.visibilityOfElementLocated(cartNumberOfProductsLocator));
	}
}