package balls.view.gameplay;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.*;
import balls.common.events.game.SetNewGamePlayEvent;

/**
 * Klasa odpowiedzialna za ustawienie nowej planszy do gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public class SetNewGamePlay implements MouseListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	SetNewGamePlay(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	/**
	 * Metoda obsługująca właściwe zdarzenie i generująca odpowiedni Event.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		try
		{
			blockingQueue.put(new SetNewGamePlayEvent());
		} catch (InterruptedException e)
		{
			System.out.println("" + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
