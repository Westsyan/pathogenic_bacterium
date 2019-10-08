package test

import java.io.File

import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

object test09 {

  def main(args: Array[String]): Unit = {

    val city = Array("china", "usa_nih", "usa_bmbl", "australia", "belgium", "canada", "eu", "germany", "japan", "singapore", "switzerland", "uk")

    for (c <- city) {

      val f = FileUtils.readLines(new File(s"F:\\database\\pathogenic\\2019-6-27/${c}_samDF.txt")).asScala

      val same = f.map(_.split("\t")).count(x => x.last == x.init.last)

      val repeat = (same.toDouble * 100 / f.length.toDouble).formatted("%.1f") + "%"

      FileUtils.writeStringToFile(new File(s"F:\\database\\pathogenic\\2019-6-27/${c}_repeat.txt"),repeat)
    }
  }
}
