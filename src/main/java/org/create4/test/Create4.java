package org.create4.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.create4.utils.TestMethods.*;

public class Create4 {
    public Create4() {
    }

    public static void main(String[] args){

        // Initializing the driver
        System.setProperty("webdriver.chrome.driver", "C:/Drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Executing the first URL Test
        seleniumUniversityTest(driver, wait);

        // Executing the second URL Test
        webDriverUniversityTest(driver);
    }

}
