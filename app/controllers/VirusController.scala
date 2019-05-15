package controllers

import javax.inject.Inject

import dao._
import models.Tables._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import utils._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class VirusController @Inject()(virusdao: virusDao) extends Controller {


  //插入测试数据
  def insertVirus = Action {
    /*    val file = FileUtils.readLines(new File("D:/复旦病原菌数据库资料/viruses.txt")).asScala

        var family = ""
        var genus = ""

        val row = file.drop(2).map { x =>

          val column = x.split("\t")

          def getInfo(i: Int): String = {
            column.drop(i).headOption.getOrElse("")
          }

          family = if(column.head == ""){family}else{column.head}
          genus = if(column.drop(1).head == ""){genus}else{column.drop(1).head}
          val species = if(getInfo(2) == ""){getInfo(3)}else{getInfo(2)}

          VirusRow(0,family,genus,species,getInfo(3),getInfo(4),getInfo(5),getInfo(6),getInfo(7),getInfo(8),getInfo(9),getInfo(10)
            ,getInfo(11),getInfo(12),getInfo(13),getInfo(14),getInfo(15),getInfo(16),getInfo(17),getInfo(18),getInfo(19),getInfo(20))

        }
        Await.result(virusdao.insertAll(row), Duration.Inf)*/
    Ok(Json.toJson("1"))
  }

  def toTree = Action {implicit request=>
    Ok(views.html.english.virus.tree())
  }

  def getTreeJson = Action.async {

    virusdao.getAll.map { x =>

      val species = x.map { z =>
        val y = transForm.transVirusRow(z)
        val html = "<a onclick='getInfo(this)' id='" + y.id + "' style='color: inherit;'>" + y.name + "</a>"
        (y.family, y.genus, Json.obj("text" -> html, "nodes" -> ""))
      }

      val genus = x.map { z =>
        val y = transForm.transVirusRow(z)
        val node = species.filter(_._1 == y.family).filter(_._2 == y.genus).map(_._3).distinct
        (y.order,y.family, Json.obj("text" -> y.genus, "tags" -> Array(node.size), "nodes" -> node))
      }

      val family = x.map { z =>
        val y = transForm.transVirusRow(z)
        val node = genus.filter(_._1 == y.order).filter(_._2 == y.family).map(_._3).distinct
        (y.order, Json.obj("text" -> y.family, "tags" -> Array(node.size), "nodes" -> node))
      }.distinct

      val order = x.map{z=>
        val y = transForm.transVirusRow(z)
        y.order
      }.distinct

      val nodes = order.map { y =>
        val node = family.filter(_._1 == y).map(_._2).distinct
        Json.obj("text" -> y, "tags" -> Array(node.size), "nodes" -> node)
      }
      Ok(Json.toJson(nodes))

    }
  }

  def getInfoById(id: Int) = Action.async {
    virusdao.getById(id).map { z=>
      val x = transForm.transVirusRow(z)

      val html =
        s"""
           |                    <tr>
           |                        <th>名称</th>
           |                        <td>${x.name}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>目（order）</th>
           |                        <td>${x.order}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>科（family）</th>
           |                        <td>${x.family}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>亚科（subfamily）</th>
           |                        <td>${x.subfamily}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>属（genus）</th>
           |                        <td>${x.genus}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>种（species）</th>
           |                        <td>${x.species}</td>
           |                    </tr>

           |                    <tr>
           |                        <th>Genome Composition:dsDNA</th>
           |                        <td>${x.genome_composition}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染人</th>
           |                        <td>${x.isPeople}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染动物</th>
           |                        <td>${x.isAnimal}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染植物</th>
           |                        <td>${x.isPlant}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>传播途径</th>
           |                        <td>${x.tranRoute}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>感染剂量</th>
           |                        <td>${x.infectiveDose}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>体外存活条件</th>
           |                        <td>${x.survivalCondition}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效消毒剂</th>
           |                        <td>${x.isSanitizer}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>预后/死亡率</th>
           |                        <td>${x.deathRate}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效治疗药物</th>
           |                        <td>${x.isMedicine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>疫苗</th>
           |                        <td>${x.isVaccine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>中国（2006）</th>
           |                        <td>${x.chinaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>美国NIH (2016)</th>
           |                        <td>${x.usanihRisk}</td>
           |                    </tr>
           |
           |                    <tr>
           |                        <th>美国BMBL (2009)*</th>
           |                        <td>${x.usabmblRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>澳大利亚/新西兰 Australia/New Zealand  (2010)</th>
           |                        <td>${x.australiaNewzealandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>比利时 Belgium (2008)</th>
           |                        <td>${x.belgiumRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>加拿大 Canada (2015)</th>
           |                        <td>${x.canadaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>欧盟 EU (2000)</th>
           |                        <td>${x.euRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>德国 Germany (2013)</th>
           |                        <td>${x.germanyRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>日本 Japan</th>
           |                        <td>${x.japanRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>新加坡 Singapore</th>
           |                        <td>${x.singaporeRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>瑞士 Switzerland</th>
           |                        <td>${x.switzerlandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>英国 UK (2013)</th>
           |                        <td>${x.ukRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent CDC</th>
           |                        <td>${x.selectAgentCdc}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent USDA</th>
           |                        <td>${x.selectAgentUsda}</td>
           |                    </tr>
        """.stripMargin

      Ok(Json.obj("html" -> html, "name" -> x.name))
    }

  }

  def toBrowse = Action {implicit request=>
    Ok(views.html.english.virus.browse())
  }

  def getAllVirus = Action.async {
    virusdao.getAll.map { x =>
      val row = x.sortBy(_.tail.head).map { y =>
       getJson(y)
      }
      Ok(Json.toJson(row))
    }
  }

  def getAllOrder = Action.async{
    virusdao.getAll.map{x=>
      val row = "All" +: x.map(y=>transForm.transVirusRow(y).order).sorted.distinct
      Ok(Json.toJson(row))
    }
  }


  def getAllFamily = Action.async {
    virusdao.getAll.map { x =>
      val row = "All" +: x.map(y=>transForm.transVirusRow(y).family).sorted.distinct
      Ok(Json.toJson(row))
    }
  }

  def getAllGenus = Action.async {
    virusdao.getAll.map { x =>
      val row = "All" +: x.map(y=>transForm.transVirusRow(y).genus).sorted.distinct
      Ok(Json.toJson(row))
    }
  }

  def getAllSpecies = Action.async {
    virusdao.getAll.map { x =>
      val row = "All" +: x.map(y=>transForm.transVirusRow(y).species).sorted.distinct
      Ok(Json.toJson(row))
    }
  }

  def toSearchByTax = Action {implicit request=>
    Ok(views.html.english.virus.searchByTax())
  }

  def getByOrder(order:String) = Action.async{implicit request=>
    virusdao.getByOrder(order).map{x=>
      val row = x.map(y => transForm.transVirusRow(y))
      val family = "All" +: row.map(_.family).sorted.distinct
      Ok(Json.obj("family" -> family))
    }
  }

  def getByFamily(family:String) = Action.async{implicit request=>
    virusdao.getByFamily(family).map{x=>
      val row = x.map(y => transForm.transVirusRow(y))
      val genus = "All" +: row.map(_.genus).sorted.distinct
      Ok(Json.obj("genus" -> genus))
    }
  }

  def searchByTax = Action.async { implicit request =>
    val data = transForm.searchByTaxForm.bindFromRequest.get
    val order = data.order
    val family = data.family.getOrElse("All")
    val genus = data.genus.getOrElse("All")
    val isPeople = data.isPeople
    val tranRoute = data.tranRoute.getOrElse("All")
    virusdao.getByTax(order,family, genus,isPeople,tranRoute).map { x =>
      val row = x.map { y =>
          getJson(y)
      }
      Ok(Json.toJson(row))
    }
  }

  def getJson(y:VirusRow) = {
    val t = transForm.transVirusRow(y)

    Json.obj("id" -> t.id,"name"-> t.name ,"chinese" -> t.chinese, "order"-> t.order, "family" -> t.family, "subfamily" -> t.subfamily ,"genus" -> t.genus,
      "species" -> t.species,"genome_composition" -> t.genome_composition,"isPeople" -> t.isPeople, "isAnimal" -> t.isAnimal, "isPlant" -> t.isPlant,
      "tranRoute" -> t.tranRoute, "infectiveDose" -> t.infectiveDose, "survivalCondition" -> t.survivalCondition,
      "isSanitizer" -> t.isSanitizer, "deathRate" -> t.deathRate, "isMedicine" -> t.isMedicine,
      "isVaccine" -> t.isVaccine, "chinaRisk" -> t.chinaRisk, "chinaNotes" -> t.chinaNotes,
      "usanihRisk" -> t.usabmblRisk, "usanihNotes" -> t.usanihNotes, "usabmblRisk" -> t.usabmblRisk,
      "usabmblNotes" ->  t.usabmblNotes, "australiaNewzealandRisk" -> t.australiaNewzealandRisk,
      "australiaNewzealandNotes" -> t.australiaNewzealandNotes, "belgiumRisk" -> t.belgiumRisk,
      "belgiumNotes"-> t.belgiumNotes, "canadaRisk" -> t.canadaRisk, "canadaNotes" -> t.canadaNotes,
      "euRisk" -> t.euRisk, "euNotes" -> t.euNotes, "germanyRisk" -> t.germanyRisk, "germanyNotes" -> t.germanyNotes,
      "japanRisk" -> t.japanRisk, "japanNotes" -> t.japanNotes, "singaporeRisk" -> t.singaporeRisk,
      "singaporeNotes" -> t.singaporeNotes,  "switzerlandRisk" -> t.switzerlandRisk,
      "switzerlandNotes" -> t.switzerlandNotes, "ukRisk" -> t.ukRisk, "ukNotes" -> t.ukNotes,
      "selectAgentCdc" -> t.selectAgentCdc, "selectAgentUsda" -> t.selectAgentUsda)
  }

  def moreInfo(id: Int) = Action.async {implicit request=>
    virusdao.getById(id).map { x =>
      Ok(views.html.english.virus.moreInfo(transForm.transVirusRow(x)))
    }
  }

  case class virusData1(name : Option[String] ,chinese:Option[String] , order : Option[String], family : Option[String],subfamily:Option[String],
                        genus : Option[String], species : Option[String],genome_composition:Option[String],
                        isPeople : Option[String], isAnimal : Option[String], isPlant : Option[String],
                        tranRoute : Option[String], infectiveDose : Option[String], survivalCondition : Option[String],
                        isSanitizer : Option[String], deathRate : Option[String], isMedicine : Option[String],
                        isVaccine : Option[String], chinaRisk : Option[String], chinaNotes : Option[String],
                        usanihRisk : Option[String], usanihNotes : Option[String])

  val virusForm1 = Form(
    mapping(
      "name"-> optional(text),"chinese" -> optional(text), "order"-> optional(text), "family" -> optional(text),"subfamily" -> optional(text),
      "genus" -> optional(text), "species" -> optional(text),"genome_composition" -> optional(text),
      "isPeople" -> optional(text), "isAnimal" -> optional(text), "isPlant" -> optional(text),
      "tranRoute" -> optional(text), "infectiveDose" -> optional(text), "survivalCondition" -> optional(text),
      "isSanitizer" -> optional(text), "deathRate" -> optional(text), "isMedicine" -> optional(text),
      "isVaccine" -> optional(text), "chinaRisk" -> optional(text), "chinaNotes" -> optional(text),
      "usanihRisk" -> optional(text), "usanihNotes" -> optional(text)
    )(virusData1.apply)(virusData1.unapply)
  )

  case class virus(usabmblNotes : Option[String])

  val virusForm = Form(
    mapping(
      "usabmblNotes" -> optional(text)
    )(virus.apply)(virus.unapply)
  )

  case class virusData2(usabmblRisk : Option[String],usabmblNotes : Option[String],australiaNewzealandRisk : Option[String], australiaNewzealandNotes : Option[String],
                        belgiumRisk : Option[String], belgiumNotes : Option[String], canadaRisk : Option[String],
                        canadaNotes : Option[String], euRisk : Option[String], euNotes : Option[String],
                        germanyRisk : Option[String], germanyNotes : Option[String], japanRisk : Option[String],
                        japanNotes : Option[String], singaporeRisk : Option[String], singaporeNotes : Option[String],
                        switzerlandRisk : Option[String], switzerlandNotes : Option[String], ukRisk : Option[String],
                        ukNotes : Option[String], selectAgentCdc : Option[String], selectAgentUsda : Option[String])

  val virusForm2 = Form(
    mapping(
      "usabmblRisk" -> optional(text),"usabmblNotes" -> optional(text),
      "australiaNewzealandRisk" -> optional(text), "australiaNewzealandNotes" -> optional(text),
      "belgiumRisk" -> optional(text), "belgiumNotes"-> optional(text), "canadaRisk" -> optional(text),
      "canadaNotes" -> optional(text), "euRisk" -> optional(text), "euNotes" -> optional(text),
      "germanyRisk" -> optional(text), "germanyNotes" -> optional(text), "japanRisk" -> optional(text),
      "japanNotes" -> optional(text), "singaporeRisk" -> optional(text), "singaporeNotes" -> optional(text),
      "switzerlandRisk" -> optional(text), "switzerlandNotes" -> optional(text), "ukRisk" -> optional(text),
      "ukNotes" -> optional(text), "selectAgentCdc" -> optional(text), "selectAgentUsda" -> optional(text)
    )(virusData2.apply)(virusData2.unapply)
  )

  def dealWithF1(b: virusData1, id: String): Array[String] = {
    Array(id, b.name.getOrElse(""),b.chinese.getOrElse(""), b.order.getOrElse(""), b.family.getOrElse(""), b.subfamily.getOrElse(""),
      b.genus.getOrElse(""), b.species.getOrElse(""),b.genome_composition.getOrElse(""), b.isPeople.getOrElse(""),
      b.isAnimal.getOrElse(""), b.isPlant.getOrElse(""), b.tranRoute.getOrElse(""), b.infectiveDose.getOrElse(""),
      b.survivalCondition.getOrElse(""), b.isSanitizer.getOrElse(""), b.deathRate.getOrElse(""),
      b.isMedicine.getOrElse(""), b.isVaccine.getOrElse(""), b.chinaRisk.getOrElse(""), b.chinaNotes.getOrElse(""),
      b.usanihRisk.getOrElse(""), b.usanihNotes.getOrElse("") )
  }

  def dealWithF2(b: virusData2): Array[String] = {
    Array(b.usabmblRisk.getOrElse(""),b.usabmblNotes.getOrElse(""),b.australiaNewzealandRisk.getOrElse(""), b.australiaNewzealandNotes.getOrElse(""), b.belgiumRisk.getOrElse(""),
      b.belgiumNotes.getOrElse(""), b.canadaRisk.getOrElse(""), b.canadaNotes.getOrElse(""), b.euRisk.getOrElse(""),
      b.euNotes.getOrElse(""), b.germanyRisk.getOrElse(""), b.germanyNotes.getOrElse(""), b.japanRisk.getOrElse(""),
      b.japanNotes.getOrElse(""), b.singaporeRisk.getOrElse(""), b.singaporeNotes.getOrElse(""),
      b.switzerlandRisk.getOrElse(""),
      b.switzerlandNotes.getOrElse(""), b.ukRisk.getOrElse(""), b.ukNotes.getOrElse(""), b.selectAgentCdc.getOrElse(""),
      b.selectAgentUsda.getOrElse(""))
  }

  def uploadBefore = Action{implicit request=>
    Ok(views.html.english.virus.uploadVirus())
  }

  def uploadVirus = Action { implicit request =>
    var valid = "true"
    var message = ""
    try {
      val f1 = virusForm1.bindFromRequest.get
      val f2 = virusForm2.bindFromRequest.get
      val b1 = dealWithF1(f1, "0")
      val b2 = dealWithF2(f2)
      val b = b1 ++ b2
      val row = transForm.getVirusRow(b)
      Await.result(virusdao.addVirus(row), Duration.Inf)
    } catch {
      case e: Exception => valid = "false"; message = e.getMessage
    }
    Ok(Json.obj("valid" -> valid, "message" -> message))
  }

  def manageBefore = Action { implicit request =>
    Ok(views.html.english.virus.manageVirus())
  }

  def updateBefore(id: Int) = Action.async { implicit request =>
    virusdao.getById(id).map { x =>
      Ok(views.html.english.virus.updateVirus(transForm.transVirusRow(x)))
    }
  }

  def updateVirus(id: String) = Action { implicit request =>
    var valid = "true"
    var message = ""
    try {
      val f1 = virusForm1.bindFromRequest.get
      val f2 = virusForm2.bindFromRequest.get
      val f3 = virusForm.bindFromRequest.get.usabmblNotes.getOrElse("")
      val b1 = dealWithF1(f1, id)
      val b2 = dealWithF2(f2)
      val b = b1 ++ Array(f3) ++ b2
      val row = transForm.getVirusRow(b)
      Await.result(virusdao.updateVirus(id.toInt, row), Duration.Inf)
    } catch {
      case e: Exception => valid = "false"; message = e.getMessage
    }
    Ok(Json.obj("valid" -> valid, "message" -> message))
  }

  def deleteVirus(id: Int) = Action.async { implicit request =>
    virusdao.deleteVirus(id).map { x =>
      Ok(Json.toJson("success"))
    }
  }



}
