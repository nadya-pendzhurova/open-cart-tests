package admin;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.CategoriesPage;
import pages.admin.ProductsPage;
import pages.admin.ReviewsPage;
import utils.LeftMenu;

    public class ReviewsTest extends BaseAdminTest {
        @Test
        public void ReviewsIsCreatedWithValidData() {
            LeftMenu.navigate("menu-catalog", 2, "Products");

            ProductsPage.navigateToCreateProduct();

            var productName = "qa-test-" + RandomStringUtils.randomAlphanumeric(6);
            var model = RandomStringUtils.randomAlphanumeric(6);
            ProductsPage.createProduct(productName,
                    RandomStringUtils.randomAlphanumeric(3),
                    model,
                    RandomStringUtils.randomAlphanumeric(6));

            LeftMenu.navigate("menu-catalog", 9, "Reviews");

            ReviewsPage.navigateToCreateReviews();

            var author = RandomStringUtils.randomAlphanumeric(6);

            ReviewsPage.createReview(
                    author,
                    productName,
                    RandomStringUtils.randomAlphanumeric(100),
                    true);

            LeftMenu.navigate("menu-catalog", 9, "Reviews");

            var actualReview = ReviewsPage.searchReview(productName);

            Assert.assertEquals(actualReview.productName, productName);
            Assert.assertEquals(actualReview.author, author);
        }

        @Test
        public void ReviewsIsCreatedWithEmptyAuthor() {
            LeftMenu.navigate("menu-catalog", 2, "Products");

            ProductsPage.navigateToCreateProduct();

            var productName = "qa-test-" + RandomStringUtils.randomAlphanumeric(6);
            var model = RandomStringUtils.randomAlphanumeric(6);
            ProductsPage.createProduct(productName,
                    RandomStringUtils.randomAlphanumeric(3),
                    model,
                    RandomStringUtils.randomAlphanumeric(6));

            LeftMenu.navigate("menu-catalog", 9, "Reviews");

            ReviewsPage.navigateToCreateReviews();

            ReviewsPage.createReview(
                    "",
                    productName,
                    RandomStringUtils.randomAlphanumeric(100),
                    true);

            var expectedValidationError = "Author must be between 3 and 64 characters!";
            var actualValidationError = CategoriesPage.getValidationError("error-author");
            Assert.assertEquals(actualValidationError, expectedValidationError);
        }

        @Test
        public void ReviewsIsCreatedWithLongerAuthor() {

            LeftMenu.navigate("menu-catalog", 2, "Products");

            ProductsPage.navigateToCreateProduct();

            var productName = "qa-test-" + RandomStringUtils.randomAlphanumeric(6);
            var model = RandomStringUtils.randomAlphanumeric(6);
            ProductsPage.createProduct(productName,
                    RandomStringUtils.randomAlphanumeric(3),
                    model,
                    RandomStringUtils.randomAlphanumeric(6));

            LeftMenu.navigate("menu-catalog", 9, "Reviews");

            ReviewsPage.navigateToCreateReviews();

            var author = RandomStringUtils.randomAlphanumeric(65);

            ReviewsPage.createReview(
                    author,
                    productName,
                    RandomStringUtils.randomAlphanumeric(100),
                    true);


            var expectedValidationError = "Author must be between 3 and 64 characters!";
            var actualValidationError = CategoriesPage.getValidationError("error-author");
            Assert.assertEquals(actualValidationError, expectedValidationError);
        }

        @Test
        public void ReviewsIsCreatedWithEmptyProduct() {
            LeftMenu.navigate("menu-catalog", 9, "Reviews");
            ReviewsPage.navigateToCreateReviews();

            var author = RandomStringUtils.randomAlphanumeric(6);

            ReviewsPage.createReview(
                    author,
                    "",
                    RandomStringUtils.randomAlphanumeric(100),
                    true);

            var expectedValidationError = "Product required!";
            var actualValidationError = CategoriesPage.getValidationError("error-product");
            Assert.assertEquals(actualValidationError, expectedValidationError);

        }

        @Test

        public void ReviewsIsCreatedWithEmptyText() {
            LeftMenu.navigate("menu-catalog", 2, "Products");

            ProductsPage.navigateToCreateProduct();

            var productName = "qa-test-" + RandomStringUtils.randomAlphanumeric(6);
            var model = RandomStringUtils.randomAlphanumeric(6);
            ProductsPage.createProduct(productName,
                    RandomStringUtils.randomAlphanumeric(3),
                    model,
                    RandomStringUtils.randomAlphanumeric(6));

            LeftMenu.navigate("menu-catalog", 9, "Reviews");

            ReviewsPage.navigateToCreateReviews();

            var author = RandomStringUtils.randomAlphanumeric(6);
            ReviewsPage.createReview(
                    author,
                    productName,
                    "",
                    true);

            var expectedValidationError = "Review Text must be at least 1 character!";
            var actualValidationError = CategoriesPage.getValidationError("error-text");
            Assert.assertEquals(actualValidationError, expectedValidationError);

        }

        @Test
        public void ReviewsIsCreatedWithoutRating() {
            LeftMenu.navigate("menu-catalog", 2, "Products");

            ProductsPage.navigateToCreateProduct();

            var productName = "qa-test-" + RandomStringUtils.randomAlphanumeric(6);
            var model = RandomStringUtils.randomAlphanumeric(6);
            ProductsPage.createProduct(productName,
                    RandomStringUtils.randomAlphanumeric(3),
                    model,
                    RandomStringUtils.randomAlphanumeric(6));

            LeftMenu.navigate("menu-catalog", 9, "Reviews");

            ReviewsPage.navigateToCreateReviews();

            var author = RandomStringUtils.randomAlphanumeric(6);
            ReviewsPage.createReview(
                    author,
                    productName,
                    RandomStringUtils.randomAlphanumeric(101),
                    false);

            var expectedValidationError = "Review rating required!";
            var actualValidationError = CategoriesPage.getValidationError("error-rating");
            Assert.assertEquals(actualValidationError, expectedValidationError);

        }
    }