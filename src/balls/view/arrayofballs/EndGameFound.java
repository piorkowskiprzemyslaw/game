package balls.view.arrayofballs;

import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.game.EndGameFoundEvent;

/**
 * Klasa odpowiedzialna za obsługe zdarzenia związanego z wykryciem końca gry na danej makiecie.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class EndGameFound
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	EndGameFound(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	/**
	 * Generuje Event odpowiedni dla danego zdarzenia.
	 */
	void confirmEndGameEvent()
	{
		try
		{
			blockingQueue.put(new EndGameFoundEvent());
		} catch (InterruptedException e)
		{
			System.err.println("" + e.getMessage());
			e.printStackTrace();
		}
	}
}
