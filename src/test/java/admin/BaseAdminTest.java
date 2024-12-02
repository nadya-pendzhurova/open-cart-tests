package admin;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.admin.LoginPage;
import utils.WaitTool;

public class BaseAdminTest extends BaseTest {
    @BeforeMethod
    public void setup() {
        super.setup();
        LoginPage.navigate();
        LoginPage.login("admin", "admin");
        WaitTool.waitUntilElementWithTextIsVisible("h1", "Dashboard");
    }
}
