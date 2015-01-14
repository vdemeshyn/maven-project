package glue;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedCondition;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * Created by vadim on 13.01.2015.
 */
public class MyStepdefs extends Configurations {

    @Given("^I am on Google home page$")
    public void I_am_on_Google_home_page() throws Throwable {
        //If wanna use chromedriver for testing in chrome. Can be also implemented in global configuration class.
        // System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
         browser.get("https://www.google.com");
    }

    @When("^I search 'google translate' text$")
    public void I_search_google_translate_text() throws Throwable {
        browser.findElement(By.id("gbqfq")).sendKeys("google translate");
    }

    @And("^I open result page in other browser's session$")
    public void I_open_result_page_in_other_browser_s_session() throws Throwable {
        WebElement element1 = delay.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("id('rso')//li[2]/div/h3/a")));

     // getting URL from needed Google response
        String href = element1.getAttribute("href");
        WebDriver browser = new FirefoxDriver() {
        };
        browser.get(href);

     // switching to new browser window (new session, as requested)
        for (String winHandle : browser.getWindowHandles()) {
            browser.switchTo().window(winHandle);

        }


     //And I enter phrase 'luxoft test task' into text area field using it's id ,select right language to 'Polish' and click on 'Translate' button
        WebElement elem = browser.findElement(By.id("source"));
        elem.sendKeys("luxoft test task");

        browser.findElement(By.id("gt-tl-gms")).click();
        browser.findElement(By.id(":47")).click();

     //Implementing step - should ensure that result contains 'zadaniem testu'

        String elemCheck = browser.findElement(By.id("result_box")).getText();

        assert elemCheck.matches("zadaniem testu");
        browser.close();

    }
}
