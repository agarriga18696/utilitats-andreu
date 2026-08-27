package io.github.agarriga18696.andreuutils.swing;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import io.github.agarriga18696.andreuutils.core.Language;
import io.github.agarriga18696.andreuutils.core.LanguageManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for {@link ApplicationMenuBarSwing}.
 */
class ApplicationMenuBarSwingTest {

    @AfterEach
    void resetLanguage() {
        LanguageManager.setLanguage(Language.ENGLISH);
    }

    @Test
    void createsBaseApplicationMenuBar() {

        LanguageManager.setLanguage(Language.ENGLISH);

        JMenuBar menuBar =
                ApplicationMenuBarSwing.builder(new JPanel())
                        .onHome(() -> {
                        })
                        .onExit(() -> {
                        })
                        .onAbout(() -> {
                        })
                        .build();

        assertEquals(4, menuBar.getMenuCount());

        JMenu fileMenu = menuBar.getMenu(0);
        JMenu viewMenu = menuBar.getMenu(1);
        JMenu settingsMenu = menuBar.getMenu(2);
        JMenu helpMenu = menuBar.getMenu(3);

        assertEquals("File", fileMenu.getText());
        assertEquals("View", viewMenu.getText());
        assertEquals("Settings", settingsMenu.getText());
        assertEquals("Help", helpMenu.getText());

        assertEquals("Home", fileMenu.getItem(0).getText());
        assertNull(fileMenu.getItem(1));
        assertEquals("Exit to desktop", fileMenu.getItem(2).getText());

        assertEquals("Themes", viewMenu.getItem(0).getText());
        assertEquals("Language", settingsMenu.getItem(0).getText());

        assertEquals("About...", helpMenu.getItem(0).getText());
    }

    @Test
    void executesBaseMenuActions() {

        int[] homeCalls = {0};
        int[] exitCalls = {0};
        int[] aboutCalls = {0};

        JMenuBar menuBar =
                ApplicationMenuBarSwing.builder(new JPanel())
                        .onHome(() -> homeCalls[0]++)
                        .onExit(() -> exitCalls[0]++)
                        .onAbout(() -> aboutCalls[0]++)
                        .build();

        menuBar.getMenu(0).getItem(0).doClick();
        menuBar.getMenu(0).getItem(2).doClick();
        menuBar.getMenu(3).getItem(0).doClick();

        assertEquals(1, homeCalls[0]);
        assertEquals(1, exitCalls[0]);
        assertEquals(1, aboutCalls[0]);
    }

    @Test
    void refreshesTextsWhenLanguageChanges() {

        LanguageManager.setLanguage(Language.ENGLISH);

        JMenuBar menuBar =
                ApplicationMenuBarSwing.builder(new JPanel())
                        .onHome(() -> {
                        })
                        .onExit(() -> {
                        })
                        .onAbout(() -> {
                        })
                        .build();

        LanguageManager.setLanguage(Language.SPANISH);

        assertEquals("Archivo", menuBar.getMenu(0).getText());
        assertEquals("Ver", menuBar.getMenu(1).getText());
        assertEquals("Configuración", menuBar.getMenu(2).getText());
        assertEquals("Ayuda", menuBar.getMenu(3).getText());

        assertEquals("Inicio", menuBar.getMenu(0).getItem(0).getText());
        assertEquals(
                "Salir al escritorio",
                menuBar.getMenu(0).getItem(2).getText()
        );

        assertEquals("Temas", menuBar.getMenu(1).getItem(0).getText());
        assertEquals("Idioma", menuBar.getMenu(2).getItem(0).getText());

        assertEquals(
                "Acerca de...",
                menuBar.getMenu(3).getItem(0).getText()
        );
    }

    @Test
    void addsCustomFileMenuItemsBeforeExitSeparator() {

        JMenuBar menuBar =
                ApplicationMenuBarSwing.builder(new JPanel())
                        .onHome(() -> {
                        })
                        .onExit(() -> {
                        })
                        .onAbout(() -> {
                        })
                        .configureFileMenu(menu ->
                                menu.add(
                                        MenusSwing.item(
                                                "Custom action",
                                                () -> {
                                                }
                                        )
                                )
                        )
                        .build();

        JMenu fileMenu = menuBar.getMenu(0);

        assertEquals("Home", fileMenu.getItem(0).getText());
        assertEquals("Custom action", fileMenu.getItem(1).getText());
        assertNull(fileMenu.getItem(2));
        assertEquals(
                "Exit to desktop",
                fileMenu.getItem(3).getText()
        );
    }

    @Test
    void addsCustomViewMenuItemsAfterThemes() {

        JMenuBar menuBar =
                ApplicationMenuBarSwing.builder(new JPanel())
                        .onHome(() -> {
                        })
                        .onExit(() -> {
                        })
                        .onAbout(() -> {
                        })
                        .configureViewMenu(menu ->
                                menu.add(
                                        MenusSwing.item(
                                                "Custom view",
                                                () -> {
                                                }
                                        )
                                )
                        )
                        .build();

        JMenu viewMenu = menuBar.getMenu(1);

        assertEquals("Themes", viewMenu.getItem(0).getText());
        assertEquals("Custom view", viewMenu.getItem(1).getText());
    }

    @Test
    void addsCustomSettingsMenuItemsAfterLanguage() {

        JMenuBar menuBar =
                ApplicationMenuBarSwing.builder(new JPanel())
                        .onHome(() -> {
                        })
                        .onExit(() -> {
                        })
                        .onAbout(() -> {
                        })
                        .configureSettingsMenu(menu ->
                                menu.add(
                                        MenusSwing.item(
                                                "Custom setting",
                                                () -> {
                                                }
                                        )
                                )
                        )
                        .build();

        JMenu settingsMenu = menuBar.getMenu(2);

        assertEquals("Language", settingsMenu.getItem(0).getText());
        assertEquals("Custom setting", settingsMenu.getItem(1).getText());
    }

    @Test
    void addsCustomHelpMenuItemsBeforeAboutSeparator() {

        JMenuBar menuBar =
                ApplicationMenuBarSwing.builder(new JPanel())
                        .onHome(() -> {
                        })
                        .onExit(() -> {
                        })
                        .onAbout(() -> {
                        })
                        .configureHelpMenu(menu ->
                                menu.add(
                                        MenusSwing.item(
                                                "Documentation",
                                                () -> {
                                                }
                                        )
                                )
                        )
                        .build();

        JMenu helpMenu = menuBar.getMenu(3);

        assertEquals("Documentation", helpMenu.getItem(0).getText());
        assertNull(helpMenu.getItem(1));
        assertEquals("About...", helpMenu.getItem(2).getText());
    }
}