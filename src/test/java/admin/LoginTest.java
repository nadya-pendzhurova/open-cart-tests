package admin;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.LoginPage;
import utils.ConfigLoader;
import utils.WaitTool;

public class LoginTest extends BaseTest {
    @BeforeMethod
    public void setup() {
        super.setup();
        LoginPage.navigate();
    }

    @Test
    public void LoginIsSuccessfulWithCorrectUserNameAndPassword (){
        LoginPage.login(ConfigLoader.getAdminUsername(), ConfigLoader.getAdminPassword());

        var expectedTitle = "Dashboard";
        WaitTool.waitUntilElementWithTextIsVisible("h1", expectedTitle);

        var title = LoginPage.getTitleText();
        Assert.assertEquals(title, expectedTitle);
    }

    @Test
    public void LoginIsNotSuccessfulWithWrongPassword (){
        LoginPage.login(ConfigLoader.getAdminUsername(), "");

        var expectedAlert = "No match for Username and/or Password.";
        WaitTool.waitUntilElementWithTextIsVisible(".alert.alert-danger.alert-dismissible", expectedAlert);
        var alertError = LoginPage.getAlertText();
        Assert.assertEquals(alertError, expectedAlert);
    }

    @Test
    public void LoginIsNotSuccessfulWithWrongUsername (){
        LoginPage.login("", ConfigLoader.getAdminPassword());

        var expectedAlert = "No match for Username and/or Password.";
        WaitTool.waitUntilElementWithTextIsVisible(".alert.alert-danger.alert-dismissible", expectedAlert);
        var alertError = LoginPage.getAlertText();
        Assert.assertEquals(alertError, expectedAlert);
    }

    @Test
    public void LoginIsNotSuccessfulWithWrongUsernameAndPassword (){
        LoginPage.login("", "");

        var expectedAlert = "No match for Username and/or Password.";
        WaitTool.waitUntilElementWithTextIsVisible(".alert.alert-danger.alert-dismissible", expectedAlert);
        var alertError = LoginPage.getAlertText();
        Assert.assertEquals(alertError, expectedAlert);
    }
}
