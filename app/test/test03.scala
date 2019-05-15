package test

import java.io.File

import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

object test03{


  def main(args: Array[String]): Unit = {



    val f = FileUtils.readLines(new File("D:\\水稻资源中心资料\\02428_genome_chr.all/02428_genome_chr.all_5K.Assemblytics_structural_variants.bed")).asScala

   val s =  f.tail.map(_.split("\t")).filter(x=> x(4).toInt>5000).map(_.mkString("\t")).size
    println(s)


     }

}
