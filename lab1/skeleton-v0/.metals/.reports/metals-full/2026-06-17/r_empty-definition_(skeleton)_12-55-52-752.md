error id: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab1/lab1/skeleton-v0/skeleton/src/main/scala/Main.scala:
file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab1/lab1/skeleton-v0/skeleton/src/main/scala/Main.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb

found definition using fallback; symbol toList
offset: 334
uri: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab1/lab1/skeleton-v0/skeleton/src/main/scala/Main.scala
text:
```scala
object Main {
  def main(args: Array[String]): Unit = {

    val header = s"Reddit Post Parser\n${"=" * 40}"

    val subscriptions = FileIO.readSubscriptions("subscriptions.json").toList.flatten 
    //uso toList para pasar a una lista de option, y el flatten para eliminar el option.
    val posts = FileIO.downloadFeed(subs._2).toL@@ist.flatten //descargo los

    val allPosts = subscriptions.flatMap { subs =>
      println(s"Fetching posts from: $subs")
      val filteredPosts = FileIO.filterPost(posts)
      filteredPosts
    }
    println(allPosts) 
    val wordCount = subscriptions.map {subs =>
      val posts = FileIO.downloadFeed(subs._2).toList.flatten
      val filteredPosts = FileIO.filterPost(posts)
      val wCounts = TextProcessing.countWords(filteredPosts.map(post => post._3).mkString(" "))
      wCounts
    }

    val output = allPosts
      .map { post => Formatters.formatSubscription(post) }
      .mkString("\n")

    println(output)
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 