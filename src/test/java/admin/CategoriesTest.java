package admin;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.CategoriesPage;
import pages.admin.ProductsPage;
import utils.ClickTool;
import utils.LeftMenu;

public class CategoriesTest extends BaseAdminTest {
    @Test
    public void CategoryIsCreatedWithValidData() {
        LeftMenu.navigate("menu-catalog", 1, "Categories");

        CategoriesPage.navigateToCreateCategory();

        var categoryName = "qa-test-" + RandomStringUtils.randomAlphanumeric(6);
        CategoriesPage.createCategory(categoryName,
                RandomStringUtils.randomAlphanumeric(3),
                RandomStringUtils.randomAlphanumeric(6));

        LeftMenu.navigate("menu-catalog", 1, "Categories");

        var actual = CategoriesPage.searchCategory(categoryName);

        Assert.assertEquals(actual, categoryName);
    }

    @Test
    public void CategoryIsCreatedWithEmptyName() {
        LeftMenu.navigate("menu-catalog", 1, "Categories");

        CategoriesPage.navigateToCreateCategory();

        CategoriesPage.createCategory(
                "",
                RandomStringUtils.randomAlphanumeric(3),
                RandomStringUtils.randomAlphanumeric(6));

        ClickTool.clickTab("#tab-general");

        var expectedValidationError = "Category Name must be between 1 and 255 characters!";
        var actualValidationError = CategoriesPage.getValidationError("error-name-1");
        Assert.assertEquals(actualValidationError, expectedValidationError);
    }

    @Test
    public void CategoryIsNotCreatedWithLongerName() {
        LeftMenu.navigate("menu-catalog", 1, "Categories");

        CategoriesPage.navigateToCreateCategory();

        CategoriesPage.createCategory(
                RandomStringUtils.randomAlphanumeric(256),
                RandomStringUtils.randomAlphanumeric(3),
                RandomStringUtils.randomAlphanumeric(6));

        ClickTool.clickTab("#tab-general");

        var expectedValidationError = "Category Name must be between 1 and 255 characters!";
        var actualValidationError = CategoriesPage.getValidationError("error-name-1");
        Assert.assertEquals(actualValidationError, expectedValidationError);
    }

    @Test
    public void CategoryIsNotCreatedWithEmptyTag() {
        LeftMenu.navigate("menu-catalog", 1, "Categories");

        CategoriesPage.navigateToCreateCategory();

        CategoriesPage.createCategory(
                "qa-test-" + RandomStringUtils.randomAlphanumeric(6),
                "",
                RandomStringUtils.randomAlphanumeric(6));

        ClickTool.clickTab("#tab-general");

        var expectedValidationError = "Meta Title must be greater than 1 and less than 255 characters!";
        var actualValidationError = ProductsPage.getValidationError("error-meta-title-1");
        Assert.assertEquals(actualValidationError, expectedValidationError);
    }

    @Test
    public void CategoryIsNotCreatedWithLongerTag() {
        LeftMenu.navigate("menu-catalog", 1, "Categories");

        CategoriesPage.navigateToCreateCategory();

        CategoriesPage.createCategory(
                "qa-test-" + RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(65),
                RandomStringUtils.randomAlphanumeric(6));



        ClickTool.clickTab("#tab-seo");

        var expectedValidationError = "Meta Title must be greater than 1 and less than 255 characters!";
        var actualValidationError = ProductsPage.getValidationError("error-meta-title-1");
        Assert.assertEquals(actualValidationError, expectedValidationError);

    }
    @Test
    public void  CategoryIsNotCreatedWithEmptySeo() {

        LeftMenu.navigate("menu-catalog", 1, "Categories");

        CategoriesPage.navigateToCreateCategory();

        CategoriesPage.createCategory(
                "qa-test-" + RandomStringUtils.randomAlphanumeric(6),
                RandomStringUtils.randomAlphanumeric(3),
                (""));

        ClickTool.clickTab("#tab-seo");

        var expectedValidationError = "SEO URL must be between 1 and 64 characters!";
        var actualValidationError = ProductsPage.getValidationError("error-keyword-0-1");
        Assert.assertEquals(actualValidationError, expectedValidationError);
    }
}
