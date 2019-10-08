package test

import java.io.File

import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

object test08 {


  def main(args: Array[String]): Unit = {

    val city = Array("china","usa_nih", "usa_bmbl", "australia", "belgium", "canada", "eu", "germany", "japan", "singapore", "switzerland", "uk")

    val encoding = "GB2312"

    val china = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/china.txt"),encoding).asScala
    val usa_nih = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/usa_nih.txt"),encoding).asScala
    val usa_bmbl = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/usa_bmbl.txt"),encoding).asScala
    val australia = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/australia.txt"),encoding).asScala
    val belgium = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/belgium.txt"),encoding).asScala
    val canada = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/canada.txt"),encoding).asScala
    val eu = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/eu.txt"),encoding).asScala
    val germany = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/germany.txt"),encoding).asScala
    val japan = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/japan.txt"),encoding).asScala
    val singapore = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/singapore.txt"),encoding).asScala
    val switzerland = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/switzerland.txt"),encoding).asScala
    val uk = FileUtils.readLines(new File("F:/database/pathogenic/2019-6-24/com/uk.txt"),encoding).asScala

    val data = china ++ usa_nih.tail ++ usa_bmbl.tail ++ australia.tail ++ belgium.tail ++ canada.tail ++ eu.tail ++
      germany.tail ++ japan.tail ++ singapore.tail ++ switzerland.tail ++ uk.tail

    val map = Map(("china",china),("usa_nih",usa_nih),("usa_bmbl",usa_bmbl),("australia",australia),("belgium",belgium),
      ("canada",canada),("eu",eu),("germany",germany),("japan",japan),("singapore",singapore),("switzerland",switzerland),
      ("uk",uk))


/*    val all = data.tail.map{z=>
      val c =z.split("\t")
      (c.init.mkString("\t"),c.last)
    }*/

    val all = data.tail.map(_.split("\t")).groupBy(_.tail.init.mkString("\t")).map(_._2.last).toBuffer




    for(c <- city){
      val ci = map(c).tail.map(_.split("\t").tail.init.mkString("\t"))
      val e = all.filter(x=> !ci.contains(x.tail.init.mkString("\t"))).map(_.mkString("\t"))
      val row = map(c) ++ e
      FileUtils.writeLines(new File(s"F:\\database\\pathogenic\\2019-6-24/comb/$c.txt"),encoding,row.distinct.asJava)

    }




  }
}
