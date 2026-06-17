import scala.io.Source
import org.json4s._
import org.json4s.jackson.JsonMethods._

object FileIO {
  type Subscription = (String, String) // (subredditName, url) 
  type Post = (String, String, String, String, Int, String) // (subreddit, title, selftext, date, score, URL)
  // Pure function to read subscriptions from a JSON file
  def readSubscriptions(path : String): Option[List[Subscription]] = {
    
    try{ 
      implicit val formats: Formats = DefaultFormats
      val source = Source.fromFile(path)  //Leo el path
      val jsonString = source.mkString //paso el JSON a 1 String
      val file = parse(jsonString).extract[List[Map[String,String]]]//parseo el string para poder navegarlo
      //me devuelve un JValue, para trabajar comodamente hago el extract
      //donde cada Map es una subsc con el campo name y url
      val subscriptions = file.map{ submap => (submap("name"), submap("url"))} //mapeo la subscripciones
      source.close() //cierro el archivo
      Some(subscriptions) //retorno la lista de subscripciones
    }catch{
      case e : Exception =>
        println("Error: Archivo no existe")
        None
    }
  }

  // Pure function to download JSON feed from a URL
  def downloadFeed(url: String): Option[List[Post]] = {
  try{
    implicit val formats: Formats = DefaultFormats
    val source = Source.fromURL(url)
    val content = source.mkString
    val contentJson = parse(content)
    val child = (contentJson \ "data" \ "children").extract[List[JValue]]
    val posts = child.map{postmap =>   //extraigo las cosas que quiero imprimir del post
      val subreddit = (postmap \ "data" \ "subreddit").extract[String]
      val title = (postmap \ "data" \ "title").extract[String]
      val selftext = (postmap \ "data" \ "selftext").extract[String]
      val createdUtc = (postmap\ "data" \ "created_utc").extract[Double].toLong
      val date = TextProcessing.formatDateFromUTC(createdUtc)
      val score = (postmap \ "data" \ "score").extract[Int]
      val url = (postmap \ "data" \ "url").extract[String]
      (subreddit, title, selftext, date, score, url) //las imprimo
    }
    source.close()  //cierro el archivo
    Some(posts)
  }catch{
    case e : Exception =>
    //println(s"Error: ${e.getMessage}") print temporal para ver que error estaba pasando.
    None
  }
  }

  def filterPost(posts : List[Post]) : List[Post] = {
    posts.filter{ post => 
      post._2.nonEmpty &&     //cheque q el titulo no sea vacio
      post._3.nonEmpty &&     //chequeo que el selftext no sea vacio
      post._3.trim.nonEmpty   //chequeo que no sea un posts con solo espacios
    }
  }

}
