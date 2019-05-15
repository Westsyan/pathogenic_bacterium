package dao

import javax.inject.Inject

import models.Tables._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

class fungusDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends
  HasDatabaseConfigProvider[JdbcProfile]   {

  import profile.api._

  def getAllFungus : Future[Seq[FungusRow]] = {
    db.run(Fungus.result)
  }

  def getById(id:Int) : Future[FungusRow] = {
    db.run(Fungus.filter(_.id === id).result.head)
  }

  def getByTax(order:String,family: String, genus: String,isPeople : String,tranRoute:String): Future[Seq[FungusRow]] = {
    val o = order.equals("All")
    val f = family.equals("All")
    val g = genus.equals("All")
    val i = isPeople.equals("All")
    val t = tranRoute.equals("All")
    db.run(Fungus.filter(_.order === order || o).filter(_.family === family || f).filter(_.genus === genus || g).
      filter(_.isPeople === isPeople || i).filter(_.tranRoute.like("%" + tranRoute + "%") || t).result)
  }

  def getByOrder(order:String) : Future[Seq[FungusRow]] = {
    db.run(Fungus.filter(_.order === order).result)
  }

  def getByFamily(family:String) : Future[Seq[FungusRow]] = {
    db.run(Fungus.filter(_.family === family).result)
  }

  def addFungus(row : FungusRow) : Future[Unit] ={
    db.run(Fungus += row).map(_=>())
  }

  def updateFungus(id:Int,row:FungusRow) : Future[Unit] ={
    db.run(Fungus.filter(_.id === id).update(row)).map(_=>())
  }

  def deleteFungus(id:Int) : Future[Unit] ={
    db.run(Fungus.filter(_.id === id).delete).map(_=>())
  }

  def searchByLike(input:String) : Future[Seq[FungusRow]] = {
    db.run(Fungus.filter(x=> x.name.like("%" + input + "%") || x.order.like("%" + input + "%") || x.family.like("%" + input + "%")
      || x.genus.like("%" + input + "%") || x.species.like("%" + input + "%") || x.tranRoute.like("%" + input + "%") ||
      x.infectiveDose.like("%" + input + "%") || x.survivalCondition.like("%" + input + "%") || x.isSanitizer.like("%" + input + "%")
      || x.deathRate.like("%" + input + "%") || x.isMedicine.like("%" + input + "%") || x.isVaccine.like("%" + input + "%")).result)
  }


}
