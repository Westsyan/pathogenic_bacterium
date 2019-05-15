package controllers

import java.io.File

import org.apache.commons.io.FileUtils
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import utils.Utils

import scala.collection.JavaConverters._

class RiskController extends Controller{

  def meanDecreaseGini = Action{implicit request=>
    Ok(views.html.english.riskEvaluation.meanDecreaseGini())
  }


  def getGiniJson = Action{implicit request=>
    val file  = FileUtils.readLines(new File(Utils.path + "/MeanDecreaseGini.txt"),"UTF-8").asScala

    val json = file.tail.map{x=>
      val c = x.split("\t")
      Json.obj("country" -> c.head,"is_breath" -> c(1),"is_people" -> c(2),"is_100" -> c(3),"is_24" -> c(4),
      "is_medicine" -> c(5),"is_vaccine" -> c(6),"death" -> c.last)
    }
    Ok(Json.toJson(json))
  }

}
