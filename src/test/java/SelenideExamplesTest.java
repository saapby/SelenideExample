import com.codeborne.selenide.testng.BrowserPerClass;
import core.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.FactaryHomePage;
import pages.FactoryRegistrationPage;
import pages.StaticHomePage;
import pages.StaticRegistrationPage;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;

@Listeners({BrowserPerClass.class, helpers.ScreenShooter.class})
public class SelenideExamplesTest extends TestBase {
    private FactaryHomePage homePage;
    private FactoryRegistrationPage registrationPage;

    @BeforeMethod
    public void setup () {
        registrationPage = open("login", FactoryRegistrationPage.class);
    }

    @Issue("Some-issue-id-27")
    @TestCaseId("Test1")
    @Test
    public void simpleLoginTest() {
        $("#username").val("tomsmith"); //tomsmith was make down
        $("#password").val("SuperSecretPassword!");
        $("button[type='submit']").click();
        $("#flash").should(appear, cssClass("success"), text("You logged into a secure area!"));
    }

    @TestCaseId("Test2")
    @Test
    public void staticPageLoginTest() {
        StaticRegistrationPage.login("tomsmith", "SuperSecretPassword!");
        $(StaticHomePage.FLASH).should(appear, cssClass("success"), text("You logged into a secure area!"));
    }

    @TestCaseId("Test3")
    @Test
    public void staticPageLogoutTets() {
        StaticRegistrationPage.login("tomsmith", "SuperSecretPassword!");
        StaticHomePage.logout();
        $(StaticRegistrationPage.FLASH).should(appear, cssClass("success"), text("You logged out of the secure area!"));
    }

    @TestCaseId("Test4")
    @Test
    public void factoryPageLoginTest() {
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        homePage.flash.should(appear, cssClass("success"), text("You logged into a secure area!"));
    }

    @TestCaseId("Test5")
    @Test
    public void factoryPageLogoutTets() {
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        registrationPage = homePage.logout();
        registrationPage.flash.should(appear, cssClass("success"), text("You logged out of the secure area!"));
    }
}


// mvn -Dbrowser=firefox clean test site
// mvn -Dallure.tests.management.pattern=http://tms.company.com/tests/%s