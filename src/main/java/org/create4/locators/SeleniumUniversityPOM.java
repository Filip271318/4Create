package org.create4.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.create4.utils.Constants.SUCCESS_MESSAGE;

public class SeleniumUniversityPOM {

    WebDriver driver;
    WebDriverWait wait;

    private By productPrice = By.className("product-price");
    private By displayedProducts = By.className("product-action");
    private By basketIcon = By.xpath("//img[@alt='Cart']");
    private By totalAmount = By.className("totAmt");
    private By button = By.tagName("button");
    private By proceedSection = By.className("action-block");
    private By promoCode = By.className("promoCode");
    private By applyButton = By.className("promoBtn");
    private By errorMessage = By.className("promoInfo");
    private By placeOrder = By.xpath("//button[text()='Place Order']");
    private By termsAndConditions = By.xpath("//a[text()='Terms & Conditions']");
    private By selectOption = By.xpath("//option[text()='Select']");
    private By countryDropdown = By.tagName("select");
    private By countryList = By.tagName("option");
    private By termsAndConditionsCheckbox = By.className("chkAgree");
    private By proceedTiC = By.xpath("//button[text()='Proceed']");
    private By successMessage =  By.xpath("//span[contains(text(),'" + SUCCESS_MESSAGE + "')]");


    public SeleniumUniversityPOM(WebDriver driver, WebDriverWait wait){

        this.driver = driver;
        this.wait = wait;

    }

    public String getProductPrice(List<WebElement> itemList, int productIndex){

        return itemList.get(productIndex).findElement(productPrice).getText();
    }

    public List<WebElement> getElementsDisplayed(){

        return driver.findElements(displayedProducts);

    }

    public void clickOnBasketIcon(){

        driver.findElement(basketIcon).click();

    }

    public void clickOnProceed(){

        driver.findElement(proceedSection).findElement(button).click();

    }

    public void waitForCheckoutPage(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmount));

    }


    public String getPromoCode(){

        return driver.findElement(totalAmount).getText();
    }

    public void enterPromoCode(String totalAmount){

        driver.findElement(promoCode).sendKeys(totalAmount);

    }

    public void clickOnApply(){

        driver.findElement(applyButton).click();

    }

    public void waitForErrorMessage(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

    }

    public String getErrorMessageText(){

        return driver.findElement(errorMessage).getText();
    }

    public void clickOnPlaceOrder(){

        driver.findElement(placeOrder).click();

    }

    public void waitForTermsAndConditionsPage(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(termsAndConditions));
    }

    public WebElement getSelectOption(){

        return driver.findElement(selectOption);
    }

    public void clickOnCountryDropdown(){

        driver.findElement(countryDropdown).click();
    }

    public List<WebElement> getCountryList(){

        return driver.findElements(countryList);
    }

    public void clickOnTermsAndConditionsCheckbox(){

        driver.findElement(termsAndConditionsCheckbox).click();

    }

    public void clickOnProceedToAcceptTiC(){

        driver.findElement(proceedTiC).click();

    }

    public void waitForSuccessMessage(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    public WebElement getSuccessMessage(){

        return driver.findElement(successMessage);
    }

}
