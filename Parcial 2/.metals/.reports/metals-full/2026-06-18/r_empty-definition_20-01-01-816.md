error id: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab2/parcial2-skeleton/parcial2/src/main/scala/Analyzer.scala:java/lang/String#toLowerCase(+1).
file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab2/parcial2-skeleton/parcial2/src/main/scala/Analyzer.scala
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -text/toLowerCase.
	 -text/toLowerCase#
	 -text/toLowerCase().
	 -scala/Predef.text.toLowerCase.
	 -scala/Predef.text.toLowerCase#
	 -scala/Predef.text.toLowerCase().
offset: 572
uri: file://<HOME>/Documentos/0Paradigmas%20de%20p./Recuperatorios/Lab2/parcial2-skeleton/parcial2/src/main/scala/Analyzer.scala
text:
```scala
/**
 * Responsable de detectar entidades nombradas en texto libre y
 * producir estadísticas sobre ellas.
 */
object Analyzer {

  /**
   * Detecta las entidades del diccionario que aparecen en el texto dado.
   *
   * @param text       texto a analizar (ej: título o cuerpo de un post)
   * @param dictionary lista de entidades conocidas (cargadas desde los diccionarios)
   * @return lista de entidades cuyo texto aparece en el texto analizado
   */
  def detectEntities(text: String, dictionary: List[NamedEntity]): List[NamedEntity] = {
    val lowerText = text.toLowe@@rCase
    dictionary.filter(entity => entity.matches(text))
  }

  /**
   * Cuenta cuántas entidades de cada tipo fueron detectadas.
   *
   * @param entities lista de entidades detectadas
   * @return mapa de entityType → cantidad de apariciones
   */
  def countByType(entities: List[NamedEntity]): Map[String, Int] = {
    entities.groupBy(_.entityType).view.mapValues(_.size).toMap
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 