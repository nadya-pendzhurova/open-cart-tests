package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickTool {
    private static WebDriver _driver;

    public static void create(WebDriver driver) {
        _driver = driver;
    }
    public static void click(WebElement element) {
        Actions actions = new Actions(_driver);
        actions.moveToElement(element).perform();
        element.click();
    }

    public static void clickTab(String href) {
        _driver.findElement(By.cssSelector("a[href='" + href + "']")).click();
    }
}