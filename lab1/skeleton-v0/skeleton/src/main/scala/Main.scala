object Main {
  def main(args: Array[String]): Unit = {

    val header = s"Reddit Post Parser\n${"=" * 40}"

    val subscriptions = FileIO.readSubscriptions("subscriptions.json").toList.flatten 
    //uso toList para pasar a una lista de option, y el flatten para eliminar el option.
    val posts = subscriptions.flatMap { subs =>  //descargo los posts
                  FileIO.downloadFeed(subs._2).toList.flatten
                }
    val filteredPosts = FileIO.filterPost(posts)
    filteredPosts.take(5).foreach{ post =>
      println(s"Titulo: ${post._2} | Fecha: ${post._4} | Url: ${post._6}")
    }
    
    val subredditScore = filteredPosts.groupBy(word => word._1).mapValues{list => list
    .foldLeft(0){(acumulador, post) => acumulador + post._5}}
    subredditScore.foreach{ case (subreddit, score) =>
      println(s"Subreddit: $subreddit - Total score: $score")
    }

    val allPosts = filteredPosts
    //println(allPosts) 
    
    val wordCount = TextProcessing.countWords(filteredPosts.map(post => post._3).mkString(" "))
    println(s"Cantidad de palabras : $wordCount")  

    val output = allPosts
      .map { post => Formatters.formatSubscription(post) }
      .mkString("\n")

    println(output)
  }
}
