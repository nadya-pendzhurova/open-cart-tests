package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitTool {
    private static WebDriverWait _wait;

    public static void create(WebDriverWait wait) {
        _wait = wait;
    }

    public static WebElement waitElementToBeClickable(String selector) {
        return _wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
    }

    public static void waitUntilElementWithTextIsVisible(String selector, String text) {
        _wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                for (WebElement element : driver.findElements(By.cssSelector(selector))) {
                    if (text.trim().equals(element.getText().trim())) {
                        return element;
                    }
                }
                return null;
            }
        });
    }

    public static void waitUntilSearchCompletes(String selector, int numberOfResults) {
        _wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                var rows = driver.findElements(By.cssSelector(selector));
                return rows.size() == numberOfResults;
            }
        });
    }

    public static void waitForClassToBeRemoved(String selector, String className) {
        _wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                var element = driver.findElement(By.cssSelector(selector));
                String classes = element.getAttribute("class");
                return classes == null || !classes.contains(className);
            }
        });
    }

    public static void waitForElementToBeRemoved(String selector) {
        _wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                var result = driver.findElements(By.cssSelector(selector)).isEmpty();
                return result;
            }
        });
    }

    public static void wait(int seconds) {
        try {
            // Workaround for 429 Error
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}