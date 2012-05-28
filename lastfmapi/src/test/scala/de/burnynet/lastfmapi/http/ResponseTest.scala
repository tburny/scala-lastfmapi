package de.burnynet.lastfmapi.http

import org.scalatest.{GivenWhenThen, FunSpec}
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 *
 * @author tobi
 */
@RunWith(classOf[JUnitRunner])
class ResponseTest extends FunSpec with GivenWhenThen with ShouldMatchers {

  describe("A successful API response") {
    given("The response xml with status ok")
    val xml = <lfm status="ok"><artist>test</artist></lfm>
    and("the Response object")
    val response = Response(xml)

    it("should not have an error") {
      response.error should equal(None)
    }
    it("should have selected the correct data tag") {
      response.dataXml.text should equal("test")
    }
  }


  describe("A failed API response") {
    given("The response object")
    val xml = <lfm status="failed">
      <error code="10">Error reason</error>
    </lfm>
    val response = Response(xml)

    then("should have error object")

    response.error should be('defined)

    given("the error")
    val error = response.error.get

    it("should have an error code") {
      error.code should be > (0)
    }

    it("should have an error message") {
      error.message should equal("Error reason")
    }

  }
}
