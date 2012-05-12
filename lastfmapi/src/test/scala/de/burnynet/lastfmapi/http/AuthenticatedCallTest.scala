package de.burnynet.lastfmapi.http

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSpec, GivenWhenThen}
import de.burnynet.lastfmapi.{Md5Utility, ApiAccount}
import org.scalatest.matchers.ShouldMatchers

/**
 *
 * @author tobi
 */
@RunWith(classOf[JUnitRunner])
class AuthenticatedCallTest extends FunSpec with GivenWhenThen with ShouldMatchers {

  describe("An AuthenticatedCall") {
    given("A paremeter map, a session key and an authenticated call")
    val params = Map("key1" -> "value1", "key2" -> "value2")
    val subject = new AuthenticatedCall("package", "method", "mySessionKey", params)

    it("should calculate the method parameter correctly") {
      then("the API method parameter must be package.method")
      subject.methodParameter should be("package.method")

      and("the session key and the parameters must be set")
      subject.sessionKey should be("mySessionKey")
    }
    it("should have the correct session key and parameters set") {
      subject.sessionKey should be("mySessionKey")
      subject.parameters should equal(params)
    }
  }

  describe("The parameter map returned by getParameterMap ") {
    given("A parameter map of an AuthenticatedCall")
    val c = new AuthenticatedCall("package", "method", "mySessionKey", Map("key1" -> "value1", "key2" -> "value2"))
    val subject = c.getParameterMap(createDummyApiAccount)

    it("should return all parameters required for a valid API request.") {
      subject should contain key ("method")
      subject should contain key ("api_key")
      subject should contain key ("api_sig")
    }

    it("should calculate the correct api signature") {
      val account = createDummyApiAccount
      val ref = Md5Utility.md5SumString((
        "api_key" + account.key +
          "key1" + "value1" +
          "key2" + "value2" +
          "method" + "package.method" +
          "session" + "mySessionKey" +
          account.secret
        ).getBytes("UTF-8"))

      subject("api_sig") should equal(ref)
      //assert (subject contains "session")
    }

    it("should contain the session as parameter") {
      subject should contain key ("session")
    }

  }

  describe("An auth.getSession call") {
    given("an AuthenticatedCall whose method is auth.getSession")
    val c = new AuthenticatedCall("auth", "getSession", null, Map("key1" -> "value1", "key2" -> "value2"))
    and("its request parameter map")
    val subject = c.getParameterMap(createDummyApiAccount)

    it("should not contain a session parameter") {
      subject should not contain key("session")
    }

    it("should not include the session parameter in the signature calculation") {
      val account = createDummyApiAccount
      val ref = Md5Utility.md5SumString((
        "api_key" + account.key +
          "key1" + "value1" +
          "key2" + "value2" +
          "method" + "auth.getSession" +
          account.secret
        ).getBytes("UTF-8"))
      subject("api_sig") should equal(ref)
    }
  }

  def createDummyApiAccount = new ApiAccount {
    def secret = "testsecret"

    def key = "testkey"
  }
}
