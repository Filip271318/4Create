package org.create4.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperMethods {

     static void addRandomItemToCart(WebDriver driver, List<WebElement> elementList){

        Random r = new Random();

        int random = r.nextInt(elementList.size());
        WebElement randomElement = elementList.get(random);
        JavascriptExecutor je =(JavascriptExecutor)driver;
        je.executeScript("window.scrollTo(0,\"randomElement.getLocation().x+\")");
        randomElement.click();

        elementList.remove(random);
    }

    static int findCheapestItem(WebDriver driver) {

        List<WebElement> itemList =  driver.findElements(By.className("product"));
        int lowestPrice = Integer.valueOf(itemList.get(0).findElement(By.className("product-price")).getText());
        int cheapestItemNumber = 0;
        for(int i = 1; i < itemList.size()-1; i++) {
            int currentItemPrice = Integer.valueOf(itemList.get(i).findElement(By.className("product-price")).getText());
            if (currentItemPrice < lowestPrice){
                lowestPrice = currentItemPrice;
                cheapestItemNumber = i;
            }
        }
        return cheapestItemNumber;
    }

    static int findMostExpensiveItem(WebDriver driver) {

        List<WebElement> itemList =  driver.findElements(By.className("product"));
        int highestPrice = Integer.valueOf(itemList.get(0).findElement(By.className("product-price")).getText());
        int mostExpensiveItemNumber = 0;
        for(int i = 1; i < itemList.size()-1; i++) {
            int currentItemPrice = Integer.valueOf(itemList.get(i).findElement(By.className("product-price")).getText());
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
}
