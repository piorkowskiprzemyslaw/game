/**
 * 
 */
package balls.view.gameplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.game.NormalGravitySetDuringGameEvent;

/**
 * Klasa odpowiedzlana za obsługe zdarzenia związanego ze zmnianą grawitacji wczasie gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class NormalGravitySetDuringGame implements ActionListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	NormalGravitySetDuringGame(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			blockingQueue.put(new NormalGravitySetDuringGameEvent());
		} catch (InterruptedException e)
		{
			System.err.println("" + e.getMessage());
			e.printStackTrace();
		}

	}

}
