# Utilitats Java

Llibreria d'utilitats Java per desenvolupament d'aplicacions multiplataforma.

> Versió actual de la llibreria: **3.2** → 
> [Descàrrega directa](https://github.com/agarriga18696/utilitats-andreu/releases/download/v3.2/utilitats-andreu-3.2.jar)

## Paquets:

- **aplicacio** → conjunt de classes base per crear aplicacions de manera més eficient i estructurada
- **utilitats** → conjunt de classes d'utilitats per fer el codi més eficient

## Classes del paquet aplicacio

- **AplicacioBase** → classe abstracta que gestiona el fluxe d'execució i de classes de l'aplicació.
- **ControladorBase** → classe abstracta que gestiona la lògica de l'aplicació.
- **VistaBase** → classe abstracta amb mètodes essencials per la classe Vista

## Classes del paquet utilitats

- **Aleatori** → generació de valors aleatoris
- **Arrays** → utilitats per treballar amb arrays
- **Cadenes** → manipulació i validació de cadenes de text
- **Coleccions** → utilitats per treballar amb col·leccions
- **Consola** → utilitats per mostrar dades estructurades per consola
- **Conversor** → convertir tipus de dades
- **Data** → utilitats per treballar amb dates i hores
- **Escriure** → lectura de dades per consola amb validació
- **Fitxers** → lectura i escriptura de fitxers
- **Formatador** → utilitats per formatar i transformar dades
- **Matematiques** → utilitats per a operacions matemàtiques
- **Menu** → creació de menús per consola
- **Missatges** → mostrar missatges estructurats per consola
- **SerialitzableCSV** → interfície que permet convertir un objecte serialitzat a format CSV
- **Validacions** → validació de dades

## Instal·lació

Afegeix la llibreria al teu projecte Java i importa les classes necessàries.

## Característiques

- [X] Suport per a tipus **primitius** i **genèrics**
- [X] Codi reutilitzable i modular
- [ ] Compatibilitat total amb tots els tipus de col·leccions (en procés)

## Exemples d'ús més destacables

### Mostrar missatges

```java
Missatges.titol("Menú principal de l'aplicació");
Missatges.subtitol("Opció 1 del menú");
Missatges.mostrar("Missatge normal amb salt de línia."); // Equivalent a System.out.println();
Missatges.mostrarEnLinia("Missatge normal sense salt de línia."); // Equivalent a System.out.print();
Missatges.error("Missatge..."); // ERROR: Missatge...
Missatges.exit("Missatge..."); // ÈXIT: Missatge...
Missatges.avis("Missatge..."); // AVÍS: Missatge...
Missatges.info("Missatge..."); // INFO: Missatge...
```

### Manipulació de cadenes

```java
String text = Cadenes.eliminarAccents("àngel garcía");
Missatges.mostrar(text); // angel garcia
Missatges.mostrar(Formatador.capitalitzar(text)); // Angel garcia
Missatges.mostrar(Formatador.capitalitzarParaules(text)); // Angel Garcia
```

### Validació de dades

```java
String dni = Escriure.cadena("Introdueix el teu DNI: ");
String nom = Escriure.cadenaMinima("Introdueix el teu nom: ", 2);
int edat = Escriure.enterPositiu("Introdueix la teva edat: ");
String correu = Escriure.cadenaMinima("Introdueix el teu correu: ", 12);
String telefon = Escriure.cadenaMinima("Introdueix el telèfon: ", 9);
String genere = Escriure.cadenaOpcional("Introdueix el teu gènere (opcional): "); // pot retornar null

boolean esDniValid = Validacions.esDNI(dni);
boolean esCorreuValid = Validacions.esEmail(correu);
boolean esTelefonValid = Validacions.esTelefon(telefon);
```

### Col·leccions

```java
Collection<Integer> coleccio1 = Coleccions.generarAleatoris(20, 1, 30); // 20 números entre 1 i 30
Collection<Integer> coleccio2 = Coleccions.generarAleatoris(40, 30, 50); // 40 números entre 30 i 50
Collection<Integer> coleccio3 = Coleccions.unir(coleccio1, coleccio2);
Consola.llista(coleccio3);
```

### Fitxers

```java
// Lectura - Text
String dades = Fitxers.llegirTot("dades.txt");
String[] linies = Fitxers.llegirLinies("dades.txt");
String[][] liniesCSV = Fitxers.llegirCSV("dades.txt", ","); // el segon paràmetre indica quin tipus de separador utilitza el fitxer. També accepta fitxers amb extensió .csv
int numLinies = Fitxers.comptarLinies("dades.txt"); // → String[] linies = new String[numLinies];

// Escriptura - Text
Fitxers.escriure("dades.txt", dades, boolean afegir);
Fitxers.escriureLinies("dades.txt", String[] linies, boolean afegir);

// Escriptura - Binaris
Fitxers.guardarObjectesCSV("dades.csv", T[] objectes, String separador, boolean afegir);
Fitxers.guardarObjectes("dades.dat", T[] objectes);
Fitxers.guardarObjectes("dades.dat", Collection<T> objectes);
Fitxers.guardarMapa("mapa.dat", Map<K, V> mapa);

// Lectura - Binaris
T[] array = Fitxers.carregarObjectes("dades.dat", Classe.class); // Ex: Persona.class
List<T> llista = Fitxers.carregarObjectes("dades.dat");
Map<K, V> mapa = carregarMapa("mapa.dat");
int numObjectes = Fitxers.comptarObjectes("dades.dat");

// Gestió
Fitxers.crearFitxerSiNoExisteix("dades.dat");
Fitxers.crearDirectoriSiNoExisteix("fitxers");
```

## Crea aplicacions de manera senzilla

### Estructura base del projecte

La llibreria està pensada per treballar amb l'arquitectura MVC.
Crea els següents paquets al teu projecte:
- model
- vista
- controlador

Llavors crea les següents classes al projecte:
- App.java (paquet per defecte o crea un paquet main)
- Controlador.java (esten de ControladorBase)
- Vista (esten de VistaBase)

Implementa els mètodes requerits.
