package com.evolutionnext.testable

import java.nio.charset.Charset
import java.nio.{ByteBuffer, CharBuffer}
import java.util.Base64

import scala.collection.immutable.StringOps

//import com.evolutionnext.testable.IntegrationTest.source

import scala.io.{BufferedSource, Source}
//
//object Foo {
//  def getActionAsBase64(appName: String = null, taskType: String = null,
//                        taskName: String = null): String = {
//    val pwd = System.getProperty("user.dir")
//    val filePath = Paths
//      .get(pwd, "..", "tasks", appName, taskType, taskName, taskName + ".zip")
//      .toString
//    val simplified = Files.simplifyPath(filePath)
//
//    // Reading the file as a FileInputStream
//    val file = new File(simplified)
//    val in = new FileInputStream(file)
//    val bytes = new Array[Byte](file.length.toInt)
//    in.read(bytes) // stream inserts bytes into the array
//    in.close()
//
//    // Encoding the file using Base64encoder
//    val encoded =
//      new BASE64Encoder()
//        .encode(bytes)
//        .replace("\n", "")
//        .replace("\r", "")
//    return encoded.toString
//  }
//}

object Base64Iterator {
  private lazy val encoder: Base64.Encoder = Base64.getEncoder
  private lazy val decoder: Base64.Decoder = Base64.getDecoder

  def encode(it: Iterator[String]): Iterator[Array[Byte]] = {
    it.map(s => encoder.encode(s.getBytes("UTF-8")))
  }

  def decode(it: Iterator[Array[Byte]]) = {
    it.map(decoder.decode)
      .map(_.map(_.toChar).mkString)
      .toList.mkString("\n")
  }
}

object UnitTest extends App {
  private val original: Iterator[String] = List("Zoom", "Bob").toIterator
  println(Base64Iterator.decode(Base64Iterator.encode(original)) == "Zoom\nBob")
}



object IntegrationTest extends App {
//
//  private lazy val encoder: Base64.Encoder = Base64.getEncoder
//  private lazy val decoder: Base64.Decoder = Base64.getDecoder
//
//  private val source: BufferedSource = Source
//    .fromFile(
//      "/Users/danno/Development/java-concepts/src/main/java/com/evolutionnext/OuterClass.java")
//
//  private val byteBufferIterator: Iterator[ByteBuffer] =
//    source
//      .grouped(100)
//      .map(_.foldLeft(ByteBuffer.allocate(3000))((bb, c) => bb.putChar(c)))
//
//  private val buffers: Iterator[ByteBuffer] = byteBufferIterator.map(_.compact()).map(encoder.encode)
//
//  private val result = buffers.map(decoder.decode).map(bb => bb.asCharBuffer().toString).mkString
//
//  println(result)

  val m = implicitly[Null => String]
  val m2 = implicitly[Null => StringOps]
  //val m3 = implicitly[StringOps => String]
  val g = implicitly[Ordering[Int]]

  val r: String = 1 + null

}
