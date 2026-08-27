package io.github.agarriga18696.andreuutils.application;

/**
 * Base class for creating console applications.
 *
 * @author Andreu
 * @version 2.0
 */
public abstract class ApplicationBase {

	////////////////////////////////////////////
	/// ABSTRACT METHODS
	////////////////////////////////////////////

	protected abstract ControllerBase controller();
	protected abstract ViewBase view(ControllerBase controller);

	////////////////////////////////////////////
	/// PUBLIC METHODS
	////////////////////////////////////////////

	/**
	 * Runs the application.
	 */
	public void run() {
		ControllerBase controller = controller();
		controller.initialize();
		view(controller).showMenu();
	}

}
