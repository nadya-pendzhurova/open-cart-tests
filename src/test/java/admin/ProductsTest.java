package admin;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.ProductsPage;
import utils.ClickTool;
import utils.LeftMenu;

public class ProductsTest extends BaseAdminTest {
    @Test
    public void ProductIsCreatedWithValidData() {
        LeftMenu.navigate("menu-catalog", 2, "Products");

        ProductsPage.navigateToCreateProduct();

        var productName = "qa-test-" + RandomStringUtils.randomAlphanumeric(6);
        var model = RandomStringUtils.randomAlphanumeric(6);
        ProductsPage.createProduct(productName,
                RandomStringUtils.randomAlphanumeric(3),
                model,
                RandomStringUtils.randomAlphanumeric(6));

        LeftMenu.navigate("menu-catalog", 2, "Products");

        var actualProduct = ProductsPage.searchProduct(productName);

        Assert.assertEquals(actualProduct.name, productName);
        Assert.assertEquals(actualProduct.model, model);
    }

    @Test
    public void ProductIsNotCreatedWithEmptyName() {
        LeftMenu.navigate("menu-catalog", 2, "Products");

        ProductsPage.navigateToCreateProduct();

        ProductsPage.createProduct(
                "",
                RandomStringUtils.randomAlphanumeric(3),
                RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(6));

        ClickTool.clickTab("#tab-general");

        var expectedValidationError = "Product Name must be greater than 1 and less than 255 characters!";
        var actualValidationError = ProductsPage.getValidationError("error-name-1");
        Assert.assertEquals(actualValidationError, expectedValidationError);
    }

    @Test
    public void ProductIsNotCreatedWithLongerName() {
        LeftMenu.navigate("menu-catalog", 2, "Products");

        ProductsPage.navigateToCreateProduct();

        ProductsPage.createProduct(
                RandomStringUtils.randomAlphanumeric(256),
                RandomStringUtils.randomAlphanumeric(3),
                RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(6));

        ClickTool.clickTab("#tab-general");

        var expectedValidationError = "Product Name must be greater than 1 and less than 255 characters!";
        var actualValidationError = ProductsPage.getValidationError("error-name-1");
        Assert.assertEquals(actualValidationError, expectedValidationError);
    }

    @Test
    public void ProductIsNotCreatedWithEmptyTag() {
        LeftMenu.navigate("menu-catalog", 2, "Products");

        ProductsPage.navigateToCreateProduct();

        ProductsPage.createProduct(
                "qa-test-" + RandomStringUtils.randomAlphanumeric(6),
                "",
                RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(6));

        ClickTool.clickTab("#tab-general");

        var expectedValidationError = "Meta Title must be greater than 1 and less than 255 characters!";
        var actualValidationError = ProductsPage.getValidationError("error-meta-title-1");
        Assert.assertEquals(actualValidationError, expectedValidationError);
    }

    @Test
    public void ProductIsNotCreatedWithLongerTag() {
        LeftMenu.navigate("menu-catalog", 2, "Products");

        ProductsPage.navigateToCreateProduct();

        ProductsPage.createProduct(
                "qa-test-" + RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(256),
                RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(6));

        ClickTool.clickTab("#tab-general");

        var expectedValidationError = "Meta Title must be greater than 1 and less than 255 characters!";
        var actualValidationError = ProductsPage.getValidationError("error-meta-title-1");
        Assert.assertEquals(actualValidationError, expectedValidationError);

    }

    @Test
    public void ProductIsNotCreatedWithEmptyModel() {
        LeftMenu.navigate("menu-catalog", 2, "Products");

        ProductsPage.navigateToCreateProduct();

        ProductsPage.createProduct(
                "qa-test-" + RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(3),
                "",
                RandomStringUtils.randomAlphanumeric(6));

        ClickTool.clickTab("#tab-data");

        var expectedValidationError = "Product Model must be greater than 1 and less than 64 characters!";
        var actualValidationError = ProductsPage.getValidationError("error-model");
        Assert.assertEquals(actualValidationError, expectedValidationError);
    }

    @Test
    public void ProductIsNotCreatedWithLongerModel() {
        LeftMenu.navigate("menu-catalog", 2, "Products");

        ProductsPage.navigateToCreateProduct();

        ProductsPage.createProduct(
                "qa-test-" + RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(3),
                RandomStringUtils.randomAlphanumeric(65),
                RandomStringUtils.randomAlphanumeric(6));

        ClickTool.clickTab("#tab-data");

        var expectedValidationError = "Product Model must be greater than 1 and less than 64 characters!";
        var actualValidationError = ProductsPage.getValidationError("error-model");
        Assert.assertEquals(actualValidationError, expectedValidationError);


    }

}