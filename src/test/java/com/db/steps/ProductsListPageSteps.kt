package com.db.steps

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class ProductsListPageSteps(private val webDriver: WebDriver) {
  init {
    PageFactory.initElements(webDriver, this)
  }

  fun rootIsDisplayed(): Boolean {
    return webDriver.findElement(By.id("products-list-root")).isDisplayed
  }

  fun productIsDisplayed(id: String): Boolean {
    return webDriver
      .findElement(By.id("products-list-item-$id"))
      .isDisplayed
  }

  fun checkout() {
    webDriver
      .findElement(By.id("products-list-checkout"))
      .click()
  }

  fun addProduct(id: String) {
    webDriver
      .findElement(By.id("products-list-item-$id"))
      .findElement(By.id("product-add-product"))
      .click()
  }

  fun removeProduct(id: String) {
    webDriver
      .findElement(By.id("products-list-item-$id"))
      .findElement(By.id("product-remove-product"))
      .click()
  }

  fun getProductAmount(id: String): String {
    return webDriver
      .findElement(By.id("products-list-item-$id"))
      .findElement(By.id("product-amount"))
      .text
  }
}
