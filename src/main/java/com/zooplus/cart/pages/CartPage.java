package com.zooplus.cart.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPage extends AbstractBasePage {

    private static final String cartPageURL = "checkout/cart";
    private final String pageURL = getBaseURL() + cartPageURL;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Maximum wait time of 10 seconds


    @FindBy(css = "#onetrust-accept-btn-handler")
    private WebElement Agreebutton;

    @FindBy(css = "#splide03 > div.splide__arrows.splide__arrows--ltr > button.splide__arrow--next.uNp32lhEEmXQrWBUZ3wa > svg")
    private WebElement arrowButton;

    @FindBy(xpath = "//button[@class='z-btn z-btn--cart z-btn--cart-icon z-btn--small U4BZkzVFwtf33Kl5j64c']")
    private List<WebElement> addtoCart;

    @FindBy(css = "[data-zta^=\"productStandardPriceAmount\"]")
    private List<WebElement> priceElementss;

    @FindBy(css = ".z-price__price-wrap")
    private List<WebElement> priceElements;

    @FindBy(css = ".z-price__price-wrap")
    private WebElement price_Element;

    @FindBy(xpath = "//button[@class='z-btn z-btn--cart z-btn--cart-icon z-btn--small U4BZkzVFwtf33Kl5j64c']")
    private WebElement add2cart;
    @FindBy(xpath = "//*[@data-zta=\"quantityStepperIncrementButton\" and contains(@class, \"z-qty-stepper__btn\")]")
    private List<WebElement> incrementQuantity;

    @FindBy(xpath = "//*[@data-zta=\"quantityStepperDecrementButton\" and contains(@class, \"z-qty-stepper__btn\")]")
    private List<WebElement> deleteButtons;

    @FindBy(css = "[data-zta='overviewSubTotalValue'].z-p1")
    private WebElement subtotalElement;

    @FindBy(css = "#cartSummary > div:nth-child(2) > div > div > div > div > a > svg")
    private WebElement selectdropdown;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div[2]/div/div[1]/div/button")
    private WebElement Openlist;

    @FindBy(xpath = "//*[@id=\"dd-menu-BreakpointAll\"]/div/div/ul/button[19]/li/p")
    private WebElement selectShippingOption;

    @FindBy(css = "body > div.Pg4muwj7xJkk7JEJxMas > div > div.yBYUjN8nJhPBH41rP9hM > div.z-form-control-wrap.chvZY7DaEYO_tcQI_UAl.Ecxpkfhz6V4fLueQaX36 > input")
    private WebElement postalCode;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/button")
    private WebElement update;

    @FindBy(xpath = "  //*[@id=\"cartSummary\"]/button")
    private WebElement checkout;


    public CartPage(WebDriver driver) {


        super(driver);
    }

    @Override
    public String getUrl() {
        return pageURL;
    }

    public CartPage dismissPopup() {

        WebElement agreebutton = wait.until(ExpectedConditions.elementToBeClickable(Agreebutton));
        agreebutton.click();
        return this;
    }

    public CartPage AddMultipleProducts() {

        List<Double> prices = getproductprice(priceElements);
        System.out.println(" newly fetched Non-sorted Product prices: €" + prices);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // 10 seconds implicit wait

        int targetCount = Math.min(prices .size(), 4); // Add a maximum of 5 products or the available products

        for (int i = 0; i < targetCount; i++) {
            WebElement button = addtoCart.get(i); //  addtoCart contains all the "Add to Cart" WebElements


            try {
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                System.out.println("Added product " + (i+1));

                // Wait for the page to reload or settle down
                wait.until(ExpectedConditions.stalenessOf(button));
                // Fetch updated elements after page reload
                wait.until(ExpectedConditions.elementToBeClickable(arrowButton));
                // Click the arrow button to reveal hidden products
                arrowButton.click();

                // Wait for the page to update and the additional products to become visible
            //    wait.until(ExpectedConditions.visibilityOfElementLocated((By) price_Element));

                addtoCart = driver.findElements(By.xpath("//button[@class='z-btn z-btn--cart z-btn--cart-icon z-btn--small U4BZkzVFwtf33Kl5j64c'][last()"));
                priceElements = driver.findElements(By.cssSelector(".z-price__price-wrap"));

            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException encountered. Retrying...");
            } catch (Exception e) {
                System.out.println("Error adding product: " + e.getMessage());
            }
        }
            return this;
        }

        public void addproduct () throws InterruptedException {

            wait.until(ExpectedConditions.elementToBeClickable(add2cart));
            int numberOfTimes = 1;

            for (int i = 0; i < numberOfTimes; i++) {
                add2cart.click();
                //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                 Thread.sleep(3000);
            }
        }

    public CartPage AssertURL()
    {

        String currentURL = driver.getCurrentUrl();

        try {
            assert currentURL.contains("/cart");
            System.out.println("/cart URL Assertion passed.");
        } catch (AssertionError e) {
            System.out.println("Assertion failed: URL does not contain '/cart'");
        }
        return this;
    }

    public CartPage sortProductPrice() {

        List<Double> prices = getproductprice(priceElements);
        System.out.println("Non-sorted Product prices: €" + prices);

        if (!prices.isEmpty()) {
            Collections.sort(prices, Collections.reverseOrder());

            for (double price : prices) {
                System.out.println("Sorted Product prices: €" + price);
            }

            double lowestPrice = prices.get(prices.size() - 1);
            System.out.println("Lowest Price: €" + lowestPrice);
        } else {
            System.out.println("No prices found.");
        }

        return this;
    }


    public CartPage incrementQuantityLowPriceProduct() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        List<Double> prices = getproductprice(priceElements);

        System.out.println ("Increment method prices list:" + prices);
        List<WebElement> incrementButtons = driver.findElements(By.xpath("//*[@data-zta=\"quantityStepperIncrementButton\" and contains(@class, \"z-qty-stepper__btn\")]"));

        // Find and click the lowest price increment button
        if (!prices.isEmpty()) {
            double lowestPrice = Collections.min(prices);
            int lowestPriceIndex = prices.indexOf(lowestPrice);
            System.out.println("Lowest price:" + lowestPrice);
            System.out.println("Lowest price index:" + lowestPriceIndex);

          if (lowestPriceIndex >= 0 && lowestPriceIndex < incrementQuantity.size()) {
                WebElement lowestPriceIncrementButton = incrementButtons.get(lowestPriceIndex);
                wait.until(ExpectedConditions.elementToBeClickable(lowestPriceIncrementButton));
                try {
                    System.out.println("Clicking on increment button for lowest price index: " + lowestPriceIndex);
                    lowestPriceIncrementButton.click();
                    System.out.println("Clicked successfully on increment button for lowest price index: " + lowestPriceIndex);
                } catch (Exception e) {
                    System.err.println("Error while clicking on increment button: " + e.getMessage());
                }
            }

        }

        return this;

    }

    public CartPage deleteHighPriceProduct() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");


        List<Double> prices = getproductprice(priceElements);
        System.out.println ("decrement method prices list:" + prices);

        if (!prices.isEmpty()) {
            double highestPrice = Collections.max(prices);
            int highestPriceIndex = prices.indexOf(highestPrice);
            System.out.println("New Highest price index:" + highestPrice);
            System.out.println("New Highest price index:" + highestPriceIndex);

            if (highestPriceIndex >= 0 && highestPriceIndex < deleteButtons.size()) {
                try {
                    WebElement highestPriceDeleteButton = deleteButtons.get(highestPriceIndex);

                    wait.until(ExpectedConditions.elementToBeClickable(highestPriceDeleteButton));
                    highestPriceDeleteButton.click();
                    System.out.println("Clicked on decrement or delete button for Highest price index:" + highestPriceIndex);
                } catch (StaleElementReferenceException e) {
                    System.out.println("Error: Stale Element Reference - " + e.getMessage());
                }
            }
        }
        return this;
    }

    public CartPage updateShipCountry(String postalcode) {

        wait.until(ExpectedConditions.elementToBeClickable(selectdropdown));
        selectdropdown.click();

        wait.until(ExpectedConditions.elementToBeClickable(Openlist));
        Openlist.click();

        wait.until(ExpectedConditions.elementToBeClickable(selectShippingOption));
        selectShippingOption.click();

        wait.until(ExpectedConditions.elementToBeClickable(postalCode));
        postalCode.sendKeys(postalcode);

        wait.until(ExpectedConditions.elementToBeClickable(update));
        update.click();
        return this;
    }

    public CartPage CheckOut() {

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", checkout);
        checkout.click();
        return this;
    }

    public List<Double> getproductprice(List<WebElement> priceElements) {
        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("€", "");

            if (priceText.matches("(Now )?\\d+\\.\\d{2}")) {
                try {
                    double price = Double.parseDouble(priceText);
                    prices.add(price);
                    System.out.println("getproduct Price: " + price);
                } catch (NumberFormatException e) {
                    System.err.println("Failed to parse price: " + priceText);
                }
            }
        }

        return prices;

    }
    public CartPage checkSubtotal() throws InterruptedException {

        while (true) {

            String subtotalText = subtotalElement.getText().replaceAll("[^\\d.]", "");
            double subtotal = Double.parseDouble(subtotalText);

            if (subtotal < 19) {

                addproduct();

            } else {
                break; // Exit the loop if subtotal is not less than 19
            }
        }
        return this;
    }



}





