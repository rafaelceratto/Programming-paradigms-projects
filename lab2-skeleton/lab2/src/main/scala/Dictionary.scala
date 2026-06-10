import scala.io.Source
// =====================================================================
// Ejercicio 2: Cargar diccionarios de entidades
// =====================================================================

/**
 * Responsable de cargar colecciones de entidades nombradas desde archivos.
 *
 * Un diccionario es un archivo de texto plano donde cada línea contiene
 * el nombre de una entidad conocida del mismo tipo.
 *
 * Ejemplo — data/people.txt:
 *   Martin Odersky
 *   Alan Turing
 *   Ada Lovelace
 *
 * Ejemplo — data/languages.txt:
 *   Scala
 *   Python
 *   Haskell
 */
object Dictionary {

  /**
   * Lee un archivo de diccionario y crea una lista de entidades del tipo indicado.
   *
   * @param filePath   ruta al archivo de diccionario (ej: "data/people.txt")
   * @param entityType tipo de entidad: "Person", "University", "ProgrammingLanguage", etc.
   * @return lista de NamedEntity del tipo correspondiente
   *
   * TODO (Ejercicio 2): Implementar este método.
   *
   *   Pasos sugeridos:
   *     1. Leer las líneas del archivo
   *     2. Para cada línea, crear la instancia de la clase correcta
   *     3. Retornar la lista de entidades creadas
   *
   *   Para crear la clase correcta según el tipo se puede usar match:
   *
   */
  def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {
    val ejecuteDict = Source.fromFile(filePath)  //abro el archivo, segun el path que le pase (por ej data/people.txt)
    .getLines().map{ line =>       //con .getLines() obtengo las lineas del path, luego con map
                                  //agarro cada string de getLines, y digo ¿Que objeto tengo que crear?       
      entityType match {          //entonces aca uso el match, que mira el string, evalua que clase es y lo pone en la correcta
        
        case "Person" => new Person(line)
        case "ProgrammingLanguage" => new ProgrammingLanguage(line)
        case "Organization" => new Organization(line)
        case "Place" => new Place(line)
        case "University" => new University(line)
      } 
    }.toList //Paso todo lo que hice a una lista
      
    source.close() //cierro el archivo por las dudas 
    ejecuteDict //devuelvo la lista            
  }

  /**
   * Carga todos los diccionarios disponibles y combina sus entidades.
   *
   * @return lista con todas las entidades de todos los diccionarios
   *
   * TODO (Ejercicio 2): Implementar este método.
   *
   */
  def loadAll(): List[NamedEntity] = {
    
    loadFromFile("data/people.txt", "Person") :::     // ::: es para concatenar las listas
    loadFromFile("data/languages.txt", "ProgrammingLanguage") :::
    loadFromFile("data/organizations.txt", "Organization") :::
    loadFromFile("data/places.txt", "Place") :::
    loadFromFile("data/universities.txt", "University")    
  }
}
