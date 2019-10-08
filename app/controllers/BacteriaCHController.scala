package controllers

import java.io.File

import dao._
import javax.inject.Inject
import org.apache.commons.io.FileUtils
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, Controller}
import utils.transForm

import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration


class BacteriaCHController @Inject()(bacteriadao: bacteriaDao) extends Controller {


  //插入测试数据
  def insertBacteria = Action {
    val file = FileUtils.readLines(new File("D:/复旦病原菌数据库资料/bacterias.txt")).asScala

    val row = file.drop(2).map { x =>

      val column = x.split("\t")

      def getInfo(i: Int): String = {
        column.drop(i).headOption.getOrElse("")
      }

      /*      BacteriaRow(0, column.head, getInfo(1), getInfo(2), getInfo(3), getInfo(4), getInfo(5), getInfo(6), getInfo(7), getInfo(8), getInfo(9), getInfo(10)
              , getInfo(11), getInfo(12), getInfo(13), getInfo(14), getInfo(15), getInfo(16), getInfo(17))*/
    }
    //Await.result(bacteriadao.insertAll(row), Duration.Inf)
    Ok(Json.toJson("1"))
  }

  def browse = Action { implicit request =>
    Ok(views.html.chinese.bacteria.browse())
  }


  def getAllInfo : Action[AnyContent] = Action.async {
    bacteriadao.getAllinfo.map { x =>
      val row = x.sortBy(_.tail.head).map { y =>
        transForm.getBacteriaJson(y)
      }
      Ok(Json.toJson(row))
    }
  }


  def searchByTaxBefore = Action { implicit request =>
    Ok(views.html.chinese.bacteria.searchByTax())
  }

  def getAllSelect = Action.async { implicit request =>
    bacteriadao.getAllinfo.map { x =>
      val row = x.map(y => transForm.transBacteriaRow(y))
      val order = "All" +: row.map(_.order).sorted.distinct
      val family = "All" +: row.map(_.family).sorted.distinct
      val genus = "All" +: row.map(_.genus).sorted.distinct
      Ok(Json.obj("order" -> order, "family" -> family, "genus" -> genus))
    }
  }

  def getByOrder(order: String) = Action.async { implicit request =>
    bacteriadao.getByOrder(order).map { x =>
      val row = x.map(y => transForm.transBacteriaRow(y))
      val family = "All" +: row.map(_.family).sorted.distinct
      val genus = "All" +: row.map(_.genus).sorted.distinct
      Ok(Json.obj("family" -> family, "genus" -> genus))
    }
  }

  def getByFamily(family: String) = Action.async { implicit request =>
    bacteriadao.getByFamily(family).map { x =>
      val row = x.map(y => transForm.transBacteriaRow(y))
      val order = row.map(_.order).distinct
      val genus = "All" +: row.map(_.genus).sorted.distinct
      Ok(Json.obj("order" -> order, "genus" -> genus))
    }
  }

  def getByGenus(genus: String) = Action.async { implicit request =>
    bacteriadao.getByGenus(genus).map { x =>
      val row = x.map(y => transForm.transBacteriaRow(y))
      val order = row.map(_.order).distinct
      val family = row.map(_.family).distinct
      Ok(Json.obj("order" -> order, "family" -> family))
    }
  }

  def searchByTax = Action.async { implicit request =>
    val data = transForm.searchByTaxForm.bindFromRequest.get
    val order = data.order
    val family = data.family.getOrElse("All")
    val genus = data.genus.getOrElse("All")
    val isPeople = data.isPeople
    val tranRoute = data.tranRoute.getOrElse("All")

    bacteriadao.getByTax(order, family, genus, isPeople, tranRoute).map { x =>
      val row = x.map { y =>
        transForm.getBacteriaJson(y)
      }
      Ok(Json.toJson(row))
    }
  }

  def moreInfo(id: Int) = Action.async { implicit request =>
    bacteriadao.getById(id).map { x =>
      Ok(views.html.chinese.bacteria.moreInfo(transForm.transBacteriaRow(x)))
    }
  }

  def toRandomForest = Action { implicit request =>
    Ok(views.html.chinese.bacteria.ramdomForest())
  }


  def uploadBefore = Action { implicit request =>
    Ok(views.html.chinese.bacteria.uploadBacteria())
  }


  case class bacteriaData1(name: Option[String],chinese:Option[String], order: Option[String], suborder: Option[String],
                           family: Option[String], genus: Option[String], species: Option[String],
                           isPeople: Option[String], isAnimal: Option[String], isPlant: Option[String],
                           tranRoute: Option[String], infectiveDose: Option[String],
                           survivalCondition: Option[String], isSanitizer: Option[String], deathRate: Option[String],
                           isMedicine: Option[String], isVaccine: Option[String], chinaRisk: Option[String],
                           chinaNotes: Option[String], usanihRisk: Option[String], usanihNotes: Option[String]
                           )

  val bacteriaForm1 = Form(
    mapping(
      "name" -> optional(text),"chinese" -> optional(text), "order" -> optional(text), "suborder" -> optional(text), "family" -> optional(text),
      "genus" -> optional(text), "species" -> optional(text), "isPeople" -> optional(text),
      "isAnimal" -> optional(text), "isPlant" -> optional(text), "tranRoute" -> optional(text),
      "infectiveDose" -> optional(text), "survivalCondition" -> optional(text), "isSanitizer" -> optional(text),
      "deathRate" -> optional(text), "isMedicine" -> optional(text), "isVaccine" -> optional(text),
      "chinaRisk" -> optional(text), "chinaNotes" -> optional(text), "usanihRisk" -> optional(text),
      "usanihNotes" -> optional(text)
    )(bacteriaData1.apply)(bacteriaData1.unapply)
  )

  case class bacteriaData2(usabmblRisk: Option[String], usabmblNotes: Option[String],australiaNewzealandRisk: Option[String], australiaNewzealandNotes: Option[String],
                           belgiumRisk: Option[String], belgiumNotes: Option[String], canadaRisk: Option[String],
                           canadaNotes: Option[String], euRisk: Option[String], euNotes: Option[String],
                           germanyRisk: Option[String], germanyNotes: Option[String], japanRisk: Option[String],
                           japanNotes: Option[String], singaporeRisk: Option[String], singaporeNotes: Option[String],
                           switzerlandRisk: Option[String], switzerlandNotes: Option[String], ukRisk: Option[String],
                           ukNotes: Option[String], selectAgentCdc: Option[String], selectAgentUsda: Option[String])

  val bacteriaForm2 = Form(
    mapping(
      "usabmblRisk" -> optional(text), "usabmblNotes" -> optional(text),
      "australiaNewzealandRisk" -> optional(text), "australiaNewzealandNotes" -> optional(text),
      "belgiumRisk" -> optional(text), "belgiumNotes" -> optional(text), "canadaRisk" -> optional(text),
      "canadaNotes" -> optional(text), "euRisk" -> optional(text), "euNotes" -> optional(text),
      "germanyRisk" -> optional(text), "germanyNotes" -> optional(text), "japanRisk" -> optional(text),
      "japanNotes" -> optional(text), "singaporeRisk" -> optional(text), "singaporeNotes" -> optional(text),
      "switzerlandRisk" -> optional(text), "switzerlandNotes" -> optional(text), "ukRisk" -> optional(text),
      "ukNotes" -> optional(text), "selectAgentCdc" -> optional(text), "selectAgentUsda" -> optional(text)
    )(bacteriaData2.apply)(bacteriaData2.unapply)
  )

  def dealWithF1(b: bacteriaData1, id: String): Array[String] = {
    Array(id, b.name.getOrElse(""),b.chinese.getOrElse(""), b.order.getOrElse(""), b.suborder.getOrElse(""), b.family.getOrElse(""), b.genus.getOrElse(""),
      b.species.getOrElse(""), b.isPeople.getOrElse(""), b.isAnimal.getOrElse(""), b.isPlant.getOrElse(""),
      b.tranRoute.getOrElse(""), b.infectiveDose.getOrElse(""), b.survivalCondition.getOrElse(""),
      b.isSanitizer.getOrElse(""), b.deathRate.getOrElse(""), b.isMedicine.getOrElse(""), b.isVaccine.getOrElse(""),
      b.chinaRisk.getOrElse(""), b.chinaNotes.getOrElse(""), b.usanihRisk.getOrElse(""), b.usanihNotes.getOrElse("")
    )
  }

  def dealWithF2(b: bacteriaData2): Array[String] = {
    Array(  b.usabmblRisk.getOrElse(""), b.usabmblNotes.getOrElse(""),b.australiaNewzealandRisk.getOrElse(""),
      b.australiaNewzealandNotes.getOrElse(""), b.belgiumRisk.getOrElse(""), b.belgiumNotes.getOrElse(""),
      b.canadaRisk.getOrElse(""), b.canadaNotes.getOrElse(""), b.euRisk.getOrElse(""), b.euNotes.getOrElse(""),
      b.germanyRisk.getOrElse(""), b.germanyNotes.getOrElse(""), b.japanRisk.getOrElse(""), b.japanNotes.getOrElse(""),
      b.singaporeRisk.getOrElse(""), b.singaporeNotes.getOrElse(""), b.switzerlandRisk.getOrElse(""),
      b.switzerlandNotes.getOrElse(""), b.ukRisk.getOrElse(""), b.ukNotes.getOrElse(""), b.selectAgentCdc.getOrElse(""),
      b.selectAgentUsda.getOrElse(""))
  }

  def uploadBacteria = Action { implicit request =>
    var valid = "true"
    var message = ""
    try {
      val f1 = bacteriaForm1.bindFromRequest.get
      val f2 = bacteriaForm2.bindFromRequest.get
      val b1 = dealWithF1(f1, "0")
      val b2 = dealWithF2(f2)
      val b = b1 ++ b2
      val row = transForm.getBacteriaRow(b)
      Await.result(bacteriadao.addBacteria(row), Duration.Inf)
    } catch {
      case e: Exception => valid = "false"; message = e.getMessage
    }
    Ok(Json.obj("valid" -> valid, "message" -> message))
  }

  def manageBacteriaBefore = Action { implicit request =>
    Ok(views.html.chinese.bacteria.manageBacteria())
  }

  def updateBefore(id: Int) = Action.async { implicit request =>
    bacteriadao.getById(id).map { x =>
      Ok(views.html.chinese.bacteria.updateBacteria(transForm.transBacteriaRow(x)))
    }
  }

  def updateBacteria(id: String) = Action { implicit request =>
    var valid = "true"
    var message = ""
    try {
      val f1 = bacteriaForm1.bindFromRequest.get
      val f2 = bacteriaForm2.bindFromRequest.get
      val b1 = dealWithF1(f1, id)
      val b2 = dealWithF2(f2)
      val b = b1 ++ b2
      val row = transForm.getBacteriaRow(b)
      Await.result(bacteriadao.updateBacteria(id.toInt, row), Duration.Inf)
    } catch {
      case e: Exception => valid = "false"; message = e.getMessage
    }
    Ok(Json.obj("valid" -> valid, "message" -> message))
  }

  def deleteBacteria(id: Int) = Action.async { implicit request =>
    bacteriadao.deleteBacteria(id).map { x =>
      Ok(Json.toJson("success"))
    }
  }

}
