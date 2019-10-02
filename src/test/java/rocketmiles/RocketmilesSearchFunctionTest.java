package rocketmiles;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RocketmilesSearchFunctionTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String homePageUrl = "https://www.rocketmiles.com/";
    private final String expectedPageTitle = "Rocketmiles - Book Hotels Earn Thousands of Frequent Flyer Miles";
    private RocketmilesHomePage rocketmilesHomePage;


    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        rocketmilesHomePage = new RocketmilesHomePage(driver, homePageUrl);
        driver.get(homePageUrl);
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleIs(expectedPageTitle));
        rocketmilesHomePage.clickCookiesBtn();
        rocketmilesHomePage.clickPopUpWindowCloseBtn();
    }

    //Check that user is on the right page
    @Test
    public void givenUserIsOnHomePage_PageTitleIsCorrect() {
        assertThat(driver.getTitle(), is(expectedPageTitle));
    }

    //Check that a message is displayed when no location is entered
    @Test
    public void givenNoLocationEntry_ErrorIsDisplayed(){
        rocketmilesHomePage.clickSearchBtn();
        boolean locationError = rocketmilesHomePage.isLocationErrorDisplayed();
        assertThat(locationError, is(true));
    }

    //Check that the field is populated correctly with the user's selection
    @Test
    public void givenALocation_fieldDisplaysProperLocation(){
        rocketmilesHomePage.selectLocationValue("Toronto, ON");
        assertThat(driver.findElement(By.cssSelector(".location-search-container > input:nth-child(1)")).getText(),
                is("Toronto, ON"));
    }

    //Check that a message is displayed when no reward program is selected
    @Test
    public void givenNoRewardProgram_ErrorIsDisplayed(){
        rocketmilesHomePage.clickSearchBtn();
        boolean rewardsError = rocketmilesHomePage.isRewardProgramErrorDisplayed();
        assertThat(rewardsError, is(true));
    }

    @After
    public void tearDown() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }
}
