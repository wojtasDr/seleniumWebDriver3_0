package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindElementsMethods {
	public static boolean isElementPresent(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (InvalidSelectorException ex) {
			throw ex;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}
	
	public static void waitUntillElementBecomesStale(WebDriverWait wait, WebElement wE){
		wait.until(ExpectedConditions.stalenessOf(wE));
	}

	public static boolean areElementsPresent(WebDriver driver, By locator) {
		return driver.findElements(locator).size() > 0;
	}
	
	public static int getNumberOfElements(WebElement wE, By locator){
		return wE.findElements(locator).size();
	}
	
	public static int getNumberOfElements(WebDriver driver, By locator){
		return driver.findElements(locator).size();
	}
}
