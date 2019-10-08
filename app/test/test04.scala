package test

import java.io.File

import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

object test04 {


  def main(args: Array[String]): Unit = {

    val city = Array("usa_nih", "usa_bmbl", "australia", "belgium", "canada", "eu", "germany", "japan", "singapore", "switzerland", "uk")

   // val city = Array("china")
    city.foreach { c =>


      val f = FileUtils.readLines(new File(s"F:/database/pathogenic/2019-6-24/$c.txt"), "GB2312").asScala

      val row = f.map { x =>
        val t = x.split("\t")
        (t.head, t.tail.init.mkString("\t"), t.last)
      }.groupBy(_._2).flatMap { x =>
        val different = x._2.map(_._3).distinct
        val max = different.map { d =>
          val risk = x._2.count(_._3 == d)
          (d, risk)
        }.sortBy(_._1.toInt).minBy(_._2)
        x._2.map(z => z._1 + "\t" + z._2 + "\t" + max._1)
      }.toBuffer

      val header = "Name\t是否呼吸道传播\t是否人人之间传播\t感染剂量是否小于100个病原\t是否可以在自然条件下存活超过24小时\t死亡率\t是否没有有效治疗药物\t是否没有有效的预防性疫苗\trisk"



      FileUtils.writeLines(new File(s"F:/database/pathogenic/2019-6-24/com/$c.txt"), "GB2312",(header +: row).asJava)
    }

  }


  def sortCity = {
    val f = FileUtils.readLines(new File("D:\\复旦病原菌数据库资料\\tmp\\risk/switzerland.txt"), "GB2312").asScala

    val row = f.map { x =>
      val t = x.split("\t")
      (t.take(2).mkString("\t"), t.drop(2).init.mkString("\t"), t.last)
    }.sortBy(_._2).map(x => x._1 + "\t" + x._2 + "\t" + x._3)

    FileUtils.writeLines(new File("D:\\复旦病原菌数据库资料\\tmp\\risk/switzerlandS.txt"), row.asJava)

  }
}
