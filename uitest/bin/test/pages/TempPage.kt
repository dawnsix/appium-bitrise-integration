package pages

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.*
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class TempPage(driver: AppiumDriver<*>): Page(driver) {
     @AndroidFindBy(id = DreamlabConstants.PACKAGE + ":id/walkthroughBack")
    private var btnBack: MobileElement? = null

    public fun ClickBackButton() {
        var wait = WebDriverWait(driver, 30)
        wait.until(ExpectedConditions.elementToBeClickable(btnBack)).click()
    }
}