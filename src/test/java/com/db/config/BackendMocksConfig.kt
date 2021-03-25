package com.db.config

import com.db.data.Product
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.google.gson.Gson

object BackendMocksConfig {
  fun startMock(wireMockRule: WireMockRule, products: List<Product>) {
    val body = Gson().toJson(products)

    wireMockRule.stubFor(
      get(urlEqualTo("/shopping_card_api/products"))
        .willReturn(aResponse()
          .withStatus(200)
          .withHeader("Content-Type", "application/json")
          .withHeader("Access-Control-Allow-Origin", "*")
          .withHeader("Access-Control-Allow-Credentials", "true")
          .withHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, HEAD, OPTIONS")
          .withHeader("Access-Control-Allow-Headers", "Authorization, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
          .withBody(body)
        )
    )
  }
}
