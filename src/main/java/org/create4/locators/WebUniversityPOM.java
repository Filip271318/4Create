package org.create4.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebUniversityPOM {

    WebDriver driver;

    private By homePageHeader = By.id("udemy-promo-thumbnail");
    private By actionSection = By.id("actions");
    private By actionHeader = By.id("main-header");
    private By dragBox = By.id("draggable");
    private By dropBox = By.id("droppable");
    private By hoverOverMe = By.cssSelector(".dropdown.hover");
    private By linkOne = By.className("list-alert");
    private By contactUsHeader = By.name("contactme");
    private By commentsSection = By.name("message");

    public WebUniversityPOM(WebDriver driver){

        this.driver = driver;
    }

    public WebElement getHomePage(){

        return driver.findElement(homePageHeader);
    }

    public void scrollToActions(Actions actions){

        WebElement actionsSection = driver.findElement(actionSection);

        actions.moveToElement(actionsSection);
        actions.perform();

    }

    public void clickOnActions(){

        WebElement actionsSection = driver.findElement(actionSection);

        actionsSection.click();
    }

    public String getActionsHeader(){

        return driver.findElement(actionHeader).getText();

    }

    public WebElement getDragBox(){

        return driver.findElement(dragBox);

    }

    public WebElement getDropBox(){

        return driver.findElement(dropBox);

    }


    public String getDropBoxText(){

        return driver.findElement(dropBox).getText();
    }

    public WebElement getHoverOverMeButton(){

        return driver.findElement(hoverOverMe);

    }

    public WebElement getLink1(){

        return getHoverOverMeButton().findElement(linkOne);

    }

    public String getContactUsHeader(){

        return driver.findElement(contactUsHeader).getText();
    }

    public void insertTextInCommentBox(String alertMessage){

        driver.findElement(commentsSection).sendKeys(alertMessage);
    }

}
