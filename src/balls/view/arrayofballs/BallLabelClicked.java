package balls.view.arrayofballs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JLabel;

import balls.common.Coordinates;
import balls.common.events.AppEvent;
import balls.common.events.game.BallLabelClickedEvent;

/**
 * Klasa odpowiedzialna za obsluge zdarzenia zwiazanego z kliknieciem Label'a zawierajacego kulke.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class BallLabelClicked implements MouseListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;
	private final JLabel[][] ballLabels;

	BallLabelClicked(final LinkedBlockingQueue<AppEvent> blockingQueue, final JLabel[][] ballLabels)
	{
		this.blockingQueue = blockingQueue;
		this.ballLabels = ballLabels;
	}

	/**
	 * Zdarzenie generujace Event z informacjami na temat wspolrzednych kliknietej kulki.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		JLabel clicked = (JLabel) arg0.getSource();
		int i = 0, j = 0;
		boolean flag = false;

		for (i = 0; i < ballLabels.length; ++i)
		{
			for (j = 0; j < ballLabels.length; ++j)
			{
				if (ballLabels[i][j] == clicked)
				{
					flag = true;
					break;
				}
			}
			if (flag == true)
			{
				break;
			}
		}
		try
		{
			blockingQueue.put(new BallLabelClickedEvent(new Coordinates(i, j)));
		} catch (InterruptedException e)
		{
			System.err.println("" + e.getMessage());
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
