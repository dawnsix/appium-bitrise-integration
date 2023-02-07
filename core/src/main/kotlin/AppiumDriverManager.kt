import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.net.URL

import java.util.concurrent.TimeUnit


object AppiumDriverManager {

    private var appiumLoc = "http://127.0.0.1:4723/wd/hub"
    private var appiumService: AppiumDriverLocalService? = null
    private var androidDriver: AndroidDriver<WebElement>? = null
    private var espressoDriver: AndroidDriver<WebElement>? = null
    private var iosDriver: IOSDriver<WebElement>? = null

    fun getAppiumService(): AppiumDriverLocalService {
        if (appiumService == null) {
            // bind remote or create local server
            appiumService = AppiumDriverLocalService.buildService(AppiumServiceBuilder()
                .withAppiumJS(File("/usr/local/bin/appium"))
                .withIPAddress("127.0.0.1"))
        }
        if (!appiumService!!.isRunning) {
            appiumService!!.start()
        }
        return appiumService!!
    }

    fun getAndroidDriver(): AndroidDriver<WebElement> {
        if (androidDriver == null) {
            val classpathRoot = File(System.getProperty("user.dir"))
            val appDir = File(classpathRoot, "../apps")
            //val app = File(appDir.canonicalPath, EmvConstants.APP_ANDROID)
            val app = File(ComposeConstants.APP_ANDROID)
            val capabilities = getAndroidCapabilities(app.absolutePath)
            androidDriver = AndroidDriver<WebElement>(URL(appiumLoc), capabilities)
                    .apply {
                        manage().timeouts().implicitlyWait(2500, TimeUnit.MILLISECONDS)
                    }
        }
        return androidDriver!!
    }

    fun getEspressoDriver(): AndroidDriver<WebElement> {
        if (espressoDriver == null) {

            //val classpathRoot = File(System.getProperty("user.dir"))
            //val appDir = File(classpathRoot, "../apps")
            //val app = File(appDir.canonicalPath, ComposeConstants.APP_ANDROID)
            //val capabilities = getEspressoCapabilities(app.absolutePath)

            //val capabilities = getEspressoCapabilities(ComposeConstants.APP_URI)

            val capabilities = getEspressoCapabilities(ComposeConstants.APP_ENV_VAR)

            espressoDriver = AndroidDriver<WebElement>(URL(appiumLoc), capabilities)
                .apply {
                    manage().timeouts().implicitlyWait(2500, TimeUnit.MILLISECONDS)
                }
        }
        return espressoDriver!!
    }

    fun getIosDriver(): IOSDriver<WebElement> {
        if (iosDriver == null) {
            val classpathRoot = File(System.getProperty("user.dir"))
            val appDir = File(classpathRoot, "../apps")
            val app = File(appDir.canonicalPath, DreamlabConstants.APP_IOS)
            val capabilities = getIosCapabilities(app.absolutePath)
            iosDriver = IOSDriver<WebElement>(URL(appiumLoc), capabilities)
                .apply {
                    manage().timeouts().implicitlyWait(2500, TimeUnit.MILLISECONDS)
                }
        }
        return iosDriver!!
    }

    fun isAndroidDriverExists() = this.androidDriver != null

    fun isEspressoDriverExists() = this.espressoDriver != null

    fun isIosDriverExists() = this.iosDriver != null

    private fun getAndroidCapabilities(absolutePath: String): DesiredCapabilities = DesiredCapabilities().apply {
        setCapability(MobileCapabilityType.DEVICE_NAME, "automation-test")
        setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        setCapability("appActivity", ComposeConstants.ACTIVITY)
        setCapability("appPackage", ComposeConstants.PACKAGE)
        setCapability("app", absolutePath)
        setCapability("fullReset", true)
    }

    private fun getEspressoCapabilities(absolutePath: String): DesiredCapabilities = DesiredCapabilities().apply {
        setCapability("automationName", "Espresso")
        setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator")
        setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        setCapability("appPackage", ComposeConstants.PACKAGE)
        setCapability("appActivity", ComposeConstants.ACTIVITY)
        //setCapability("appium:espressoBuildConfig", ComposeConstants.ESPRESSO_CONFIG)
        setCapability("appium:forceEspressoRebuild", true)
        setCapability("app", absolutePath)
        //setCapability("app", DreamlabConstants.APP_ANDROID)
        setCapability("fullReset", true)
    }

    private fun getIosCapabilities(absolutePath: String): DesiredCapabilities = DesiredCapabilities().apply {
        setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS")
        setCapability("automationName", "XCUITest")
        setCapability("deviceName", "iPhone 14 Pro Max")
        setCapability("app", absolutePath)
        setCapability("fullReset", true)
    }
}

object DreamlabConstants {
    const val APP_ANDROID = "app-local-debug.apk"
    //const val APP_ANDROID = "http://localhost:8088/app-local-debug.apk"
    const val APP_IOS = "[redacted]"
    const val PACKAGE = "[redacted]"
    const val ACTIVITY = "[redacted]"
    val ESPRESSO_CONFIG = File(File(System.getProperty("user.dir")), "../esconfig.json").canonicalPath
}

object TestConstants {
    const val PACKAGE = "io.appium.android.apis"
    const val ACTIVITY = "io.appium.android.apis.ApiDemos"
    const val APP_ANDROID = "ApiDemos-debug.apk"
}

object ComposeConstants{
    const val PACKAGE = "com.vad.compose_login"
    const val ACTIVITY = "com.vad.compose_login.MainActivity"
    const val APP_ANDROID = "app-compose.apk"
    const val APP_URI = "http://localhost:8088/app-compose.apk"
    val APP_ENV_VAR = System.getenv("BITRISE_APK_PATH")
}

