package io.github.agarriga18696.andreuutils.application;

import io.github.agarriga18696.andreuutils.core.FileUtils;
import io.github.agarriga18696.andreuutils.core.MessageUtils;

/**
 * Base class for creating console application controllers.
 *
 * @author Andreu
 * @version 2.1
 */
public abstract class ControllerBase {

    /**
     * Creates a new controller base.
     */
    public ControllerBase() {
        // Base constructor
    }

    // ----------------------------------------
    // ABSTRACT METHODS
    // ----------------------------------------

    /**
     * Returns the directory used by the application.
     *
     * @return Application directory.
     */
    protected abstract String directory();

    /**
     * Loads the application data.
     */
    protected abstract void load();

    /**
     * Saves the application data.
     */
    protected abstract void save();

    // ----------------------------------------
    // PUBLIC METHODS
    // ----------------------------------------

    /**
     * Initializes the application directory and loads its data.
     */
    public void initialize() {
        FileUtils.createDirectoriesIfAbsent(directory());
        load();
    }

    /**
     * Saves the application data and terminates the program.
     */
    public void shutdown() {
        save();
        MessageUtils.endProgram();
        System.exit(0);
    }
}