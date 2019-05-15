package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import com.github.tototoshi.slick.MySQLJodaSupport._
  import org.joda.time.DateTime
  import slick.model.ForeignKeyAction
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Bacteria.schema ++ Fungus.schema ++ Parasite.schema ++ Password.schema ++ Virus.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Row type of table Bacteria */
  type BacteriaRow = HCons[Int,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for BacteriaRow providing default values if available in the database schema. */
  def BacteriaRow(id: Int, name: String, `·chinese·`: String, order: String, suborder: String, family: String, genus: String, species: String, isPeople: String, isAnimal: String, isPlant: String, tranRoute: String, infectiveDose: String, survivalCondition: String, isSanitizer: String, deathRate: String, isMedicine: String, isVaccine: String, chinaRisk: String, chinaNotes: String, usanihRisk: String, usanihNotes: String, usabmblRisk: String, usabmblNotes: String, australiaNewzealandRisk: String, australiaNewzealandNotes: String, belgiumRisk: String, belgiumNotes: String, canadaRisk: String, canadaNotes: String, euRisk: String, euNotes: String, germanyRisk: String, germanyNotes: String, japanRisk: String, japanNotes: String, singaporeRisk: String, singaporeNotes: String, switzerlandRisk: String, switzerlandNotes: String, ukRisk: String, ukNotes: String, selectAgentCdc: String, selectAgentUsda: String): BacteriaRow = {
    id :: name :: `·chinese·` :: order :: suborder :: family :: genus :: species :: isPeople :: isAnimal :: isPlant :: tranRoute :: infectiveDose :: survivalCondition :: isSanitizer :: deathRate :: isMedicine :: isVaccine :: chinaRisk :: chinaNotes :: usanihRisk :: usanihNotes :: usabmblRisk :: usabmblNotes :: australiaNewzealandRisk :: australiaNewzealandNotes :: belgiumRisk :: belgiumNotes :: canadaRisk :: canadaNotes :: euRisk :: euNotes :: germanyRisk :: germanyNotes :: japanRisk :: japanNotes :: singaporeRisk :: singaporeNotes :: switzerlandRisk :: switzerlandNotes :: ukRisk :: ukNotes :: selectAgentCdc :: selectAgentUsda :: HNil
  }
  /** GetResult implicit for fetching BacteriaRow objects using plain SQL queries */
  implicit def GetResultBacteriaRow(implicit e0: GR[Int], e1: GR[String]): GR[BacteriaRow] = GR{
    prs => import prs._
    <<[Int] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: HNil
  }
  /** Table description of table bacteria. Objects of this class serve as prototypes for rows in queries. */
  class Bacteria(_tableTag: Tag) extends profile.api.Table[BacteriaRow](_tableTag, Some("pathogenic_bacterium"), "bacteria") {
    def * = id :: name :: `·chinese·` :: order :: suborder :: family :: genus :: species :: isPeople :: isAnimal :: isPlant :: tranRoute :: infectiveDose :: survivalCondition :: isSanitizer :: deathRate :: isMedicine :: isVaccine :: chinaRisk :: chinaNotes :: usanihRisk :: usanihNotes :: usabmblRisk :: usabmblNotes :: australiaNewzealandRisk :: australiaNewzealandNotes :: belgiumRisk :: belgiumNotes :: canadaRisk :: canadaNotes :: euRisk :: euNotes :: germanyRisk :: germanyNotes :: japanRisk :: japanNotes :: singaporeRisk :: singaporeNotes :: switzerlandRisk :: switzerlandNotes :: ukRisk :: ukNotes :: selectAgentCdc :: selectAgentUsda :: HNil

    /** Database column id SqlType(INT), AutoInc */
    val id: Rep[Int] = column[Int]("id", O.AutoInc)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column ·chinese· SqlType(VARCHAR), Length(255,true) */
    val `·chinese·`: Rep[String] = column[String]("·chinese·", O.Length(255,varying=true))
    /** Database column order SqlType(LONGTEXT), Length(2147483647,true) */
    val order: Rep[String] = column[String]("order", O.Length(2147483647,varying=true))
    /** Database column suborder SqlType(LONGTEXT), Length(2147483647,true) */
    val suborder: Rep[String] = column[String]("suborder", O.Length(2147483647,varying=true))
    /** Database column family SqlType(LONGTEXT), Length(2147483647,true) */
    val family: Rep[String] = column[String]("family", O.Length(2147483647,varying=true))
    /** Database column genus SqlType(LONGTEXT), Length(2147483647,true) */
    val genus: Rep[String] = column[String]("genus", O.Length(2147483647,varying=true))
    /** Database column species SqlType(LONGTEXT), Length(2147483647,true) */
    val species: Rep[String] = column[String]("species", O.Length(2147483647,varying=true))
    /** Database column is_people SqlType(LONGTEXT), Length(2147483647,true) */
    val isPeople: Rep[String] = column[String]("is_people", O.Length(2147483647,varying=true))
    /** Database column is_animal SqlType(LONGTEXT), Length(2147483647,true) */
    val isAnimal: Rep[String] = column[String]("is_animal", O.Length(2147483647,varying=true))
    /** Database column is_plant SqlType(LONGTEXT), Length(2147483647,true) */
    val isPlant: Rep[String] = column[String]("is_plant", O.Length(2147483647,varying=true))
    /** Database column tran_route SqlType(LONGTEXT), Length(2147483647,true) */
    val tranRoute: Rep[String] = column[String]("tran_route", O.Length(2147483647,varying=true))
    /** Database column infective_dose SqlType(LONGTEXT), Length(2147483647,true) */
    val infectiveDose: Rep[String] = column[String]("infective_dose", O.Length(2147483647,varying=true))
    /** Database column survival_condition SqlType(LONGTEXT), Length(2147483647,true) */
    val survivalCondition: Rep[String] = column[String]("survival_condition", O.Length(2147483647,varying=true))
    /** Database column is_sanitizer SqlType(LONGTEXT), Length(2147483647,true) */
    val isSanitizer: Rep[String] = column[String]("is_sanitizer", O.Length(2147483647,varying=true))
    /** Database column death_rate SqlType(LONGTEXT), Length(2147483647,true) */
    val deathRate: Rep[String] = column[String]("death_rate", O.Length(2147483647,varying=true))
    /** Database column is_medicine SqlType(LONGTEXT), Length(2147483647,true) */
    val isMedicine: Rep[String] = column[String]("is_medicine", O.Length(2147483647,varying=true))
    /** Database column is_vaccine SqlType(LONGTEXT), Length(2147483647,true) */
    val isVaccine: Rep[String] = column[String]("is_vaccine", O.Length(2147483647,varying=true))
    /** Database column china_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val chinaRisk: Rep[String] = column[String]("china_risk", O.Length(2147483647,varying=true))
    /** Database column china_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val chinaNotes: Rep[String] = column[String]("china_notes", O.Length(2147483647,varying=true))
    /** Database column usanih_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val usanihRisk: Rep[String] = column[String]("usanih_risk", O.Length(2147483647,varying=true))
    /** Database column usanih_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val usanihNotes: Rep[String] = column[String]("usanih_notes", O.Length(2147483647,varying=true))
    /** Database column usabmbl_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val usabmblRisk: Rep[String] = column[String]("usabmbl_risk", O.Length(2147483647,varying=true))
    /** Database column usabmbl_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val usabmblNotes: Rep[String] = column[String]("usabmbl_notes", O.Length(2147483647,varying=true))
    /** Database column australia_newzealand_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val australiaNewzealandRisk: Rep[String] = column[String]("australia_newzealand_risk", O.Length(2147483647,varying=true))
    /** Database column australia_newzealand_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val australiaNewzealandNotes: Rep[String] = column[String]("australia_newzealand_notes", O.Length(2147483647,varying=true))
    /** Database column belgium_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val belgiumRisk: Rep[String] = column[String]("belgium_risk", O.Length(2147483647,varying=true))
    /** Database column belgium_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val belgiumNotes: Rep[String] = column[String]("belgium_notes", O.Length(2147483647,varying=true))
    /** Database column canada_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val canadaRisk: Rep[String] = column[String]("canada_risk", O.Length(2147483647,varying=true))
    /** Database column canada_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val canadaNotes: Rep[String] = column[String]("canada_notes", O.Length(2147483647,varying=true))
    /** Database column eu_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val euRisk: Rep[String] = column[String]("eu_risk", O.Length(2147483647,varying=true))
    /** Database column eu_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val euNotes: Rep[String] = column[String]("eu_notes", O.Length(2147483647,varying=true))
    /** Database column germany_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val germanyRisk: Rep[String] = column[String]("germany_risk", O.Length(2147483647,varying=true))
    /** Database column germany_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val germanyNotes: Rep[String] = column[String]("germany_notes", O.Length(2147483647,varying=true))
    /** Database column japan_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val japanRisk: Rep[String] = column[String]("japan_risk", O.Length(2147483647,varying=true))
    /** Database column japan_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val japanNotes: Rep[String] = column[String]("japan_notes", O.Length(2147483647,varying=true))
    /** Database column singapore_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val singaporeRisk: Rep[String] = column[String]("singapore_risk", O.Length(2147483647,varying=true))
    /** Database column singapore_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val singaporeNotes: Rep[String] = column[String]("singapore_notes", O.Length(2147483647,varying=true))
    /** Database column switzerland_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val switzerlandRisk: Rep[String] = column[String]("switzerland_risk", O.Length(2147483647,varying=true))
    /** Database column switzerland_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val switzerlandNotes: Rep[String] = column[String]("switzerland_notes", O.Length(2147483647,varying=true))
    /** Database column uk_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val ukRisk: Rep[String] = column[String]("uk_risk", O.Length(2147483647,varying=true))
    /** Database column uk_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val ukNotes: Rep[String] = column[String]("uk_notes", O.Length(2147483647,varying=true))
    /** Database column Select_Agent_CDC SqlType(LONGTEXT), Length(2147483647,true) */
    val selectAgentCdc: Rep[String] = column[String]("Select_Agent_CDC", O.Length(2147483647,varying=true))
    /** Database column Select_Agent_USDA SqlType(LONGTEXT), Length(2147483647,true) */
    val selectAgentUsda: Rep[String] = column[String]("Select_Agent_USDA", O.Length(2147483647,varying=true))

    /** Primary key of Bacteria (database name bacteria_PK) */
    val pk = primaryKey("bacteria_PK", id :: name :: HNil)
  }
  /** Collection-like TableQuery object for table Bacteria */
  lazy val Bacteria = new TableQuery(tag => new Bacteria(tag))

  /** Row type of table Fungus */
  type FungusRow = HCons[Int,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for FungusRow providing default values if available in the database schema. */
  def FungusRow(id: Int, name: String, chinese: String, order: String, family: String, genus: String, species: String, isPeople: String, isAnimal: String, isPlant: String, tranRoute: String, infectiveDose: String, survivalCondition: String, isSanitizer: String, deathRate: String, isMedicine: String, isVaccine: String, chinaRisk: String, chinaNotes: String, usanihRisk: String, usanihNotes: String, usabmblRisk: String, usabmblNotes: String, australiaNewzealandRisk: String, australiaNewzealandNotes: String, belgiumRisk: String, belgiumNotes: String, canadaRisk: String, canadaNotes: String, euRisk: String, euNotes: String, germanyRisk: String, germanyNotes: String, japanRisk: String, japanNotes: String, singaporeRisk: String, singaporeNotes: String, switzerlandRisk: String, switzerlandNotes: String, ukRisk: String, ukNotes: String, selectAgentCdc: String, selectAgentUsda: String): FungusRow = {
    id :: name :: chinese :: order :: family :: genus :: species :: isPeople :: isAnimal :: isPlant :: tranRoute :: infectiveDose :: survivalCondition :: isSanitizer :: deathRate :: isMedicine :: isVaccine :: chinaRisk :: chinaNotes :: usanihRisk :: usanihNotes :: usabmblRisk :: usabmblNotes :: australiaNewzealandRisk :: australiaNewzealandNotes :: belgiumRisk :: belgiumNotes :: canadaRisk :: canadaNotes :: euRisk :: euNotes :: germanyRisk :: germanyNotes :: japanRisk :: japanNotes :: singaporeRisk :: singaporeNotes :: switzerlandRisk :: switzerlandNotes :: ukRisk :: ukNotes :: selectAgentCdc :: selectAgentUsda :: HNil
  }
  /** GetResult implicit for fetching FungusRow objects using plain SQL queries */
  implicit def GetResultFungusRow(implicit e0: GR[Int], e1: GR[String]): GR[FungusRow] = GR{
    prs => import prs._
    <<[Int] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: HNil
  }
  /** Table description of table fungus. Objects of this class serve as prototypes for rows in queries. */
  class Fungus(_tableTag: Tag) extends profile.api.Table[FungusRow](_tableTag, Some("pathogenic_bacterium"), "fungus") {
    def * = id :: name :: chinese :: order :: family :: genus :: species :: isPeople :: isAnimal :: isPlant :: tranRoute :: infectiveDose :: survivalCondition :: isSanitizer :: deathRate :: isMedicine :: isVaccine :: chinaRisk :: chinaNotes :: usanihRisk :: usanihNotes :: usabmblRisk :: usabmblNotes :: australiaNewzealandRisk :: australiaNewzealandNotes :: belgiumRisk :: belgiumNotes :: canadaRisk :: canadaNotes :: euRisk :: euNotes :: germanyRisk :: germanyNotes :: japanRisk :: japanNotes :: singaporeRisk :: singaporeNotes :: switzerlandRisk :: switzerlandNotes :: ukRisk :: ukNotes :: selectAgentCdc :: selectAgentUsda :: HNil

    /** Database column id SqlType(INT), AutoInc */
    val id: Rep[Int] = column[Int]("id", O.AutoInc)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column chinese SqlType(VARCHAR), Length(255,true) */
    val chinese: Rep[String] = column[String]("chinese", O.Length(255,varying=true))
    /** Database column order SqlType(LONGTEXT), Length(2147483647,true) */
    val order: Rep[String] = column[String]("order", O.Length(2147483647,varying=true))
    /** Database column family SqlType(LONGTEXT), Length(2147483647,true) */
    val family: Rep[String] = column[String]("family", O.Length(2147483647,varying=true))
    /** Database column genus SqlType(LONGTEXT), Length(2147483647,true) */
    val genus: Rep[String] = column[String]("genus", O.Length(2147483647,varying=true))
    /** Database column species SqlType(LONGTEXT), Length(2147483647,true) */
    val species: Rep[String] = column[String]("species", O.Length(2147483647,varying=true))
    /** Database column is_people SqlType(LONGTEXT), Length(2147483647,true) */
    val isPeople: Rep[String] = column[String]("is_people", O.Length(2147483647,varying=true))
    /** Database column is_animal SqlType(LONGTEXT), Length(2147483647,true) */
    val isAnimal: Rep[String] = column[String]("is_animal", O.Length(2147483647,varying=true))
    /** Database column is_plant SqlType(LONGTEXT), Length(2147483647,true) */
    val isPlant: Rep[String] = column[String]("is_plant", O.Length(2147483647,varying=true))
    /** Database column tran_route SqlType(LONGTEXT), Length(2147483647,true) */
    val tranRoute: Rep[String] = column[String]("tran_route", O.Length(2147483647,varying=true))
    /** Database column infective_dose SqlType(LONGTEXT), Length(2147483647,true) */
    val infectiveDose: Rep[String] = column[String]("infective_dose", O.Length(2147483647,varying=true))
    /** Database column survival_condition SqlType(LONGTEXT), Length(2147483647,true) */
    val survivalCondition: Rep[String] = column[String]("survival_condition", O.Length(2147483647,varying=true))
    /** Database column is_sanitizer SqlType(LONGTEXT), Length(2147483647,true) */
    val isSanitizer: Rep[String] = column[String]("is_sanitizer", O.Length(2147483647,varying=true))
    /** Database column death_rate SqlType(LONGTEXT), Length(2147483647,true) */
    val deathRate: Rep[String] = column[String]("death_rate", O.Length(2147483647,varying=true))
    /** Database column is_medicine SqlType(LONGTEXT), Length(2147483647,true) */
    val isMedicine: Rep[String] = column[String]("is_medicine", O.Length(2147483647,varying=true))
    /** Database column is_vaccine SqlType(LONGTEXT), Length(2147483647,true) */
    val isVaccine: Rep[String] = column[String]("is_vaccine", O.Length(2147483647,varying=true))
    /** Database column china_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val chinaRisk: Rep[String] = column[String]("china_risk", O.Length(2147483647,varying=true))
    /** Database column china_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val chinaNotes: Rep[String] = column[String]("china_notes", O.Length(2147483647,varying=true))
    /** Database column usanih_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val usanihRisk: Rep[String] = column[String]("usanih_risk", O.Length(2147483647,varying=true))
    /** Database column usanih_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val usanihNotes: Rep[String] = column[String]("usanih_notes", O.Length(2147483647,varying=true))
    /** Database column usabmbl_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val usabmblRisk: Rep[String] = column[String]("usabmbl_risk", O.Length(2147483647,varying=true))
    /** Database column usabmbl_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val usabmblNotes: Rep[String] = column[String]("usabmbl_notes", O.Length(2147483647,varying=true))
    /** Database column australia_newzealand_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val australiaNewzealandRisk: Rep[String] = column[String]("australia_newzealand_risk", O.Length(2147483647,varying=true))
    /** Database column australia_newzealand_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val australiaNewzealandNotes: Rep[String] = column[String]("australia_newzealand_notes", O.Length(2147483647,varying=true))
    /** Database column belgium_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val belgiumRisk: Rep[String] = column[String]("belgium_risk", O.Length(2147483647,varying=true))
    /** Database column belgium_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val belgiumNotes: Rep[String] = column[String]("belgium_notes", O.Length(2147483647,varying=true))
    /** Database column canada_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val canadaRisk: Rep[String] = column[String]("canada_risk", O.Length(2147483647,varying=true))
    /** Database column canada_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val canadaNotes: Rep[String] = column[String]("canada_notes", O.Length(2147483647,varying=true))
    /** Database column eu_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val euRisk: Rep[String] = column[String]("eu_risk", O.Length(2147483647,varying=true))
    /** Database column eu_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val euNotes: Rep[String] = column[String]("eu_notes", O.Length(2147483647,varying=true))
    /** Database column germany_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val germanyRisk: Rep[String] = column[String]("germany_risk", O.Length(2147483647,varying=true))
    /** Database column germany_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val germanyNotes: Rep[String] = column[String]("germany_notes", O.Length(2147483647,varying=true))
    /** Database column japan_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val japanRisk: Rep[String] = column[String]("japan_risk", O.Length(2147483647,varying=true))
    /** Database column japan_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val japanNotes: Rep[String] = column[String]("japan_notes", O.Length(2147483647,varying=true))
    /** Database column singapore_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val singaporeRisk: Rep[String] = column[String]("singapore_risk", O.Length(2147483647,varying=true))
    /** Database column singapore_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val singaporeNotes: Rep[String] = column[String]("singapore_notes", O.Length(2147483647,varying=true))
    /** Database column switzerland_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val switzerlandRisk: Rep[String] = column[String]("switzerland_risk", O.Length(2147483647,varying=true))
    /** Database column switzerland_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val switzerlandNotes: Rep[String] = column[String]("switzerland_notes", O.Length(2147483647,varying=true))
    /** Database column uk_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val ukRisk: Rep[String] = column[String]("uk_risk", O.Length(2147483647,varying=true))
    /** Database column uk_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val ukNotes: Rep[String] = column[String]("uk_notes", O.Length(2147483647,varying=true))
    /** Database column Select_Agent_CDC SqlType(LONGTEXT), Length(2147483647,true) */
    val selectAgentCdc: Rep[String] = column[String]("Select_Agent_CDC", O.Length(2147483647,varying=true))
    /** Database column Select_Agent_USDA SqlType(LONGTEXT), Length(2147483647,true) */
    val selectAgentUsda: Rep[String] = column[String]("Select_Agent_USDA", O.Length(2147483647,varying=true))

    /** Primary key of Fungus (database name fungus_PK) */
    val pk = primaryKey("fungus_PK", id :: name :: HNil)
  }
  /** Collection-like TableQuery object for table Fungus */
  lazy val Fungus = new TableQuery(tag => new Fungus(tag))

  /** Row type of table Parasite */
  type ParasiteRow = HCons[Int,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for ParasiteRow providing default values if available in the database schema. */
  def ParasiteRow(id: Int, name: String, chinese: String, order: String, family: String, genus: String, species: String, isPeople: String, isAnimal: String, isPlant: String, tranRoute: String, infectiveDose: String, survivalCondition: String, isSanitizer: String, deathRate: String, isMedicine: String, isVaccine: String, chinaRisk: String, chinaNotes: String, usanihRisk: String, usanihNotes: String, usabmblRisk: String, usabmblNotes: String, australiaNewzealandRisk: String, australiaNewzealandNotes: String, belgiumRisk: String, belgiumNotes: String, canadaRisk: String, canadaNotes: String, euRisk: String, euNotes: String, germanyRisk: String, germanyNotes: String, japanRisk: String, japanNotes: String, singaporeRisk: String, singaporeNotes: String, switzerlandRisk: String, switzerlandNotes: String, ukRisk: String, ukNotes: String, selectAgentCdc: String, selectAgentUsda: String): ParasiteRow = {
    id :: name :: chinese :: order :: family :: genus :: species :: isPeople :: isAnimal :: isPlant :: tranRoute :: infectiveDose :: survivalCondition :: isSanitizer :: deathRate :: isMedicine :: isVaccine :: chinaRisk :: chinaNotes :: usanihRisk :: usanihNotes :: usabmblRisk :: usabmblNotes :: australiaNewzealandRisk :: australiaNewzealandNotes :: belgiumRisk :: belgiumNotes :: canadaRisk :: canadaNotes :: euRisk :: euNotes :: germanyRisk :: germanyNotes :: japanRisk :: japanNotes :: singaporeRisk :: singaporeNotes :: switzerlandRisk :: switzerlandNotes :: ukRisk :: ukNotes :: selectAgentCdc :: selectAgentUsda :: HNil
  }
  /** GetResult implicit for fetching ParasiteRow objects using plain SQL queries */
  implicit def GetResultParasiteRow(implicit e0: GR[Int], e1: GR[String]): GR[ParasiteRow] = GR{
    prs => import prs._
    <<[Int] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: HNil
  }
  /** Table description of table parasite. Objects of this class serve as prototypes for rows in queries. */
  class Parasite(_tableTag: Tag) extends profile.api.Table[ParasiteRow](_tableTag, Some("pathogenic_bacterium"), "parasite") {
    def * = id :: name :: chinese :: order :: family :: genus :: species :: isPeople :: isAnimal :: isPlant :: tranRoute :: infectiveDose :: survivalCondition :: isSanitizer :: deathRate :: isMedicine :: isVaccine :: chinaRisk :: chinaNotes :: usanihRisk :: usanihNotes :: usabmblRisk :: usabmblNotes :: australiaNewzealandRisk :: australiaNewzealandNotes :: belgiumRisk :: belgiumNotes :: canadaRisk :: canadaNotes :: euRisk :: euNotes :: germanyRisk :: germanyNotes :: japanRisk :: japanNotes :: singaporeRisk :: singaporeNotes :: switzerlandRisk :: switzerlandNotes :: ukRisk :: ukNotes :: selectAgentCdc :: selectAgentUsda :: HNil

    /** Database column id SqlType(INT), AutoInc */
    val id: Rep[Int] = column[Int]("id", O.AutoInc)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column chinese SqlType(VARCHAR), Length(255,true) */
    val chinese: Rep[String] = column[String]("chinese", O.Length(255,varying=true))
    /** Database column order SqlType(LONGTEXT), Length(2147483647,true) */
    val order: Rep[String] = column[String]("order", O.Length(2147483647,varying=true))
    /** Database column family SqlType(LONGTEXT), Length(2147483647,true) */
    val family: Rep[String] = column[String]("family", O.Length(2147483647,varying=true))
    /** Database column genus SqlType(LONGTEXT), Length(2147483647,true) */
    val genus: Rep[String] = column[String]("genus", O.Length(2147483647,varying=true))
    /** Database column species SqlType(LONGTEXT), Length(2147483647,true) */
    val species: Rep[String] = column[String]("species", O.Length(2147483647,varying=true))
    /** Database column is_people SqlType(LONGTEXT), Length(2147483647,true) */
    val isPeople: Rep[String] = column[String]("is_people", O.Length(2147483647,varying=true))
    /** Database column is_animal SqlType(LONGTEXT), Length(2147483647,true) */
    val isAnimal: Rep[String] = column[String]("is_animal", O.Length(2147483647,varying=true))
    /** Database column is_plant SqlType(LONGTEXT), Length(2147483647,true) */
    val isPlant: Rep[String] = column[String]("is_plant", O.Length(2147483647,varying=true))
    /** Database column tran_route SqlType(LONGTEXT), Length(2147483647,true) */
    val tranRoute: Rep[String] = column[String]("tran_route", O.Length(2147483647,varying=true))
    /** Database column infective_dose SqlType(LONGTEXT), Length(2147483647,true) */
    val infectiveDose: Rep[String] = column[String]("infective_dose", O.Length(2147483647,varying=true))
    /** Database column survival_condition SqlType(LONGTEXT), Length(2147483647,true) */
    val survivalCondition: Rep[String] = column[String]("survival_condition", O.Length(2147483647,varying=true))
    /** Database column is_sanitizer SqlType(LONGTEXT), Length(2147483647,true) */
    val isSanitizer: Rep[String] = column[String]("is_sanitizer", O.Length(2147483647,varying=true))
    /** Database column death_rate SqlType(LONGTEXT), Length(2147483647,true) */
    val deathRate: Rep[String] = column[String]("death_rate", O.Length(2147483647,varying=true))
    /** Database column is_medicine SqlType(LONGTEXT), Length(2147483647,true) */
    val isMedicine: Rep[String] = column[String]("is_medicine", O.Length(2147483647,varying=true))
    /** Database column is_vaccine SqlType(LONGTEXT), Length(2147483647,true) */
    val isVaccine: Rep[String] = column[String]("is_vaccine", O.Length(2147483647,varying=true))
    /** Database column china_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val chinaRisk: Rep[String] = column[String]("china_risk", O.Length(2147483647,varying=true))
    /** Database column china_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val chinaNotes: Rep[String] = column[String]("china_notes", O.Length(2147483647,varying=true))
    /** Database column usanih_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val usanihRisk: Rep[String] = column[String]("usanih_risk", O.Length(2147483647,varying=true))
    /** Database column usanih_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val usanihNotes: Rep[String] = column[String]("usanih_notes", O.Length(2147483647,varying=true))
    /** Database column usabmbl_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val usabmblRisk: Rep[String] = column[String]("usabmbl_risk", O.Length(2147483647,varying=true))
    /** Database column usabmbl_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val usabmblNotes: Rep[String] = column[String]("usabmbl_notes", O.Length(2147483647,varying=true))
    /** Database column australia_newzealand_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val australiaNewzealandRisk: Rep[String] = column[String]("australia_newzealand_risk", O.Length(2147483647,varying=true))
    /** Database column australia_newzealand_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val australiaNewzealandNotes: Rep[String] = column[String]("australia_newzealand_notes", O.Length(2147483647,varying=true))
    /** Database column belgium_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val belgiumRisk: Rep[String] = column[String]("belgium_risk", O.Length(2147483647,varying=true))
    /** Database column belgium_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val belgiumNotes: Rep[String] = column[String]("belgium_notes", O.Length(2147483647,varying=true))
    /** Database column canada_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val canadaRisk: Rep[String] = column[String]("canada_risk", O.Length(2147483647,varying=true))
    /** Database column canada_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val canadaNotes: Rep[String] = column[String]("canada_notes", O.Length(2147483647,varying=true))
    /** Database column eu_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val euRisk: Rep[String] = column[String]("eu_risk", O.Length(2147483647,varying=true))
    /** Database column eu_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val euNotes: Rep[String] = column[String]("eu_notes", O.Length(2147483647,varying=true))
    /** Database column germany_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val germanyRisk: Rep[String] = column[String]("germany_risk", O.Length(2147483647,varying=true))
    /** Database column germany_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val germanyNotes: Rep[String] = column[String]("germany_notes", O.Length(2147483647,varying=true))
    /** Database column japan_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val japanRisk: Rep[String] = column[String]("japan_risk", O.Length(2147483647,varying=true))
    /** Database column japan_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val japanNotes: Rep[String] = column[String]("japan_notes", O.Length(2147483647,varying=true))
    /** Database column singapore_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val singaporeRisk: Rep[String] = column[String]("singapore_risk", O.Length(2147483647,varying=true))
    /** Database column singapore_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val singaporeNotes: Rep[String] = column[String]("singapore_notes", O.Length(2147483647,varying=true))
    /** Database column switzerland_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val switzerlandRisk: Rep[String] = column[String]("switzerland_risk", O.Length(2147483647,varying=true))
    /** Database column switzerland_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val switzerlandNotes: Rep[String] = column[String]("switzerland_notes", O.Length(2147483647,varying=true))
    /** Database column uk_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val ukRisk: Rep[String] = column[String]("uk_risk", O.Length(2147483647,varying=true))
    /** Database column uk_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val ukNotes: Rep[String] = column[String]("uk_notes", O.Length(2147483647,varying=true))
    /** Database column Select_Agent_CDC SqlType(LONGTEXT), Length(2147483647,true) */
    val selectAgentCdc: Rep[String] = column[String]("Select_Agent_CDC", O.Length(2147483647,varying=true))
    /** Database column Select_Agent_USDA SqlType(LONGTEXT), Length(2147483647,true) */
    val selectAgentUsda: Rep[String] = column[String]("Select_Agent_USDA", O.Length(2147483647,varying=true))

    /** Primary key of Parasite (database name parasite_PK) */
    val pk = primaryKey("parasite_PK", id :: name :: HNil)
  }
  /** Collection-like TableQuery object for table Parasite */
  lazy val Parasite = new TableQuery(tag => new Parasite(tag))

  /** Entity class storing rows of table Password
   *  @param id Database column id SqlType(INT), AutoInc
   *  @param user Database column user SqlType(VARCHAR), Length(255,true)
   *  @param password Database column password SqlType(VARCHAR), Length(255,true) */
  final case class PasswordRow(id: Int, user: String, password: String)
  /** GetResult implicit for fetching PasswordRow objects using plain SQL queries */
  implicit def GetResultPasswordRow(implicit e0: GR[Int], e1: GR[String]): GR[PasswordRow] = GR{
    prs => import prs._
    PasswordRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table password. Objects of this class serve as prototypes for rows in queries. */
  class Password(_tableTag: Tag) extends profile.api.Table[PasswordRow](_tableTag, Some("pathogenic_bacterium"), "password") {
    def * = (id, user, password) <> (PasswordRow.tupled, PasswordRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(user), Rep.Some(password)).shaped.<>({r=>import r._; _1.map(_=> PasswordRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc */
    val id: Rep[Int] = column[Int]("id", O.AutoInc)
    /** Database column user SqlType(VARCHAR), Length(255,true) */
    val user: Rep[String] = column[String]("user", O.Length(255,varying=true))
    /** Database column password SqlType(VARCHAR), Length(255,true) */
    val password: Rep[String] = column[String]("password", O.Length(255,varying=true))

    /** Primary key of Password (database name password_PK) */
    val pk = primaryKey("password_PK", (id, user))
  }
  /** Collection-like TableQuery object for table Password */
  lazy val Password = new TableQuery(tag => new Password(tag))

  /** Row type of table Virus */
  type VirusRow = HCons[Int,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HCons[String,HNil]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
  /** Constructor for VirusRow providing default values if available in the database schema. */
  def VirusRow(id: Int, name: String, chinese: String, order: String, family: String, subfamily: String, genus: String, species: String, genomeComposition: String, isPeople: String, isAnimal: String, isPlant: String, tranRoute: String, infectiveDose: String, survivalCondition: String, isSanitizer: String, deathRate: String, isMedicine: String, isVaccine: String, chinaRisk: String, chinaNotes: String, usanihRisk: String, usanihNotes: String, usabmblRisk: String, usabmblNotes: String, australiaNewzealandRisk: String, australiaNewzealandNotes: String, belgiumRisk: String, belgiumNotes: String, canadaRisk: String, canadaNotes: String, euRisk: String, euNotes: String, germanyRisk: String, germanyNotes: String, japanRisk: String, japanNotes: String, singaporeRisk: String, singaporeNotes: String, switzerlandRisk: String, switzerlandNotes: String, ukRisk: String, ukNotes: String, selectAgentCdc: String, selectAgentUsda: String): VirusRow = {
    id :: name :: chinese :: order :: family :: subfamily :: genus :: species :: genomeComposition :: isPeople :: isAnimal :: isPlant :: tranRoute :: infectiveDose :: survivalCondition :: isSanitizer :: deathRate :: isMedicine :: isVaccine :: chinaRisk :: chinaNotes :: usanihRisk :: usanihNotes :: usabmblRisk :: usabmblNotes :: australiaNewzealandRisk :: australiaNewzealandNotes :: belgiumRisk :: belgiumNotes :: canadaRisk :: canadaNotes :: euRisk :: euNotes :: germanyRisk :: germanyNotes :: japanRisk :: japanNotes :: singaporeRisk :: singaporeNotes :: switzerlandRisk :: switzerlandNotes :: ukRisk :: ukNotes :: selectAgentCdc :: selectAgentUsda :: HNil
  }
  /** GetResult implicit for fetching VirusRow objects using plain SQL queries */
  implicit def GetResultVirusRow(implicit e0: GR[Int], e1: GR[String]): GR[VirusRow] = GR{
    prs => import prs._
    <<[Int] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: <<[String] :: HNil
  }
  /** Table description of table virus. Objects of this class serve as prototypes for rows in queries. */
  class Virus(_tableTag: Tag) extends profile.api.Table[VirusRow](_tableTag, Some("pathogenic_bacterium"), "virus") {
    def * = id :: name :: chinese :: order :: family :: subfamily :: genus :: species :: genomeComposition :: isPeople :: isAnimal :: isPlant :: tranRoute :: infectiveDose :: survivalCondition :: isSanitizer :: deathRate :: isMedicine :: isVaccine :: chinaRisk :: chinaNotes :: usanihRisk :: usanihNotes :: usabmblRisk :: usabmblNotes :: australiaNewzealandRisk :: australiaNewzealandNotes :: belgiumRisk :: belgiumNotes :: canadaRisk :: canadaNotes :: euRisk :: euNotes :: germanyRisk :: germanyNotes :: japanRisk :: japanNotes :: singaporeRisk :: singaporeNotes :: switzerlandRisk :: switzerlandNotes :: ukRisk :: ukNotes :: selectAgentCdc :: selectAgentUsda :: HNil

    /** Database column id SqlType(INT), AutoInc */
    val id: Rep[Int] = column[Int]("id", O.AutoInc)
    /** Database column name SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("name", O.Length(255,varying=true))
    /** Database column chinese SqlType(VARCHAR), Length(255,true) */
    val chinese: Rep[String] = column[String]("chinese", O.Length(255,varying=true))
    /** Database column order SqlType(LONGTEXT), Length(2147483647,true) */
    val order: Rep[String] = column[String]("order", O.Length(2147483647,varying=true))
    /** Database column family SqlType(LONGTEXT), Length(2147483647,true) */
    val family: Rep[String] = column[String]("family", O.Length(2147483647,varying=true))
    /** Database column subfamily SqlType(LONGTEXT), Length(2147483647,true) */
    val subfamily: Rep[String] = column[String]("subfamily", O.Length(2147483647,varying=true))
    /** Database column genus SqlType(LONGTEXT), Length(2147483647,true) */
    val genus: Rep[String] = column[String]("genus", O.Length(2147483647,varying=true))
    /** Database column species SqlType(LONGTEXT), Length(2147483647,true) */
    val species: Rep[String] = column[String]("species", O.Length(2147483647,varying=true))
    /** Database column genome_composition SqlType(LONGTEXT), Length(2147483647,true) */
    val genomeComposition: Rep[String] = column[String]("genome_composition", O.Length(2147483647,varying=true))
    /** Database column is_people SqlType(LONGTEXT), Length(2147483647,true) */
    val isPeople: Rep[String] = column[String]("is_people", O.Length(2147483647,varying=true))
    /** Database column is_animal SqlType(LONGTEXT), Length(2147483647,true) */
    val isAnimal: Rep[String] = column[String]("is_animal", O.Length(2147483647,varying=true))
    /** Database column is_plant SqlType(LONGTEXT), Length(2147483647,true) */
    val isPlant: Rep[String] = column[String]("is_plant", O.Length(2147483647,varying=true))
    /** Database column tran_route SqlType(LONGTEXT), Length(2147483647,true) */
    val tranRoute: Rep[String] = column[String]("tran_route", O.Length(2147483647,varying=true))
    /** Database column infective_dose SqlType(LONGTEXT), Length(2147483647,true) */
    val infectiveDose: Rep[String] = column[String]("infective_dose", O.Length(2147483647,varying=true))
    /** Database column survival_condition SqlType(LONGTEXT), Length(2147483647,true) */
    val survivalCondition: Rep[String] = column[String]("survival_condition", O.Length(2147483647,varying=true))
    /** Database column is_sanitizer SqlType(LONGTEXT), Length(2147483647,true) */
    val isSanitizer: Rep[String] = column[String]("is_sanitizer", O.Length(2147483647,varying=true))
    /** Database column death_rate SqlType(LONGTEXT), Length(2147483647,true) */
    val deathRate: Rep[String] = column[String]("death_rate", O.Length(2147483647,varying=true))
    /** Database column is_medicine SqlType(LONGTEXT), Length(2147483647,true) */
    val isMedicine: Rep[String] = column[String]("is_medicine", O.Length(2147483647,varying=true))
    /** Database column is_vaccine SqlType(LONGTEXT), Length(2147483647,true) */
    val isVaccine: Rep[String] = column[String]("is_vaccine", O.Length(2147483647,varying=true))
    /** Database column china_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val chinaRisk: Rep[String] = column[String]("china_risk", O.Length(2147483647,varying=true))
    /** Database column china_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val chinaNotes: Rep[String] = column[String]("china_notes", O.Length(2147483647,varying=true))
    /** Database column usanih_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val usanihRisk: Rep[String] = column[String]("usanih_risk", O.Length(2147483647,varying=true))
    /** Database column usanih_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val usanihNotes: Rep[String] = column[String]("usanih_notes", O.Length(2147483647,varying=true))
    /** Database column usabmbl_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val usabmblRisk: Rep[String] = column[String]("usabmbl_risk", O.Length(2147483647,varying=true))
    /** Database column usabmbl_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val usabmblNotes: Rep[String] = column[String]("usabmbl_notes", O.Length(2147483647,varying=true))
    /** Database column australia_newzealand_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val australiaNewzealandRisk: Rep[String] = column[String]("australia_newzealand_risk", O.Length(2147483647,varying=true))
    /** Database column australia_newzealand_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val australiaNewzealandNotes: Rep[String] = column[String]("australia_newzealand_notes", O.Length(2147483647,varying=true))
    /** Database column belgium_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val belgiumRisk: Rep[String] = column[String]("belgium_risk", O.Length(2147483647,varying=true))
    /** Database column belgium_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val belgiumNotes: Rep[String] = column[String]("belgium_notes", O.Length(2147483647,varying=true))
    /** Database column canada_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val canadaRisk: Rep[String] = column[String]("canada_risk", O.Length(2147483647,varying=true))
    /** Database column canada_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val canadaNotes: Rep[String] = column[String]("canada_notes", O.Length(2147483647,varying=true))
    /** Database column eu_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val euRisk: Rep[String] = column[String]("eu_risk", O.Length(2147483647,varying=true))
    /** Database column eu_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val euNotes: Rep[String] = column[String]("eu_notes", O.Length(2147483647,varying=true))
    /** Database column germany_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val germanyRisk: Rep[String] = column[String]("germany_risk", O.Length(2147483647,varying=true))
    /** Database column germany_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val germanyNotes: Rep[String] = column[String]("germany_notes", O.Length(2147483647,varying=true))
    /** Database column japan_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val japanRisk: Rep[String] = column[String]("japan_risk", O.Length(2147483647,varying=true))
    /** Database column japan_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val japanNotes: Rep[String] = column[String]("japan_notes", O.Length(2147483647,varying=true))
    /** Database column singapore_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val singaporeRisk: Rep[String] = column[String]("singapore_risk", O.Length(2147483647,varying=true))
    /** Database column singapore_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val singaporeNotes: Rep[String] = column[String]("singapore_notes", O.Length(2147483647,varying=true))
    /** Database column switzerland_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val switzerlandRisk: Rep[String] = column[String]("switzerland_risk", O.Length(2147483647,varying=true))
    /** Database column switzerland_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val switzerlandNotes: Rep[String] = column[String]("switzerland_notes", O.Length(2147483647,varying=true))
    /** Database column uk_risk SqlType(LONGTEXT), Length(2147483647,true) */
    val ukRisk: Rep[String] = column[String]("uk_risk", O.Length(2147483647,varying=true))
    /** Database column uk_notes SqlType(LONGTEXT), Length(2147483647,true) */
    val ukNotes: Rep[String] = column[String]("uk_notes", O.Length(2147483647,varying=true))
    /** Database column Select_Agent_CDC SqlType(LONGTEXT), Length(2147483647,true) */
    val selectAgentCdc: Rep[String] = column[String]("Select_Agent_CDC", O.Length(2147483647,varying=true))
    /** Database column Select_Agent_USDA SqlType(LONGTEXT), Length(2147483647,true) */
    val selectAgentUsda: Rep[String] = column[String]("Select_Agent_USDA", O.Length(2147483647,varying=true))

    /** Primary key of Virus (database name virus_PK) */
    val pk = primaryKey("virus_PK", id :: name :: HNil)
  }
  /** Collection-like TableQuery object for table Virus */
  lazy val Virus = new TableQuery(tag => new Virus(tag))
}
