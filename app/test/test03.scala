package test

import java.io.File

import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._
import scala.collection.mutable

object test03{


  def main(args: Array[String]): Unit = {


    val states = Seq("china", "usa_nih", "usa_bmbl", "australia", "belgium", "canada", "eu", "germany", "japan", "singapore", "switzerland", "uk")

    states.foreach{state=>


    val f = FileUtils.readLines(new File(s"F:\\database\\pathogenic/$state.txt"),"GB2312").asScala


/*      val s = f.map(_.split("\t").init).transpose.map{x=>
        x.map {
          case "是" => "1"
          case "否" => "0"
          case "未知" => "0.5"
          case "<1" => "0"
          case "1-10" => "0"
          case "11-20" => "0.1"
          case "21-30" => "0.2"
          case "31-40" => "0.3"
          case "41-50" => "0.4"
          case "51-60" => "0.5"
          case "61-70" => "0.6"
          case "71-80" => "0.7"
          case "81-90" => "0.8"
          case "91-100" => "0.9"
          case "100" => "1"
          case z: String => z
        }
      }*/


      val s = f.map(_.split("\t").init).transpose

    val r = mutable.Buffer("\t" +(1 until  s.head.length).mkString("\t")) ++: s.map(_.mkString("\t"))

    val r2 =    f.zipWithIndex.tail.map{x=>
      x._2 + "\t" + x._1.split("\t").last
  }

    FileUtils.writeLines(new File(s"D:\\ip4m\\path3/peak_$state.txt"),"GB2312",r.asJava)
    FileUtils.writeLines(new File(s"D:\\ip4m\\path3/group_$state.txt"),r2.asJava)
    }


  }

  def deal = {

    val city = Array("china", "usa_nih", "usa_bmbl", "australia", "belgium", "canada", "eu", "germany", "japan", "singapore", "switzerland", "uk")

    for (c <- city) {


      val f = FileUtils.readLines(new File(s"F:\\database\\pathogenic\\2019-6-24\\comb/$c/$c.txt"), "GB2312").asScala


      val row = if (c == "china") {
        f.map { x =>
          val c = x.split("\t")
          val last = c.last match {
            case "1" => "high"
            case "2" => "high"
            case "3" => "low"
            case "4" => "low"
            case y: String => y
          }
          c.init.mkString("\t") + "\t" + last
        }
      } else {
        f.map { x =>
          val c = x.split("\t")
          val last = c.last match {
            case "1" => "low"
            case "2" => "low"
            case "3" => "high"
            case "4" => "high"
            case y: String => y
          }
          c.init.mkString("\t") + "\t" + last
        }
      }

      FileUtils.writeLines(new File(s"F:\\database\\pathogenic\\2019-6-27/$c.txt"), "GB2312", row.asJava)

    }

  }

}
