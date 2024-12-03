package pages.admin;

import org.openqa.selenium.By;
import pages.BasePage;
import utils.ClickTool;
import utils.WaitTool;

public class CategoriesPage extends BasePage {
    public static void navigateToCreateCategory() {
        driver.findElement(By.cssSelector("#content .page-header .btn-primary")).click();
        WaitTool.waitUntilElementWithTextIsVisible("div.card-header", "Add Category");
    }

    public static void createCategory(String name, String tag, String seoKeyword) {
        var nameElement = driver.findElement(By.id("input-name-1"));
        nameElement.sendKeys(name);

        var tagElement = driver.findElement(By.id("input-meta-title-1"));
        tagElement.sendKeys(tag);

        ClickTool.clickTab("#tab-seo");

        var keywordElement = driver.findElement(By.id("input-keyword-0-1"));
        keywordElement.sendKeys(seoKeyword);

        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        WaitTool.waitForClassToBeRemoved("#content .page-header .btn-primary i", "fa-circle-notch");
    }

    public static String searchCategory(String name){
        var filterCategoryName = driver.findElement(By.id("input-name"));
        filterCategoryName.sendKeys(name);

        var searchButton = driver.findElement(By.id("button-filter"));
        ClickTool.click(searchButton);

        WaitTool.waitUntilSearchCompletes("table tbody tr", 1);

        return driver.findElement(By.cssSelector("table tbody tr td:nth-child(2)")).getText().split("\n")[0];
    }
}
