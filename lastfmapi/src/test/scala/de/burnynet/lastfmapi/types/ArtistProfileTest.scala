package de.burnynet.lastfmapi.types

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{FunSpec, GivenWhenThen}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 *
 * @author tobi
 */
@RunWith(classOf[JUnitRunner])
class ArtistProfileTest extends FunSpec with GivenWhenThen with ShouldMatchers {

  describe("An ArtistProfileObject") {
    given("The artist profile")
    val res: ArtistProfile = ArtistProfile(testXml)
    it("Should have the name Cher") {
      res.name should equal("Cher")
    }

    it("Should have the correct MusicBrainzId") {
      res.musicBrainzId should equal("bfcc6d75-a6a5-4bc6-8282-47aec8531818")
    }

    it("Should have the correct url") {
      res.url should equal("http://www.last.fm/music/Cher")
    }
    it("Should be streamable") {
      res.streamable should equal(true)
    }

    it("Should have 765515 listeners") {
      res.listeners should equal(765515)
    }

    it("Should have a playcount of 7855028") {
      res.playcount should equal(7855028)
    }

    it("Should have 5 similar artists") {
      res.similarArtists.size should equal(5)
    }

    it("Should have 5 tags") {
      res.tags.size should equal(5)
    }

    it("should have a bigraphy") {
      // Test timezone too?
      res.biography.published.toString should equal("Tue Jan 29 22:14:02 CET 2002")
      // 30 is arbitary, just to eleminate white space!
      res.biography.content.size should be > 30
      res.biography.summary.size should be > 30
    }

    it("Should have 5 images") {
      res.images.size should equal(5)
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

    given("The similar artists")
    it("should be the artists Sonn & Cher, Madonna, Cyndi Lauper, Kylie Minogue and Céline Dion") {
      val r = res.similarArtists.foldLeft("")((res: String, s: SimpleArtist) => res + s.name + ",")
      r should equal("Sonny & Cher,Madonna,Cyndi Lauper,Kylie Minogue,Céline Dion,")
    }

    given("The artists tags")
    it("should be pop, female vocalists, 80s, dance and rock") {
      val r = res.tags.foldLeft("")((res: String, s: Tag) => res + s.name + ",")
      r should equal("pop,female vocalists,80s,dance,rock,")
    }

  }


  def testXml = <artist>
    <name>Cher</name>
    <mbid>bfcc6d75-a6a5-4bc6-8282-47aec8531818</mbid>
    <url>http://www.last.fm/music/Cher</url>
    <image size="small">http://userserve-ak.last.fm/serve/34/69838348.png</image>
    <image size="medium">http://userserve-ak.last.fm/serve/64/69838348.png</image>
    <image size="large">http://userserve-ak.last.fm/serve/126/69838348.png</image>
    <image size="extralarge">http://userserve-ak.last.fm/serve/252/69838348.png</image>
    <image size="mega">
      http://userserve-ak.last.fm/serve/500/69838348/Cher.png
    </image>
    <streamable>1</streamable>
    <stats>
      <listeners>765515</listeners>
      <playcount>7855028</playcount>
    </stats>
    <similar>
      <artist>
        <name>Sonny &amp; Cher</name>
        <url>http://www.last.fm/music/Sonny+
          &amp;
          +Cher</url>
        <image size="small">http://userserve-ak.last.fm/serve/34/71168880.png</image>
        <image size="medium">http://userserve-ak.last.fm/serve/64/71168880.png</image>
        <image size="large">http://userserve-ak.last.fm/serve/126/71168880.png</image>
        <image size="extralarge">http://userserve-ak.last.fm/serve/252/71168880.png</image>
        <image size="mega">
          http://userserve-ak.last.fm/serve/500/71168880/Sonny++Cher.png
        </image>
      </artist>
      <artist>
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
      <artist>
        <name>Cyndi Lauper</name>
        <url>http://www.last.fm/music/Cyndi+Lauper</url>
        <image size="small">http://userserve-ak.last.fm/serve/34/76537198.png</image>
        <image size="medium">http://userserve-ak.last.fm/serve/64/76537198.png</image>
        <image size="large">http://userserve-ak.last.fm/serve/126/76537198.png</image>
        <image size="extralarge">http://userserve-ak.last.fm/serve/252/76537198.png</image>
        <image size="mega">
          http://userserve-ak.last.fm/serve/_/76537198/Cyndi+Lauper+++I+have+a+dream.png
        </image>
      </artist>
      <artist>
        <name>Kylie Minogue</name>
        <url>http://www.last.fm/music/Kylie+Minogue</url>
        <image size="small">http://userserve-ak.last.fm/serve/34/48868483.png</image>
        <image size="medium">http://userserve-ak.last.fm/serve/64/48868483.png</image>
        <image size="large">http://userserve-ak.last.fm/serve/126/48868483.png</image>
        <image size="extralarge">http://userserve-ak.last.fm/serve/252/48868483.png</image>
        <image size="mega">
          http://userserve-ak.last.fm/serve/_/48868483/Kylie+Minogue+Kylie.png
        </image>
      </artist>
      <artist>
        <name>Céline Dion</name>
        <url>http://www.last.fm/music/C%C3%A9line+Dion</url>
        <image size="small">http://userserve-ak.last.fm/serve/34/65670054.png</image>
        <image size="medium">http://userserve-ak.last.fm/serve/64/65670054.png</image>
        <image size="large">http://userserve-ak.last.fm/serve/126/65670054.png</image>
        <image size="extralarge">http://userserve-ak.last.fm/serve/252/65670054.png</image>
        <image size="mega">
          http://userserve-ak.last.fm/serve/_/65670054/Cline+Dion+PNG.png
        </image>
      </artist>
    </similar>
    <tags>
      <tag>
        <name>pop</name>
        <url>http://www.last.fm/tag/pop</url>
      </tag>
      <tag>
        <name>female vocalists</name>
        <url>http://www.last.fm/tag/female%20vocalists</url>
      </tag>
      <tag>
        <name>80s</name>
        <url>http://www.last.fm/tag/80s</url>
      </tag>
      <tag>
        <name>dance</name>
        <url>http://www.last.fm/tag/dance</url>
      </tag>
      <tag>
        <name>rock</name>
        <url>http://www.last.fm/tag/rock</url>
      </tag>
    </tags>
    <bio>
      <published>Tue, 1 May 2012 12:51:56 +0000</published>
      <summary>
        <![CDATA[





        Cher (born Cherilyn Sarkisian on May 20, 1946) is an American singer, actress, songwriter, author and entertainer.
         Among her many career accomplishments in music, television and film, she has won an Academy Award...





        ]]>
      </summary>
      <content>
        <![CDATA[





        Cher (born Cherilyn Sarkisian on May 20, 1946) is an American singer, actress, songwriter, author and
        entertainer. Among her many career accomplishments in music, television and film, she has won an Academy Award,
         a Grammy Award, an Emmy Award and three Golden Globe Awards among others.





        ]]>
      </content>
    </bio>
  </artist>
}
