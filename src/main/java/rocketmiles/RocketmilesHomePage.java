package rocketmiles;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.WebElement;


public class RocketmilesHomePage {
    private WebDriver driver;
    private String homePageUrl;
    WebDriverWait wait;

//    This is here only for demonstration purpose
//    While finding elements using `@FindBy`, you are implicitly waiting for those
//    elements to be present before attempting to act on them.
//    @FindBy(css = ".rm-btn-orange > span:nth-child(1)")
//    private WebElement searchBtn;
//
//    @FindBy(css = ".cookie-banner-button")
//    private WebElement cookiesBtn;
//
//    @FindBy(css = ".close > span:nth-child(1)")
//    private WebElement popUpWindowCloseBtn;
//
//    @FindBy(className = "popover-content ng-binding")
//    private WebElement locationErrorMsg;


    public RocketmilesHomePage(WebDriver driver, String homePageUrl) {
        this.driver = driver;
        this.homePageUrl = homePageUrl;
    }

    // Used Javascript to click the Search button due to a Selenium exception
    public void clickSearchBtn() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector
                (".rm-btn-orange > span:nth-child(1)")));
    }

    // Used a round about way to close the cookies banner due to a Selenium exception
    public void clickCookiesBtn() {
        wait = new WebDriverWait(driver, 30);
        try {
            driver.findElement(By.cssSelector(".cookie-banner-button")).click();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.cssSelector(".cookie-banner-button")).click();
        }
    }

    // Used a round about way to close the popup window due to a Selenium exception
    public void clickPopUpWindowCloseBtn() {
        wait = new WebDriverWait(driver, 30);
        try {
            driver.findElement(By.cssSelector(".close > span:nth-child(1)")).click();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.cssSelector(".close > span:nth-child(1)")).click();
        }
    }

    // Had to find elements in the methods as opposed to storing them as Webelements in the class
    // This is a necessary alternate approach due to the limitations of the web page and Selenium
    public boolean isLocationErrorDisplayed() {
        boolean result = driver.findElement(By.cssSelector("div.popover-content:nth-child(1)")).isDisplayed()
                ? true
                : false;
        return result;
    }

    // Method finds the error message web element. Returns true if found else false.
    public boolean isRewardProgramErrorDisplayed() {
        boolean result = driver.findElement(By.cssSelector("div.popover-content:nth-child(3)")).isDisplayed()
                ? true
                : false;
        return result;
    }

    // Populates the location field
    public void selectLocationValue(String location) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector
                    (".location-search-container > input:nth-child(1)"))).click().perform();

        }

}
