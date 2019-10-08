package test

import java.io.{File, FileInputStream, FileOutputStream}
import java.text.SimpleDateFormat

import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.StringUtils
import org.apache.poi.ss.usermodel.{CellType, DateUtil, FillPatternType, IndexedColors}
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import utils.Utils

import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConverters._

object test05 {


  def main(args: Array[String]): Unit = {

    val xlsx = xlsx2Lines(new File("D:\\复旦病原菌数据库资料/汇总 2019-5-27英文版.xlsx"), 4)

    val xl = xlsx.map { x =>
      val c = x.split("\t").map(_.trim.replaceAll("\n", ""))
      (c.head, c.slice(1, 8).mkString("\t"), c.drop(8))
    }.groupBy(_._2)

    val lines = xl.flatMap(_._2).map(x => x._1 + "\t" + x._2 + "\t" + x._3.mkString("\t")).toArray

    val group = xl.flatMap { x =>
      val colors = (0 to 11).map { i =>
        val state =  x._2.map { z =>
          if (i == 11 && z._3.length < 12) {
            ""
          } else {
            z._3(i).trim
          }
        }.filter(_ != "")

        if(state.distinct.length > 1){
           state.groupBy(g=> g).map(m => (m._1,m._2.length)).toArray.sortBy(_._1.toInt).reverse.maxBy(_._2)._1
        }else{
          state.distinct
        }

        val states = state.distinct.length


        val color = if (states > 1) {
          "yellow"
        } else {
          " "
        }

        color
      }.mkString("\t")
      x._2.map { y =>
        (y._1 + "\t" + y._2 + "\t" + colors).split("\t")
      }
    }.toArray




  }


  def getRisk = {
    val xlsx = xlsx2Lines(new File("D:\\复旦病原菌数据库资料/汇总 2019-5-27英文版.xlsx"), 4)

    val xl = xlsx.map { x =>
      val c = x.split("\t").map(_.trim.replaceAll("\n", ""))
      (c.head, c.slice(1, 8).mkString("\t"), c.drop(8))
    }.groupBy(_._2)

    val lines = xl.flatMap(_._2).map(x => x._1 + "\t" + x._2 + "\t" + x._3.mkString("\t")).toArray

    val group = xl.flatMap { x =>
      val colors = (0 to 11).map { i =>
        val state =  x._2.map { z =>
          if (i == 11 && z._3.length < 12) {
            ""
          } else {
            z._3(i).trim
          }
        }

        val states = state.distinct.count(_ != "")
        val color = if (states > 1) {
          "yellow"
        } else {
          " "
        }

        color
      }.mkString("\t")
      x._2.map { y =>
        (y._1 + "\t" + y._2 + "\t" + colors).split("\t")
      }
    }.toArray


    val outputWorkbook = new XSSFWorkbook()
    val outputSheet = outputWorkbook.createSheet("Sheet1")
    val yellowStyle = getYellowStyle(outputWorkbook)
    for (i <- lines.indices) {
      val outputEachRow = outputSheet.createRow(i)
      val line = lines(i)
      val columns = line.split("\t")
      for (j <- 0 until 20) {
        val cell = outputEachRow.createCell(j)
        if(columns.length > j){
          cell.setCellValue(columns(j))
        }else{
          cell.setCellValue("")
        }
        group(i)(j) match {
          case "yellow" =>
            cell.setCellStyle(yellowStyle)
          case _ =>
        }

      }
    }


    val fileOutputStream = new FileOutputStream("D:\\复旦病原菌数据库资料/test.xlsx")
    outputWorkbook.write(fileOutputStream)
    fileOutputStream.close()
    outputWorkbook.close()
  }

  def getYellowStyle(workbook: XSSFWorkbook) = {
    val style = workbook.createCellStyle()
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND)
    style.setFillForegroundColor(IndexedColors.YELLOW.getIndex)
    style
  }

  def dye(file: File, colorFile: File, outFile: File) = {
    val outputWorkbook = new XSSFWorkbook()
    val outputSheet = outputWorkbook.createSheet("Sheet1")
    val format = outputWorkbook.createDataFormat()
    val lines = FileUtils.readLines(file).asScala
    val colorLines = FileUtils.readLines(colorFile).asScala.map(_.split("\t"))
    val yellowStyle = getYellowStyle(outputWorkbook)
    for (i <- lines.indices) {
      val outputEachRow = outputSheet.createRow(i)
      val line = lines(i)
      val columns = line.split("\t")
      for (j <- 0 until columns.size) {
        val cell = outputEachRow.createCell(j)
        cell.setCellValue(columns(j))
        val bat = colorLines(i)(1)
        if (i > 0 && j > 1 && Utils.isDouble(bat)) {
          if (Utils.isDouble(columns(j))) {
            cell.setCellValue(columns(j).toDouble)
          }
          colorLines(i)(j) match {
            case "yellow" =>
              cell.setCellStyle(yellowStyle)
            case _ =>
          }
        }

      }
    }
    val fileOutputStream = new FileOutputStream(outFile)
    outputWorkbook.write(fileOutputStream)
    fileOutputStream.close()
    outputWorkbook.close()
  }


  def xlsx2Lines(xlsxFile: File, sheetIndex: Int): ArrayBuffer[String] = {
    val is = new FileInputStream(xlsxFile.getAbsolutePath)
    val xssfWorkbook = new XSSFWorkbook(is)
    val xssfSheet = xssfWorkbook.getSheetAt(sheetIndex)
    val lines = ArrayBuffer[String]()
    for (i <- 0 to xssfSheet.getLastRowNum) {
      val xssfRow = xssfSheet.getRow(i)
      val columns = ArrayBuffer[String]()
      for (j <- 0 until xssfRow.getLastCellNum) {
        val cell = xssfRow.getCell(j)
        var value = ""
        if (cell != null) {
          cell.getCellType match {
            case CellType.STRING =>
              value = cell.getStringCellValue
            case CellType.NUMERIC =>
              if (DateUtil.isCellDateFormatted(cell)) {
                val dateFormat = new SimpleDateFormat("yyyy/MM/dd")
                value = dateFormat.format(cell.getDateCellValue)
              } else {
                val doubleValue = cell.getNumericCellValue
                value = if (doubleValue == doubleValue.toInt) {
                  doubleValue.toInt.toString
                } else doubleValue.toString
              }
            case CellType.BLANK =>
              value = ""
            case _ =>
              value = ""
          }
        }

        columns += value.replaceAll("\n", " ")
      }
      val line = columns.mkString("\t")
      lines += line
    }
    xssfWorkbook.close()
    lines.filter(StringUtils.isNotBlank(_))
  }


}
