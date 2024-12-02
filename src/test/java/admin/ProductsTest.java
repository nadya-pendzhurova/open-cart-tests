package admin;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.ProductsPage;
import utils.LeftMenu;

public class ProductsTest extends BaseAdminTest {
    @Test
    public void ProductIsCreatedWithValidData () {
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

        Assert.assertEquals(actualProduct.Name, productName);
        Assert.assertEquals(actualProduct.Model, model);
    }
}


