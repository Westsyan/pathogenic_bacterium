package controllers

import javax.inject.Inject

import dao._
import play.api.libs.json.{JsObject, Json}
import play.api.mvc._
import utils.transForm

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class SearchController @Inject()(bacteriadao: bacteriaDao, virusdao: virusDao, parasitedao: parasiteDao,
                                 fungusdao: fungusDao) extends Controller {


  def searchBefore(input:String) = Action { implicit request =>
    Ok(views.html.english.search.search(input))
  }

  def getKeyWord(input: String, key: String) = {
    val index = input.toUpperCase.indexOf(key.toUpperCase)
    val value = input.slice(index, index + key.length)
    input.split("<span style='color:red;'>").map(_.split("</span>").
      map(_.replaceAll("(?i)" + key, "<span style='color:red;'>" + value + "</span>")).
      mkString("</span>")).mkString("<span style='color:red;'>")
  }

  def getKeyWordHead(input: String, key: String) = {
    val index = input.toUpperCase.indexOf(key.toUpperCase)
    val value = input.slice(index, index + key.length)
    input.replaceAll("(?i)" + key, "<span style='color:red;'>" + value + "</span>")
  }

  def searchBacteria(input: String) : Seq[JsObject] = {
    val keys = input.trim.split(" ")

    val r = Await.result(bacteriadao.searchByLike(keys.head), Duration.Inf)

    val flt = r.map(x=> transForm.transBacteriaRow(x)).map(y => ((y.id.toInt, y.name), Array(y.name, y.order, y.family, y.genus,
      y.species, y.tranRoute, y.infectiveDose, y.survivalCondition, y.isSanitizer, y.deathRate,y.isMedicine,y.isVaccine)))


    val rows = getSearchResult(flt, input,"bacteria")
    rows
  }

  def searchVirus(input: String) : Seq[JsObject] = {
    val keys = input.trim.split(" ")

    val r = Await.result(virusdao.searchByLike(keys.head), Duration.Inf)

    val flt = r.map(x=> transForm.transVirusRow(x)).map(y => ((y.id.toInt, y.name), Array(y.name, y.order, y.family, y.genus,
      y.species, y.tranRoute, y.infectiveDose, y.survivalCondition, y.isSanitizer, y.deathRate,y.isMedicine,y.isVaccine)))


    val rows = getSearchResult(flt, input,"virus")
    rows
  }

  def searchFungus(input: String) : Seq[JsObject] = {
    val keys = input.trim.split(" ")
    val r = Await.result(fungusdao.searchByLike(keys.head), Duration.Inf)

    val flt = r.map(x=> transForm.transFungusRow(x)).map(y => ((y.id.toInt, y.name), Array(y.name, y.order, y.family, y.genus,
      y.species, y.tranRoute, y.infectiveDose, y.survivalCondition, y.isSanitizer, y.deathRate,y.isMedicine,y.isVaccine)))


    val rows = getSearchResult(flt, input,"fungus")
    rows
  }

  def searchParasite(input: String) : Seq[JsObject] = {
    val keys = input.trim.split(" ")

    val r = Await.result(parasitedao.searchByLike(keys.head), Duration.Inf)


    val flt = r.map(x=> transForm.transParasiteRow(x)).map(y => ((y.id.toInt, y.name), Array(y.name, y.order, y.family, y.genus,
      y.species, y.tranRoute, y.infectiveDose, y.survivalCondition, y.isSanitizer, y.deathRate,y.isMedicine,y.isVaccine)))


    val rows = getSearchResult(flt, input,"parasite")
    rows
  }


  def getSearchResult(flts: Seq[((Int, String), Array[String])], input: String,tp:String): Seq[JsObject] = {

    val option = Array("名称", "目", "科", "属", "种", "传播途径", "感染剂量", "体外存活条件", "有效消毒剂", "预后/死亡率",
      "有效治疗药物","疫苗")

    var results = Array[((Int, String), Int)]()

    val keys = input.trim.split(" ")
    var flt = flts

    if (keys.length > 1) {

      //过滤
      keys.tail.foreach { y =>
        flt = flt.filter(z => z._2.count(_.toUpperCase.contains(y.toUpperCase)) != 0)
      }

      //多次过滤结果，得到基因ID和对应的字段名的下标
      results = keys.flatMap { k =>
        flt.map { y =>
          (y._1, y._2.indexOf(y._2.filter(_.toUpperCase.contains(k.toUpperCase)).head))
        }
      }.distinct

    } else {
      results = flt.map { y =>
        (y._1, y._2.indexOf(y._2.filter(_.toUpperCase.contains(keys.head.toUpperCase)).head))
      }.distinct.toArray

    }

    val fltMap = flt.toMap


    //整合
    val rows = results.groupBy(_._1).toArray.sortBy(_._1._2).map { y =>
      val re = y._2.distinct.map { z =>
        //得到结果json
        var resu = fltMap(y._1)(z._2)

        resu = getKeyWordHead(resu, keys.head)

        if (keys.length != 1) {
          keys.tail.foreach { k =>
            resu = getKeyWord(resu, k)
          }
        }

        Json.obj("option" -> option(z._2), "result" -> resu)
      }

      Json.obj("id" -> y._1._1, "name" -> y._1._2, "type" -> tp,"result" -> re)
    }.toSeq


    rows
  }


  def search(input:String) = Action{implicit request=>

    val json =  searchBacteria(input) ++ searchVirus(input) ++ searchFungus(input) ++ searchParasite(input)

    Ok(Json.toJson(json))
  }


}
