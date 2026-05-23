# Llibreria d'utilitats Andreu

Llibreria Java per crear **aplicacions de consola i aplicacions gràfiques Swing** de manera ràpida, estructurada i professional.

Requereix **Java 21** o superior.

---

**Versió actual: `5.1.1`** → [Descàrrega directa](https://github.com/agarriga18696/utilitats-andreu/releases/download/v5.1.1/utilitats-andreu-5.1.1.jar)

---

## Paquets disponibles

| Paquet | Descripció |
|---|---|
| `aplicaciogui` | Components, panells, diàlegs, menús, icones i utilitats per a aplicacions Swing |
| `utilitats` | Utilitats generals: cadenes, fitxers, dates, col·leccions i entrada per consola |

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
    () -> gestor.guardar(),         // Supplier: s'executa en segon pla
    _ -> lblEstat.setText("Desat"), // Consumer: s'executa a l'EDT quan acaba
    ex -> mostrarError(ex)          // Consumer<Throwable>: s'executa si hi ha error
);
```

#### Executar amb barra de progrés

```java
TasquesSwing.executarAmbBarraProgres(
    frame,
    "Carregant...",
    () -> gestor.carregar(),         // Supplier: s'executa en segon pla
    dades -> refrescarLlista(dades), // Consumer: rep el resultat a l'EDT
    ex -> mostrarError(ex)
);
```

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

### Altres classes del paquet `aplicaciogui`

| Classe | Descripció |
|---|---|
| `FinestresSwing` | Creació de `JFrame` i `JDialog` |
| `ComponentsSwing` | Botons, camps de text, llistes, etiquetes, scroll |
| `PanellsSwing` | Panells amb BorderLayout, FlowLayout, GridLayout, BoxLayout |
| `MenusSwing` | Barres de menú, menús, ítems, radiobuttons |
| `DialegsSwing` | Diàlegs d'informació, avís, error i confirmació |
| `EdtSwing` | Execució segura al fil d'esdeveniments (EDT) |
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
        TasquesSwing.executarEnFons(
            () -> { this.gestor.guardar(); return null; },
            _ -> DialegsSwing.info(this.frmPrincipal, "Èxit", "Dades guardades."),
            _ -> DialegsSwing.error(this.frmPrincipal, "Error", "No s'han pogut guardar les dades.")
        );
    }
}
```

---

## Requisits

- Java 21 o superior
- Cap dependència externa

## Autor

Andreu Garriga Cendán
