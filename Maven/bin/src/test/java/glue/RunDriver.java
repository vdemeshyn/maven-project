package glue;

/**
 * Created by vadim on 13.01.2015.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diesel on 9/12/14.
 */
public interface RunDriver extends WebDriver {

    // Please use code below if you want to use Chrome as testing browser.
    //System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
    public WebDriver browser = new FirefoxDriver();

    static final int DEFAULT_WAIT = 60;

    @Override
    void close();

    void deleteAllCookies();

    WebElement waitForVisible(By by);
    List<WebElement> waitForVisible(By by, boolean isList);
    boolean hasFlashEnabled();


    //TODO: might be better to do this in another way
    class RunDriverDefaultImp {
        private RunDriverDefaultImp() {

        }

        public static WebElement waitForVisible(RunDriver self, By by) {
            WebElement element = null;

            WebDriverWait wait = new WebDriverWait(self, DEFAULT_WAIT);
            try {
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } catch (StaleElementReferenceException ex) {
                System.err.println(ex.getMessage());
                return waitForVisible(self, by);
            }

            return element;
        }

        public static List<WebElement> waitForVisible(RunDriver self, By by, boolean isList) {
            WebDriverWait wait = new WebDriverWait(self, DEFAULT_WAIT);
            List<WebElement> elements = new ArrayList<>();
            try {
                elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            } catch (StaleElementReferenceException ex) {
                System.err.println(ex.getMessage());
                return waitForVisible(self, by, true);
            }

            return elements;
        }
    }
}