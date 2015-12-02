package pages;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;

public class StaticRegistrationPage {
    public final static By USER_NAME_FIELDS = get("reg.username.field");
    public final static By PASSWORD_FIELDS = get("reg.password.field");
    public final static By LOGIN_BUTTON = get("reg.button");
    public final static By FLASH = get("reg.flash");

    @Step("Login {0}, password {1}")
    public static void login (String user, String pass) {
        $(USER_NAME_FIELDS).val(user);
        $(PASSWORD_FIELDS).val(pass);
        $(LOGIN_BUTTON).click();
    }
}
