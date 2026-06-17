error id: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab1/lab1/skeleton-v0/skeleton/src/main/scala/FileIO.scala:None.
file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab1/lab1/skeleton-v0/skeleton/src/main/scala/FileIO.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -org/json4s/None.
	 -org/json4s/None#
	 -org/json4s/None().
	 -org/json4s/jackson/JsonMethods.None.
	 -org/json4s/jackson/JsonMethods.None#
	 -org/json4s/jackson/JsonMethods.None().
	 -None.
	 -None#
	 -None().
	 -scala/Predef.None.
	 -scala/Predef.None#
	 -scala/Predef.None().
offset: 2022
uri: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab1/lab1/skeleton-v0/skeleton/src/main/scala/FileIO.scala
text:
```scala
import scala.io.Source
import org.json4s._
import org.json4s.jackson.JsonMethods._

object FileIO {
  type Subscription = (String, String) // (subredditName, url) 
  type Post = (String, String, String, String) // (subreddit, title, selftext, date)
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
    val posts = child.map{postmap => 
      val subreddit = (postmap \ "data" \ "subreddit").extract[String]
      val title = (postmap \ "data" \ "title").extract[String]
      val selftext = (postmap \ "data" \ "selftext").extract[String]
      val createdUtc = (postmap\ "data" \ "created_utc").extract[Double].toLong
      val date = TextProcessing.formatDateFromUTC(createdUtc)
      (subreddit, title, selftext, date)
    }
    source.close()  
    Some(posts)
  }catch{
    case e : Exception =>
    println(s"Error: ${e.getMessage}")
    Non@@e
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

```


#### Short summary: 

empty definition using pc, found symbol in pc: 