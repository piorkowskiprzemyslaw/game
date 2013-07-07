package balls.view.hello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.nogame.BigSizeSetEvent;

/**
 * Klasa odpowiadająca za obłsuge zdarzenia zmiany rozmiaru planszy gry na duży.
 * 
 * @author Przemysław Piórkowski
 *
 */
final class BigSizeSet implements ActionListener
{

	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	BigSizeSet(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			blockingQueue.put(new BigSizeSetEvent());
		}
		catch (InterruptedException e1)
		{
			System.err.println("" + e1.getMessage());
			e1.printStackTrace();
		}

	}

}
