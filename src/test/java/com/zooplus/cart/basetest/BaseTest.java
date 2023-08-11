package com.zooplus.cart.basetest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

public class BaseTest
{
    private WebDriver driver;

   @BeforeTest
    public void setupClass()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        try
        {
            this.driver = new ChromeDriver(options);

        }
        catch (Exception ex)
        {
            throw new RuntimeException("could not create the chrome driver");
        }

    }


    public WebDriver getDriver()
    {
        return driver;
    }
}
