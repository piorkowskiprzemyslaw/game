package balls.view.hello;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;

import balls.common.events.AppEvent;
import balls.common.events.nogame.UserNameChangedEvent;

/**
 * Klasa odpowiedzialna za obsługe zdarzenia związanego ze zmianą nazwy użytkownika, przed rozpoczęciem gry.
 * Wystawia najpierw okienko do zmiany imienia, a potem generuje zdarzenie zmieniające imie użytkownika.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public class UserNameChanged implements ActionListener
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;

	UserNameChanged(LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String newUserName = JOptionPane.showInputDialog("Podaj nową nazwe użytkownika :");
		if (newUserName != null)
		{
			try
			{
				blockingQueue.put(new UserNameChangedEvent(newUserName));
			} catch (InterruptedException e)
			{
				System.err.println("" + e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
