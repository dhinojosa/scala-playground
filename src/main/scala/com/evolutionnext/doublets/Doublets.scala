package com.evolutionnext.doublets

import scala.util.matching.Regex

object Doublets {

  def findDoublets(dictionary: LazyList[String], from: String, to: String): List[List[String]] = {
    val smallerDictionary = findLowerCaseWordsByLength(dictionary, from.length)
    def recur(word: String, acc: List[String]): List[List[String]] = {
      val nextList: List[String] = Doublets
        .findWordChoices(smallerDictionary, word)
        .filterNot(s => s == word)
        .filterNot(s => acc.contains(s))
      if (nextList == Nil) Nil
      else if (nextList.contains(to)) List(acc :+ word :+ to)
      else nextList.flatMap(c => recur(c, acc :+ word)).filter(_.nonEmpty)
    }
    recur(from, Nil)
  }

  def findWordChoices(dictionary: LazyList[String], str: String): List[String] = {
    (0 until str.length)
      .map(i => str.updated(i, '.'))
      .flatMap(regex => getCombinations(dictionary, str, regex.r))
      .toList
  }

  def getCombinations(dictionary: LazyList[String], str: String, regex: Regex): List[String] = {
    dictionary
      .filter(w => regex.matches(w))
      .filterNot(w => w == str)
      .toList
  }

  def findLowerCaseWordsByLength(dictionary: LazyList[String], size: Int): scala.LazyList[String] =
    dictionary
      .filter(s => s.length == size)
      .filter(s => s.head.isLower)
      .to(LazyList)
}
