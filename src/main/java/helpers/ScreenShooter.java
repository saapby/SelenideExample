package helpers;

import com.codeborne.selenide.Screenshots;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;
import ru.yandex.qatools.allure.annotations.Attachment;
import java.io.File;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by Admin on 30.11.2015.
 */
public class ScreenShooter extends ExitCodeListener {

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        makeScreenshot();
    }


    @Attachment
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment
    public File makeScreenshotMethod() {
        return Screenshots.getScreenShotAsFile();
    }
}
