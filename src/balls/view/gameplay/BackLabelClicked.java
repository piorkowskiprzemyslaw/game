package balls.view.gameplay;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.game.BackLabelClickedEvent;

/**
 * Klasa odpowiedzialna za obsługe zdarzenia kliknięcia etykiety cofającej ostatni ruch.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class BackLabelClicked implements MouseListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	BackLabelClicked(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	/**
	 * Właściwa metoda generująca Event odpowiadający przechwyconemu zdarzeniu.
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		try
		{
			blockingQueue.put(new BackLabelClickedEvent());
		} catch (InterruptedException e1)
		{
			System.err.println("" + e1.getMessage());
			e1.printStackTrace();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

}
