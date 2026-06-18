// =====================================================================
// Ejercicio 6: Integración del sistema completo
// =====================================================================
import Analyzer.detectEntities
import Formatters.formatNERResult

object Main {
  def main(args: Array[String]): Unit = {

    // ------------------------------------------------------------------
    // Paso 1: Cargar diccionarios
    // ------------------------------------------------------------------
    // TODO (Ejercicio 2)
    val dictionary: List[NamedEntity] = Dictionary.loadAll()

    println(s"Diccionario cargado: ${dictionary.size} entidades.\n")

    // ------------------------------------------------------------------
    // Paso 2: Descargar posts
    // ------------------------------------------------------------------
    val subscriptions = FileIO.readSubscriptions()

    val allPosts: List[(String, List[String])] = subscriptions.map { url =>
      println(s"Descargando posts de: $url")
      val json   = FileIO.downloadFeed(url)
      val titles = FileIO.extractPostTitles(json)
      (url, titles)
    }

    // ------------------------------------------------------------------
    // Paso 3: Detectar entidades y mostrar resultados por post
    // ------------------------------------------------------------------
    // TODO (Ejercicios 3, 4 y 6):
    //   Para cada post:
    //     1. Detectar entidades
    //     2. Formatear y mostrar el resultado
    val allTitles = allPosts.flatMap{case (url,titles) => titles}
    val detectarEntidad = allTitles.foreach{ tmp => 
          val formatter = Analyzer.detectEntities(tmp,dictionary)
          val result = Formatters.formatNERResult(tmp,formatter)
          println(result)}
    
    // ------------------------------------------------------------------
    // Paso 4: Estadísticas globales
    // ------------------------------------------------------------------
    // TODO (Ejercicios 5 y 6):
    //   1. Recolectar TODAS las entidades detectadas en todos los posts
    //   2. Contar por tipo
    //   3. Mostrar el resumen
    val allEntities = allTitles.flatMap{tmp =>
     Analyzer.detectEntities(tmp,dictionary)}
    val juntoEntidades = Analyzer.countByType(allEntities)
    val mostrarResumen = Formatters.formatEntityStats(juntoEntidades)
    println(mostrarResumen)
  }
}
