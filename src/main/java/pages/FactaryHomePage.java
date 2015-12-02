package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.page;

public class FactaryHomePage {

    @FindBy(id = "flash")
    public SelenideElement flash;

    @FindBy(css = "a.button")
    public SelenideElement logoutButton;

    @Step
    public FactoryRegistrationPage logout() {
        logoutButton.click();
        return page(FactoryRegistrationPage.class);
    }
}
