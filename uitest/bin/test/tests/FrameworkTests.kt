package tests

import org.junit.jupiter.api.*
import org.openqa.selenium.remote.RemoteWebElement
import pages.EspressoPage
import pages.MainPage
import pages.Page
import pages.TempPage
import java.util.HashMap

@DisplayName("basic dreamlab uitest")
class FrameworkTests: BaseTest() {

    @Test
    @Tag("dreamlabBlackbox")
    @DisplayName("dreamlab uia2 test basic")
    fun TestCodeChange() {
        //var driver_android = AppiumDriverManager.getAndroidDriver()
        var driver_android = AppiumDriverManager.getEspressoDriver()

        var xmap: Page

        xmap = MainPage(driver_android)
        xmap.ClickNextButton()

        xmap = TempPage(driver_android)
        xmap.ClickBackButton()

        // execute mobile script
        val msScript: HashMap<String, Any> = HashMap()
        msScript["style"] = "Dark"
        //driver_ios.executeScript("mobile: setAppearance", msScript)

        Thread.sleep(5000)
        xmap.Shutdown()
    }

    @Test
    @Tag("testappEspresso")
    @DisplayName("test app espresso test")
    fun EspressoAppiumTest(){
        var driver_espresso = AppiumDriverManager.getEspressoDriver()
        var espressoDemo = EspressoPage(driver_espresso)
        espressoDemo.EspressoDemoTestApp()
        espressoDemo.Shutdown()
    }

    @Test
    @Tag("composeDemo")
    @DisplayName("test app compose element test")
    fun ComposeElementTest(){
        var driver_espresso = AppiumDriverManager.getEspressoDriver()
        var espressoDemo = EspressoPage(driver_espresso)
        espressoDemo.ComposeDemoTestApp()
        //espressoDemo.Shutdown()
    }

    @Test
    @Tag("dreamlabWhitebox")
    @DisplayName("dreamlab espresso test")
    fun DreamlabEsspressoTest(){
        var driver_espresso = AppiumDriverManager.getEspressoDriver()
        var espressoDemo = EspressoPage(driver_espresso)
        espressoDemo.EspressoDemoDreamlab()

        Thread.sleep(5000)
        espressoDemo.Shutdown()
    }

    @Test
    @Tag("emv")
    @DisplayName("poc emv test")
    fun EmvDemoTest(){
        var driver_android = AppiumDriverManager.getAndroidDriver()
        var emvDemoPage = MainPage(driver_android)
        emvDemoPage.ClickEmvButton()

        Thread.sleep(5000)
    }
}
