package org.create4.utils;

import org.create4.locators.SeleniumUniversityPOM;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class HelperMethods {

    static SeleniumUniversityPOM locators;

     static void addRandomItemToCart(WebDriver driver, List<WebElement> elementList){

        Random r = new Random();

        int random = r.nextInt(elementList.size());
        WebElement randomElement = elementList.get(random);
        JavascriptExecutor je =(JavascriptExecutor)driver;
        je.executeScript("window.scrollTo(0,\"randomElement.getLocation().x+\")");
        randomElement.click();

        elementList.remove(random);
    }

    static int findCheapestItem(WebDriver driver, WebDriverWait wait) {

         locators = new SeleniumUniversityPOM(driver, wait);

        List<WebElement> itemList =  driver.findElements(By.className("product"));
        int lowestPrice = Integer.valueOf(locators.getProductPrice(itemList, 0));
        int cheapestItemNumber = 0;
        for(int i = 1; i < itemList.size()-1; i++) {
            int currentItemPrice = Integer.valueOf(locators.getProductPrice(itemList, i));
            if (currentItemPrice < lowestPrice){
                lowestPrice = currentItemPrice;
                cheapestItemNumber = i;
            }
        }
        return cheapestItemNumber;
    }

    static int findMostExpensiveItem(WebDriver driver, WebDriverWait wait) {

        locators = new SeleniumUniversityPOM(driver, wait);

        List<WebElement> itemList =  driver.findElements(By.className("product"));
        int highestPrice = Integer.valueOf(locators.getProductPrice(itemList, 0));
        int mostExpensiveItemNumber = 0;
        for(int i = 1; i < itemList.size()-1; i++) {
            int currentItemPrice = Integer.valueOf(locators.getProductPrice(itemList, i));
            if (currentItemPrice > highestPrice){
                highestPrice = currentItemPrice;
                mostExpensiveItemNumber = i;
            }
        }
        return mostExpensiveItemNumber;
    }

    static void addItemToCart(WebDriver driver, List<WebElement> itemList, int itemNumber){
        WebElement element = itemList.get(itemNumber);
        JavascriptExecutor je =(JavascriptExecutor)driver;
        je.executeScript("window.scrollTo(0,\"randomElement.getLocation().x+\")");
        element.click();
    }

    static void selectRandomCountry(){

        List<WebElement> countryList = locators.getCountryList();

        Random r = new Random();
        int random = r.nextInt(countryList.size() - 1) + 1;
        countryList.get(random).click();
    }
}
