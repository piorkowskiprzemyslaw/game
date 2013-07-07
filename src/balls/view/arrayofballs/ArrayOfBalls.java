package balls.view.arrayofballs;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import balls.common.events.AppEvent;
import balls.model.mockups.*;

/**
 * Klasa przechowujaca komponent GamePlayWindow reprezentujacy dwuwymiarowa tablice kulek.
 */
public class ArrayOfBalls
{
	private final JPanel panel;
	private final ImageLoader imageLoader;
	private final JLabel balls[][];
	private final MouseListener ballLabelClicked;
	private final EndGameFound endGameFound;
	private final LinkedBlockingQueue<AppEvent> blockingQueue;
	private BallColor[][] diff;
	private int SIZE = 15;

	/**
	 * Domyślny konstruktor odpowiedzialny za organizacje tego panelu.
	 */
	public ArrayOfBalls(final LinkedBlockingQueue<AppEvent> blockingQueue, final Mockups mockup)
	{
		this.panel = new JPanel();
		this.imageLoader = new ImageLoader();
		this.balls = new JLabel[SIZE][SIZE];
		this.blockingQueue = blockingQueue;
		this.ballLabelClicked = new BallLabelClicked(this.blockingQueue, balls);
		this.endGameFound = new EndGameFound(this.blockingQueue);
		this.SIZE = mockup.getSize();
		initializePanel();
	}

	/**
	 * Inicjalizowanie właściwego wyglądu panelu.
	 */
	private void initializePanel()
	{
		this.panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		this.panel.setBounds(220, 15, 28 * SIZE, 28 * SIZE);
		this.panel.setLayout(new GridLayout(SIZE, SIZE, 0, 0));
		for (int i = 0; i < SIZE; ++i)
		{
			for (int j = 0; j < SIZE; ++j)
			{
				balls[j][i] = new JLabel();
				this.panel.add(balls[j][i]);
				balls[j][i].addMouseListener(ballLabelClicked);
			}
		}
	}

	/**
	 * Zwraca JPanel dzieki ktoremu mozemy dodac tablice kulek do JFrame.
	 * 
	 * @return JPanel.
	 */
	public JPanel getPanel()
	{
		return panel;
	}

	/**
	 * Ustawienie kompletnie nowej tablicy kulek. Bez brania roznicy z poprzednia tablica. Ustawienie nowej tablicy
	 * poprzedniej wlasnie w tym miejscu. Wywoływane w trakcie startu nowej gry, lub odswiezenia planszy.
	 * 
	 * @param mockup
	 */
	public void setNewArrayOfBalls(final Mockups mockup)
	{
		final BallColor[][] array = mockup.getGamePlay();
		this.diff = mockup.getGamePlay();
		for (int i = 0; i < SIZE; ++i)
		{
			for (int j = 0; j < SIZE; ++j)
			{
				switch (array[i][j])
				{
				case BLUE:
					balls[i][j].setIcon(imageLoader.getBlue());
					break;
				case EMPTY:
					balls[i][j].setIcon(imageLoader.getEmpty());
					break;
				case GREEN:
					balls[i][j].setIcon(imageLoader.getGreen());
					break;
				case ORANGE:
					balls[i][j].setIcon(imageLoader.getOrange());
					break;
				case RED:
					balls[i][j].setIcon(imageLoader.getRed());
					break;
				case VIOLET:
					balls[i][j].setIcon(imageLoader.getViolet());
					break;
				}
			}
		}

		if (true == mockup.getIsEnd())
		{
			JOptionPane.showMessageDialog(null, "Koniec Gry!\n" + mockup.getUserName() + " Twój wynik to : "
					+ mockup.getPoints(), "Koniec Gry!", JOptionPane.INFORMATION_MESSAGE);
			endGameFound.confirmEndGameEvent();
		}
	}

	/**
	 * Przestawia isniejaca tablice do gry zgodnie z przesnlana makieta w celu szybszego dzialania sprawdzanie
	 * roznicy z poprzednia makieta. Wywoływane w trakcie zbijania kulek.
	 * 
	 * @param mockup
	 */
	public void setArrayOfBalls(final Mockups mockup)
	{
		JLabel actual;
		for (DiffPair diff : getDiff(mockup))
		{
			actual = balls[diff.getI()][diff.getJ()];
			switch (diff.getColor())
			{
			case BLUE:
				actual.setIcon(imageLoader.getBlue());
				break;
			case EMPTY:
				actual.setIcon(imageLoader.getEmpty());
				break;
			case GREEN:
				actual.setIcon(imageLoader.getGreen());
				break;
			case ORANGE:
				actual.setIcon(imageLoader.getOrange());
				break;
			case RED:
				actual.setIcon(imageLoader.getRed());
				break;
			case VIOLET:
				actual.setIcon(imageLoader.getViolet());
				break;
			}
		}

		if (true == mockup.getIsEnd())
		{
			endGameFound.confirmEndGameEvent();
			JOptionPane.showMessageDialog(null, mockup.getUserName() + " Twój wynik to : " + mockup.getPoints(),
					"Koniec Gry!", JOptionPane.INFORMATION_MESSAGE);
		}

		this.diff = mockup.getGamePlay();
	}

	/**
	 * Metoda wywoływana przez metode setArrayOfBalls w celu ustalenia na których pozycjach należy wprowadzić
	 * zmiany w tablicy tak aby nie ustawiać wszystkich Icon od nowa, a tylko te ktore zmienily sie podczas
	 * ostatniego ruchu.
	 * 
	 * @param mockup
	 *            - nowa makieta
	 * @return Lista opisująca pozycje na których trzeba wprowadzić zmiany.
	 */
	public LinkedList<DiffPair> getDiff(final Mockups mockup)
	{
		LinkedList<DiffPair> returned = new LinkedList<DiffPair>();
		BallColor[][] mocBallArray = mockup.getGamePlay();
		for (int i = 0; i < SIZE; ++i)
		{
			for (int j = 0; j < SIZE; ++j)
			{
				if (mocBallArray[i][j] != diff[i][j])
				{
					returned.add(new DiffPair(mocBallArray[i][j], i, j));
				}
			}
		}
		return returned;
	}

}
