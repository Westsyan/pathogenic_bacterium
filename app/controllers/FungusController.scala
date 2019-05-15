package controllers

import javax.inject.Inject

import dao._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import utils.transForm
import models.Tables._
import play.api.data.Form
import play.api.data.Forms._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class FungusController @Inject()(fungusdao: fungusDao) extends Controller  {


  def browse = Action {implicit request=>
    Ok(views.html.english.fungus.browse())
  }

  def getJson(y:FungusRow) = {
    val t = transForm.transFungusRow(y)

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
      "singaporeNotes" -> t.singaporeNotes, "switzerlandRisk" -> t.switzerlandRisk,
      "switzerlandNotes" -> t.switzerlandNotes, "ukRisk" -> t.ukRisk, "ukNotes" -> t.ukNotes,
      "selectAgentCdc" -> t.selectAgentCdc, "selectAgentUsda" -> t.selectAgentUsda)
  }

  def getAllFungus = Action.async {
    fungusdao.getAllFungus.map { x =>
      val json = x.sortBy(_.tail.head).map { y =>
        getJson(y)
      }
      Ok(Json.toJson(json))
    }
  }


  def moreInfo(id:Int) = Action.async{implicit request=>
    fungusdao.getById(id).map{x=>
      Ok(views.html.english.fungus.moreInfo(transForm.transFungusRow(x)))
    }
  }

  def searchByTaxBefore = Action{implicit request=>
    Ok(views.html.english.fungus.searchByTax())
  }

  def getAllSelect = Action.async{implicit request=>
    fungusdao.getAllFungus.map{x=>
      val row = x.map(y=>transForm.transFungusRow(y))
      val order = "All" +: row.map(_.order).sorted.distinct
      Ok(Json.obj("order" -> order))
    }
  }

  def getByOrder(order:String) = Action.async{implicit request=>
    fungusdao.getByOrder(order).map{x=>
      val row = x.map(y => transForm.transFungusRow(y))
      val family = "All" +: row.map(_.family).sorted.distinct
      Ok(Json.obj("family" -> family))
    }
  }

  def getByFamily(family:String) = Action.async{implicit request=>
    fungusdao.getByFamily(family).map{x=>
      val row = x.map(y => transForm.transFungusRow(y))
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
    fungusdao.getByTax(order,family, genus,isPeople,tranRoute).map { x =>
      val row = x.map { y =>
        getJson(y)
      }
      Ok(Json.toJson(row))
    }
  }

  case class fungusData1(name : Option[String],chinese:Option[String], order : Option[String],  family : Option[String], genus : Option[String],
                         species : Option[String], isPeople : Option[String], isAnimal : Option[String],
                         isPlant : Option[String], tranRoute : Option[String], infectiveDose : Option[String],
                         survivalCondition : Option[String], isSanitizer : Option[String], deathRate : Option[String],
                         isMedicine : Option[String], isVaccine : Option[String], chinaRisk : Option[String],
                         chinaNotes : Option[String], usanihRisk : Option[String], usanihNotes : Option[String],
                         usabmblRisk : Option[String], usabmblNotes : Option[String])

  val fungusForm1 = Form(
    mapping(
      "name"-> optional(text),"chinses" -> optional(text), "order"-> optional(text), "family" -> optional(text), "genus" -> optional(text),
      "species" -> optional(text), "isPeople" -> optional(text), "isAnimal" -> optional(text), "isPlant" -> optional(text),
      "tranRoute" -> optional(text), "infectiveDose" -> optional(text), "survivalCondition" -> optional(text),
      "isSanitizer" -> optional(text), "deathRate" -> optional(text), "isMedicine" -> optional(text),
      "isVaccine" -> optional(text), "chinaRisk" -> optional(text), "chinaNotes" -> optional(text),
      "usanihRisk" -> optional(text), "usanihNotes" -> optional(text),"usabmblRisk" -> optional(text),"usabmblNotes" -> optional(text)
    )(fungusData1.apply)(fungusData1.unapply)
  )

  case class fungusData2(australiaNewzealandRisk : Option[String], australiaNewzealandNotes : Option[String],
                         belgiumRisk : Option[String], belgiumNotes : Option[String], canadaRisk : Option[String],
                         canadaNotes : Option[String], euRisk : Option[String], euNotes : Option[String],
                         germanyRisk : Option[String], germanyNotes : Option[String], japanRisk : Option[String],
                         japanNotes : Option[String], singaporeRisk : Option[String], singaporeNotes : Option[String],
                         switzerlandRisk : Option[String], switzerlandNotes : Option[String], ukRisk : Option[String],
                         ukNotes : Option[String], selectAgentCdc : Option[String], selectAgentUsda : Option[String])

  val fungusForm2 = Form(
    mapping(
      "australiaNewzealandRisk" -> optional(text), "australiaNewzealandNotes" -> optional(text),
      "belgiumRisk" -> optional(text), "belgiumNotes"-> optional(text), "canadaRisk" -> optional(text),
      "canadaNotes" -> optional(text), "euRisk" -> optional(text), "euNotes" -> optional(text),
      "germanyRisk" -> optional(text), "germanyNotes" -> optional(text), "japanRisk" -> optional(text),
      "japanNotes" -> optional(text), "singaporeRisk" -> optional(text), "singaporeNotes" -> optional(text),
      "switzerlandRisk" -> optional(text), "switzerlandNotes" -> optional(text), "ukRisk" -> optional(text),
      "ukNotes" -> optional(text), "selectAgentCdc" -> optional(text), "selectAgentUsda" -> optional(text)
    )(fungusData2.apply)(fungusData2.unapply)
  )

  def dealWithF1(b: fungusData1,id:String) : Array[String] = {
    Array(id,b.name.getOrElse(""),b.chinese.getOrElse(""),b.order.getOrElse(""),b.family.getOrElse(""),b.genus.getOrElse(""),
      b.species.getOrElse(""),b.isPeople.getOrElse(""),b.isAnimal.getOrElse(""),b.isPlant.getOrElse(""),
      b.tranRoute.getOrElse(""),b.infectiveDose.getOrElse(""),b.survivalCondition.getOrElse(""),
      b.isSanitizer.getOrElse(""), b.deathRate.getOrElse(""),b.isMedicine.getOrElse(""),b.isVaccine.getOrElse(""),
      b.chinaRisk.getOrElse(""),b.chinaNotes.getOrElse(""),b.usanihRisk.getOrElse(""),b.usanihNotes.getOrElse(""),
      b.usabmblRisk.getOrElse(""),b.usabmblNotes.getOrElse(""))
  }

  def dealWithF2(b: fungusData2) : Array[String] = {
    Array(b.australiaNewzealandRisk.getOrElse(""),b.australiaNewzealandNotes.getOrElse(""),b.belgiumRisk.getOrElse(""),
      b.belgiumNotes.getOrElse(""),b.canadaRisk.getOrElse(""), b.canadaNotes.getOrElse(""),b.euRisk.getOrElse(""),
      b.euNotes.getOrElse(""),b.germanyRisk.getOrElse(""), b.germanyNotes.getOrElse(""),b.japanRisk.getOrElse(""),
      b.japanNotes.getOrElse(""), b.singaporeRisk.getOrElse(""), b.singaporeNotes.getOrElse(""), b.switzerlandRisk.getOrElse(""),
      b.switzerlandNotes.getOrElse(""),b.ukRisk.getOrElse(""),b.ukNotes.getOrElse(""), b.selectAgentCdc.getOrElse(""),
      b.selectAgentUsda.getOrElse(""))
  }




  def uploadBefore = Action{implicit request=>
    Ok(views.html.english.fungus.uploadFungus())
  }

  def uploadFungus = Action { implicit request =>
    var valid = "true"
    var message = ""
    try {
      val f1 = fungusForm1.bindFromRequest.get
      val f2 = fungusForm2.bindFromRequest.get
      val b1 = dealWithF1(f1, "0")
      val b2 = dealWithF2(f2)
      val b = b1 ++ b2
      val row = transForm.getFungusRow(b)
      Await.result(fungusdao.addFungus(row), Duration.Inf)
    } catch {
      case e: Exception => valid = "false"; message = e.getMessage
    }
    Ok(Json.obj("valid" -> valid, "message" -> message))
  }

  def updateBefore(id:Int) = Action.async{implicit request=>
    fungusdao.getById(id).map{x=>
      Ok(views.html.english.fungus.updateFungus(transForm.transFungusRow(x)))
    }
  }

  def updateFungus(id: String) = Action { implicit request =>
    var valid = "true"
    var message = ""
    try {
      val f1 = fungusForm1.bindFromRequest.get
      val f2 = fungusForm2.bindFromRequest.get
      val b1 = dealWithF1(f1, id)
      val b2 = dealWithF2(f2)
      val b = b1 ++ b2
      val row = transForm.getFungusRow(b)
      Await.result(fungusdao.updateFungus(id.toInt, row), Duration.Inf)
    } catch {
      case e: Exception => valid = "false"; message = e.getMessage
    }
    Ok(Json.obj("valid" -> valid, "message" -> message))
  }

  def manageBefore = Action{implicit request=>
    Ok(views.html.english.fungus.manageFungus())
  }

  def deleteFungus(id: Int) = Action.async { implicit request =>
    fungusdao.deleteFungus(id).map { x =>
      Ok(Json.toJson("success"))
    }
  }

}
