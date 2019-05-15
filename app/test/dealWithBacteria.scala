package test

import java.io.File

import org.apache.commons.io.FileUtils

import scala.collection.JavaConverters._

object dealWithBacteria {


  /*def main(args: Array[String]): Unit = {
    val f = FileUtils.readFileToString(new File("D:\\复旦病原菌数据库资料\\致病菌数据信息 2018.10.12\\数据信息 2018.10.12/Bacteria-2.txt"), "UTF-8")
    val buffer = f.split("病原微生物特性")

    val pa = buffer.drop(1).map { x =>
      val all = x.split("[\n|\r]").distinct.drop(1).mkString("\n")
      val head = all.split("各国危害等级\n")
      val info = head.head.split("\n")

      // val other = Array("亚科（Subfamily）","亚科（subfamily）","亚科（family）","亚科（Subfamily）")

      val other = "亚目（Suborder）"

      val qs = info(2).split(":").head
      val infos = if(other.contains(qs)){
        info
      }else{
        val xx= info.take(2) :+ "亚目（Suborder）:"
        xx ++ info.drop(2)
      }


      val q = infos.map { y =>
        val element = y.split(":")
        val size = element.size


        val w = if (size == 1) {
          " "
        } else if (size > 2) {
          element.drop(1).dropRight(1).map(_.split("\t").head).mkString("\t") + "\t" + element.last
        } else {
          element.last
        }
        w
      }.diff(Array("null"))

      val la = head.last.split("\n").map { y =>
        val element = y.split(":")
        val size = element.size
        val w = if (size == 1) {
          " \t "
        }else if( size == 2){
          element.last + "\t "
        }
        else {
          element.drop(1).map(_.split("\t").head).head + "\t" + element.last
        }
        w
      }

      q.mkString("\t") + "\t" + la.mkString("\t")
    }.toBuffer

    val head = "名称\t目（order）\t亚目（Suborder）\t科（family）\t属（genus）\t种（species）\t是否感染人\t是否感染动物\t是否感染植物\t传播途径\t感染剂量\t体外存活条件\t有效消毒剂\t预后/死亡率\t有效治疗药物\t疫苗" +
      "\t中国（2006）\tnotes\t美国NIH (2016)\tnotes\t美国BMBL (2009)*\tnotes\t澳大利亚/新西兰 Australia/New Zealand  (2010)\tnotes\t比利时 Belgium (2008)\tnotes\t加拿大 Canada (2015)\t" +
      "notes\t欧盟 EU (2000)\tnotes\t德国 Germany (2013)\tnotes\t日本 Japan\tnotes\t新加坡 Singapore\tnotes\tSingapor Schedule\tnotes\t瑞士 Switzerland\tnotes\t英国 UK (2013)\t" +
      "notes\tSelect Agent CDC\tSelect Agent USDA"

    //最后手动添加多余信息
    FileUtils.writeLines(new File("D:\\复旦病原菌数据库资料\\致病菌数据信息 2018.10.12\\数据信息 2018.10.12/Bacteria-2table.txt.txt"), "UTF-8",(head +: pa).asJava)
  }*/

  def main(args: Array[String]): Unit = {
    val f = FileUtils.readFileToString(new File("D:\\复旦病原菌数据库资料\\致病菌数据信息 2018.10.12\\数据信息 2018.10.12/Bacteria-2.txt"), "UTF-8")
    val buffer = f.split("病原微生物特性")

    val pa = buffer.drop(1).map { x =>
      val all = x.split("[\n|\r]").distinct.drop(1).mkString("\n")
      val head = all.split("各国危害等级\n")
      val info = head.head.split("\n")

      val i = info.head.trim.split("\t")

      val name = info(1).split(":")
      val n = if(name.size == 1){
        i.head + " " +i.last
      }else{
        name.last
      }
      val q = info.drop(6).map { y =>
        val element = y.split(":")
        val size = element.size

        val w =if (size == 1) {
          " "
        } else if (size > 2) {
          element.drop(1).dropRight(1).map(_.split("\t").head).mkString("\t") + "\t" + element.last
        } else {
          element.last
        }
        w
      }

      val la = head.last.split("\n").map { y =>
        val element = y.split(":")
        val size = element.size
        val w = if (size == 1) {
          " \t "
        }else if( size == 2){
          element.last + "\t "
        }
        else {
          element.drop(1).map(_.split("\t").head).head + "\t" + element.last
        }
        w
      }

      n + "\t \t \t" +  i.head + "\t" + i.last + "\t" + q.mkString("\t") + "\t" + la.mkString("\t")
    }.toBuffer

    val head = "名称\t目（order）\t科（family）\t属（genus）\t种（species）\t是否感染人\t是否感染动物\t是否感染植物\t传播途径\t感染剂量\t体外存活条件\t有效消毒剂\t预后/死亡率\t有效治疗药物\t疫苗" +
      "\t中国（2006）\tnotes\t美国NIH (2016)\tnotes\t美国BMBL (2009)*\tnotes\t澳大利亚/新西兰 Australia/New Zealand  (2010)\tnotes\t比利时 Belgium (2008)\tnotes\t加拿大 Canada (2015)\t" +
      "notes\t欧盟 EU (2000)\tnotes\t德国 Germany (2013)\tnotes\t日本 Japan\tnotes\t新加坡 Singapore\tnotes\tSingapor Schedule\tnotes\t瑞士 Switzerland\tnotes\t英国 UK (2013)\t" +
      "notes\tSelect Agent CDC\tSelect Agent USDA"

    //最后手动添加多余信息
    FileUtils.writeLines(new File("D:\\复旦病原菌数据库资料\\致病菌数据信息 2018.10.12\\数据信息 2018.10.12/Bacteria11.20.txt"), "UTF-8",(head +: pa).asJava)
  }

}
