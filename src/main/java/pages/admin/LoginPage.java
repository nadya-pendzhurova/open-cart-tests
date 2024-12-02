package pages.admin;

import org.openqa.selenium.By;
import pages.BasePage;
import utils.ClickTool;
import utils.WaitTool;

public class LoginPage extends BasePage {
    public static void navigate() {
        driver.get(BasePage.getBaseUrl() + "admin");
    }

    public static void login(String username, String password) {
        var inputElement = driver.findElement(By.id("input-username"));
        inputElement.clear();
        inputElement.sendKeys(username);

        var passwordElement = driver.findElement(By.id("input-password"));
        passwordElement.clear();
        passwordElement.sendKeys(password);

        var submitElement = WaitTool.waitElementToBeClickable("button[type=submit");
        ClickTool.click(submitElement);
    }
}