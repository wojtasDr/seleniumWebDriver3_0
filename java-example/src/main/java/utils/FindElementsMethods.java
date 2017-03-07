package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

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

	public static boolean areElementsPresent(WebDriver driver, By locator) {
		return driver.findElements(locator).size() > 0;
	}
}
