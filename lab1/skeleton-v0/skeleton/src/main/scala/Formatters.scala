
object Formatters {

  // Pure function to format posts from a subscription
  def formatSubscription(post : FileIO.Post): String = {
    val (subreddit, title, selftext, date, score, url) = post
    val header = s"\n${"=" * 80}\nPosts from: $subreddit \n${"=" * 80}"
    val formattedPosts = title.take(80)
    header + "\n" + formattedPosts
  }
}
