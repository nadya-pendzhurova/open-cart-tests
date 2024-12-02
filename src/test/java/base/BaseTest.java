package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;


public class BaseTest {
    @BeforeMethod
    public void setup() {
        BasePage.setup();
    }

    @AfterMethod
    public void tearDown() {
        BasePage.tearDown();
    }
}
