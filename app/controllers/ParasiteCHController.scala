package controllers

import dao._
import javax.inject.Inject
import models.Tables._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import utils.transForm

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class ParasiteCHController @Inject()(parasitedao: parasiteDao) extends Controller {


  def browse = Action {implicit request=>
    Ok(views.html.chinese.parasite.browse())
  }

  def getJson(y:ParasiteRow) = {
    val t = transForm.transParasiteRow(y)

    Json.obj("id" -> t.id,"name"-> t.name ,"chinese" -> t.chinese, "order"-> t.order, "family" -> t.family, "genus" -> t.genus,
      "species" -> t.species, "isPeople" -> t.isPeople, "isAnimal" -> t.isAnimal, "isPlant" -> t.isPlant,
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

  def getAllParasite = Action.async {
    parasitedao.getAllParasite.map { x =>
      val json = x.sortBy(_.tail.head).map { y =>
        getJson(y)
      }
      Ok(Json.toJson(json))
    }
  }

  def moreInfo(id:Int) = Action.async{implicit request=>
    parasitedao.getById(id).map{x=>
      Ok(views.html.chinese.parasite.moreInfo(transForm.transParasiteRow(x)))
    }
  }

  def searchByTaxBefore = Action{implicit request=>
    Ok(views.html.chinese.parasite.searchByTax())
  }

  def getAllSelect = Action.async{implicit request=>
    parasitedao.getAllParasite.map{x=>
      val row = x.map(y=>transForm.transParasiteRow(y))
      val order = "All" +: row.map(_.order).sorted.distinct
      val family = "All" +: row.map(_.family).sorted.distinct
      val genus = "All" +: row.map(_.genus).sorted.distinct
      Ok(Json.obj("order" -> order,"family" -> family,"genus" -> genus))
    }
  }

  def getByOrder(order:String) = Action.async{implicit request=>
    parasitedao.getByOrder(order).map{x=>
      val row = x.map(y => transForm.transParasiteRow(y))
      val family = "All" +: row.map(_.family).sorted.distinct
      Ok(Json.obj("family" -> family))
    }
  }

  def getByFamily(family:String) = Action.async{implicit request=>
    parasitedao.getByFamily(family).map{x=>
      val row = x.map(y => transForm.transParasiteRow(y))
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
    parasitedao.getByTax(order,family, genus,isPeople,tranRoute).map { x =>
      val row = x.map { y =>
        getJson(y)
      }
      Ok(Json.toJson(row))
    }
  }

  case class parasiteData1(name : Option[String],chinese:Option[String], order : Option[String],  family : Option[String], genus : Option[String],
                           species : Option[String], isPeople : Option[String], isAnimal : Option[String],
                           isPlant : Option[String], tranRoute : Option[String], infectiveDose : Option[String],
                           survivalCondition : Option[String], isSanitizer : Option[String], deathRate : Option[String],
                           isMedicine : Option[String], isVaccine : Option[String], chinaRisk : Option[String],
                           chinaNotes : Option[String], usanihRisk : Option[String], usanihNotes : Option[String],
                           usabmblRisk : Option[String], usabmblNotes : Option[String])

  val parasiteForm1 = Form(
    mapping(
      "name"-> optional(text), "chinese" -> optional(text) ,"order"-> optional(text), "family" -> optional(text), "genus" -> optional(text),
      "species" -> optional(text), "isPeople" -> optional(text), "isAnimal" -> optional(text), "isPlant" -> optional(text),
      "tranRoute" -> optional(text), "infectiveDose" -> optional(text), "survivalCondition" -> optional(text),
      "isSanitizer" -> optional(text), "deathRate" -> optional(text), "isMedicine" -> optional(text),
      "isVaccine" -> optional(text), "chinaRisk" -> optional(text), "chinaNotes" -> optional(text),
      "usanihRisk" -> optional(text), "usanihNotes" -> optional(text),"usabmblRisk" -> optional(text),"usabmblNotes" -> optional(text)
    )(parasiteData1.apply)(parasiteData1.unapply)
  )

  case class parasiteData2(australiaNewzealandRisk : Option[String], australiaNewzealandNotes : Option[String],
                           belgiumRisk : Option[String], belgiumNotes : Option[String], canadaRisk : Option[String],
                           canadaNotes : Option[String], euRisk : Option[String], euNotes : Option[String],
                           germanyRisk : Option[String], germanyNotes : Option[String], japanRisk : Option[String],
                           japanNotes : Option[String], singaporeRisk : Option[String], singaporeNotes : Option[String],
                           switzerlandRisk : Option[String], switzerlandNotes : Option[String], ukRisk : Option[String],
                           ukNotes : Option[String], selectAgentCdc : Option[String], selectAgentUsda : Option[String])

  val parasiteForm2 = Form(
    mapping(
      "australiaNewzealandRisk" -> optional(text), "australiaNewzealandNotes" -> optional(text),
      "belgiumRisk" -> optional(text), "belgiumNotes"-> optional(text), "canadaRisk" -> optional(text),
      "canadaNotes" -> optional(text), "euRisk" -> optional(text), "euNotes" -> optional(text),
      "germanyRisk" -> optional(text), "germanyNotes" -> optional(text), "japanRisk" -> optional(text),
      "japanNotes" -> optional(text), "singaporeRisk" -> optional(text), "singaporeNotes" -> optional(text),
      "switzerlandRisk" -> optional(text), "switzerlandNotes" -> optional(text), "ukRisk" -> optional(text),
      "ukNotes" -> optional(text), "selectAgentCdc" -> optional(text), "selectAgentUsda" -> optional(text)
    )(parasiteData2.apply)(parasiteData2.unapply)
  )

  def dealWithF1(b: parasiteData1,id:String) : Array[String] = {
    Array(id,b.name.getOrElse(""),b.chinese.getOrElse(""),b.order.getOrElse(""),b.family.getOrElse(""),b.genus.getOrElse(""),
      b.species.getOrElse(""),b.isPeople.getOrElse(""),b.isAnimal.getOrElse(""),b.isPlant.getOrElse(""),
      b.tranRoute.getOrElse(""),b.infectiveDose.getOrElse(""),b.survivalCondition.getOrElse(""),
      b.isSanitizer.getOrElse(""), b.deathRate.getOrElse(""),b.isMedicine.getOrElse(""),b.isVaccine.getOrElse(""),
      b.chinaRisk.getOrElse(""),b.chinaNotes.getOrElse(""),b.usanihRisk.getOrElse(""),b.usanihNotes.getOrElse(""),
      b.usabmblRisk.getOrElse(""),b.usabmblNotes.getOrElse(""))
  }

  def dealWithF2(b: parasiteData2) : Array[String] = {
    Array(b.australiaNewzealandRisk.getOrElse(""),b.australiaNewzealandNotes.getOrElse(""),b.belgiumRisk.getOrElse(""),
      b.belgiumNotes.getOrElse(""),b.canadaRisk.getOrElse(""), b.canadaNotes.getOrElse(""),b.euRisk.getOrElse(""),
      b.euNotes.getOrElse(""),b.germanyRisk.getOrElse(""), b.germanyNotes.getOrElse(""),b.japanRisk.getOrElse(""),
      b.japanNotes.getOrElse(""), b.singaporeRisk.getOrElse(""), b.singaporeNotes.getOrElse(""), b.switzerlandRisk.getOrElse(""),
      b.switzerlandNotes.getOrElse(""),b.ukRisk.getOrElse(""),b.ukNotes.getOrElse(""), b.selectAgentCdc.getOrElse(""),
      b.selectAgentUsda.getOrElse(""))
  }

  def uploadBefore = Action{implicit request=>
    Ok(views.html.chinese.parasite.uploadParasite())
  }

  def uploadParasite = Action { implicit request =>
    var valid = "true"
    var message = ""
    try {
      val f1 = parasiteForm1.bindFromRequest.get
      val f2 = parasiteForm2.bindFromRequest.get
      val b1 = dealWithF1(f1, "0")
      val b2 = dealWithF2(f2)
      val b = b1 ++ b2
      val row = transForm.getParasiteRow(b)
      Await.result(parasitedao.addParasite(row), Duration.Inf)
    } catch {
      case e: Exception => valid = "false"; message = e.getMessage
    }
    Ok(Json.obj("valid" -> valid, "message" -> message))
  }

  def updateBefore(id:Int) = Action.async{implicit request=>
    parasitedao.getById(id).map{x=>
      Ok(views.html.chinese.parasite.updateParasite(transForm.transParasiteRow(x)))
    }
  }

  def updateParasite(id: String) = Action { implicit request =>
    var valid = "true"
    var message = ""
    try {
      val f1 = parasiteForm1.bindFromRequest.get
      val f2 = parasiteForm2.bindFromRequest.get
      val b1 = dealWithF1(f1, id)
      val b2 = dealWithF2(f2)
      val b = b1 ++ b2
      val row = transForm.getParasiteRow(b)
      Await.result(parasitedao.updateParasite(id.toInt, row), Duration.Inf)
    } catch {
      case e: Exception => valid = "false"; message = e.getMessage
    }
    Ok(Json.obj("valid" -> valid, "message" -> message))
  }

  def manageBefore = Action{implicit request=>
    Ok(views.html.chinese.parasite.manageParasite())
  }

  def deleteParasite(id: Int) = Action.async { implicit request =>
    parasitedao.deleteParasite(id).map { x =>
      Ok(Json.toJson("success"))
    }
  }

}
