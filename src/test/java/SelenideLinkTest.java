import com.codeborne.selenide.CollectionCondition;
import core.TestBase;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideLinkTest extends TestBase {

    @BeforeMethod
    public void setup () {
        open("");
    }

    @Test
    public void linkTest() throws IOException {
        List<String> listText = Helper.readAllLines(".\\src\\main\\resources\\linkListText");
        $$("ul>li>a").shouldHave(CollectionCondition.exactTexts(listText.toArray(new String[listText.size()])));
    }
    //если барать ul>li>a то в списке надо удалять текст ...
}
