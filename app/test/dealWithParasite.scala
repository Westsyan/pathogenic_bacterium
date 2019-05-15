package test

import java.io.File

import org.apache.commons.io.FileUtils
import scala.collection.JavaConverters._

object dealWithParasite {


  def main(args: Array[String]): Unit = {
    val f = FileUtils.readFileToString(new File("D:\\复旦病原菌数据库资料\\致病菌数据信息 2018.10.12\\数据信息 2018.10.12/Parasite.txt"), "UTF-8")
    val buffer = f.split("病原微生物特性")

    val pa = buffer.drop(1).map { x =>
      val all = x.split("[\n|\r]").distinct.drop(1).mkString("\n")
      val head = all.split("各国危害等级\n")
      val info = head.head.split("\n")

      val other = Array("亚种（Subspecies）","亚科（Subfamily）","亚目（Suborder）","Superfamily")
      val q = info.map { y =>
        val element = y.split(":")
        val size = element.size


        val w = if(other.contains(element.head)){
            "null"
          }else if (size == 1) {
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

    val head = "名称\t目（order）\t科（family）\t属（genus）\t种（species）\t是否感染人\t是否感染动物\t是否感染植物\t传播途径\t感染剂量\t体外存活条件\t有效消毒剂\t预后/死亡率\t有效治疗药物\t疫苗" +
      "\t中国（2006）\tnotes\t美国NIH (2016)\tnotes\t美国BMBL (2009)*\tnotes\t澳大利亚/新西兰 Australia/New Zealand  (2010)\tnotes\t比利时 Belgium (2008)\tnotes\t加拿大 Canada (2015)\t" +
      "notes\t欧盟 EU (2000)\tnotes\t德国 Germany (2013)\tnotes\t日本 Japan\tnotes\t新加坡 Singapore\tnotes\tSingapor Schedule\tnotes\t瑞士 Switzerland\tnotes\t英国 UK (2013)\t" +
      "notes\tSelect Agent CDC\tSelect Agent USDA"

    //最后手动添加多余信息
    FileUtils.writeLines(new File("D:\\复旦病原菌数据库资料\\致病菌数据信息 2018.10.12\\数据信息 2018.10.12/Parasite1.txt"), "UTF-8",(head +: pa).asJava)
  }
}
