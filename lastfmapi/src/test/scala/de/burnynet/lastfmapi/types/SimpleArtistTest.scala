package de.burnynet.lastfmapi.types

import org.scalatest.{GivenWhenThen, FunSpec}
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 *cleab
 * @author tobi
 */
@RunWith(classOf[JUnitRunner])
class SimpleArtistTest extends FunSpec with GivenWhenThen with ShouldMatchers {
  describe("An ArtistProfileObject") {
    given("The artist profile")
    val res: SimpleArtist = SimpleArtist(testXml)
    it("Should have the name Madonna") {
      res.name should equal("Madonna")
    }
    it("Should have the correct url") {
      res.url should equal("http://www.last.fm/music/Madonna")
    }

    given("The artists images")
    it("should have a small image") {
      res.imageWithSize(ImageSize.SMALL) should not be None
    }
    it("should have a medium image") {
      res.imageWithSize(ImageSize.MEDIUM) should not be None
    }
    it("should have a large image") {
      res.imageWithSize(ImageSize.LARGE) should not be None
    }
    it("should have an extralarge image") {
      res.imageWithSize(ImageSize.EXTRA_LARGE) should not be None
    }
    it("should have a 'mega' image") {
      res.imageWithSize(ImageSize.MEGA) should not be None
    }
  }

    def testXml = <artist>
    <name>Madonna</name>
    <url>http://www.last.fm/music/Madonna</url>
    <image size="small">http://userserve-ak.last.fm/serve/34/77687788.png</image>
    <image size="medium">http://userserve-ak.last.fm/serve/64/77687788.png</image>
    <image size="large">http://userserve-ak.last.fm/serve/126/77687788.png</image>
    <image size="extralarge">http://userserve-ak.last.fm/serve/252/77687788.png</image>
    <image size="mega">
      http://userserve-ak.last.fm/serve/_/77687788/Madonna+NewMDNApromopic.png
    </image>
  </artist>
}
