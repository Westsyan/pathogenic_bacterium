package controllers

import play.api.mvc.{Action, Controller}

class ChineseController extends Controller{


  def index = Action{implicit request=>
    Ok(views.html.chinese.home.index())
  }

  def toLogin = Action{implicit request=>
    Ok(views.html.chinese.admin.login())
  }

  def toPassword = Action { implicit request =>
    Ok(views.html.chinese.admin.changePassword())
  }

  def meanDecreaseGini = Action{implicit request=>
    Ok(views.html.chinese.riskEvaluation.meanDecreaseGini())
  }

  def tree = Action{implicit request=>
    Ok(views.html.chinese.tree.tree())
  }

  def searchBefore(input:String) = Action { implicit request =>
    Ok(views.html.chinese.search.search(input))
  }




}
