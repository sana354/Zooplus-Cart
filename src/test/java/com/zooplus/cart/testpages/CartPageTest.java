package com.zooplus.cart.testpages;

import com.zooplus.cart.basetest.BaseTest;
import com.zooplus.cart.pages.CartPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {
    private static CartPage cartPage;

    @Test (priority = 0)
    public void beforeTest()
    {
        cartPage = new CartPage(getDriver());
        cartPage.open();
    }

   @Test (priority = 1)
   @Owner("Sana")
   @Severity(SeverityLevel.NORMAL)
   @Description("Verify that the pop is being dismissed")

    public void dismissPopUp ()
    {
        cartPage.dismissPopup();

    }

    @Test (priority = 5)
    @Owner("Sana")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that multiple products are beng added")
    public void AddMultipleProductsFromRecommendation() {

        cartPage.AddMultipleProducts();
    }

     @Test (priority = 2)
     @Owner("Sana")
     @Severity(SeverityLevel.NORMAL)
     @Description("Verify that  url contains /cart ")
     public void Assert_Cart_exist() {
        cartPage.AssertURL();

    }

    @Test (priority = 3)

    @Owner("Sana")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify to proceed with CheckOut")
    public  void addproducts () throws InterruptedException {

        cartPage.addproduct();
    }


    @Test (priority = 4)
    @Owner("Sana")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that getting product prices and sort them in descending order")
    public  void SortProductPrice () {
     cartPage.sortProductPrice();

    }
    @Test (priority = 6)
    @Owner("Sana")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that Increment by one the quantity of the product")
          public  void IncrementQuantity (){
         cartPage.incrementQuantityLowPriceProduct();
          }


    @Test (priority = 7)
    @Owner("Sana")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify to delete the product with highest price")
    public  void deleteHighPriceProduct() {
        cartPage.deleteHighPriceProduct();

    }
    @Test (priority = 8)

    @Owner("Sana")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify to Changing shipping country to Portugal, with “5000” as postcode")
    public  void UpdateShippingCountry (){
     cartPage.updateShipCountry("5000");

    }

    @Test (priority = 9)

    @Owner("Sana")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify to proceed with CheckOut")
    public  void addProductsIfNeeded () throws InterruptedException {

        cartPage.checkSubtotal();
    }


   @Test (priority = 10)

    @Owner("Sana")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify to proceed with CheckOut")
    public  void ProceedToCheckOut (){

    cartPage.CheckOut();
    }

    @AfterTest
    public void afterTest()
    {


        cartPage.quit();


    }

}
