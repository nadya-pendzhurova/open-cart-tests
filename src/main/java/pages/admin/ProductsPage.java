package pages.admin;

import models.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import utils.ClickTool;
import utils.WaitTool;

public class ProductsPage extends BasePage {

    public static void navigateToCreateProduct() {
        driver.findElement(By.cssSelector("#content .page-header .btn-primary")).click();
        WaitTool.waitUntilElementWithTextIsVisible("div.card-header", "Add Product");
    }

    public static void createProduct(String name, String tag, String model, String seoKeyword) {
        var productNameElement = driver.findElement(By.id("input-name-1"));
        productNameElement.sendKeys(name);

        var productTagElement = driver.findElement(By.id("input-meta-title-1"));
        productTagElement.sendKeys(tag);
        
        ClickTool.clickTab("#tab-data");

        var modelElement = driver.findElement(By.id("input-model"));
        modelElement.sendKeys(model);

        ClickTool.clickTab("#tab-seo");

        var keywordElement = driver.findElement(By.id("input-keyword-0-1"));
        keywordElement.sendKeys(seoKeyword);

        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    }

    public static ProductModel searchProduct(String name){
        var filterProductName = driver.findElement(By.id("input-name"));
        filterProductName.sendKeys(name);

        var searchButton = driver.findElement(By.id("button-filter"));
        ClickTool.click(searchButton);

        WaitTool.waitUntilSearchCompletes("table tbody tr", 1);

        var result = new ProductModel();
        result.Name = driver.findElement(By.cssSelector("table tbody tr td:nth-child(3)")).getText().split("\n")[0];
        result.Model = driver.findElement(By.cssSelector("table tbody tr td:nth-child(4)")).getText();

        return result;
    }
}
