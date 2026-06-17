import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.io.Source


object Main {
  // Define `Subscription` as a simple tuple type alias
  type Subscription = (String, String, Int, String)
  
  type Post = (String, String, String)

  val wordsCensurated = Set("llm", "llms", "ai", "chatgpt", "copilot", "claude", "ml", "gemini", "agent", "agentic")
    
    // Pure function to read subscriptions from a JSON file
  def readSubscriptions(path: String): List[Subscription] = {
    try{  
      val source = Source.fromFile(path)
      val jsonString = source.mkString
      source.close()  //cierro el archivo para no generar leaks

      implicit val formats: Formats = DefaultFormats

      val json = parse(jsonString)

      val listJson = json.extract[List[JValue]]
      listJson.map { subscriptionMap =>
      (
        (subscriptionMap \ "name").extract[String],
        (subscriptionMap \ "url").extract[String],
        (subscriptionMap \ "count" ).extractOrElse[Int](0),
        (subscriptionMap \ "before").extractOrElse[String]("") 
      )
      }
    } catch {
      case e : Exception =>
      List.empty[Subscription]
    }
  }

  def readPosts(url: String): List[Post] = {
  try{  
    val source = Source.fromURL(url)
    val content = source.mkString
    source.close()  //cierro el archivo
    
    implicit val formats: Formats = DefaultFormats
    val contentJson = parse(content)
    val child = (contentJson \ "data" \ "children").extract[List[JValue]]
    val posts = child.map{postmap =>   //extraigo las cosas que quiero imprimir del post
      val title = (postmap \ "data" \ "title").extract[String]
      val selftext = (postmap \ "data" \ "selftext").extractOrElse[String]("")
      val autor = (postmap\ "data" \ "author").extract[String] 
      (title,selftext,autor)
    }
    posts
  }catch{
    case e : Exception =>
    println(s"Error: ${e.getMessage}") // print temporal para ver que error estaba pasando.
    List.empty[Post]
  }
}


  


    def countBannedWords(post: Post): Int ={
      val text = post._1 + post._2
      val wordsTmp = text.split("\\W+").toList
      val textFilter = wordsTmp.filter{ tmp =>
        wordsCensurated.contains(tmp.toLowerCase)
      }.length 
      textFilter
    }
    def printPost(post: Post): Unit = {
     val content = post._2
     val truncatedContent = if (content.length > 80) content.take(80) else content
     println(s"${post._1} by **${post._3}**")
     println(s"Contenido: $truncatedContent")
     println(s"Palabras Censuradas: ${countBannedWords(post)}")
     println("-----------------------")
    }
    def printSubscription(urlAndPosts: (String, List[Post])): Unit = {
      println(s"Posts from: ${urlAndPosts._1}")
      urlAndPosts._2.foreach(printPost)
    }

  // Main function to run
  def main(args: Array[String]): Unit = {
    val header = s"Reddit Post Parser\n${"=" * 40}"

    println("=======================")
    println("EJ1: LEER SUSCRIPCIONES")
    val subscriptions: List[Subscription] = readSubscriptions("subscriptions.json")

    // Print subscriptions read - We can use imperative for I/O
    for (subscription <- subscriptions) {
      println(subscription)
    }

    println("=======================")
    println("")
    println("=======================")
    println("EJ2: DESCARGAR POSTS")

    // Descargar y parsear los posts
    val allPosts: List[Post] = subscriptions.flatMap(sub => readPosts(sub._2)) 
    println(s"Se descargaron ${allPosts.length} posts en total.")

    println("=======================")
    println("")
    println("=======================")
    println("EJ3: IMPRIMIR POSTS Y CONTEO DE PALABRAS CENSURADAS")

    val tmp = subscriptions.map(sub => (sub._2, readPosts(sub._2))) //paso para que ahora sea una List[(String, List[Post])]
    tmp.foreach(printSubscription) //itero cada elemento de la lista y lo suscribo.
  
    // Print final results
    //allPosts.map(printSubscription)
    println("=======================")
  }
}
