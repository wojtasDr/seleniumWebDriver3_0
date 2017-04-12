package ru.stqa.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.FindElementsMethods;

public class CartPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	// Locators
	private By removeButtonLocator = By.cssSelector("button[name='remove_cart_item']");
	private By productsSummaryTableLocator = By.cssSelector("div#box-checkout-summary");
	
	// WebElements
	private WebElement removeButton;
	
	public CartPage(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;	
	}
	
	public CartPage removeProductFromCart(){
		removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(removeButtonLocator));
		removeButton.click();
		
		return this;
	}
	
	public void waitUntilSummaryTableIsReloaded(WebElement SummaryTable) {
		FindElementsMethods.waitUntillElementBecomesStale(wait, SummaryTable);
	}
	
	public boolean isProductSummaryTablePresent() {
		return FindElementsMethods.isElementPresent(driver, productsSummaryTableLocator);
	}
	
	public WebElement getProductsSummaryTable(){
		return wait.until(ExpectedConditions.visibilityOfElementLocated(productsSummaryTableLocator));
	}
}
