# Andreu Utils :hammer_and_wrench: :coffee:

Llibreria Java d'utilitats per crear **aplicacions de consola i aplicacions gràfiques Swing** de manera ràpida, estructurada i reutilitzable.

La branca `v6` introdueix una reorganització completa de l'API: paquets i classes en anglès, internacionalització integrada, utilitats Swing ampliades i una barra de menú d'aplicació reutilitzable.

---

## Requisits :memo:

- **Java 25** o superior
- **Maven 3+** per compilar, provar i empaquetar
- Cap dependència externa en producció
- **JUnit 5** per als tests, gestionat automàticament per Maven

---

## Versions :label:

> [!IMPORTANT]
> La branca `v6` correspon actualment a **`6.0.0`**.
> És una versió amb canvis incompatibles respecte de la sèrie 5.x.

La versió actual és:

- **v6.0.0** — [Descàrrega directa del JAR](https://github.com/agarriga18696/utilitats-andreu/releases/download/v6.0.0/andreu-utils-6.0.0.jar)

La darrera versió de la sèrie anterior és:

- **v5.1.8** — [Descàrrega directa del JAR](https://github.com/agarriga18696/utilitats-andreu/releases/download/v5.1.8/utilitats-andreu-5.1.8.jar)

---

## Compilació amb Maven

Compila el projecte i empaqueta el JAR:

```bash
mvn clean package
```

Instal·la la llibreria al repositori Maven local (`~/.m2/repository`) perquè altres projectes la puguin resoldre com a dependència:

```bash
mvn clean install
```

La dependència de la branca `v6` és:

```xml
<dependency>
    <groupId>io.github.agarriga18696</groupId>
    <artifactId>andreu-utils</artifactId>
    <version>6.0.0</version>
</dependency>
```

---

## Tests :test_tube:

Executa tota la suite:

```bash
mvn test
```

Executa una sola classe de tests:

```bash
mvn -Dtest=MathUtilsTest test
mvn -Dtest=ValidationUtilsTest test
```

### Cobertura actual

| Classe de tests | Tests |
|---|---:|
| `ConversionUtilsTest` | 17 |
| `FormatUtilsTest` | 17 |
| `LanguageManagerTest` | 6 |
| `MathUtilsTest` | 30 |
| `ValidationUtilsTest` | 47 |
| `ApplicationMenuBarSwingTest` | 12 |
| `I18nSwingTest` | 14 |
| `LanguageMenuSwingTest` | 3 |
| `ThemeMenuSwingTest` | 1 |
| **Total** | **147** |

Estat actual:

```text
Tests run: 147, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## Javadoc :books:

Genera la documentació de l'API:

```bash
mvn -DskipTests javadoc:javadoc
```

La branca `v6` genera actualment el Javadoc sense warnings.

---

## Estructura de paquets

Tota l'API pública es troba sota:

```text
io.github.agarriga18696.andreuutils
├── application
├── core
└── swing
```

| Paquet | Descripció |
|---|---|
| `application` | Classes base per estructurar aplicacions de consola |
| `core` | Utilitats generals independents de Swing |
| `swing` | Components, diàlegs, menús, taules, tasques, icones, i18n i utilitats Swing |

---

# `application`

## `ApplicationBase`

Classe base per estructurar una aplicació de consola.

El cicle principal és:

```text
controller()
    ↓
controller.initialize()
    ↓
view(controller).showMenu()
```

Implementació mínima:

```java
import io.github.agarriga18696.andreuutils.application.ApplicationBase;
import io.github.agarriga18696.andreuutils.application.ControllerBase;
import io.github.agarriga18696.andreuutils.application.ViewBase;

public final class MyApplication extends ApplicationBase {

    @Override
    protected ControllerBase controller() {
        return new MyController();
    }

    @Override
    protected ViewBase view(ControllerBase controller) {
        return new MyView((MyController) controller);
    }

    public static void main(String[] args) {
        new MyApplication().run();
    }
}
```

Per defecte, l'aplicació s'inicialitza en anglès. Es pot sobreescriure `language()` per seleccionar un altre idioma inicial.

---

## `ControllerBase`

Classe base per a controladors de consola.

Les subclasses implementen:

```java
protected abstract String directory();
protected abstract void load();
protected abstract void save();
```

Mètodes de cicle de vida:

| Mètode | Funció |
|---|---|
| `initialize()` | Crea el directori si no existeix i carrega les dades |
| `shutdown()` | Guarda les dades, finalitza el programa i surt |

---

# `core`

El paquet `core` conté utilitats que no depenen de Swing.

| Classe | Finalitat |
|---|---|
| `ArrayUtils` | Cerca, manipulació, estadístiques, filtratge i transformació d'arrays |
| `CollectionUtils` | Operacions, estadístiques, filtratge i transformació de col·leccions |
| `ConsoleUtils` | Utilitats de consola |
| `ConversionUtils` | Conversió entre tipus i valors |
| `CsvSerializable` | Contracte per a objectes serialitzables en CSV |
| `DateTimeUtils` | Utilitats de data i hora |
| `FileUtils` | Fitxers, directoris i persistència |
| `FormatUtils` | Format de text i valors |
| `InputUtils` | Lectura i validació d'entrada |
| `Language` | Idiomes suportats per la llibreria |
| `LanguageManager` | Estat global d'idioma i notificació de canvis |
| `MathUtils` | Operacions i comprovacions matemàtiques |
| `MenuUtils` | Utilitats per a menús de consola |
| `MessageUtils` | Missatges reutilitzables |
| `RandomUtils` | Generació de valors aleatoris |
| `SecurityUtils` | Utilitats relacionades amb seguretat |
| `StringUtils` | Operacions amb cadenes |
| `ValidationUtils` | Validació de valors i formats |

---

## Internacionalització

La v6 incorpora un sistema d'idioma compartit entre el nucli i Swing.

Idiomes disponibles:

```java
Language.ENGLISH
Language.SPANISH
Language.CATALAN
```

Canviar l'idioma global:

```java
LanguageManager.setLanguage(Language.SPANISH);
```

Els components Swing vinculats al sistema d'i18n s'actualitzen quan canvia l'idioma.

---

# `swing`

El paquet `swing` concentra les utilitats per construir interfícies Swing sense repetir infraestructura habitual.

Entre les classes principals hi ha:

| Classe | Finalitat |
|---|---|
| `GuiApplicationBase` | Cicle de vida base per a aplicacions Swing |
| `ApplicationMenuBarSwing` | Barra de menú estàndard i extensible per a aplicacions |
| `ComponentsSwing` | Creació i configuració de components |
| `DialogsSwing` | Diàlegs i selectors de fitxer |
| `EdtSwing` | Execució segura al Swing Event Dispatch Thread |
| `FramesSwing` | Creació i configuració de finestres |
| `I18nSwing` | Traducció i vinculació dinàmica de components |
| `LanguageMenuSwing` | Menú de selecció d'idioma |
| `ListsSwing` | Utilitats per a `JList` |
| `LookAndFeelSwing` | Gestió de Look and Feel |
| `LookAndFeelThemeSwing` | Definició d'un tema de Look and Feel |
| `MenusSwing` | Creació de barres, menús i ítems |
| `PanelsSwing` | Creació de panells i layouts |
| `TableModelSwing` | `TableModel` genèric |
| `TablesSwing` | Creació i gestió de taules |
| `TasksSwing` | Tasques en segon pla amb callbacks a l'EDT |
| `ThemeMenuSwing` | Menú de selecció de tema |

---

## `ApplicationMenuBarSwing`

Crea una barra de menú base amb una estructura comuna:

```text
File
├── Home
├── [ítems específics de l'aplicació]
├── separator
└── Exit to desktop

View
├── Themes >
└── [ítems específics de l'aplicació]

Settings
├── Language >
└── [ítems específics de l'aplicació]

Help
├── [ítems específics de l'aplicació]
├── separator
└── About...
```

Les accions `Home`, `Exit` i `About` són obligatòries:

```java
JMenuBar menuBar = ApplicationMenuBarSwing.builder(frame)
        .onHome(this::goHome)
        .onExit(this::exitApplication)
        .onAbout(this::showAbout)
        .build();

frame.setJMenuBar(menuBar);
```

Es poden afegir opcions pròpies sense reconstruir la barra:

```java
JMenuBar menuBar = ApplicationMenuBarSwing.builder(frame)
        .onHome(this::goHome)
        .onExit(this::exitApplication)
        .onAbout(this::showAbout)
        .configureFileMenu(menu ->
                menu.add(MenusSwing.item("Reload", this::reload))
        )
        .configureViewMenu(menu ->
                menu.add(MenusSwing.item("Fullscreen", this::toggleFullscreen))
        )
        .build();
```

També es poden rebre callbacks quan l'usuari canvia l'idioma o aplica un tema:

```java
ApplicationMenuBarSwing.builder(frame)
        .onHome(this::goHome)
        .onExit(this::exitApplication)
        .onAbout(this::showAbout)
        .onLanguageChanged(language -> refreshContent())
        .onThemeApplied(theme -> refreshLayout())
        .build();
```

Els textos estàndard de la barra s'actualitzen automàticament amb l'idioma actiu.

---

## `EdtSwing`

Centralitza l'execució segura de tasques al fil d'esdeveniments de Swing.

```java
EdtSwing.runAndWait(() -> {
    // Codi que ha de completar-se a l'EDT abans de continuar
});

EdtSwing.runLater(() -> {
    // Codi encuat de manera asíncrona a l'EDT
});

boolean onEdt = EdtSwing.isEdt();
```

---

## Menús de tema i idioma

`ThemeMenuSwing` crea un menú de Look and Feel reutilitzable:

```java
JMenu themes = ThemeMenuSwing.create(frame);
```

`LanguageMenuSwing` crea el selector d'idioma:

```java
JMenu language = LanguageMenuSwing.create();
```

Ambdós formen part automàticament d'`ApplicationMenuBarSwing`.

---

## Icones

La llibreria incorpora diversos catàlegs d'icones:

| Classe | Catàleg |
|---|---|
| `IconsFugue` | Fugue Icons |
| `IconsFatCow` | FatCow Icons |
| `IconsFlags` | Banderes |
| `IconsGame` | Game Icons |
| `IconsApp` | Constants semàntiques d'ús habitual |

La càrrega es centralitza a `IconsSwing`:

```java
Icon home = IconsSwing.load(IconsFugue.HOME);
```

Les icones es carreguen sota demanda i es reutilitzen mitjançant memòria cau.

---

## Taules genèriques

`ColumnSwing`, `TableModelSwing` i `TablesSwing` permeten construir taules a partir d'objectes sense implementar manualment un `AbstractTableModel` per a cada cas.

Exemple conceptual:

```java
List<ColumnSwing<Product>> columns = List.of(
        new ColumnSwing<>("ID", Product::getId),
        new ColumnSwing<>("Name", Product::getName),
        new ColumnSwing<>("Price", Product::getPrice)
);
```

A partir d'aquí, `TableModelSwing` manté les dades i `TablesSwing` proporciona les utilitats de presentació i selecció.

---

## Tasques en segon pla

`TasksSwing` encapsula `SwingWorker` per executar operacions lentes fora de l'EDT i retornar els callbacks a la interfície de manera segura.

És apropiat per a operacions com:

- lectura o escriptura de fitxers
- consultes a base de dades
- càrregues costoses
- operacions que no han de bloquejar la interfície

---

# Migració de v5 a v6 :warning:

La v6 reorganitza i anglifica l'API. No és un canvi compatible a nivell de noms.

Alguns exemples:

| v5 | v6 |
|---|---|
| `aplicacio` | `io.github.agarriga18696.andreuutils.application` |
| `utilitats` | `io.github.agarriga18696.andreuutils.core` |
| `aplicaciogui` | `io.github.agarriga18696.andreuutils.swing` |
| `AplicacioBase` | `ApplicationBase` |
| `ControladorBase` | `ControllerBase` |
| `VistaBase` | `ViewBase` |
| `AplicacioGuiBase` | `GuiApplicationBase` |
| `DialegsSwing` | `DialogsSwing` |
| `FinestresSwing` | `FramesSwing` |
| `PanellsSwing` | `PanelsSwing` |
| `TasquesSwing` | `TasksSwing` |
| `TaulesSwing` | `TablesSwing` |
| `ModelTaulaSwing` | `TableModelSwing` |
| `ColumnaSwing` | `ColumnSwing` |
| `IconesSwing` | `IconsSwing` |
| `IconesFugue` | `IconsFugue` |
| `MenuTemesSwing` | `ThemeMenuSwing` |

> [!TIP]
> Si migres un projecte existent, usa **Refactor → Rename** d'IntelliJ IDEA sempre que sigui possible en lloc de substituir noms globalment com a text.

---

## Estat de la branca `v6`

- Java 25
- 45 fitxers font compilats
- 147 tests
- 0 failures
- 0 errors
- 0 skipped
- Javadoc sense warnings
- API principal en anglès
- i18n per anglès, castellà i català

---

## Autor :technologist:

Andreu Garriga Cendán
