package io.github.agarriga18696.andreuutils.application;

import io.github.agarriga18696.andreuutils.core.Fitxers;
import io.github.agarriga18696.andreuutils.core.Missatges;

/**
 * Base class for creating console application controllers.
 *
 * @author Andreu
 * @version 2.0
 */
public abstract class ControllerBase {

	////////////////////////////////////////////
	/// ABSTRACT METHODS
	////////////////////////////////////////////

	protected abstract String directory();
	protected abstract void load();
	protected abstract void save();

	////////////////////////////////////////////
	/// PUBLIC METHODS
	////////////////////////////////////////////

	/**
	 * Initializes the directory and loads data from the file.
	 */
	public void initialize() {
		Fitxers.crearDirectoriSiNoExisteix(directory());
		load();
	}

	/**
	 * Saves the data and terminates the application.
	 */
	public void shutdown() {
		save();
		Missatges.fiPrograma();
		System.exit(0);
	}

}
