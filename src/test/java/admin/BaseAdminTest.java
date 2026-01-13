package admin;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import pages.admin.LoginPage;
import utils.ConfigLoader;
import utils.WaitTool;

public class BaseAdminTest extends BaseTest {
    @BeforeMethod
    public void setup() {
        super.setup();
        LoginPage.navigate();
        LoginPage.login(ConfigLoader.getAdminUsername(), ConfigLoader.getAdminPassword());
        WaitTool.waitUntilElementWithTextIsVisible("h1", "Dashboard");
    }
}