package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ClickTool;
import utils.ConfigLoader;
import utils.LeftMenu;
import utils.WaitTool;

import java.time.Duration;

public class BasePage {
    public static WebDriver driver;
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WaitTool.create(new WebDriverWait(driver, Duration.ofSeconds(10)));
        ClickTool.create(driver);
        LeftMenu.create(driver);
    }

    public static String getBaseUrl() {
        return ConfigLoader.getBaseUrl();
    }

    public static void tearDown() {
        //driver.quit();//
    }

    public static String getTitleText() {
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public static String getAlertText() {
        return driver.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).getText();
    }
}
