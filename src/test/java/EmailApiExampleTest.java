import core.TestBase;
import helpers.mail.Email;
import helpers.mail.GuerrillaMail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.open;
import java.util.List;

/**
 * Created by Admin on 04.12.2015.
 */
public class EmailApiExampleTest extends TestBase {

    private GuerrillaMail mailer;
    private String mail;

    @BeforeMethod
    public void setup() throws Exception {
        mailer = new GuerrillaMail();
        mail = mailer.getEmailAddress();
        open("forgot_password");
        $("#email").val(mail);
        $("#form_submit").click();
    }

    @Test
    public void forgotPasswordTest() throws Exception {
        com.codeborne.selenide.Configuration.timeout = 120000;
        com.codeborne.selenide.Configuration.pollingInterval = 6000;

        Wait().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                boolean result = false;
                try {
                    if (mailer.getEmailList().size() > 1) {
                        result = true;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return result;
            }
        });

        List<Email> bodyList = mailer.getEmailList();    //2
//        List<Email> bodyList = mailer.checkEmail();    //1
//        String body = mailer.checkEmail().get(0).getBody(); //1
            Assert.assertTrue(bodyList.get(1).getExcerpt().contains("http://the-internet.herokuapp.com/forgot_password"));   //2
//        Assert.assertTrue(body.contains(mail)); //1
    }
}