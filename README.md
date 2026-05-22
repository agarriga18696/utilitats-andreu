# Utilitats Andreu per a Java

Llibreria d'utilitats Java per al desenvolupament d'aplicacions multiplataforma.

> **Versió actual: 4.7** → [Descàrrega directa](https://github.com/agarriga18696/utilitats-andreu/releases/download/v4.7/utilitats-andreu-4.7.jar)

---

## Paquets

### `aplicacio`
Conjunt de classes base per a crear aplicacions MVC per consola de manera estructurada i senzilla.

| Classe | Descripció |
|---|---|
| `AplicacioBase` | Gestiona el flux d'execució de l'aplicació |
| `ControladorBase` | Gestiona la lògica i persistència de dades |
| `VistaBase` | Gestiona la interfície d'usuari per consola |

### `aplicaciogui`
Conjunt de classes base i utilitats per a crear aplicacions gràfiques amb Swing de manera modular i estructurada.

| Classe | Descripció |
|---|---|
| `AplicacioGuiBase` | Classe base abstracta per a aplicacions Swing. Garanteix l'execució a l'EDT |
| `ComponentsSwing` | Creació de components comuns: botons, etiquetes, camps de text, combos... |
| `DialegsSwing` | Diàlegs informatius, d'avís, error, confirmació i text llarg |
| `FinestresSwing` | Creació de finestres `JFrame` |
| `IconesSwing` | Motor genèric de càrrega d'icones amb caché interna |
| `IconesPaquetSwing` | Càrrega d'icones organitzades per paquets dins la llibreria |
| `IconesPredeterminadesSwing` | Catàleg d'icones predeterminades per a accions habituals |
| `LookAndFeelSwing` | Aplicació de temes visuals (sistema, Metal, Nimbus...) |
| `MenusSwing` | Creació de barres de menú, menús, opcions i dreceres de teclat |
| `PanellsSwing` | Creació de panells amb layouts habituals i marges |

### `utilitats`
Conjunt de classes d'utilitats per fer el codi més eficient i reutilitzable.

| Classe | Descripció |
|---|---|
| `Aleatori` | Generació de valors aleatoris |
| `Arrays` | Utilitats per treballar amb arrays (cerca, filtrat, transformació, ordenació) |
| `Cadenes` | Manipulació i validació de cadenes de text |
| `Coleccions` | Utilitats per treballar amb col·leccions (filtrat, transformació, comprovacions) |
| `Consola` | Mostrar dades estructurades per consola |
| `Conversor` | Conversió entre tipus de dades |
| `Data` | Utilitats per treballar amb dates i hores |
| `Escriure` | Lectura de dades per consola amb validació |
| `Fitxers` | Lectura i escriptura de fitxers de text i binaris |
| `Formatador` | Formatació i transformació de dades |
| `GUI` | Utilitats per a components d'interfície gràfica amb Swing *(retrocompatibilitat)* |
| `Matematiques` | Operacions matemàtiques |
| `Menu` | Creació de menús per consola |
| `Missatges` | Missatges estructurats per consola |
| `SerialitzableCSV` | Interfície per convertir objectes a format CSV |
| `Validacions` | Validació de dades (DNI, email, telèfon, rangs...) |

---

## Instal·lació

1. Descarrega el `.jar` de la [darrera versió](https://github.com/agarriga18696/utilitats-andreu/releases/download/v4.7/utilitats-andreu-4.7.jar)
2. Afegeix-lo al classpath del teu projecte Java
3. Importa les classes necessàries

```java
import utilitats.*;
import aplicacio.*;
import aplicaciogui.*;
```

---

## Característiques

- [x] Suport per a tipus **primitius** i **genèrics**
- [x] Codi reutilitzable i modular
- [x] Arquitectura **MVC** integrada per a aplicacions de consola
- [x] Interfície gràfica amb **Swing** via paquet `aplicaciogui`
- [x] Operacions funcionals amb **Stream** (`Predicate`, `Function`, `Comparator`)
- [x] Sistema d'icones amb caché i catàleg predeterminat
- [ ] Compatibilitat total amb tots els tipus de col·leccions (en procés)

---

## Exemples d'ús

### Missatges

```java
Missatges.titol("Menú principal");
Missatges.subtitol("Opció 1");
Missatges.mostrar("Missatge normal");
Missatges.error("Missatge d'error");    // ERROR: Missatge d'error
Missatges.exit("Operació correcta");    // OK: Operació correcta
Missatges.avis("Compte amb això");      // AVÍS: Compte amb això
Missatges.info("Informació útil");      // INFO: Informació útil
```

### Cadenes i format

```java
String text = Cadenes.eliminarAccents("àngel garcía"); // angel garcia
Formatador.capitalitzar(text);                         // Angel garcia
Formatador.capitalitzarParaules(text);                 // Angel Garcia
```

### Validació i entrada de dades

```java
int edat      = Escriure.enterPositiu("Edat: ");
String dni    = Escriure.cadena("DNI: ");
String correu = Escriure.cadena("Correu: ");

Validacions.esDNI(dni);             // → true / false
Validacions.esEmail(correu);        // → true / false
Validacions.esTelefon("666123456"); // → true / false
```

### Col·leccions amb Stream

```java
List<Integer> llista = Coleccions.generarAleatoris(20, 1, 100);

// Filtrar
List<Integer> parells = Coleccions.filtrar(llista, n -> n % 2 == 0);

// Transformar
List<Double> arrels = Coleccions.transformar(llista, n -> Math.sqrt(n));

// Comprovar
boolean hiHaPrimers  = Coleccions.existeix(llista, Matematiques::esPrimer);
boolean totsPositius = Coleccions.tots(llista, n -> n > 0);

// Cercar i comptar
Integer primer = Coleccions.primerQue(llista, n -> n > 50);
long quants    = Coleccions.comptar(llista, n -> n % 3 == 0);
```

### Fitxers

```java
// Text
String contingut = Fitxers.llegirTot("dades.txt");
String[] linies  = Fitxers.llegirLinies("dades.txt");
String[][] csv   = Fitxers.llegirCSV("dades.csv", ";");
Fitxers.escriure("dades.txt", contingut, false); // false = sobreescriu

// Binaris
Fitxers.guardarObjectes("dades.dat", llista);
List<Persona> persones = Fitxers.carregarObjectes("dades.dat");
Fitxers.guardarMapa("mapa.dat", mapa);
Map<String, Persona> mapa = Fitxers.carregarMapa("mapa.dat");
```

### GUI amb Swing — `aplicaciogui`

```java
// Aplicació Swing bàsica
public class LaMevaApp extends AplicacioGuiBase {
    @Override
    protected void inicialitzar() {
        LookAndFeelSwing.aplicarSistema();

        JFrame frame = FinestresSwing.frame("La meva app", 600, 400, null, WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = PanellsSwing.panelAmbMarge(new BorderLayout(), 10);

        JMenuBar barra   = MenusSwing.barraMenu();
        JMenu menuFitxer = MenusSwing.menu("Fitxer");

        menuFitxer.add(MenusSwing.item(
            "Sortir",
            IconesPredeterminadesSwing.carregar(IconesPredeterminadesSwing.SORTIR),
            () -> frame.dispose()
        ));

        barra.add(menuFitxer);
        panel.add(ComponentsSwing.etiquetaCentrada("Benvingut"), BorderLayout.CENTER);

        frame.setJMenuBar(barra);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}

new LaMevaApp().executar();
```

### Aplicació MVC (consola)

```java
// App.java
public class App {
    public static void main(String[] args) {
        new ControladorApp().executar();
    }
}

// ControladorApp.java
public class ControladorApp extends AplicacioBase {
    @Override
    protected ControladorBase controlador() { return new ControladorLogica(); }
    @Override
    protected VistaBase vista(ControladorBase c) { return new Vista((ControladorLogica) c); }
}

// ControladorLogica.java
public class ControladorLogica extends ControladorBase {
    @Override protected String directori() { return "directoriFitxers"; }
    @Override protected void carregar() { }
    @Override protected void guardar() { }
}

// Vista.java
public class Vista extends VistaBase {
    @Override protected String titol() { return "La meva app"; }
    @Override protected String[] opcions() { return new String[]{ "Opció 1", "Sortir" }; }
    @Override protected void gestionar(int opcio) {
        switch(opcio) {
            case 1 -> System.out.println("Opció 1");
            case 0 -> controlador.finalitzar();
        }
    }
}
```

---

## Requisits

- Java **21** o superior
