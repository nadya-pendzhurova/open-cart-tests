package admin;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.LoginPage;
import utils.WaitTool;

public class LoginTest extends BaseTest {
    @BeforeMethod
    public void setup() {
        super.setup();
        LoginPage.navigate();
    }

    @Test
    public void LoginIsSuccessfulWithCorrectUserNameAndPassword (){
        LoginPage.login("admin", "admin");

        var expectedTitle = "Dashboard";
        WaitTool.waitUntilElementWithTextIsVisible("h1", expectedTitle);

        var title = LoginPage.getTitleText();
        Assert.assertEquals(title, expectedTitle);
    }

    @Test
    public void LoginIsNotSuccessfulWithWrongPassword (){
        LoginPage.login("admin", "");

        var expectedAlert = "No match for Username and/or Password.";
        WaitTool.waitUntilElementWithTextIsVisible(".alert.alert-danger.alert-dismissible", expectedAlert);
        var alertError = LoginPage.getAlertText();
        Assert.assertEquals(alertError, expectedAlert);
    }

    @Test
    public void LoginIsNotSuccessfulWithWrongUsername (){
        LoginPage.login("", "admin");

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
