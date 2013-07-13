/**
 * 
 */
package balls;

import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.controller.Controller;
import balls.model.Model;
import balls.view.View;

/**
 * 
 * @author Przemysław Piórkowski
 * 
 */
public final class StartClass
{
	public static void main(String[] args)
	{
		final Model model = new Model();
		final LinkedBlockingQueue<AppEvent> blockingQueue = new LinkedBlockingQueue<AppEvent>();
		final View view = new View(blockingQueue);
		final Controller controller = new Controller(model, view, blockingQueue);
		controller.start();
	}
}
