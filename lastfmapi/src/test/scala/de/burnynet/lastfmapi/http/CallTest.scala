package de.burnynet.lastfmapi.http

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import de.burnynet.lastfmapi.ApiAccount
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{FunSpec, GivenWhenThen}

/**
 *
 * @author tobi
 */

@RunWith(classOf[JUnitRunner])
class CallTest extends FunSpec with GivenWhenThen with ShouldMatchers {

  describe("A normal Call object") {
    given("the call object")
    it("should calculate the method parameter correcty") {
      val c = new Call("package", "method", null)
      c.methodParameter should equal("package.method")
    }

    given("The call objects getParameterMap method")
    val account = createDummyApiAccount
    val c = new Call("package", "method", Map("key1" -> "value1", "key2" -> "value2"))
    val res = c.getParameterMap(account)
    it("should return all required parameters correctly") {

      res should contain key ("method")
      res("method") should equal("package.method")
      res("api_key") should equal(account.key)
      res should contain key ("key1")
      res("key1") should equal("value1")
      res should contain key ("key2")
      res("key2") should equal("value2")
      info("all parameters were correct")
    }
  }

  def createDummyApiAccount = new ApiAccount {
    def secret = "testsecret"

    def key = "testkey"
  }
}
