/** 
 * Pakiet zawiera implementacje klas widoku. */
package balls.view;

import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.*;

import balls.common.events.AppEvent;
import balls.model.mockups.Mockups;
import balls.view.gameplay.GamePlayWindow;
import balls.view.hello.HelloWindow;

/**
 * Klasa fasadowa przechowująca API widoku.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public class View
{
	private final LinkedBlockingQueue<AppEvent> blockingQueue;
	private final HelloWindow helloWindow;
	private GamePlayWindow gamePlayWindow;

	/**
	 * Domyślny konstruktor
	 * 
	 * @param blockingQueue
	 *            referencja do wspólnej kolejki View i Controllera
	 */
	public View(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
		helloWindow = new HelloWindow(this.blockingQueue);
	}

	/**
	 * Metoda pokazująca okienko powitalne.
	 */
	public void showHelloWindow()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				helloWindow.show();
			}
		});
	}

	/**
	 * Metoda pokazująca okienko gry zgodne z informacjami zawartymi w makiecie.
	 * 
	 * @param mockup
	 *            odwzorowujący aktualny stan modelu.
	 */
	public void showGamePlayWindow(final Mockups mockup)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				View.this.gamePlayWindow = new GamePlayWindow(blockingQueue, mockup);
				helloWindow.hide();
				gamePlayWindow.show();
				gamePlayWindow.setNewGamePlay(mockup);
			}
		});
	}

	/**
	 * Metoda ustawiająca okno gry, szybsza niż setNewGamePlayWindow, bo nie ustawia całej tablicy kulek od nowa, a
	 * tylko te elementy które uległy zmianie.
	 * 
	 * @param mockup
	 *            makieta odworowująca aktualny stan obiektu
	 */
	public void setRefreshedGamePlayWindow(final Mockups mockup)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				gamePlayWindow.setGamePlay(mockup);

			}

		});
	}

	/**
	 * Metoda ustawiająca zupełnie nowe okno gry.
	 * 
	 * @param mockup
	 *            makieta odwzorowująca aktualny stan obiektu.
	 */
	public void setNewGamePlayWindow(final Mockups mockup)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				gamePlayWindow.setNewGamePlay(mockup);

			}
		});
	}

}
