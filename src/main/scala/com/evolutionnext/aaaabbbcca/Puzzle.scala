package com.evolutionnext.aaaabbbcca

object Puzzle {
  def solve(word: String): List[(Char, Int)] = {
    if (word.isEmpty) Nil
    else {
      word.tail
        .zip(word)
        .foldLeft(List(word.head -> 1)) { (acc, next) =>
          acc.headOption.fold((next._1, 1) +: acc) { h =>
            if (h._1 == next._1) (next._1, h._2 + 1) +: acc.tail
            else (next._1, 1) +: acc
          }
        }
        .reverse
    }
  }
}

object PuzzleApp extends App {
  println(Puzzle.solve("aaaabbbcca"))
  println(Puzzle.solve(""))
  println(Puzzle.solve("abcdefghhhhiijj"))
}
