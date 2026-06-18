// =====================================================================
// Ejercicio 2: Cargar diccionarios de entidades
// =====================================================================
import scala.io.Source
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
    val source = Source.fromFile(filePath)
    val checkEntidad : String => NamedEntity = entityType match{
        case "Person" => (nombre: String) => new Person(nombre)
        case "University" => (nombre: String) => new University(nombre)
        case "ProgrammingLanguage" => (nombre: String) => new ProgrammingLanguage(nombre)
        case "Organization" => (nombre: String) => new Organization(nombre)
        case "Place" => (nombre: String) => new Place(nombre)
        case _ =>  throw new IllegalArgumentException(s"Tipo desconocido: $entityType") //Excepcion por si es otra entrada en entityType
      }
    val listEntity = source.getLines().map{ tE=> checkEntidad(tE)}.toList   //getLines leo lineas del archivo
    //con el map transformo las lineas, y con el toList las paso a una lista
    source.close()
    listEntity
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
    loadFromFile("data/languages.txt", "ProgrammingLanguage") :::  // ::: operador para concatenar listas
      loadFromFile("data/organizations.txt", "Organization") :::
        loadFromFile("data/people.txt", "Person") :::
          loadFromFile("data/places.txt", "Place") :::
            loadFromFile("data/universities.txt", "University")
  }
}
