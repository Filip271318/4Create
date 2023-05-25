package org.create4.utils;

import org.create4.locators.SeleniumUniversityPOM;
import org.create4.locators.WebUniversityPOM;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.create4.utils.HelperMethods.*;
import static org.create4.utils.Constants.*;

public class TestMethods {

    static SeleniumUniversityPOM seleniumUniversity;
    static WebUniversityPOM webUniversity;

    // Method is used to test the first part of the task;
    // https://rahulshettyacademy.com/seleniumPractise/#/
    public static void seleniumUniversityTest(WebDriver driver, WebDriverWait wait){
        // TC ID 1
        // Opening browser and navigating to URL
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        driver.get(SELENIUM_UNIVERSITY_URL);

        seleniumUniversity = new SeleniumUniversityPOM(driver, wait);

        // TC ID 2
        // Finding all items
        List<WebElement> itemsDisplayed = seleniumUniversity.getElementsDisplayed();

        // Adding the cheapest item to the cart
        int cheapestItem = findCheapestItem(driver, wait);
        addItemToCart(driver, itemsDisplayed, cheapestItem);
        itemsDisplayed.remove(cheapestItem);

        // Adding the most expensive item to the cart
        int mostExpensiveItem = findMostExpensiveItem(driver, wait);
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
        seleniumUniversity.clickOnBasketIcon();

        // Clicking on Proceed to checkout
        seleniumUniversity.clickOnProceed();

        // TC ID 3
        // Waiting for the checkout page
        seleniumUniversity.waitForCheckoutPage();

        // Getting the total amount which is going to be used as a promo code
        String totalAmount = seleniumUniversity.getPromoCode();

        // Entering the promo code
        seleniumUniversity.enterPromoCode(totalAmount);

        // TC ID 4
        // Clicking on the Apply button
        seleniumUniversity.clickOnApply();

        // Waiting for the error message
        seleniumUniversity.waitForErrorMessage();

        // Verify the error message has been displayed
        String errorMessage = seleniumUniversity.getErrorMessageText();
        Assert.assertTrue(errorMessage.contains(PROMO_CODE_FAILURE_MESSAGE));

        // TC ID 5
        // Clicking on the Place Order button
        seleniumUniversity.clickOnPlaceOrder();

        // TC ID 6
        // Waiting for the T&C Page
        seleniumUniversity.waitForTermsAndConditionsPage();

        // Asserting that the Select option is disabled
        Assert.assertFalse(seleniumUniversity.getSelectOption().isEnabled());

        // Clicking on the country dropdown
        seleniumUniversity.clickOnCountryDropdown();

        // Selecting a random country
        selectRandomCountry();

        // TC ID 7
        // Clicking on the Terms and Conditions checkbox
        seleniumUniversity.clickOnTermsAndConditionsCheckbox();

        // Clicking on proceed
        seleniumUniversity.clickOnProceedToAcceptTiC();

        // Waiting for the Success message
        seleniumUniversity.waitForSuccessMessage();

        // Verifying that the correct success message has been displayed
        Assert.assertTrue(seleniumUniversity.getSuccessMessage().isDisplayed());

    }

    // Method is used to test the second part of the task;
    // http://www.webdriveruniversity.com/
    public static void webDriverUniversityTest(WebDriver driver){

        webUniversity = new WebUniversityPOM(driver);

        // TC ID 8
        // Opening a new tab
        driver.switchTo().newWindow(WindowType.TAB);

        // Navigating to a different URL
        driver.navigate().to(WEB_UNIVERSITY_URL);

        // Asserting that the user is on the Home Page
        Assert.assertTrue(webUniversity.getHomePage().isDisplayed());

        // TC ID 9.1.
        // Scrolling down to the Actions section
        Actions actions = new Actions(driver);
        webUniversity.scrollToActions(actions);

        // Taking a screenshot
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);

        // TC ID 9.2.
        // Clicking on the Actions section
        webUniversity.clickOnActions();

        // TC ID 10.1.
        // Switching the driver to the Actions tab a verifying that it is opened correctly
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(2));
        Assert.assertTrue(webUniversity.getActionsHeader().contains(ACTIONS_HEADER));

        // TC ID 10.2.
        // Returning to the Home Page and taking a screenshot
        driver.switchTo().window(tabs.get(1));
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // TC ID 10.3.
        // Returning to the Actions tab and verifying name contains 'Actions'
        driver.switchTo().window(tabs.get(2));
        String tabTitle = driver.getTitle();
        Assert.assertTrue(tabTitle.contains("Actions"));

        // TC ID 11
        // Dragging and dropping one box inside another one
        WebElement dragBox = webUniversity.getDragBox();
        WebElement dropBox = webUniversity.getDropBox();

        actions.dragAndDrop(dragBox, dropBox).build().perform();

        // Asserting that it was successful
        Assert.assertTrue(webUniversity.getDropBoxText().contains(DROPPED_SUCCESSFUL_MESSAGE));

        // TC ID 12
        // Asserting that Link 1 is not visible
        WebElement hoverOverMe = webUniversity.getHoverOverMeButton();
        Assert.assertFalse(webUniversity.getLink1().isDisplayed());

        // TC ID 13
        // Asserting that Link 1 is visible once Hovered over
        actions.moveToElement(hoverOverMe).perform();
        WebElement linkOne = webUniversity.getLink1();

        Assert.assertTrue(linkOne.isDisplayed());

        // TC ID 14
        // Clicking on Link 1
        linkOne.click();

        // Saving the Alert message text
        String alertMessage = driver.switchTo().alert().getText();

        // Asserting that the alert message is displayed
        Assert.assertTrue(alertMessage.contains(ALERT_MESSAGE));

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
        driver.get(WEB_UNIVERSITY_CONTACT_US_URL);

        // Asserting that the page is opened
        webUniversity = new WebUniversityPOM(driver);
        Assert.assertTrue(webUniversity.getContactUsHeader().contains(CONTACT_US_HEADER));

        // TC ID 18
        // Inserting the Alert message text inside the comments checkbox
        webUniversity.insertTextInCommentBox(alertMessage);
    }
}
