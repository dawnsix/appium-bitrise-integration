package pages

import DreamlabConstants
import HelperFunctions
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.AndroidFindBy
import io.appium.java_client.pagefactory.iOSXCUITFindBy
import io.appium.java_client.serverevents.CustomEvent
import org.openqa.selenium.By
import org.openqa.selenium.json.Json
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.*
import kotlin.concurrent.thread


class EspressoPage(driver: AppiumDriver<*>): Page(driver) {

    //DL elements
    @AndroidFindBy(id = DreamlabConstants.PACKAGE + ":id/walkthroughNext")
    @iOSXCUITFindBy(accessibility = "btnTrailing")
    private var btnNext: MobileElement? = null

    @AndroidFindBy(id = DreamlabConstants.PACKAGE + ":id/walkthroughBack")
    private var btnBack: MobileElement? = null

    @AndroidFindBy(id = DreamlabConstants.PACKAGE + ":id/walkthroughSkip")
    private var btnSkipWalkthrough: MobileElement? = null

    private var privateUIElement = MobileBy.AccessibilityId("Graphics")

    private var composeField_username = MobileBy.AndroidViewTag("user")
    private var composeField_usernamedesc = MobileBy.AccessibilityId("blahx")
    private var composeField_password = MobileBy.AndroidViewTag("password")
    private var composeField_login = MobileBy.AndroidViewTag("loginbutton")

    public fun EspressoDemoTestApp() {
        var wait = WebDriverWait(driver, 30)
        wait.until(ExpectedConditions.elementToBeClickable(privateUIElement)).click()

        //graybox from here
        var list = wait.until(ExpectedConditions.presenceOfElementLocated(
            MobileBy.className("android.widget.FrameLayout")));

        val selector = Json().toJson(
            ImmutableMap.of(
                "name", "hasEntry",
                "args", ImmutableList.of("title", "PolyToPoly")
            )
        )

        var listItem = list.findElement<MobileElement>(By.id("android:id/list"))
            .findElement(MobileBy.androidDataMatcher(selector))

        driver.executeScript("mobile: flashElement",
            HelperFunctions().scriptFlash(listItem))

        listItem.click()
    }

    public fun EspressoDemoDreamlab() {
        var wait = WebDriverWait(driver, 30)
        wait.until(ExpectedConditions.elementToBeClickable(btnNext))

        driver.executeScript("mobile: flashElement",
            HelperFunctions().scriptFlash(btnNext as MobileElement))

        var dunno = wait.until(ExpectedConditions.presenceOfElementLocated(
            MobileBy.className("androidx.viewpager.widget.ViewPager"))) //"android.widget.FrameLayout")))

        driver.executeScript("mobile: flashElement",
            HelperFunctions().scriptFlash(dunno as MobileElement))
    }

    public fun ComposeDemoTestApp() {
        var wait = WebDriverWait(driver, 30)
        driver.setSetting("driver", "compose")

        wait.until(ExpectedConditions.visibilityOfElementLocated(composeField_username)).sendKeys("ABC")
        driver.findElement(composeField_password).sendKeys("ABC")

        driver.findElement(composeField_usernamedesc).clear()
        driver.findElement(composeField_usernamedesc).sendKeys("hi from semantics appdesc!")

        driver.findElement(composeField_login).click()

        var scriptArgsx: ImmutableMap<String, Any> = ImmutableMap.of(
            "target", "activity",
            "methods", Arrays.asList(ImmutableMap.of(
                    "name", "mToast",
                    "args", Arrays.asList(ImmutableMap.of(
                            "value", "XAB-235",
                            "type", "String"
                        )
                    )
                )
            )
        )

        //driver.executeScript("mobile: backdoor", scriptArgsx)
    }
}
