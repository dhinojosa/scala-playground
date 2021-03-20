package com.evolutionnext.doublets

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should

import scala.io.Source

class DoubletsSpec extends AnyFunSpec with should.Matchers {
  describe("findWordsByLength should findAllFiveLetterWords") {
    info("Going to try just an object, not sure I need a class")
    it("should be provided a dictionary of iterator and find all the words") {
      val dictionary = LazyList("one", "Two", "seven", "cloud", "me", "they", "Choice", "Rebel")
      val result: LazyList[String] = Doublets.findLowerCaseWordsByLength(dictionary, 5)
      result should be(LazyList("seven", "cloud"))
    }
  }

  describe("get combinations") {
    it("should get all available combinations") {
      val source = Source.fromFile("/usr/share/dict/words")
      val dictionary = source.getLines().to(LazyList)
      val fourLetterWords: LazyList[String] = Doublets.findLowerCaseWordsByLength(dictionary, 4)
      val result = Doublets.getCombinations(fourLetterWords, "door", ".oor".r)
      result should contain allElementsOf List("boor", "poor", "moor")
      source.close()
    }
  }

  describe("find some word choices except for the word we are looking for") {
    it("should find all set of words by each character") {
      val source = Source.fromFile("/usr/share/dict/words")
      val dictionary = source.getLines().to(LazyList)
      val fourLetterWords: LazyList[String] =
        Doublets.findLowerCaseWordsByLength(dictionary, 4)
      val result = Doublets.findWordChoices(fourLetterWords, "door")
      result should be(
        List("boor", "moor", "poor", "doer", "dour", "doob", "dook", "dool", "doom", "doon")
      )
      source.close()
    }
  }

  describe("find a tree until we find the destination word") {
    it("should handle an empty dictionary") {
      val result = Doublets.findDoublets(LazyList.empty[String], "door", "lock")
      result shouldBe Symbol("empty")
    }

    it("should handle an element on word in the dictionary") {
      val dict =
        LazyList("mike", "like", "bike")
      val result = Doublets.findDoublets(LazyList.empty[String], "door", "lock")
      result shouldBe List.empty[String]
    }

    it("should handle two elements, pawn and lawn") {
      val dict = LazyList("pawn", "lawn")
      val result = Doublets.findDoublets(dict, "pawn", "lawn")
      result shouldBe List(List("pawn", "lawn"))
    }

    it("should handle three elements, pawn and sewn") {
      val dict = LazyList("pawn", "crap", "fawn", "slap", "fewn", "evil", "sewn")
      val result = Doublets.findDoublets(dict, "pawn", "sewn")
      result shouldBe List(List("pawn", "fawn", "fewn", "sewn"))
    }

    it("should find a path from door to lock") {
      val fourLetterWords =
        LazyList("plot", "dool", "vial", "claw", "book", "look", "lock", "cask", "firk", "mesa", "boor")
      val result = Doublets.findDoublets(fourLetterWords, "door", "lock")
      result should be(List(List("door", "boor", "book", "look", "lock")))
    }

    it("should find a path from pit to die") {
      val dictionary =
        LazyList("plot", "dool", "pie", "die", "pit", "claw", "book", "look", "lock", "cask", "firk", "mesa", "boor")
      val result = Doublets.findDoublets(dictionary, "pit", "die")
      result should be(List(List("pit", "pie", "die")))
    }

    it("should find a path from door to lock from the regular dictionary") {
      val source = Source.fromInputStream(getClass.getResourceAsStream("/words.txt"))
      val dictionary = source.getLines().to(LazyList)
      Doublets.findDoublets(dictionary, "door", "lock") should be(List(List("door", "boor", "book", "look", "lock")))
    }

    it("should find a path from bank to loan from the regular dictionary") {
      val source = Source.fromInputStream(getClass.getResourceAsStream("/words.txt"))
      val dictionary = source.getLines().to(LazyList)
      Doublets.findDoublets(dictionary, "bank", "loan") should be(
        List(List("bank", "bonk", "book", "look", "loon", "loan"))
      )
    }

    it("should find a path from head to tail from the regular dictionary") {
      val source = Source.fromInputStream(getClass.getResourceAsStream("/words.txt"))
      val dictionary = source.getLines().to(LazyList)
      Doublets.findDoublets(dictionary, "head", "tail") should be(
        List(List("head", "heal", "teal", "tell", "tall", "tail"))
      )
    }

    it("should find a path from wheat to bread from the regular dictionary") {
      val source = Source.fromInputStream(getClass.getResourceAsStream("/words.txt"))
      val dictionary = source.getLines().to(LazyList)
      Doublets.findDoublets(dictionary, "wheat", "bread") should be(
        List(List("wheat", "cheat", "cheap", "cheep", "creep", "creed", "breed", "bread"))
      )
    }

    it("should find multiple doublets") {
      val fourLetterWords =
        LazyList("plot", "dool", "vial", "lint", "book", "lent", "lock", "line", "firk", "mesa", "lene", "boor", "lend")
      Doublets.findDoublets(fourLetterWords, "line", "lend") should be(List(List("line", "lene", "lend"), List("line", "lint", "lent", "lend")))
    }

    it("should handle the full dictionary, too slow to run") {
      pending
      import scala.io._
      val source = Source.fromFile("/usr/share/dict/words")
      val lazyList = source.getLines().to(LazyList)
      println(Doublets.findDoublets(lazyList, "door", "lock"))
      source.close()
    }
  }
}
