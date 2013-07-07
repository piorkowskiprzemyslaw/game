/**
 * 
 */
package balls.view.hello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.nogame.BackGravitySetEvent;

/**
 * Klasa odpowiedzialna za obsługe zdarzenia zmiany grawitacji przed rozpoczętą grą.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class BackGravitySet implements ActionListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	BackGravitySet(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			blockingQueue.put(new BackGravitySetEvent());
		} catch (InterruptedException e1)
		{
			System.err.println("" + e1.getMessage());
			e1.printStackTrace();
		}

	}

}
