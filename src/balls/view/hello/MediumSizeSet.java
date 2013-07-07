/**
 * 
 */
package balls.view.hello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.nogame.MediumSizeSetEvent;

/**
 * Klasa odpowiadająca za obsługe zdarzenia zmiany rozmiaru planszy na średni.
 * 
 * @author Przemysław Piórkowski
 *
 */
final class MediumSizeSet implements ActionListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	MediumSizeSet(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			blockingQueue.put(new MediumSizeSetEvent());
		}
		catch (InterruptedException e1)
		{
			System.err.println("" + e1.getMessage());
			e1.printStackTrace();
		}

	}

}
