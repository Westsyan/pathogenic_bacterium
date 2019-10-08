package test

import java.io.File

import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

object test06 {


  def main(args: Array[String]): Unit = {

    val  f = FileUtils.readLines(new File("F:\\database\\pathogenic\\2019-6-24\\comb/usa_nih.txt"),"GB2312").asScala


    println(f.length)
    println(f.distinct.groupBy(_.split("\t").head).filter(_._2.length ==  2))
  }
}
