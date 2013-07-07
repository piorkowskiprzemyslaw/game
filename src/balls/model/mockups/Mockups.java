/**
 * 
 */
package balls.model.mockups;

import java.util.Arrays;

/**
 * Klasa implementująca makiete do komunakacji model - controller - view.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public final class Mockups
{
	private final int size;
	private final BallColor[][] gamePlay;
	private final boolean isEnd;
	private final String userName;
	private final int points;
	private final int lastMove;

	/**
	 * Domyślny konstruktor
	 * 
	 * @param gamePlay
	 * @param userName
	 * @param isEnd
	 * @param points
	 * @param lastMove
	 */
	public Mockups(BallColor[][] gamePlay, String userName, boolean isEnd,
			int points, int lastMove)
	{
		this.points = points;
		this.lastMove = lastMove;
		this.isEnd = isEnd;
		this.size = gamePlay.length;
		this.userName = userName;
		this.gamePlay = Arrays.copyOf(gamePlay, gamePlay.length);
		for (int i = 0; i < gamePlay.length; ++i)
		{
			this.gamePlay[i] = Arrays.copyOf(gamePlay[i], gamePlay[i].length);
		}
	}

	/**
	 * Konstruktor kopiujący, wykonuje głęboką kopie danych.
	 * 
	 * @param another
	 */
	public Mockups(Mockups another)
	{
		this.points = another.points;
		this.lastMove = another.lastMove;
		this.isEnd = another.isEnd;
		this.size = another.size;
		this.userName = another.userName;
		this.gamePlay = Arrays.copyOf(another.gamePlay, another.gamePlay.length);
		for (int i = 0; i < gamePlay.length; ++i)
		{
			this.gamePlay[i] = Arrays.copyOf(another.gamePlay[i],
					another.gamePlay[i].length);
		}
	}

	/**
	 * Metoda zwracająca tablice dwuwymiarową enumeratorów.
	 * 
	 * @return BallColor[][]
	 */
	public BallColor[][] getGamePlay()
	{
		return gamePlay;
	}

	/**
	 * Metoda zwracająca rozmiar aktualnie przechowywanej w makiecie planszy.
	 * 
	 * @return size
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Metoda zwracajaca znacznik isEnd
	 * 
	 * @return isEnd
	 */
	public boolean getIsEnd()
	{
		return isEnd;
	}

	/**
	 * Metoda zwracajaca nazwe uzytkownika z makiety.
	 * 
	 * @return userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Metoda pobierajaca punkty.
	 * 
	 * @return points
	 */
	public int getPoints()
	{
		return points;
	}

	/**
	 * Metoda pobierajaca punkty z ostatniego ruchu.
	 * 
	 * @return lastMove.
	 */
	public int getLastMovePoints()
	{
		return lastMove;
	}
}
