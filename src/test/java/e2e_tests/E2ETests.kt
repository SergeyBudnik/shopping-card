package e2e_tests

import config.TestsConfig
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import steps.ProductsCheckoutSteps
import steps.ProductsListPageSteps
import steps.ProductsPurchaseSteps

class E2ETests {
  private lateinit var webDriver: WebDriver

  private lateinit var productsListPageSteps: ProductsListPageSteps
  private lateinit var productsCheckoutPageSteps: ProductsCheckoutSteps
  private lateinit var productsPurchasePageSteps: ProductsPurchaseSteps

  @Before
  fun before() {
    webDriver = TestsConfig.buildWebDriver()

    productsListPageSteps = ProductsListPageSteps(webDriver = webDriver)
    productsCheckoutPageSteps = ProductsCheckoutSteps(webDriver = webDriver)
    productsPurchasePageSteps = ProductsPurchaseSteps(webDriver = webDriver)

    TestsConfig.openApp(webDriver = webDriver)
  }

  @Test
  fun testSingleProduct() {
    /**
     * Проверяем, что открылась страница со списком товаров
     */
    assertTrue(productsListPageSteps.rootIsDisplayed())

    /**
     * Проверяем, что отображаются все продукты, которые мы хотим купить
     */
    assertTrue(productsListPageSteps.productIsDisplayed(id = "1"))

    /**
     * Покупаем 1 порцию продукта с id = 1
     */
    productsListPageSteps.addProduct("1")

    /**
     * Проверяем, что все продукты добавились в корзину
     */
    assertEquals("1", productsListPageSteps.getProductAmount(id = "1"))

    /**
     * Открываем страницу с подтверждением покупки
     */
    productsListPageSteps.checkout()

    /**
     * Проверяем, что открылась страница с подтверждением покупки
     */
    assertTrue(productsCheckoutPageSteps.rootIsDisplayed())

    /**
     * Подтверждаем покупку
     */
    productsCheckoutPageSteps.purchase()

    /**
     * Проверяем, что открылась страница с оплатой
     */
    assertTrue(productsPurchasePageSteps.rootIsDisplayed())

    /**
     * Проверяем, что стоимость всех товаров - 1.99$
     */
    assertEquals("1.99$", productsPurchasePageSteps.getTotalPrice())
  }

  @Test
  fun testMultipleProducts() {
    /**
     * Проверяем, что открылась страница со списком товаров
     */
    assertTrue(productsListPageSteps.rootIsDisplayed())

    /**
     * Проверяем, что отображаются все продукты, которые мы хотим купить
     */
    assertTrue(productsListPageSteps.productIsDisplayed(id = "1"))
    assertTrue(productsListPageSteps.productIsDisplayed(id = "2"))

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
    assertEquals("1", productsListPageSteps.getProductAmount(id = "1"))
    assertEquals("1", productsListPageSteps.getProductAmount(id = "2"))

    /**
     * Открываем страницу с подтверждением покупки
     */
    productsListPageSteps.checkout()

    /**
     * Проверяем, что открылась страница с подтверждением покупки
     */
    assertTrue(productsCheckoutPageSteps.rootIsDisplayed())

    /**
     * Подтверждаем покупку
     */
    productsCheckoutPageSteps.purchase()

    /**
     * Проверяем, что открылась страница с оплатой
     */
    assertTrue(productsPurchasePageSteps.rootIsDisplayed())

    /**
     * Проверяем, что стоимость всех товаров - 2.98$
     */
    assertEquals("2.98$", productsPurchasePageSteps.getTotalPrice())
  }

  @Test
  fun testMultipleProductsWithDifferentAmounts() {
    /**
     * Проверяем, что открылась страница со списком товаров
     */
    assertTrue(productsListPageSteps.rootIsDisplayed())

    /**
     * Проверяем, что отображаются все продукты, которые мы хотим купить
     */
    assertTrue(productsListPageSteps.productIsDisplayed(id = "1"))
    assertTrue(productsListPageSteps.productIsDisplayed(id = "2"))

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
    assertEquals("4", productsListPageSteps.getProductAmount(id = "1"))
    assertEquals("2", productsListPageSteps.getProductAmount(id = "2"))

    /**
     * Открываем страницу с подтверждением покупки
     */
    productsListPageSteps.checkout()

    /**
     * Проверяем, что открылась страница с подтверждением покупки
     */
    assertTrue(productsCheckoutPageSteps.rootIsDisplayed())

    /**
     * Подтверждаем покупку
     */
    productsCheckoutPageSteps.purchase()

    /**
     * Проверяем, что открылась страница с оплатой
     */
    assertTrue(productsPurchasePageSteps.rootIsDisplayed())

    /**
     * Проверяем, что стоимость всех товаров - 9.94$
     */
    assertEquals("9.94$", productsPurchasePageSteps.getTotalPrice())
  }

  @After
  fun after() {
    TestsConfig.closeApp(webDriver = webDriver)
  }
}
