package balls.view.gameplay;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.game.NextLabelClickedEvent;

/**
 * Klasa odpowiedzlana za obsługe zdarzenia związanego z naciśnięciem etykiety przywracjącej ostatnio cofnięty
 * ruch.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class NextLabelClicked implements MouseListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	NextLabelClicked(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	/**
	 * Właściwa metoda generująca odpowiedni Event i wstawiająca go do BQ.
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		try
		{
			blockingQueue.put(new NextLabelClickedEvent());
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
