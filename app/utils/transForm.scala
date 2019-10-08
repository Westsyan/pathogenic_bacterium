package utils

import models.Tables._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json


case class bacteriaData(id: String, name: String,chinese:String, order: String, suborder: String, family: String, genus: String,
                        species: String, isPeople: String, isAnimal: String, isPlant: String, tranRoute: String,
                        infectiveDose: String, survivalCondition: String, isSanitizer: String, deathRate: String,
                        isMedicine: String, isVaccine: String, chinaRisk: String, chinaNotes: String, usanihRisk: String,
                        usanihNotes: String, usabmblRisk: String, usabmblNotes: String, australiaNewzealandRisk: String,
                        australiaNewzealandNotes: String, belgiumRisk: String, belgiumNotes: String, canadaRisk: String,
                        canadaNotes: String, euRisk: String, euNotes: String, germanyRisk: String, germanyNotes: String,
                        japanRisk: String, japanNotes: String, singaporeRisk: String, singaporeNotes: String, switzerlandRisk: String,
                        switzerlandNotes: String, ukRisk: String, ukNotes: String, selectAgentCdc: String,
                        selectAgentUsda: String)

case class virusData(id: String, name: String,chinese:String, order: String, family: String, subfamily : String, genus: String,
                     species: String, genome_composition : String, isPeople: String, isAnimal: String, isPlant: String, tranRoute: String,
                     infectiveDose: String, survivalCondition: String, isSanitizer: String, deathRate: String,
                     isMedicine: String, isVaccine: String, chinaRisk: String, chinaNotes: String, usanihRisk: String,
                     usanihNotes: String, usabmblRisk: String, usabmblNotes: String, australiaNewzealandRisk: String,
                     australiaNewzealandNotes: String, belgiumRisk: String, belgiumNotes: String, canadaRisk: String,
                     canadaNotes: String, euRisk: String, euNotes: String, germanyRisk: String, germanyNotes: String,
                     japanRisk: String, japanNotes: String, singaporeRisk: String, singaporeNotes: String, switzerlandRisk: String,
                     switzerlandNotes: String, ukRisk: String, ukNotes: String, selectAgentCdc: String,
                     selectAgentUsda: String)

case class fungusData(id: String, name: String,chinese:String, order: String, family: String, genus: String,
                     species: String, isPeople: String, isAnimal: String, isPlant: String, tranRoute: String,
                     infectiveDose: String, survivalCondition: String, isSanitizer: String, deathRate: String,
                     isMedicine: String, isVaccine: String, chinaRisk: String, chinaNotes: String, usanihRisk: String,
                     usanihNotes: String, usabmblRisk: String, usabmblNotes: String, australiaNewzealandRisk: String,
                     australiaNewzealandNotes: String, belgiumRisk: String, belgiumNotes: String, canadaRisk: String,
                     canadaNotes: String, euRisk: String, euNotes: String, germanyRisk: String, germanyNotes: String,
                     japanRisk: String, japanNotes: String, singaporeRisk: String, singaporeNotes: String, switzerlandRisk: String,
                     switzerlandNotes: String, ukRisk: String, ukNotes: String, selectAgentCdc: String,
                     selectAgentUsda: String)

case class parasiteData(id: String, name: String,chinese:String, order: String, family: String, genus: String,
                      species: String, isPeople: String, isAnimal: String, isPlant: String, tranRoute: String,
                      infectiveDose: String, survivalCondition: String, isSanitizer: String, deathRate: String,
                      isMedicine: String, isVaccine: String, chinaRisk: String, chinaNotes: String, usanihRisk: String,
                      usanihNotes: String, usabmblRisk: String, usabmblNotes: String, australiaNewzealandRisk: String,
                      australiaNewzealandNotes: String, belgiumRisk: String, belgiumNotes: String, canadaRisk: String,
                      canadaNotes: String, euRisk: String, euNotes: String, germanyRisk: String, germanyNotes: String,
                      japanRisk: String, japanNotes: String, singaporeRisk: String, singaporeNotes: String, switzerlandRisk: String,
                      switzerlandNotes: String, ukRisk: String, ukNotes: String, selectAgentCdc: String,
                      selectAgentUsda: String)

case class searchByTaxData(order:String,family: Option[String], genus: Option[String],isPeople:String,tranRoute:Option[String])

case class idData(id:String)

object transForm {

  val idForm = Form(
    mapping(
      "id" -> text
    )(idData.apply)(idData.unapply)
  )

  val searchByTaxForm = Form(
    mapping(
      "order" -> text,
      "family" -> optional(text),
      "genus" -> optional(text),
      "isPeople" -> text,
      "tranRoute" -> optional(text)
    )(searchByTaxData.apply)(searchByTaxData.unapply)
  )


  def getBacteriaRow(buffer: Array[String]): BacteriaRow = {
    BacteriaRow(buffer(0).toInt, buffer(1), buffer(2), buffer(3), buffer(4), buffer(5), buffer(6), buffer(7), buffer(8),
      buffer(9), buffer(10), buffer(11), buffer(12), buffer(13), buffer(14), buffer(15), buffer(16), buffer(17), buffer(18),
      buffer(19), buffer(20), buffer(21), buffer(22), buffer(23), buffer(24), buffer(25), buffer(26), buffer(27), buffer(28),
      buffer(29), buffer(30), buffer(31), buffer(32), buffer(33), buffer(34), buffer(35), buffer(36), buffer(37), buffer(38),
      buffer(39), buffer(40), buffer(41), buffer(42), buffer(43))
  }


  def transBacteriaRow(row: BacteriaRow): bacteriaData = {
    val buffer = row.toList.map(_.toString)
    bacteriaData(buffer.head, buffer(1), buffer(2), buffer(3), buffer(4), buffer(5), buffer(6), buffer(7), buffer(8),
      buffer(9), buffer(10), buffer(11), buffer(12), buffer(13), buffer(14), buffer(15), buffer(16), buffer(17), buffer(18),
      buffer(19), buffer(20), buffer(21), buffer(22), buffer(23), buffer(24), buffer(25), buffer(26), buffer(27), buffer(28),
      buffer(29), buffer(30), buffer(31), buffer(32), buffer(33), buffer(34), buffer(35), buffer(36), buffer(37), buffer(38),
      buffer(39), buffer(40), buffer(41), buffer(42), buffer(43))
  }

  def getBacteriaJson(y: BacteriaRow) = {
    val t = transForm.transBacteriaRow(y)
    Json.obj("id" -> t.id, "name" -> t.name ,"chinese" -> t.chinese, "order" -> t.order, "suborder" -> t.suborder, "family" -> t.family, "genus" -> t.genus,
      "species" -> t.species, "isPeople" -> t.isPeople, "isAnimal" -> t.isAnimal, "isPlant" -> t.isPlant,
      "tranRoute" -> t.tranRoute, "infectiveDose" -> t.infectiveDose, "survivalCondition" -> t.survivalCondition,
      "isSanitizer" -> t.isSanitizer, "deathRate" -> t.deathRate, "isMedicine" -> t.isMedicine,
      "isVaccine" -> t.isVaccine, "chinaRisk" -> t.chinaRisk, "chinaNotes" -> t.chinaNotes,
      "usanihRisk" -> t.usabmblRisk, "usanihNotes" -> t.usanihNotes, "usabmblRisk" -> t.usabmblRisk,
      "usabmblNotes" -> t.usabmblNotes, "australiaNewzealandRisk" -> t.australiaNewzealandRisk,
      "australiaNewzealandNotes" -> t.australiaNewzealandNotes, "belgiumRisk" -> t.belgiumRisk,
      "belgiumNotes" -> t.belgiumNotes, "canadaRisk" -> t.canadaRisk, "canadaNotes" -> t.canadaNotes,
      "euRisk" -> t.euRisk, "euNotes" -> t.euNotes, "germanyRisk" -> t.germanyRisk, "germanyNotes" -> t.germanyNotes,
      "japanRisk" -> t.japanRisk, "japanNotes" -> t.japanNotes, "singaporeRisk" -> t.singaporeRisk,
      "singaporeNotes" -> t.singaporeNotes,  "switzerlandRisk" -> t.switzerlandRisk,
      "switzerlandNotes" -> t.switzerlandNotes, "ukRisk" -> t.ukRisk, "ukNotes" -> t.ukNotes,
      "selectAgentCdc" -> t.selectAgentCdc, "selectAgentUsda" -> t.selectAgentUsda)
  }


  def getVirusRow(buffer: Array[String]): VirusRow = {
    VirusRow(buffer(0).toInt, buffer(1), buffer(2), buffer(3), buffer(4), buffer(5), buffer(6), buffer(7), buffer(8),
      buffer(9), buffer(10), buffer(11), buffer(12), buffer(13), buffer(14), buffer(15), buffer(16), buffer(17), buffer(18),
      buffer(19), buffer(20), buffer(21), buffer(22), buffer(23), buffer(24), buffer(25), buffer(26), buffer(27), buffer(28),
      buffer(29), buffer(30), buffer(31), buffer(32), buffer(33), buffer(34), buffer(35), buffer(36), buffer(37), buffer(38),
      buffer(39), buffer(40), buffer(41), buffer(42), buffer(43), buffer(44))
  }


  def transVirusRow(row: VirusRow): virusData = {
    val buffer = row.toList.map(_.toString)
    virusData(buffer.head, buffer(1), buffer(2), buffer(3), buffer(4), buffer(5), buffer(6), buffer(7), buffer(8),
      buffer(9), buffer(10), buffer(11), buffer(12), buffer(13), buffer(14), buffer(15), buffer(16), buffer(17), buffer(18),
      buffer(19), buffer(20), buffer(21), buffer(22), buffer(23), buffer(24), buffer(25), buffer(26), buffer(27), buffer(28),
      buffer(29), buffer(30), buffer(31), buffer(32), buffer(33), buffer(34), buffer(35), buffer(36), buffer(37), buffer(38),
      buffer(39), buffer(40), buffer(41), buffer(42), buffer(43), buffer(44))
  }

  def getFungusRow(buffer: Array[String]): FungusRow = {
    FungusRow(buffer(0).toInt, buffer(1), buffer(2), buffer(3), buffer(4), buffer(5), buffer(6), buffer(7), buffer(8),
      buffer(9), buffer(10), buffer(11), buffer(12), buffer(13), buffer(14), buffer(15), buffer(16), buffer(17), buffer(18),
      buffer(19), buffer(20), buffer(21), buffer(22), buffer(23), buffer(24), buffer(25), buffer(26), buffer(27), buffer(28),
      buffer(29), buffer(30), buffer(31), buffer(32), buffer(33), buffer(34), buffer(35), buffer(36), buffer(37), buffer(38),
      buffer(39), buffer(40),buffer(41),buffer(42))
  }


  def transFungusRow(row: FungusRow): fungusData = {
    val buffer = row.toList.map(_.toString)
    fungusData(buffer.head, buffer(1), buffer(2), buffer(3), buffer(4), buffer(5), buffer(6), buffer(7), buffer(8),
      buffer(9), buffer(10), buffer(11), buffer(12), buffer(13), buffer(14), buffer(15), buffer(16), buffer(17), buffer(18),
      buffer(19), buffer(20), buffer(21), buffer(22), buffer(23), buffer(24), buffer(25), buffer(26), buffer(27), buffer(28),
      buffer(29), buffer(30), buffer(31), buffer(32), buffer(33), buffer(34), buffer(35), buffer(36), buffer(37), buffer(38),
      buffer(39), buffer(40), buffer(41), buffer(42))
  }

  def getParasiteRow(buffer: Array[String]): ParasiteRow = {
    ParasiteRow(buffer(0).toInt, buffer(1), buffer(2), buffer(3), buffer(4), buffer(5), buffer(6), buffer(7), buffer(8),
      buffer(9), buffer(10), buffer(11), buffer(12), buffer(13), buffer(14), buffer(15), buffer(16), buffer(17), buffer(18),
      buffer(19), buffer(20), buffer(21), buffer(22), buffer(23), buffer(24), buffer(25), buffer(26), buffer(27), buffer(28),
      buffer(29), buffer(30), buffer(31), buffer(32), buffer(33), buffer(34), buffer(35), buffer(36), buffer(37), buffer(38),
      buffer(39), buffer(40), buffer(41), buffer(42))
  }


  def transParasiteRow(row: ParasiteRow): parasiteData = {
    val buffer = row.toList.map(_.toString)
    parasiteData(buffer.head, buffer(1), buffer(2), buffer(3), buffer(4), buffer(5), buffer(6), buffer(7), buffer(8),
      buffer(9), buffer(10), buffer(11), buffer(12), buffer(13), buffer(14), buffer(15), buffer(16), buffer(17), buffer(18),
      buffer(19), buffer(20), buffer(21), buffer(22), buffer(23), buffer(24), buffer(25), buffer(26), buffer(27), buffer(28),
      buffer(29), buffer(30), buffer(31), buffer(32), buffer(33), buffer(34), buffer(35), buffer(36), buffer(37), buffer(38),
      buffer(39), buffer(40), buffer(41), buffer(42))
  }



}
