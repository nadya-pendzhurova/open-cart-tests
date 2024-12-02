package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeftMenu {
    private static WebDriver _driver;

    public static void create(WebDriver driver) {
        _driver = driver;
    }

    public static void navigate(String menuId, int subMenuIndex, String title) {
        _driver.findElement(By.id(menuId)).click();
        _driver.findElement(By.cssSelector("#" + menuId + " li:nth-child(" + subMenuIndex + ")")).click();
        WaitTool.waitUntilElementWithTextIsVisible("h1", title);
    }
}