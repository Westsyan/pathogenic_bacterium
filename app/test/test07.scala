package test

import utils.ExecCommand

object test07 {


  def main(args: Array[String]): Unit = {

    val city = Array("china","usa_nih", "usa_bmbl", "australia", "belgium", "canada", "eu", "germany", "japan", "singapore", "switzerland", "uk")

    city.foreach{c=>

    val command =
      s"D:/ip4m/IP4M_v1.8/bin/Perl/bin/perl D:/ip4m/IP4M_v1.8/tools/stat/plot-pca.pl -i D:/ip4m/path2/peak_$c.txt " +
      s"-im D:/复旦病原菌数据库资料/tmp/pca2/${c}_pca_importance.txt -pdf D:/复旦病原菌数据库资料/tmp/pca2/${c}_pca_plot.pdf "+
      s"-ro D:/复旦病原菌数据库资料/tmp/pca2/${c}_pca_rotation.txt -si D:/复旦病原菌数据库资料/tmp/pca2/${c}_pca_scores.txt " +
      s"-html D:/复旦病原菌数据库资料/tmp/pca2/${c}_outputs.html -m D:/ip4m/path2/group_$c.txt -g group_name -pc 1-2 -w 6 -h 6 -l F -min 0 -log F -zscal F"



      val exec = new ExecCommand()
      exec.exec(command)
      println(exec.isSuccess)

    }

  }
}
