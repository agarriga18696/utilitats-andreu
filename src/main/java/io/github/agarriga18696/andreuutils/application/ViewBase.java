package io.github.agarriga18696.andreuutils.application;

import io.github.agarriga18696.andreuutils.core.MenuUtils;

/**
 * Base class for creating console application views.
 *
 * @author Andreu
 * @version 3.1
 */
public abstract class ViewBase {

	////////////////////////////////////////////////////
	/// ABSTRACT METHODS
	////////////////////////////////////////////////////

	protected abstract String title();
	protected abstract String[] options();
	protected abstract void handle(int option);

	////////////////////////////////////////////////////
	/// PUBLIC METHODS
	////////////////////////////////////////////////////

	/**
	 * Returns the number of the exit option, which is always the last option in the array.
	 *
	 * @return The number of the exit option.
	 */
	protected int exitOption() {
		return options().length;
	}

	/**
	 * Displays the application's main menu with its title and options.
	 * <p>
	 * Behavior:
	 * <ul>
	 * 	<li>Prompts the user for an {@code int} option until the exit option is selected.</li>
	 * 	<li>Handles each option entered by the user.</li>
	 * </ul>
	 * <p>
	 * <i><b>Note:</b> The exit option is always the last element in the options array.</i>
	 */
	public void showMenu() {
		String[] menuOptions = options();
		int option;
		do {
			option = MenuUtils.show(title(), exitOption(), menuOptions);
			handle(option);
		} while(option != exitOption());
	}

}
