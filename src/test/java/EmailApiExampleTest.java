import core.TestBase;
import helpers.mail.Email;
import helpers.mail.GuerrillaMail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 04.12.2015.
 */
public class EmailApiExampleTest extends TestBase {

    @Test
    public void forgotPasswordTest() throws Exception {
        GuerrillaMail mailEngine = new GuerrillaMail();
        String email = mailEngine.getEmailAddress();
//        mailEngine.setEmailUser("osibbrqd");
//        email = mailEngine.getEmailAddress();
        System.out.println(email);
        String[] emailSplit = email.split("@");
        System.out.println(emailSplit[0].toString());
        open("forgot_password");
        $("#email").val(email);
        $("#form_submit").click();

        mailEngine.setEmailUser(emailSplit[0]);

        List<Email> emailList = new ArrayList<>();
        emailList = mailEngine.checkEmail();
        for (Email mail : emailList) {
            System.out.println(mail.getSubject());
            System.out.println(mail.getBody());
        }
        System.out.println(emailList.size());
    }
}
