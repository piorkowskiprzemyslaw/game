package balls.view.hello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.nogame.NewGameButtonClickedEvent;

/**
 * Klasa odpowiadjąca za obsługe zdarzenia związanego z kliknięciem przycisku nowej gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class NewGameButtonClicked implements ActionListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	NewGameButtonClicked(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		try
		{
			blockingQueue.put(new NewGameButtonClickedEvent());
		} catch (InterruptedException e)
		{
			System.err.println("" + e.getMessage());
			e.printStackTrace();
		}

	}

}
