package controllers

import java.io.File

import org.apache.commons.io.FileUtils
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import utils.{ExecCommand, Utils}

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

  def svmBefore = Action{implicit request=>
    Ok(views.html.english.riskEvaluation.svm())
  }

  def runSvm(tmpDir: String) = {
    /*建立模型
    data <- read.table("${input}_risk.txt", header = T, sep = "\t", check.names = FALSE,quote="")
    data$$risk=as.factor(as.character(data$$risk))
    library('randomForest')
    rf_ntree <- randomForest(risk~.,data=data, na.action = na.fail, proximity=TRUE, importance=TRUE)
    save(rf_ntree,file='F:/database/pathogenic/${input}_rf_ntree.txt')
     */
    val r =
      s"""|setwd("${tmpDir}")
          |library('randomForest')
          |city <- c("china", "usa_nih", "usa_bmbl","australia", "belgium", "canada", "eu", "germany", "japan", "singapore","switzerland","uk")
          |in_data <- read.table("data.txt", header = T, sep = "\t", check.names = FALSE,quote="")
          |for(c in city){
          |load(sprintf('${Utils.path}/%s_rf_ntree.txt',c))
          |RF_prediction = predict(rf_ntree, in_data)
          |sink(file=sprintf('%s_result.txt',c))
          |print(RF_prediction[length(RF_prediction)])
          |sink()
          |}
       """.stripMargin
    FileUtils.writeStringToFile(new File(tmpDir, "cmd.r"), r)
    val command = new ExecCommand
    command.exect("Rscript " + tmpDir + "/cmd.r", tmpDir)
  }


}
