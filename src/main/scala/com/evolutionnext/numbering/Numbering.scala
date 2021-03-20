package com.evolutionnext.numbering

object Numbering {
  val teens = Map(
    0 -> "Ten",
    1 -> "Eleven",
    2 -> "Twelve",
    3 -> "Thirteen",
    4 -> "Fourteen",
    5 -> "Fifteen",
    6 -> "Sixteen",
    7 -> "Seventeen",
    8 -> "Eighteen",
    9 -> "Nineteen"
  )
  val ordinaryNumbers = Map(
    1 -> "One",
    2 -> "Two",
    3 -> "Three",
    4 -> "Four",
    5 -> "Five",
    6 -> "Six",
    7 -> "Seven",
    8 -> "Eight",
    9 -> "Nine"
  )

  val tenthPositionNumbers = Map(
    2 -> "Twenty",
    3 -> "Thirty",
    4 -> "Forty",
    5 -> "Fifty",
    6 -> "Sixty",
    7 -> "Seventy",
    8 -> "Eighty",
    9 -> "Ninety"
  )

  val groupPosition = Map(
    0 -> "",
    1 -> "Thousand",
    2 -> "Million",
    3 -> "Billion",
    4 -> "Trillion",
    5 -> "Quadrillion",
    6 -> "Quintillion",
    7 -> "Sextillion",
    8 -> "Septillion",
    9 -> "Octillion",
    10 -> "Nonillion",
    11 -> "Decillion"
  )

  def translate(s: String): String = {
    s.reverse
      .grouped(3)
      .toList
      .map(_.reverse)
      .zipWithIndex
      .reverse
      .flatMap {
        case (n, i) =>
          parseGroup(n)
            .flatMap(s => groupPosition.get(i).map(p => s + " " + p))
      }
      .mkString(" ")
      .trim
  }

  protected def parseGroup(xs: List[Int]): Option[String] = {
    if (xs.length > 3) None
    else {
      xs match {
        case Nil           => None
        case x :: Nil      => ordinaryNumbers.get(x)
        case 1 :: y :: Nil => teens.get(y)
        case x :: y :: Nil =>
          Some(
            tenthPositionNumbers.getOrElse(x, "") +
              ordinaryNumbers.get(y).map(" " + _).getOrElse("")
          )
        case x :: y :: z :: Nil =>
          Some(
            ordinaryNumbers.get(x).map(_ + " Hundred").getOrElse("") +
              tenthPositionNumbers.get(y).map(" " + _).getOrElse("") +
              ordinaryNumbers.get(z).map(" " + _).getOrElse("")
          )
        case _ => None
      }
    }
  }

  def parseGroup(s: String): Option[String] = {
    parseGroup(s.toList.map(_.toString.toInt).dropWhile(_ == 0))
  }
}
