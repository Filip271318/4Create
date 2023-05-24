package org.create4.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.create4.utils.HelperMethods.*;

public class TestMethods {

    public static void seleniumUniversityTest(WebDriver driver, WebDriverWait wait){
        // TC ID 1
        // Opening browser and navigating to URL
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

        // TC ID 2
        // Finding all items
        List<WebElement> itemsDisplayed = driver.findElements(By.className("product-action"));

        // Adding the cheapest item to the cart
        int cheapestItem = findCheapestItem(driver);
        addItemToCart(driver, itemsDisplayed, cheapestItem);
        itemsDisplayed.remove(cheapestItem);

        // Adding the most expensive item to the cart
        int mostExpensiveItem = findMostExpensiveItem(driver);
        addItemToCart(driver, itemsDisplayed, mostExpensiveItem);
        itemsDisplayed.remove(mostExpensiveItem);

        // Adding a random item to the cart (which is not one of the previous ones)
        addRandomItemToCart(driver, itemsDisplayed);

        // Adding the first displayed item to the cart
        for(int i=0; i<4; i++) {
            addItemToCart(driver, itemsDisplayed, 0);
        }
        itemsDisplayed.remove(0);

        // Adding three random items to the cart
        addRandomItemToCart(driver, itemsDisplayed);
        addRandomItemToCart(driver, itemsDisplayed);
        addRandomItemToCart(driver, itemsDisplayed);

        // Clicking on the basket icon
        driver.findElement(By.xpath("//img[@alt='Cart']")).click();

        // Clicking on Proceed to checkout
        driver.findElement(By.className("action-block")).findElement(By.tagName("button")).click();

        // TC ID 3
        // Waiting for the checkout page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("totAmt")));

        // Getting the total amount which is going to be used as a promo code
        String totalAmount = driver.findElement(By.className("totAmt")).getText();

        // Entering the promo code
        driver.findElement(By.className("promoCode")).sendKeys(totalAmount);

        // TC ID 4
        // Clicking on the Apply button
        driver.findElement(By.className("promoBtn")).click();

        // Waiting for the error message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoInfo")));

        // Verify the error message has been displayed
        String errorMessage = driver.findElement(By.className("promoInfo")).getText();
        Assert.assertTrue(errorMessage.contains("Invalid code ..!"));

        // TC ID 5
        // Clicking on the Place Order button
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        // TC ID 6
        // Waiting for the T&C Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()='Terms & Conditions']")));

        // Asserting that the Select option is disabled
        Assert.assertFalse(driver.findElement(By.xpath("//option[text()='Select']")).isEnabled());

        // Clicking on the country dropdown
        driver.findElement(By.tagName("select")).click();

        List<WebElement> countryList = driver.findElements(By.tagName("option"));

        // Selecting a random country
        Random r = new Random();
        int random = r.nextInt(countryList.size() - 1) + 1;
        countryList.get(random).click();

        // TC ID 7
        // Clicking on the Terms and Conditions checkbox
        driver.findElement(By.className("chkAgree")).click();

        // Clicking on proceed
        driver.findElement(By.xpath("//button[text()='Proceed']")).click();

        // Waiting for the Success message
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Thank you, your order has been placed successfully')]")));

        // Verifying that the correct success message has been displayed
        Assert.assertTrue(driver.findElement(
                        By.xpath("//span[contains(text(),'Thank you, your order has been placed successfully')]"))
                .isDisplayed());

    }

    public static void webDriverUniversityTest(WebDriver driver){

        // TC ID 8
        // Opening a new tab
        driver.switchTo().newWindow(WindowType.TAB);

        // Navigating to a different URL
        driver.navigate().to("http://www.webdriveruniversity.com/");

        // Asserting that the user is on the Home Page
        Assert.assertTrue(driver.findElement(By.id("udemy-promo-thumbnail")).isDisplayed());

        // TC ID 9.1.
        // Scrolling down to the Actions section
        WebElement actionsSection = driver.findElement(By.id("actions"));

        Actions actions = new Actions(driver);
        actions.moveToElement(actionsSection);
        actions.perform();

        // Taking a screenshot
        File actionsTab = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);

        // TC ID 9.2.
        // Clicking on the Actions section
        actionsSection.click();

        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());

        // TC ID 10.1.
        // Switching the driver to the Actions tab a verifying that it is opened correctly
        driver.switchTo().window(tabs.get(2));
        Assert.assertTrue(driver.findElement(By.id("main-header")).getText()
                .contains("The Key to Success is to take massive ACTION!"));

        // TC ID 10.2.
        // Returning to the Home Page and taking a screenshot
        driver.switchTo().window(tabs.get(1));
        File universityHomeTab = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // TC ID 10.3.
        // Returning to the Actions tab and verifying name contains 'Actions'
        driver.switchTo().window(tabs.get(2));
        String tabTitle = driver.getTitle();
        Assert.assertTrue(tabTitle.contains("Actions"));

        // TC ID 11
        // Dragging and dropping one box inside another one
        WebElement dragBox = driver.findElement(By.id("draggable"));
        WebElement dropBox = driver.findElement(By.id("droppable"));

        actions.dragAndDrop(dragBox, dropBox).build().perform();

        // Asserting that it was successful
        Assert.assertTrue(driver.findElement(By.id("droppable")).getText().contains("Dropped!"));

        // TC ID 12
        // Asserting that Link 1 is not visible
        WebElement hoverOverMe = driver.findElement(By.cssSelector(".dropdown.hover"));
        Assert.assertFalse(hoverOverMe.findElement(By.className("list-alert")).isDisplayed());

        // TC ID 13
        // Asserting that Link 1 is visible once Hovered over
        actions.moveToElement(hoverOverMe).perform();
        WebElement linkOne = driver.findElement(By.cssSelector(".dropdown.hover")).findElement(By.className("list-alert"));

        Assert.assertTrue(linkOne.isDisplayed());

        // TC ID 14
        // Clicking on Link 1
        linkOne.click();

        // Saving the Alert message text
        String alertMessage = driver.switchTo().alert().getText();

        // Asserting that the alert message is displayed
        Assert.assertTrue(alertMessage.contains("Well done you clicked on the link!"));

        // TC ID 15
        // Clicking OK on the alert message
        driver.switchTo().alert().accept();

        // TC ID 16
        // Closing the alert message
        driver.quit();

        // TC ID 17
        // Opening the browser again
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);

        // Navigating to the URL
        driver.get("http://www.webdriveruniversity.com/Contact-Us/contactus.html");

        // Asserting that the page is opened
        Assert.assertTrue(driver.findElement(By.name("contactme")).getText().contains("CONTACT US"));

        // TC ID 18
        // Inserting the Alert message text inside the comments checkbox
        driver.findElement(By.name("message")).sendKeys(alertMessage);
    }
}
