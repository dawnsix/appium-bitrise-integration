import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.RemoteWebElement
import java.util.HashMap

class HelperFunctions {

    public fun scriptFlash(elmt: MobileElement): HashMap<String, Any> {
        val scriptArgs: HashMap<String, Any> = HashMap()
        scriptArgs["element"] = (elmt as RemoteWebElement).id
        scriptArgs["durationMillis"] = 50
        scriptArgs["repeatCount"] = 20

        return scriptArgs
    }
}