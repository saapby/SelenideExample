import com.codeborne.selenide.testng.BrowserPerClass;
import com.codeborne.selenide.testng.ScreenShooter;
import core.TestBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.FactaryHomePage;
import pages.FactoryRegistrationPage;
import pages.StaticHomePage;
import pages.StaticRegistrationPage;
import ru.yandex.qatools.allure.annotations.Attachment;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners({BrowserPerClass.class, ScreenShooter.class})
public class SelenideExamplesTest extends TestBase {
    private FactaryHomePage homePage;
    private FactoryRegistrationPage registrationPage;



    @BeforeMethod
    public void setup () {
        registrationPage = open("login", FactoryRegistrationPage.class);
    }

    @Test
    public void simpleLoginTest() {
        $("#username").val("tomsmith");
        $("#password").val("SuperSecretPassword!");
        $("button[type='submit']").click();
        $("#flash").should(appear, cssClass("success"), text("You logged into a secure area!"));
    }

    @Test
    public void staticPageLoginTest() {
        StaticRegistrationPage.login("tomsmith", "SuperSecretPassword!");
        $(StaticHomePage.FLASH).should(appear, cssClass("success"), text("You logged into a secure area!"));
    }

    @Test
    public void staticPageLogoutTets() {
        StaticRegistrationPage.login("tomsmith", "SuperSecretPassword!");
        StaticHomePage.logout();
        $(StaticRegistrationPage.FLASH).should(appear, cssClass("success"), text("You logged out of the secure area!"));
        makeScreenshot();
    }

    @Test
    public void factoryPageLoginTest() {
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        homePage.flash.should(appear, cssClass("success"), text("You logged into a secure area!"));
        makeScreenshot();
    }

    @Test
    public void factoryPageLogoutTets() {
        homePage = registrationPage.login("tomsmith", "SuperSecretPassword!");
        registrationPage = homePage.logout();
        registrationPage.flash.should(appear, cssClass("success"), text("You logged out of the secure area!"));

        makeScreenshot();
    }

    @Attachment
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
