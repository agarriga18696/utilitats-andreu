# Llibreria d'utilitats Andreu :hammer_and_wrench: :coffee:

Llibreria Java per crear **aplicacions de consola i aplicacions gràfiques Swing** de manera ràpida, estructurada i professional.

---

## Requisits :memo:

No requereix de cap llibreria ni dependència externa.

> [!IMPORTANT]
> :paperclip: Requereix **Java 21** o superior.

---

## Descàrrega :arrow_down:

> [!NOTE]
> :paperclip: **Versió actual: `5.1.6`** → [Descàrrega directa](https://github.com/agarriga18696/utilitats-andreu/releases/download/v5.1.6/utilitats-andreu-5.1.6.jar)

---

## Paquets disponibles

| Paquet | Descripció |
|---|---|
| `aplicacio` | Components, menús i utilitats per a aplicacions de consola |
| `aplicaciogui` | Components, panells, diàlegs, menús, icones i utilitats per a aplicacions Swing |
| `utilitats` | Utilitats generals: cadenes, fitxers, dates, col·leccions i entrada per consola |

---

## Paquet `aplicacio`

### `AplicacioBase` — Base per a aplicacions de consola

Classe abstracta que estructura una aplicació de consola seguint el patró MVC. Coordina el controlador i la vista i inicia el cicle de vida de l'aplicació.

#### Cicle de vida

```
controlador().inicialitzar()  →  vista(controlador).menu()
```

| Mètode abstracte | Descripció |
|---|---|
| `controlador()` | Retorna la instància del controlador. **Override obligatori.** |
| `vista(controlador)` | Retorna la instància de la vista. **Override obligatori.** |

#### Iniciar l'aplicació

```java
new MevaAplicacio().executar();
```

---

### `ControladorBase` — Base per al controlador de consola

Classe abstracta que gestiona la inicialització, la persistència i el tancament de l'aplicació.

| Mètode abstracte | Descripció |
|---|---|
| `directori()` | Retorna la ruta del directori de dades. **Override obligatori.** |
| `carregar()` | Carrega les dades des del fitxer. **Override obligatori.** |
| `guardar()` | Guarda les dades al fitxer. **Override obligatori.** |

| Mètode concret | Descripció |
|---|---|
| `inicialitzar()` | Crea el directori si no existeix i carrega les dades. |
| `finalitzar()` | Guarda les dades, mostra el missatge de fi i surt. |

---

### `VistaBase` — Base per a la vista de consola

Classe abstracta que gestiona el menú principal de l'aplicació per consola.

| Mètode abstracte | Descripció |
|---|---|
| `titol()` | Retorna el títol del menú. **Override obligatori.** |
| `opcions()` | Retorna l'array d'opcions del menú. **Override obligatori.** |
| `gestionar(int opcio)` | Gestiona l'opció triada per l'usuari. **Override obligatori.** |

> [!TIP]
> :paperclip: L'opció de sortir sempre és l'última de l'array d'opcions. El mètode `opcioSortir()` retorna automàticament el seu número, i `menu()` gestiona el bucle fins que l'usuari la tria.

#### Exemple d'implementació

```java
// Test.java
public final class Test {
    public static void main(String[] args) {
        new MevaAplicacio().executar();
    }
}

// MevaAplicacio.java
public final class MevaAplicacio extends AplicacioBase {
    @Override
    protected ControladorBase controlador() {
        return new MeuControlador();
    }
    @Override
    protected VistaBase vista(ControladorBase controlador) {
        return new MevaVista((MeuControlador) controlador);
    }
}

// MeuControlador.java
public final class MeuControlador extends ControladorBase {
    private List<Element> elements = new ArrayList<>();

    @Override protected String directori() { return "dades"; }
    @Override protected void carregar() { /* carrega elements */ }
    @Override protected void guardar() { /* guarda elements */ }
}

// MevaVista.java
public final class MevaVista extends VistaBase {
    private final MeuControlador controlador;
    public MevaVista(MeuControlador controlador) { this.controlador = controlador; }

    @Override protected String titol() { return "Gestió d'elements"; }
    @Override protected String[] opcions() {
        return new String[]{ "Llistar", "Afegir", "Eliminar", "Sortir" };
    }
    @Override
    protected void gestionar(int opcio) {
        switch (opcio) {
            case 1 -> controlador.llistar();
            case 2 -> controlador.afegir();
            case 3 -> controlador.eliminar();
            case 4 -> controlador.finalitzar();
        }
    }
}
```

---

## Paquet `aplicaciogui`

### `AplicacioGuiBase` — Base per a aplicacions Swing

Classe abstracta que gestiona el cicle de vida d'una aplicació Swing i garanteix l'execució al fil d'esdeveniments (EDT).

#### Cicle de vida

```
abansInicialitzar()  →  inicialitzar()  →  despresInicialitzar()
```

| Mètode | Descripció |
|---|---|
| `abansInicialitzar()` | S'executa abans de crear la interfície. Override opcional. |
| `inicialitzar()` | S'executa per crear la interfície. **Override obligatori.** |
| `despresInicialitzar()` | S'executa un cop la interfície ja és visible. Override opcional. |

#### Iniciar l'aplicació

```java
// Amb Look and Feel del sistema
new MevaAplicacio().executarAmbLookAndFeel(LookAndFeelSwing.SISTEMA);

// Sense Look and Feel (usa el predeterminat de Swing)
new MevaAplicacio().executar();
```

> [!TIP]
> :paperclip: Usa sempre `executarAmbLookAndFeel(LookAndFeelSwing.SISTEMA)` per obtenir l'aparença nativa del sistema operatiu. Reserva `executar()` per a prototips ràpids.

#### Exemple d'implementació

```java
public final class MevaAplicacio extends AplicacioGuiBase {
    private static final Logger LOGGER = Logger.getLogger(MevaAplicacio.class.getName());
    private JFrame frmPrincipal;

    public MevaAplicacio() {
        // Inicialitza el model aquí (no la interfície)
    }

    @Override
    protected void abansInicialitzar() {
        LOGGER.log(Level.INFO, "Iniciant l''aplicació...");
    }

    @Override
    protected void inicialitzar() {
        this.frmPrincipal = FinestresSwing.frame("La meva app", 800, 600);
        this.frmPrincipal.setVisible(true);
    }

    @Override
    protected void despresInicialitzar() {
        LOGGER.log(Level.INFO, "Aplicació llesta.");
    }
}
```

---

### `TasquesSwing` — Operacions en segon pla

Executa operacions llargues sense bloquejar l'EDT (interfície). Basat en `SwingWorker`.

#### Executar en segon pla

```java
TasquesSwing.executarEnFons(
    () -> gestor.guardar(),          // Supplier: s'executa en segon pla
    _ -> lblEstat.setText("Desat"),  // Consumer: s'executa a l'EDT quan acaba
    ex -> mostrarError(ex)           // Consumer<Throwable>: s'executa si hi ha error
);
```

#### Executar amb barra de progrés

```java
TasquesSwing.executarAmbBarraProgres(
    frame,
    "Carregant...",
    () -> gestor.carregar(),          // Supplier: s'executa en segon pla
    dades -> refrescarLlista(dades),  // Consumer: rep el resultat a l'EDT
    ex -> mostrarError(ex)
);
```

> [!TIP]
> :paperclip: Usa `executarAmbBarraProgres` per a operacions que poden durar més d'un segon (càrrega de fitxers grans, consultes a base de dades). Per a operacions ràpides com guardar, `executarEnFons` és suficient.

---

### `LookAndFeelSwing` — Look and Feel

Aplicació i gestió de temes visuals de Swing, compatible amb totes les plataformes.

```java
LookAndFeelSwing.aplicarSistema();
LookAndFeelSwing.aplicarNimbus();
LookAndFeelSwing.aplicarMetal();
```

#### Constants disponibles

```java
LookAndFeelSwing.SISTEMA
LookAndFeelSwing.NIMBUS
LookAndFeelSwing.METAL
LookAndFeelSwing.MOTIF
LookAndFeelSwing.WINDOWS
LookAndFeelSwing.WINDOWS_CLASSIC
LookAndFeelSwing.GTK
LookAndFeelSwing.MAC_OS
```

#### Comprovar compatibilitat

```java
boolean compatible = LookAndFeelSwing.esCompatible(LookAndFeelSwing.WINDOWS);
```

#### Actualitzar la interfície

```java
LookAndFeelSwing.actualitzar(frame);
```

---

### `MenuTemesSwing` — Menú de temes

Crea automàticament un menú de selecció de Look and Feel. Els temes incompatibles amb el sistema es mostren deshabilitats.

```java
JMenu mnuTemes = MenuTemesSwing.crearMenuTemes(frame);

// Amb acció posterior
JMenu mnuTemes = MenuTemesSwing.crearMenuTemes(
    frame,
    nomTema -> lblEstat.setText("Tema aplicat: " + nomTema)
);
```

---

### `IconesSwing` — Icones

Carrega icones del paquet Fugue inclòs a la llibreria. Inclou una memòria cau thread-safe i constants semàntiques.

#### Carregar per constant semàntica

```java
Icon icona = IconesSwing.carregar(IconesSwing.AFEGIR);
Icon icona = IconesSwing.carregar(IconesSwing.GUARDAR, 24); // mida personalitzada
```

#### Carregar per nom del catàleg complet

```java
Icon icona = IconesSwing.carregar(IconesFugue.CALENDAR);
```

#### Carregar des d'un recurs extern

```java
Icon icona = IconesSwing.carregar(MevaClasse.class, "/recursos/icones/", "logo.png");
```

#### Constants semàntiques disponibles (selecció)

```java
// Accions principals
IconesSwing.NOU, GUARDAR, CARREGAR, SORTIR, INICI

// CRUD
IconesSwing.AFEGIR, TREURE, ELIMINAR, EDITAR, CERCAR, ACTUALITZAR

// Documents
IconesSwing.DOCUMENT, DOCUMENT_BINARI, DOCUMENT_PDF, DOCUMENT_EXCEL, DOCUMENT_WORD

// Llibres
IconesSwing.LLIBRE, LLIBRE_AFEGIR, LLIBRE_ELIMINAR

// Temes / sistemes operatius
IconesSwing.TEMA_JAVA, TEMA_WINDOWS, TEMA_LINUX, TEMA_MAC_OS

// Ajuda
IconesSwing.AJUDA, INFORMACIO, INTERROGACIO, BUG

// Estats
IconesSwing.OK, ERROR, AVIS
```

---

### `DialegsSwing` — Diàlegs

Tots els mètodes són thread-safe: es poden cridar des de qualsevol fil.

#### Diàlegs informatius

```java
DialegsSwing.info(frame, "Títol", "Missatge");
DialegsSwing.avis(frame, "Títol", "Missatge");
DialegsSwing.error(frame, "Títol", "Missatge");
```

#### Confirmació

```java
boolean confirmat = DialegsSwing.confirmar(frame, "Títol", "Vols continuar?");
```

#### Text llarg amb scroll

```java
DialegsSwing.textLlarg(frame, "Títol", contingut, 600, 400);
```

#### Selectors de fitxer

Retornen `Optional<File>` — buit si l'usuari cancel·la.

```java
// Guardar
DialegsSwing.triarFitxerGuardar(frame);
DialegsSwing.triarFitxerGuardar(frame, "txt");
DialegsSwing.triarFitxerGuardar(frame, "Fitxers de text", "txt");
DialegsSwing.triarFitxerGuardar(frame, "Fitxers de text", "txt", "dades.txt");

// Carregar
DialegsSwing.triarFitxerCarregar(frame);
DialegsSwing.triarFitxerCarregar(frame, "dat");
DialegsSwing.triarFitxerCarregar(frame, "Fitxers binaris", "dat");
```

> [!TIP]
> :paperclip: Encadena `ifPresent` per executar l'acció només si l'usuari ha triat un fitxer:
> ```java
> DialegsSwing.triarFitxerGuardar(frame, "txt")
>     .ifPresent(f -> gestor.guardarText(f));
> ```
> Si l'usuari cancel·la el diàleg, `Optional` és buit i no passa res.

---

### `TaulesSwing` — Taules genèriques

Crea un `JTable` a partir de qualsevol llista d'objectes i una llista de columnes, sense escriure cap `TableModel` a mà.

#### Definir columnes i crear la taula

```java
List<ColumnaSwing<Producte>> columnes = List.of(
    new ColumnaSwing<>("ID",   Producte::getId),
    new ColumnaSwing<>("Nom",  Producte::getNom),
    new ColumnaSwing<>("Preu", Producte::getPreu)
);

// Guardar referència al model per poder actualitzar les dades
ModelTaulaSwing<Producte> model = TaulesSwing.model(productes, columnes);
JTable taula = TaulesSwing.taula(model);

// O en un sol pas (sense referència al model)
JTable taula = TaulesSwing.taula(productes, columnes);
```

#### Actualitzar les dades

```java
model.actualitzar(novaLlista);
```

#### Obtenir l'element seleccionat

```java
Optional<Producte> seleccionat = TaulesSwing.getFilaSeleccionada(taula);
seleccionat.ifPresent(p -> System.out.println(p.getNom()));
```

#### Gestió d'amplades de columna

```java
// Amplada preferida (l'última columna ocupa l'espai restant)
TaulesSwing.setAmplada(taula, 0, 50);

// Amplada fixa (l'usuari no pot redimensionar)
TaulesSwing.setAmpladaFixa(taula, 0, 50);

// Auto-ajust al contingut de totes les columnes
TaulesSwing.ajustarAmpladesAContingut(taula);
```

> [!TIP]
> :paperclip: Crida `ajustarAmpladesAContingut` **després** de carregar les dades, no en la inicialització, perquè mesura les cel·les amb el contingut real.

> [!NOTE]
> :paperclip: `getFilaSeleccionada` usa `convertRowIndexToModel` internament, de manera que funciona correctament quan l'usuari ha ordenat la taula per qualsevol columna.

---

### Altres classes del paquet `aplicaciogui`

| Classe | Descripció |
|---|---|
| `FinestresSwing` | Creació de `JFrame` i `JDialog` |
| `ComponentsSwing` | Botons, camps de text, llistes, etiquetes, scroll |
| `PanellsSwing` | Panells amb BorderLayout, FlowLayout, GridLayout, BoxLayout |
| `MenusSwing` | Barres de menú, menús, ítems, radiobuttons |
| `EdtSwing` | Execució segura al fil d'esdeveniments (EDT) |
| `ModelTaulaSwing` | `AbstractTableModel` genèric per a `TaulesSwing` |
| `ColumnaSwing` | Record que defineix una columna de `ModelTaulaSwing` |
| `IconesFugue` | Catàleg complet de les icones Fugue (3500+) |
| `TemaLookAndFeelSwing` | Record que representa un tema de Look and Feel |

---

## Exemple complet

```java
// Test.java
public final class Test {
    public static void main(String[] args) {
        new MevaAplicacio().executarAmbLookAndFeel(LookAndFeelSwing.SISTEMA);
    }
}

// MevaAplicacio.java
public final class MevaAplicacio extends AplicacioGuiBase {
    private static final Logger LOGGER = Logger.getLogger(MevaAplicacio.class.getName());
    private JFrame frmPrincipal;
    private JTextField txtNom;
    private GestorDades gestor;

    public MevaAplicacio() {
        this.gestor = new GestorDades();
    }

    @Override
    protected void abansInicialitzar() {
        LOGGER.log(Level.INFO, "Iniciant...");
    }

    @Override
    protected void inicialitzar() {
        this.frmPrincipal = FinestresSwing.frame("La meva app", 800, 600);
        this.txtNom = ComponentsSwing.campText(20);

        JButton btnGuardar = ComponentsSwing.boto(
            "Guardar",
            IconesSwing.carregar(IconesSwing.GUARDAR),
            this::guardar
        );
        this.frmPrincipal.getRootPane().setDefaultButton(btnGuardar);

        JPanel panel = PanellsSwing.panelAmbMarge(new BorderLayout(), 10);
        panel.add(this.txtNom, BorderLayout.CENTER);
        panel.add(btnGuardar, BorderLayout.SOUTH);

        this.frmPrincipal.setContentPane(panel);
        this.frmPrincipal.setVisible(true);
    }

    @Override
    protected void despresInicialitzar() {
        this.txtNom.requestFocusInWindow();
        LOGGER.log(Level.INFO, "Llesta.");
    }

    private void guardar() {
        DialegsSwing.triarFitxerGuardar(this.frmPrincipal, "txt")
            .ifPresent(f -> TasquesSwing.executarEnFons(
                () -> { this.gestor.guardar(f); return null; },
                _ -> DialegsSwing.info(this.frmPrincipal, "Èxit", "Dades guardades."),
                _ -> DialegsSwing.error(this.frmPrincipal, "Error", "No s'han pogut guardar les dades.")
            ));
    }
}
```

---

## Autor :technologist:

Andreu Garriga Cendán
