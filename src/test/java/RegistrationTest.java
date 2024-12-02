import base.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.WaitTool;

public class RegistrationTest extends BaseTest {
    private static final String correctPassword = "123456";

    @BeforeMethod
    public void setup() {
        super.setup();
        RegistrationPage.navigate();
    }

    @Test
     public void RegistrationTestIsCorrect  () {
        RegistrationPage.register("Test", "Test", generateEmailAddress(), correctPassword, true);

        var expectedTitle = "Your Account Has Been Created!";
        WaitTool.waitUntilElementWithTextIsVisible("h1", expectedTitle);
        var actualTitle = RegistrationPage.getTitleText();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void RegistrationTestIsNotSuccessfulWithEmptyFirstName(){
        RegistrationPage.register("", "Test", generateEmailAddress(), correctPassword, true);

        var expected = "First Name must be between 1 and 32 characters!";
        WaitTool.waitUntilElementWithTextIsVisible("#error-firstname", expected);
        var actual = RegistrationPage.getErrorText("error-firstname");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void RegistrationTestIsNotSuccessfulWithLongerThan32SymbolsFirstName(){
        RegistrationPage.register(RandomStringUtils.randomAlphanumeric(33), "Test", generateEmailAddress(), correctPassword, true);

        var expected = "First Name must be between 1 and 32 characters!";
        WaitTool.waitUntilElementWithTextIsVisible("#error-firstname", expected);
        var actual = RegistrationPage.getErrorText("error-firstname");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void RegistrationTestIsNotSuccessfulWithEmptyLastName(){
        RegistrationPage.register("Test", "", generateEmailAddress(), correctPassword, true);

        var expected = "Last Name must be between 1 and 32 characters!";
        WaitTool.waitUntilElementWithTextIsVisible("#error-lastname", expected);
        var actual = RegistrationPage.getErrorText("error-lastname");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void RegistrationTestIsNotSuccessfulWithLongerThan32SymbolsLastName() {
        RegistrationPage.register("Test", RandomStringUtils.randomAlphanumeric(33), generateEmailAddress(), correctPassword, true);

        var expected = "Last Name must be between 1 and 32 characters!";
        WaitTool.waitUntilElementWithTextIsVisible("#error-lastname", expected);
        var actual = RegistrationPage.getErrorText("error-lastname");
        Assert.assertEquals(actual, expected);

    }
    @Test
    public void RegistrationTestIsNotSuccessfulWithInvalidEmail() {
        RegistrationPage.register("Test", "Test", "Test@test", correctPassword, true);

        var expected = "E-Mail Address does not appear to be valid!";
        WaitTool.waitUntilElementWithTextIsVisible("#error-email", expected);
        var actual = RegistrationPage.getErrorText("error-email");
        Assert.assertEquals(actual, expected);

    }
    @Test
    public void RegistrationTestIsNotSuccessfulWithEmptyEmail() {
        RegistrationPage.register("Test", "Test","", correctPassword, true);

        var expected = "E-Mail Address does not appear to be valid!";
        WaitTool.waitUntilElementWithTextIsVisible("#error-email", expected);
        var actual = RegistrationPage.getErrorText("error-email");
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void RegistrationTestIsNotSuccessfulWithShortPassword() {
        RegistrationPage.register("Test", "Test",generateEmailAddress(), "12345", true);

        var expected = "Password must be between 6 and 20 characters!";
        WaitTool.waitUntilElementWithTextIsVisible("#error-password", expected);
        var actual = RegistrationPage.getErrorText("error-password");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void RegistrationTestIsNotSuccessfulWithLongerPassword() {
        RegistrationPage.register("Test", "Test",generateEmailAddress(), RandomStringUtils.randomAlphanumeric(21), true);

        var expected = "Password must be between 6 and 20 characters!";
        WaitTool.waitUntilElementWithTextIsVisible("#error-password", expected);
        var actual = RegistrationPage.getErrorText("error-password");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void RegistrationTestIsNotSuccessfulWithNotClickButtonPrivacyPolicy() {
        RegistrationPage.register("Test", "Test", generateEmailAddress(), correctPassword, false);

        var expectedAlert = "Warning: You must agree to the Privacy Policy!";
        WaitTool.waitUntilElementWithTextIsVisible(".alert.alert-danger.alert-dismissible", expectedAlert);
        var alertError = RegistrationPage.getAlertText();
        Assert.assertEquals(alertError, expectedAlert);
    }

    private String generateEmailAddress() {
        var prefix = RandomStringUtils.randomAlphanumeric(7);
        var suffix = RandomStringUtils.randomAlphabetic(5);
        return prefix + "@" + suffix + ".com";
    }
}
