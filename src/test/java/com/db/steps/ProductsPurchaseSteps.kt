package com.db.steps

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class ProductsPurchaseSteps(private val webDriver: WebDriver) {
  init {
    PageFactory.initElements(webDriver, this)
  }

  fun rootIsDisplayed(): Boolean {
    return webDriver.findElement(By.id("products-purchase-root")).isDisplayed
  }

  fun getTotalPrice(): String {
    return webDriver
      .findElement(By.id("products-purchase-total-price"))
      .text
  }
}
