package controllers

import javax.inject.Inject

import dao.passwordDao
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global


class IndexController @Inject()(passworddao: passwordDao) extends Controller {


  def home = Action { implicit request =>
    Redirect(routes.IndexController.index())
  }


  def index = Action { implicit request =>
    Ok(views.html.english.home.index())
  }


  def toAdmin = Action { implicit request =>
    Ok(views.html.english.admin.index())
  }

  def toLogin = Action { implicit request =>
    Ok(views.html.english.admin.login())
  }

  def toPassword = Action { implicit request =>
    Ok(views.html.english.admin.changePassword())
  }

  def logout(language: String) = Action { implicit request =>
    language match {
      case "english" => Redirect(routes.IndexController.index()).withNewSession
      case "chinese" => Redirect(routes.ChineseController.index()).withNewSession
    }
  }

  case class loginData(account: String, password: String)

  val loginForm = Form(
    mapping(
      "account" -> text,
      "password" -> text
    )(loginData.apply)(loginData.unapply)
  )

  def login = Action.async { implicit request =>
    val data = loginForm.bindFromRequest.get
    val account = data.account
    val pwd = data.password
    passworddao.selectByAccount(account).map { x =>
      if (request.toString.indexOf("english") != -1) {
        if (x.isEmpty) {
          Redirect(routes.IndexController.toLogin()).flashing("info" -> "The account or password is wrong.")
        } else {
          if (x.head.password == pwd) {
            Redirect(routes.IndexController.index()).withSession(request.session + ("id" -> x.head.id.toString) + ("user" -> x.head.user))
          } else {
            Redirect(routes.IndexController.toLogin()).flashing("info" -> "The account or password is wrong.")
          }
        }
      } else {
        if (x.isEmpty) {
          Redirect(routes.ChineseController.toLogin()).flashing("info" -> "账号密码错误，请重新输入！")
        } else {
          if (x.head.password == pwd) {
            Redirect(routes.ChineseController.index()).withSession(request.session + ("id" -> x.head.id.toString) + ("user" -> x.head.user))
          } else {
            Redirect(routes.ChineseController.toLogin()).flashing("info" -> "账号密码错误，请重新输入！")
          }
        }
      }
    }
  }

  case class passwordData(newPasswordAgain: String)

  val passwordForm = Form(
    mapping(
      "newPasswordAgain" -> text
    )(passwordData.apply)(passwordData.unapply)
  )

  def updatePassword = Action.async { implicit request =>
    val data = passwordForm.bindFromRequest.get
    val id = request.session.get("id").get.toInt
    passworddao.updatePassword(data.newPasswordAgain, id).map { x =>
      Redirect(routes.IndexController.toLogin()).withNewSession.flashing("info" -> "请重新登陆")
    }
  }

  def getPassword(password: String) = Action.async { implicit request =>
    val user = request.session.get("user").get
    passworddao.selectByAccount(user).map { x =>
      val valid = if (x.head.password == password) {
        "true"
      } else {
        "false"
      }
      Ok(Json.obj("valid" -> valid))
    }
  }


}
