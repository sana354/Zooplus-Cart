### Assignment


### Test Cases/flows that are implemented:
* Add multiple products from the recommendations located at the lower part of the page
* Asserting that the url contains “/cart”
* Getting all product prices and sorting them in descending order (in your Java code, not the UI)
* Incrementing by one the quantity of the product or products (if multiple) with the lowest price
* Deleting the product with the highest price.
* Changing shipping country to Portugal, with “5000” as postcode 
* Asserting that the subtotal is less than 19 then add more products
* Proceed to CheckOut

### Technical Stack

* Programming Language: Java
* Tool & Libraries: Selenium Webdriver along with TestNG
* IDE: intelliJ IDEA
* Framework: Modular driven
* Build tool: Gradle
* Design Pattern: Page Factory
* Reporting: Allure 


### Execution of tests

There are 3 ways one can execute test cases:

1. Via gradle command in the terminal (intellij IDEA)
   * ./gradlew test

2. Via Allure reporting **(Allure reporting has been added & configured within the project)**
   * This can be achieved if the system/computer has installed & configured allure to run the test cases locally
   * If allure is installed then please do the following for reporting
       * Open the IDE terminal (intelliJ) and run this command ---> **allure serve build/allure-results/**


***PLEASE NOTE: The test cases are running smoothly by executing them using above gradle command. 
So in case of any failure please do check all the required configurations***
