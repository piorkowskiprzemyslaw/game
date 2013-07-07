/**
 * 
 */
package balls.view.gameplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.game.BackGravitySetDuringGameEvent;

/**
 * Klasa odpowiedzialna za obsługe zdarzenia zmiany grawitacji w trakcie gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class BackGravitySetDuringGame implements ActionListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	BackGravitySetDuringGame(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			blockingQueue.put(new BackGravitySetDuringGameEvent());
		} catch (InterruptedException e1)
		{
			System.err.println("" + e1.getMessage());
			e1.printStackTrace();
		}
	}

}
