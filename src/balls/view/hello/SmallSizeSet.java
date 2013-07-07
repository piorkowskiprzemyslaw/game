/**
 * 
 */
package balls.view.hello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.nogame.SmallSizeSetEvent;

/**
 * Klasa odpowiedzlana obsługe zdarzeniazwiązanego z ustawieniem małego rozmaru gamePlay'a
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class SmallSizeSet implements ActionListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	SmallSizeSet(LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			blockingQueue.put(new SmallSizeSetEvent());
		} catch (InterruptedException e)
		{
			System.err.println("" + e.getMessage());
			e.printStackTrace();
		}

	}

}
