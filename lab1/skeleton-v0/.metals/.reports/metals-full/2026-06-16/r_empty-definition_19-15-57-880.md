error id: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab1/lab1/skeleton-v0/skeleton/src/main/scala/TextProcessing.scala:ofEpochSecond.
file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab1/lab1/skeleton-v0/skeleton/src/main/scala/TextProcessing.scala
empty definition using pc, found symbol in pc: ofEpochSecond.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 164
uri: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab1/lab1/skeleton-v0/skeleton/src/main/scala/TextProcessing.scala
text:
```scala
scalaimport java.time.Instant
import java.time.format.DateTimeFormatter

object TextProcessing {
  def formatDateFromUTC(utc: Long): String = {
    Instant.ofEpochS@@econd(utc).toString
  }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: ofEpochSecond.