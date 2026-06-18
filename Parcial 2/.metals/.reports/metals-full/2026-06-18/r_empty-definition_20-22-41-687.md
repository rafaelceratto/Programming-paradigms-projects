error id: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab2/parcial2-skeleton/parcial2/src/main/scala/NamedEntity.scala:scala/Predef.String#
file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab2/parcial2-skeleton/parcial2/src/main/scala/NamedEntity.scala
empty definition using pc, found symbol in pc: scala/Predef.String#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -String#
	 -scala/Predef.String#
offset: 907
uri: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab2/parcial2-skeleton/parcial2/src/main/scala/NamedEntity.scala
text:
```scala
import scala.util.matching.Regex

/**
 * Clase base abstracta para todas las entidades nombradas.
 *
 * Una entidad nombrada es una expresión del texto que refiere a un objeto
 * del mundo real (persona, lugar, organización, tecnología, etc.).
 *
 * @param text el texto tal como aparece en el corpus
 */
abstract class NamedEntity(val text: String) {

  /**
   * Retorna el tipo de la entidad como String.
   */
  def entityType: String

  def matches(input: String): Boolean = {
    val pattern = s"(?i)(?<![a-zA-Z0-9])${Regex.quote(text)}(?![a-zA-Z0-9])".r   //(?i) es para case-insensitive
    //(?<![a-zA-Z0-9]) y (?<![a-zA-Z0-9]) son para q no haya letra ni caracteres ni numeros antes ni dsp de la palabra
    pattern.findFirstIn(input).isDefined
  }

  def isRelevant(input: String): Boolean = true
  /**
   * Retorna una línea de descripción de la entidad para el informe.
   */
  def describe: Str@@ing = s"[$entityType] $text"

}

class Person(text: String) extends NamedEntity(text) {
  def entityType: String = "Person"
  override def matches(input: String): Boolean = input.contains(text) //solo necesito ver si esta el nombre
  //como nunca existira por ejemplo MartinOrdeskyXZY, entonces solo con contains alcanza
}

class Organization(text: String) extends NamedEntity(text) {
  def entityType: String = "Organization"
  override def isRelevant: Boolean = false
}

class University(text: String) extends Organization(text) {
  override def entityType: String = "University"
  override def isRelevant: Boolean = true
}

class Place(text: String) extends NamedEntity(text) {
  def entityType: String = "Place"
  override def isRelevant: Boolean = false
}

class Technology(text: String) extends NamedEntity(text) {
  def entityType: String = "Technology"
  override def matches(input: String) : Boolean = {
    val pattern = s"(?<![a-zA-Z0-9])${Regex.quote(text)}(?![a-zA-Z0-9])".r   //saco (?i) para q sea case-sensitive
    //(?<![a-zA-Z0-9]) y (?<![a-zA-Z0-9]) son para q no haya letra ni caracteres ni numeros antes ni dsp de la palabra
    pattern.findFirstIn(input).isDefined
  }
  override def isRelevant: Boolean = false 
}

class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType: String = "ProgrammingLanguage"
  override def isRelevant: Boolean = true
}

abstract class Event(text: String) extends NamedEntity(text){

}

class Conference(text: String) extends Event(text){
  override def entityType: String = "Conference"
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Predef.String#