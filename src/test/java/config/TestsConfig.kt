package config

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.util.concurrent.TimeUnit

object TestsConfig {
  fun buildWebDriver(): WebDriver {
    val chromeOptions = ChromeOptions()

    chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")

    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sergey\\Projects\\chromedriver_89.exe")

    return ChromeDriver(chromeOptions)
  }

  fun openApp(webDriver: WebDriver) {
    webDriver.manage().window().maximize()

    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

    webDriver.get("http://localhost:4200")
  }

  fun closeApp(webDriver: WebDriver) {
    webDriver.quit()
  }
}
