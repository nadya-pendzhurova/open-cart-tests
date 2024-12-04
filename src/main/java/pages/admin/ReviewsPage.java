package pages.admin;

import models.ProductModel;
import models.ReviewModel;
import org.openqa.selenium.By;
import pages.BasePage;
import utils.ClickTool;
import utils.WaitTool;

import java.util.Objects;

public class ReviewsPage extends BasePage {
    public static void navigateToCreateReviews() {
        driver.findElement(By.cssSelector("#content .page-header .btn-primary")).click();
        WaitTool.waitUntilElementWithTextIsVisible("div.card-header", "Add Review");
    }

    public static void createReview(String author, String productName, String comment, boolean hasRating) {
        var reviewAuthorElement = driver.findElement(By.id("input-author"));
        reviewAuthorElement.sendKeys(author);

        if (!Objects.equals(productName, "")) {
            var reviewProductElement = driver.findElement(By.id("input-product"));
            reviewProductElement.sendKeys(productName);

            WaitTool.waitForElementToBeRemoved("#autocomplete-loading");

            driver.findElement(By.cssSelector("#autocomplete-product li")).click();
        }

        var reviewTextElement = driver.findElement(By.id("input-text"));
        reviewTextElement.sendKeys(comment);

        if (hasRating) {
            driver.findElement(By.id("input-rating-3")).click();
        }

        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        WaitTool.waitForClassToBeRemoved("#content .page-header .btn-primary i", "fa-circle-notch");
    }

    public static ReviewModel searchReview(String productName) {
        var filterProductName = driver.findElement(By.id("input-product"));
        filterProductName.sendKeys(productName);

        var searchButton = driver.findElement(By.id("button-filter"));
        ClickTool.click(searchButton);

        WaitTool.waitUntilSearchCompletes("table tbody tr", 1);

        var result = new ReviewModel();
        result.productName = driver.findElement(By.cssSelector("table tbody tr td:nth-child(2)")).getText().split("\n")[0];
        result.author = driver.findElement(By.cssSelector("table tbody tr td:nth-child(3)")).getText();

        return result;
    }
}