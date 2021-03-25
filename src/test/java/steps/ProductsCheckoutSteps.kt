package steps

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class ProductsCheckoutSteps(private val webDriver: WebDriver) {
  init {
    PageFactory.initElements(webDriver, this)
  }

  fun rootIsDisplayed(): Boolean {
    return webDriver.findElement(By.id("products-checkout-root")).isDisplayed
  }

  fun purchase() {
    webDriver
      .findElement(By.id("products-checkout-purchase"))
      .click()
  }
}
