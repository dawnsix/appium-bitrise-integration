package pages

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.*
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class MainPage(driver: AppiumDriver<*>): Page(driver) {

    @AndroidFindBy(id = DreamlabConstants.PACKAGE + ":id/walkthroughNext")
    @iOSXCUITFindBy(accessibility = "btnTrailing")
    private var btnNext: MobileElement? = null

    @AndroidFindBy(id = DreamlabConstants.PACKAGE + ":id/back")
    private var btnBack: MobileElement? = null

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.compose.ui.platform.e1/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")
    private var EMV_btnNext: MobileElement? = null

    public fun ClickNextButton() {
        var wait = WebDriverWait(driver, 30)
        wait.until(ExpectedConditions.elementToBeClickable(btnNext)).click()
    }

    public fun ClickEmvButton() {
        var wait = WebDriverWait(driver, 30)
        wait.until(ExpectedConditions.elementToBeClickable(EMV_btnNext)).click()
    }
}