package controllers

import javax.inject.Inject

import dao._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import utils.transForm

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class TreeController @Inject()(bacteriadao:bacteriaDao,virusdao:virusDao,fungusdao: fungusDao,parasitedao: parasiteDao) extends Controller  {

  def tree = Action{implicit request=>
    Ok(views.html.english.tree.tree())
  }

  case class treeData(id:String,sample:String,name:String,order:String,family:String,genus:String)

  def getTreeJson = Action {

    val bacteria = Await.result(bacteriadao.getAllinfo,Duration.Inf)
    val virus = Await.result(virusdao.getAll,Duration.Inf)
    val fungus = Await.result(fungusdao.getAllFungus,Duration.Inf)
    val parasite = Await.result(parasitedao.getAllParasite,Duration.Inf)

    val b = bacteria.map{x=>
      val y = transForm.transBacteriaRow(x)
      treeData(y.id,"Bacteria",y.name,y.order,y.family,y.genus)
    }

    val v = virus.map{x=>
      val y = transForm.transVirusRow(x)
      treeData(y.id,"Virus",y.name,y.order,y.family,y.genus)
    }

    val f = fungus.map{x=>
      val y = transForm.transFungusRow(x)
      treeData(y.id,"Fungus",y.name,y.order,y.family,y.genus)
    }

    val p = parasite.map{x=>
      val y = transForm.transParasiteRow(x)
      treeData(y.id,"Parasite",y.name,y.order,y.family,y.genus)
    }

    val x = b ++ v ++ f ++ p

      val species = x.map { y =>
        val html = y.sample match{
          case "Bacteria" => "<a onclick=\"getInfo("+y.id + ",'Bacteria')\"  style='color: inherit;'>" + y.name + "</a>"
          case "Virus" =>"<a onclick=\"getInfo("+y.id + ",'Virus')\"  style='color: inherit;'>" + y.name + "</a>"
          case "Parasite" => "<a onclick=\"getInfo("+y.id + ",'Parasite')\"  style='color: inherit;'>" + y.name + "</a>"
          case "Fungus" => "<a onclick=\"getInfo("+y.id + ",'Fungus')\"  style='color: inherit;'>" + y.name + "</a>"
        }
        (y.family, y.genus, Json.obj("text" -> html, "nodes" -> ""))
      }

      val genus = x.map { y =>
        val node = species.filter(_._1 == y.family).filter(_._2 == y.genus).map(_._3).distinct
        (y.order,y.family, Json.obj("text" -> y.genus, "tags" -> Array(node.size), "nodes" -> node))
      }

      val family = x.map { y =>
        val node = genus.filter(_._1 == y.order).filter(_._2 == y.family).map(_._3).distinct
        (y.sample,y.order, Json.obj("text" -> y.family, "tags" -> Array(node.size), "nodes" -> node))
      }.distinct

      val order = x.map{y=>
        val node = family.filter(_._1 == y.sample).filter(_._2 == y.order).map(_._3).distinct
        (y.sample,Json.obj("text" -> y.order, "tags" -> Array(node.size), "nodes" -> node))
      }.distinct

    val sample = x.map(_.sample).distinct

      val nodes = sample.map { y =>
        val node = order.filter(_._1 == y).map(_._2).distinct
        Json.obj("text" -> y, "tags" -> Array(node.size), "nodes" -> node)
      }
      Ok(Json.toJson(nodes))
  }

  def getInfoById(id: Int,sample:String) = Action {

    val (html,name) = if(sample == "Virus"){
      val virus = Await.result(virusdao.getById(id),Duration.Inf)
      val x = transForm.transVirusRow(virus)
      val virusHtml =
        s"""
           |                    <tr>
           |                        <th>名称</th>
           |                        <td>${x.name}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>目（order）</th>
           |                        <td>${x.order}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>科（family）</th>
           |                        <td>${x.family}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>亚科（subfamily）</th>
           |                        <td>${x.subfamily}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>属（genus）</th>
           |                        <td>${x.genus}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>种（species）</th>
           |                        <td>${x.species}</td>
           |                    </tr>

           |                    <tr>
           |                        <th>Genome Composition:dsDNA</th>
           |                        <td>${x.genome_composition}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染人</th>
           |                        <td>${x.isPeople}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染动物</th>
           |                        <td>${x.isAnimal}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染植物</th>
           |                        <td>${x.isPlant}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>传播途径</th>
           |                        <td>${x.tranRoute}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>感染剂量</th>
           |                        <td>${x.infectiveDose}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>体外存活条件</th>
           |                        <td>${x.survivalCondition}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效消毒剂</th>
           |                        <td>${x.isSanitizer}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>预后/死亡率</th>
           |                        <td>${x.deathRate}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效治疗药物</th>
           |                        <td>${x.isMedicine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>疫苗</th>
           |                        <td>${x.isVaccine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>中国（2006）</th>
           |                        <td>${x.chinaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>美国NIH (2016)</th>
           |                        <td>${x.usanihRisk}</td>
           |                    </tr>
           |
           |                    <tr>
           |                        <th>美国BMBL (2009)*</th>
           |                        <td>${x.usabmblRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>澳大利亚/新西兰 Australia/New Zealand  (2010)</th>
           |                        <td>${x.australiaNewzealandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>比利时 Belgium (2008)</th>
           |                        <td>${x.belgiumRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>加拿大 Canada (2015)</th>
           |                        <td>${x.canadaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>欧盟 EU (2000)</th>
           |                        <td>${x.euRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>德国 Germany (2013)</th>
           |                        <td>${x.germanyRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>日本 Japan</th>
           |                        <td>${x.japanRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>新加坡 Singapore</th>
           |                        <td>${x.singaporeRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>瑞士 Switzerland</th>
           |                        <td>${x.switzerlandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>英国 UK (2013)</th>
           |                        <td>${x.ukRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent CDC</th>
           |                        <td>${x.selectAgentCdc}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent USDA</th>
           |                        <td>${x.selectAgentUsda}</td>
           |                    </tr>
        """.stripMargin
      (virusHtml,x.name)
    }else if(sample == "Bacteria"){
      val bacteria = Await.result(bacteriadao.getById(id),Duration.Inf)
      val x = transForm.transBacteriaRow(bacteria)
      val bacteriaHtml =
        s"""
           |                    <tr>
           |                        <th>名称</th>
           |                        <td>${x.name}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>目（order）</th>
           |                        <td>${x.order}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>亚目（suborder）</th>
           |                        <td>${x.suborder}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>科（family）</th>
           |                        <td>${x.family}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>属（genus）</th>
           |                        <td>${x.genus}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>种（species）</th>
           |                        <td>${x.species}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染人</th>
           |                        <td>${x.isPeople}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染动物</th>
           |                        <td>${x.isAnimal}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染植物</th>
           |                        <td>${x.isPlant}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>传播途径</th>
           |                        <td>${x.tranRoute}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>感染剂量</th>
           |                        <td>${x.infectiveDose}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>体外存活条件</th>
           |                        <td>${x.survivalCondition}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效消毒剂</th>
           |                        <td>${x.isSanitizer}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>预后/死亡率</th>
           |                        <td>${x.deathRate}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效治疗药物</th>
           |                        <td>${x.isMedicine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>疫苗</th>
           |                        <td>${x.isVaccine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>中国（2006）</th>
           |                        <td>${x.chinaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>美国NIH (2016)</th>
           |                        <td>${x.usanihRisk}</td>
           |                    </tr>
           |
           |                    <tr>
           |                        <th>美国BMBL (2009)*</th>
           |                        <td>${x.usabmblRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>澳大利亚/新西兰 Australia/New Zealand  (2010)</th>
           |                        <td>${x.australiaNewzealandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>比利时 Belgium (2008)</th>
           |                        <td>${x.belgiumRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>加拿大 Canada (2015)</th>
           |                        <td>${x.canadaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>欧盟 EU (2000)</th>
           |                        <td>${x.euRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>德国 Germany (2013)</th>
           |                        <td>${x.germanyRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>日本 Japan</th>
           |                        <td>${x.japanRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>新加坡 Singapore</th>
           |                        <td>${x.singaporeRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>瑞士 Switzerland</th>
           |                        <td>${x.switzerlandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>英国 UK (2013)</th>
           |                        <td>${x.ukRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent CDC</th>
           |                        <td>${x.selectAgentCdc}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent USDA</th>
           |                        <td>${x.selectAgentUsda}</td>
           |                    </tr>
        """.stripMargin
      (bacteriaHtml,x.name)
    }else if(sample == "Fungus"){
      val fungus = Await.result(fungusdao.getById(id),Duration.Inf)
      val x = transForm.transFungusRow(fungus)
      val fungusHtml =
        s"""
           |                    <tr>
           |                        <th>名称</th>
           |                        <td>${x.name}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>目（order）</th>
           |                        <td>${x.order}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>科（family）</th>
           |                        <td>${x.family}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>属（genus）</th>
           |                        <td>${x.genus}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>种（species）</th>
           |                        <td>${x.species}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染人</th>
           |                        <td>${x.isPeople}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染动物</th>
           |                        <td>${x.isAnimal}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染植物</th>
           |                        <td>${x.isPlant}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>传播途径</th>
           |                        <td>${x.tranRoute}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>感染剂量</th>
           |                        <td>${x.infectiveDose}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>体外存活条件</th>
           |                        <td>${x.survivalCondition}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效消毒剂</th>
           |                        <td>${x.isSanitizer}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>预后/死亡率</th>
           |                        <td>${x.deathRate}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效治疗药物</th>
           |                        <td>${x.isMedicine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>疫苗</th>
           |                        <td>${x.isVaccine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>中国（2006）</th>
           |                        <td>${x.chinaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>美国NIH (2016)</th>
           |                        <td>${x.usanihRisk}</td>
           |                    </tr>
           |
           |                    <tr>
           |                        <th>美国BMBL (2009)*</th>
           |                        <td>${x.usabmblRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>澳大利亚/新西兰 Australia/New Zealand  (2010)</th>
           |                        <td>${x.australiaNewzealandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>比利时 Belgium (2008)</th>
           |                        <td>${x.belgiumRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>加拿大 Canada (2015)</th>
           |                        <td>${x.canadaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>欧盟 EU (2000)</th>
           |                        <td>${x.euRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>德国 Germany (2013)</th>
           |                        <td>${x.germanyRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>日本 Japan</th>
           |                        <td>${x.japanRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>新加坡 Singapore</th>
           |                        <td>${x.singaporeRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>瑞士 Switzerland</th>
           |                        <td>${x.switzerlandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>英国 UK (2013)</th>
           |                        <td>${x.ukRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent CDC</th>
           |                        <td>${x.selectAgentCdc}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent USDA</th>
           |                        <td>${x.selectAgentUsda}</td>
           |                    </tr>
        """.stripMargin
      (fungusHtml,x.name)
    }else{
      val parasite = Await.result(parasitedao.getById(id),Duration.Inf)
      val x = transForm.transParasiteRow(parasite)
      val parasiteHtml =
        s"""
           |                    <tr>
           |                        <th>名称</th>
           |                        <td>${x.name}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>目（order）</th>
           |                        <td>${x.order}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>科（family）</th>
           |                        <td>${x.family}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>属（genus）</th>
           |                        <td>${x.genus}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>种（species）</th>
           |                        <td>${x.species}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染人</th>
           |                        <td>${x.isPeople}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染动物</th>
           |                        <td>${x.isAnimal}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>是否感染植物</th>
           |                        <td>${x.isPlant}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>传播途径</th>
           |                        <td>${x.tranRoute}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>感染剂量</th>
           |                        <td>${x.infectiveDose}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>体外存活条件</th>
           |                        <td>${x.survivalCondition}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效消毒剂</th>
           |                        <td>${x.isSanitizer}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>预后/死亡率</th>
           |                        <td>${x.deathRate}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>有效治疗药物</th>
           |                        <td>${x.isMedicine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>疫苗</th>
           |                        <td>${x.isVaccine}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>中国（2006）</th>
           |                        <td>${x.chinaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>美国NIH (2016)</th>
           |                        <td>${x.usanihRisk}</td>
           |                    </tr>
           |
           |                    <tr>
           |                        <th>美国BMBL (2009)*</th>
           |                        <td>${x.usabmblRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>澳大利亚/新西兰 Australia/New Zealand  (2010)</th>
           |                        <td>${x.australiaNewzealandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>比利时 Belgium (2008)</th>
           |                        <td>${x.belgiumRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>加拿大 Canada (2015)</th>
           |                        <td>${x.canadaRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>欧盟 EU (2000)</th>
           |                        <td>${x.euRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>德国 Germany (2013)</th>
           |                        <td>${x.germanyRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>日本 Japan</th>
           |                        <td>${x.japanRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>新加坡 Singapore</th>
           |                        <td>${x.singaporeRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>瑞士 Switzerland</th>
           |                        <td>${x.switzerlandRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>英国 UK (2013)</th>
           |                        <td>${x.ukRisk}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent CDC</th>
           |                        <td>${x.selectAgentCdc}</td>
           |                    </tr>
           |                    <tr>
           |                        <th>Select Agent USDA</th>
           |                        <td>${x.selectAgentUsda}</td>
           |                    </tr>
        """.stripMargin
      (parasiteHtml,x.name)
    }
    Ok(Json.obj("html" -> html, "name" -> name))

  }
}
