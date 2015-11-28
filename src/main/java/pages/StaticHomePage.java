package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;

public class StaticHomePage {
    public final static By FLASH = get("homepage.flash");
    public final static By LOGOUT_BUTTON = get("homepage.logout.button");

    public static void logout() {
        $(LOGOUT_BUTTON).click();
    }
}
