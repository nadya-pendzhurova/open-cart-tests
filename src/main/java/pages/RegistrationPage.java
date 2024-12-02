package pages;

import org.openqa.selenium.By;
import utils.ClickTool;
import utils.WaitTool;

public class RegistrationPage extends BasePage { public static Object getErrorText;

    public static void navigate() {
        driver.get(BasePage.getBaseUrl() + "index.php?route=account/register&language=en-gb");
    }

    public static void register(String firstName, String lastName, String email, String password, boolean isAgreed) {
        var firstNameElement = driver.findElement(By.id("input-firstname"));
        firstNameElement.sendKeys(firstName);

        var lastNameElement = driver.findElement(By.id("input-lastname"));
        lastNameElement.sendKeys(lastName);

        var emailElement = driver.findElement(By.id("input-email"));
        emailElement.sendKeys(email);

        var passwordElement = driver.findElement(By.id("input-password"));
        passwordElement.sendKeys(password);

        if (isAgreed) {
            var agreeElement = driver.findElement(By.cssSelector("input[name='agree']"));
            ClickTool.click(agreeElement);
        }

        var submitElement = driver.findElement(By.cssSelector("#form-register button[type='submit']"));
        ClickTool.click(submitElement);
    }

    public static String getErrorText(String id) {
        var errorElement =  driver.findElement(By.id(id));
        return errorElement.getText();
    }
}