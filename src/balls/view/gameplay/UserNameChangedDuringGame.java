package balls.view.gameplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;

import balls.common.events.AppEvent;
import balls.common.events.game.UserNameChangedDuringGameEvent;

/**
 * Klasa odpowiadająca za obsługe zdarzenia związanego ze zminą imienia użytkownika podczas gry. Najpierw wystawia
 * okienko informacjne pytające o nową nazwe użytkownika, a nastepnie generuje odpowiedni Event zmieniący to imie.
 * 
 * @author Przemysław Piórkowski
 * 
 */
class UserNameChangedDuringGame implements ActionListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	UserNameChangedDuringGame(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String newUserName = JOptionPane.showInputDialog("Podaj nową nazwe użytkownika :");

		if (newUserName != null)
		{
			try
			{
				blockingQueue.put(new UserNameChangedDuringGameEvent(newUserName));
			} catch (InterruptedException e1)
			{
				System.err.println("" + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

}
