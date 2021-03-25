package com.db.component_tests

import com.db.config.BackendMocksConfig
import com.db.config.TestsConfig
import com.db.data.Product
import com.db.steps.ProductsCheckoutSteps
import com.db.steps.ProductsListPageSteps
import com.db.steps.ProductsPurchaseSteps
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.*
import org.openqa.selenium.WebDriver

class ComponentTests {
  private lateinit var webDriver: WebDriver

  private lateinit var wireMockServer: WireMockServer

  private lateinit var productsListPageSteps: ProductsListPageSteps
  private lateinit var productsCheckoutPageSteps: ProductsCheckoutSteps
  private lateinit var productsPurchasePageSteps: ProductsPurchaseSteps

  @Rule @JvmField
  val wireMockRule = WireMockRule(
    WireMockConfiguration.wireMockConfig().port(8080)
  )

  @Before
  fun before() {
    webDriver = TestsConfig.buildWebDriver()

    productsListPageSteps = ProductsListPageSteps(webDriver = webDriver)
    productsCheckoutPageSteps = ProductsCheckoutSteps(webDriver = webDriver)
    productsPurchasePageSteps = ProductsPurchaseSteps(webDriver = webDriver)

    BackendMocksConfig.startMock(
      wireMockRule = wireMockRule,
      products = listOf(
        Product(
          id = "1",
          name = "product-1",
          description = "description-1",
          priceInCents = 32
        ),

        Product(
          id = "2",
          name = "product-2",
          description = "description-2",
          priceInCents = 64
        ),

        Product(
          id = "3",
          name = "product-3",
          description = "description-3",
          priceInCents = 128
        )
      )
    )

    TestsConfig.openApp(webDriver = webDriver)
  }

  @Test
  fun testSingleProduct() {
    /**
     * Проверяем, что открылась страница со списком товаров
     */
    Assert.assertTrue(productsListPageSteps.rootIsDisplayed())

    /**
     * Проверяем, что отображаются все продукты, которые мы хотим купить
     */
    Assert.assertTrue(productsListPageSteps.productIsDisplayed(id = "1"))

    /**
     * Покупаем 1 порцию продукта с id = 1
     */
    productsListPageSteps.addProduct("1")

    /**
     * Проверяем, что все продукты добавились в корзину
     */
    Assert.assertEquals("1", productsListPageSteps.getProductAmount(id = "1"))

    /**
     * Открываем страницу с подтверждением покупки
     */
    productsListPageSteps.checkout()

    /**
     * Проверяем, что открылась страница с подтверждением покупки
     */
    Assert.assertTrue(productsCheckoutPageSteps.rootIsDisplayed())

    /**
     * Подтверждаем покупку
     */
    productsCheckoutPageSteps.purchase()

    /**
     * Проверяем, что открылась страница с оплатой
     */
    Assert.assertTrue(productsPurchasePageSteps.rootIsDisplayed())

    /**
     * Проверяем, что стоимость всех товаров - 0.32$
     */
    Assert.assertEquals("0.32$", productsPurchasePageSteps.getTotalPrice())
  }

  @Test
  fun testMultipleProducts() {
    /**
     * Проверяем, что открылась страница со списком товаров
     */
    Assert.assertTrue(productsListPageSteps.rootIsDisplayed())

    /**
     * Проверяем, что отображаются все продукты, которые мы хотим купить
     */
    Assert.assertTrue(productsListPageSteps.productIsDisplayed(id = "1"))
    Assert.assertTrue(productsListPageSteps.productIsDisplayed(id = "2"))

    /**
     * Покупаем 1 порцию продукта с id = 1
     */
    productsListPageSteps.addProduct("1")
    /**
     * Покупаем 1 порцию продукта с id = 2
     */
    productsListPageSteps.addProduct("2")

    /**
     * Проверяем, что все продукты добавились в корзину
     */
    Assert.assertEquals("1", productsListPageSteps.getProductAmount(id = "1"))
    Assert.assertEquals("1", productsListPageSteps.getProductAmount(id = "2"))

    /**
     * Открываем страницу с подтверждением покупки
     */
    productsListPageSteps.checkout()

    /**
     * Проверяем, что открылась страница с подтверждением покупки
     */
    Assert.assertTrue(productsCheckoutPageSteps.rootIsDisplayed())

    /**
     * Подтверждаем покупку
     */
    productsCheckoutPageSteps.purchase()

    /**
     * Проверяем, что открылась страница с оплатой
     */
    Assert.assertTrue(productsPurchasePageSteps.rootIsDisplayed())

    /**
     * Проверяем, что стоимость всех товаров - 0.96$
     */
    Assert.assertEquals("0.96$", productsPurchasePageSteps.getTotalPrice())
  }

  @Test
  fun testMultipleProductsWithDifferentAmounts() {
    /**
     * Проверяем, что открылась страница со списком товаров
     */
    Assert.assertTrue(productsListPageSteps.rootIsDisplayed())

    /**
     * Проверяем, что отображаются все продукты, которые мы хотим купить
     */
    Assert.assertTrue(productsListPageSteps.productIsDisplayed(id = "1"))
    Assert.assertTrue(productsListPageSteps.productIsDisplayed(id = "2"))

    /**
     * Покупаем 4 порции продукта с id = 1
     */
    productsListPageSteps.addProduct("1")
    productsListPageSteps.addProduct("1")
    productsListPageSteps.addProduct("1")
    productsListPageSteps.addProduct("1")

    /**
     * Покупаем 2 порции продукта с id = 2
     */
    productsListPageSteps.addProduct("2")
    productsListPageSteps.addProduct("2")

    /**
     * Проверяем, что все продукты добавились в корзину
     */
    Assert.assertEquals("4", productsListPageSteps.getProductAmount(id = "1"))
    Assert.assertEquals("2", productsListPageSteps.getProductAmount(id = "2"))

    /**
     * Открываем страницу с подтверждением покупки
     */
    productsListPageSteps.checkout()

    /**
     * Проверяем, что открылась страница с подтверждением покупки
     */
    Assert.assertTrue(productsCheckoutPageSteps.rootIsDisplayed())

    /**
     * Подтверждаем покупку
     */
    productsCheckoutPageSteps.purchase()

    /**
     * Проверяем, что открылась страница с оплатой
     */
    Assert.assertTrue(productsPurchasePageSteps.rootIsDisplayed())

    /**
     * Проверяем, что стоимость всех товаров - 9.94$
     */
    Assert.assertEquals("2.56$", productsPurchasePageSteps.getTotalPrice())
  }

  @After
  fun after() {
    TestsConfig.closeApp(webDriver = webDriver)
  }
}
