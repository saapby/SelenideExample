package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.page;

public class FactoryRegistrationPage {
    @FindBy(id = "username")
    public SelenideElement userName;

    @FindBy(id = "password")
    public SelenideElement password;

    @FindBy(css = "button[type='submit']")
    public SelenideElement loginButton;

    @FindBy(id = "flash")
    public SelenideElement flash;


    @Step("Login {0}, password {1}")
    public FactaryHomePage login(String user, String pass) {
        userName.val(user);
        password.val(pass);
        loginButton.click();
        return page(FactaryHomePage.class);
    }



}
