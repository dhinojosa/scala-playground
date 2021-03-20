package com.evolutionnext.liveexample


//Write a program that will correct an input string to use proper
// capitalization and spacing. Allowed punctuations are the period
// ( . ), question mark ( ? ), and exclamation ( ! ).
// Make sure that commas ( , ), colons ( : ), semicolons ( ; )
// and all other punctuation are always followed by spaces.
// The input string will be a valid English sentence.

// if there are more than one space between two words it should
// converted into one space

//Example: "first, solve the problem.then, write the code."
//Output: "First, solve the problem. Then, write the code."

//Example: "this is a test... and another test."
//Output: "This is a test... And another test."


object Problem {

  def fixSentence(s: String): String = {
    s.trim.head.toUpper + s.trim.tail
  }

  //  def solution(s: String): String = {
  //    if (s.isEmpty) ""
  //    else {
  //      val sentences = s.split((\.))
  //      val part1 = sentences.map(s => fixSentence(s)).mkString(". ")
  //      val sentenceselipsis = s.split("""(\.\.\.)""")
  //      val part2 = sentenceelipsis.map(s => fixSentence(s)).mkString(". ")
  //      val questions = part2.split("""(\?)""")
  //      val part3 = questions.map(q => fixSentence(q)).mkString("? ")
  //      val exclaim = part3.split("""(\!)""")
  //      exclaim.map(q => fixSentence(q)).mkString("! ")
  //    }
  //  }

  def sol2(s: String): String = {
    s.replaceAll("""\.([^S])""", ". $1")
      .replaceAll("""\?([^S])""", "? $1")
      .replaceAll("""\!([^S])""", "! $1").trim()
  }
}


object Runner extends App {
  //  println(Problem.solution(""))
  //  println(Problem.solution("hello"))
  //  println(Problem.solution("A word. two words"))
  //  println(Problem.solution("A word?two words"))
  //  println(Problem.solution("A word!two words"))
  println(Problem.sol2("first, solve the problem.then, write the code."))
  println("First, solve the problem. Then, write the code.")
  println(Problem.sol2("this is a test... and another test."))
  println("This is a test... And another test.")
}


