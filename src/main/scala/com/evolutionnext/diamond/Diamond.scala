package com.evolutionnext.diamond

object Diamond {
    def zippedRange(c:Char): Seq[(Char, Int)] = {
        val range = 'a' to c
        range.zip((range.length-1) to 0 by -1)
    }

    def strMaker(c:Char, outer:Int, inner:Int): String = {
        if (c == 'a') {
            s"${" ".repeat(outer)}$c${" ".repeat(outer)}"
        } else {
            s"${" ".repeat(outer)}$c${" ".repeat(inner)}$c${" ".repeat(outer)}"
        }
    }

    def getTuples(c:Char): Seq[String] = {
        zippedRange(c)
            .zip(0 +: (1 to 100).filter(i => i % 2 != 0))
            .map{case ((l, o), i) => strMaker (l,o,i)}
    }

    def mkDiamond(c:Char):String = {
        val top = getTuples(c)
        (top.init ++ top.reverse).mkString("\n")
    }
}

object Runner extends App {
    import Diamond._
    println(args(0).toCharArray.find(('a' to 'z').contains).map(mkDiamond).getOrElse("Invalid Arg"))
}
