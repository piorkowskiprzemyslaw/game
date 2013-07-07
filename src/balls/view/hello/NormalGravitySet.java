/**
 * 
 */
package balls.view.hello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.nogame.NormalGravitySetEvent;

/**
 * Klasa odpowiedzialna za odebranie zdarzenia związanego z ustawieniem grawitacji przed rozpoczęciem gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class NormalGravitySet implements ActionListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	NormalGravitySet(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			blockingQueue.put(new NormalGravitySetEvent());
		} catch (InterruptedException e)
		{
			System.err.println("" + e.getMessage());
			e.printStackTrace();
		}

	}

}
