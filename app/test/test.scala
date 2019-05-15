package test

import java.io.File

import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

object test {

  def main(args: Array[String]): Unit = {
    val x = FileUtils.readLines(new File("D:/复旦病原菌数据库资料/viruses.txt")).asScala

    var family = ""
    var genus = ""

    x.drop(2).map{y=>
      val column = y.split("\t")
      def getInfo(i : Int) : String = {
        column.drop(i).headOption.getOrElse("")
      }

      family = if(column.head == ""){family}else{column.head}
      genus = if(column.drop(1).head == ""){genus}else{column.drop(1).head}

      println(family,genus,getInfo(2),getInfo(3),getInfo(4),getInfo(5),getInfo(6),getInfo(7),getInfo(8),getInfo(9),getInfo(10)
        ,getInfo(11),getInfo(12),getInfo(13),getInfo(14),getInfo(15),getInfo(16),getInfo(17),getInfo(18),getInfo(19),getInfo(20))
    }

  }

}
