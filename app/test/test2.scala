package test

import java.io.File

import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

object test2 {


  def main(args: Array[String]): Unit = {


    val file = FileUtils.readLines(new File("D:\\复旦病原菌数据库资料\\致病菌整理好的数据 - 20181123/Virus.txt"),"UTF-8").asScala

    val f = file.map(_.split("\t")).map{ buffer=>

      if(buffer(17).trim.isEmpty){
        buffer(17) = "4"
      }

      if(buffer(19).trim.isEmpty){
        buffer(19) = "1"
      }

      if(buffer(21).trim.isEmpty){
        buffer(21) = "1"
      }

      if(buffer(23).trim.isEmpty){
        buffer(23) = "1"
      }

      if(buffer(25).trim.isEmpty){
        buffer(25) = "1"
      }

      if(buffer(27).trim.isEmpty){
        buffer(27) = "1"
      }

      if(buffer(29).trim.isEmpty){
        buffer(29) = "1"
      }

      if(buffer(31).trim.isEmpty){
        buffer(31) = "1"
      }

      if(buffer(33).trim.isEmpty){
        buffer(33) = "1"
      }

      if(buffer(35).trim.isEmpty){
        buffer(35) = "1"
      }

      if(buffer(37).trim.isEmpty){
        buffer(37) = "1"
      }

      if(buffer(39).trim.isEmpty){
        buffer(39) = "1"
      }

      if(buffer(41).trim.isEmpty){
        buffer(41) = "1"
      }

      buffer.mkString("\t")
    }


    FileUtils.writeLines(new File("D:\\复旦病原菌数据库资料\\致病菌整理好的数据 - 20181123/Virus1.txt"),"UTF-8",f.asJava)
  }


  def add1ToBacteria = {
    val file = FileUtils.readLines(new File("D:\\复旦病原菌数据库资料\\致病菌整理好的数据 - 20181123/Bacteria.txt"),"UTF-8").asScala

    val f = file.map(_.split("\t")).map{ buffer=>
      if(buffer(16).trim.isEmpty){
        buffer(16) = "4"
      }

      if(buffer(18).trim.isEmpty){
        buffer(18) = "1"
      }

      if(buffer(20).trim.isEmpty){
        buffer(20) = "1"
      }

      if(buffer(22).trim.isEmpty){
        buffer(22) = "1"
      }

      if(buffer(24).trim.isEmpty){
        buffer(24) = "1"
      }

      if(buffer(26).trim.isEmpty){
        buffer(26) = "1"
      }

      if(buffer(28).trim.isEmpty){
        buffer(28) = "1"
      }

      if(buffer(30).trim.isEmpty){
        buffer(30) = "1"
      }

      if(buffer(32).trim.isEmpty){
        buffer(32) = "1"
      }

      if(buffer(34).trim.isEmpty){
        buffer(34) = "1"
      }

      if(buffer(36).trim.isEmpty){
        buffer(36) = "1"
      }

      if(buffer(38).trim.isEmpty){
        buffer(38) = "1"
      }

      if(buffer(40).trim.isEmpty){
        buffer(40) = "1"
      }

      buffer.mkString("\t")
    }


    FileUtils.writeLines(new File("D:\\复旦病原菌数据库资料\\致病菌整理好的数据 - 20181123/Bacteria1.txt"),"UTF-8",f.asJava)
  }


}

