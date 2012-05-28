package de.burnynet.lastfmapi.types

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{GivenWhenThen, FunSpec}
import org.scalatest.matchers.ShouldMatchers

/**
 * Test for [[de.burnynet.lastfmapi.types.Session]]
 * @author tobi
 */
@RunWith(classOf[JUnitRunner])
class SessionTest extends FunSpec with GivenWhenThen with ShouldMatchers {

  describe("An Session") {
    given("The session data")
    val res: Session = Session(testXml)
    it("should have the username LastFmUser") {
      res.name should equal("LastFmUser")
    }
    it("should have the correct session key") {
      res.sessionKey should equal ("d580d57f32848f5dcf574d1ce18d78b2")
    }
    it("should indicate that the user is a subscriber") {
    res.subscriber should equal(true)
    }
  }


  def testXml = <session>
    <name>LastFmUser</name>
    <key>d580d57f32848f5dcf574d1ce18d78b2</key>
    <subscriber>1</subscriber>
    </session>
}
