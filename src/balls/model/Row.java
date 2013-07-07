/**
 * 
 */
package balls.model;

import java.util.Iterator;
import java.util.LinkedList;

import balls.model.mockups.BallColor;

/**
 * Klasa przechowująca rząd kulek.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class Row implements Iterable<Ball>
{
	private final LinkedList<Ball> ballsRow;

	/**
	 * Domyślny konstruktor tworzy pusty rząd.
	 */
	Row()
	{
		this.ballsRow = new LinkedList<Ball>();
	}

	/**
	 * Tworzy rząd identyczny jak przekazany w argumencie.
	 * 
	 * @param ballsRow
	 */
	Row(final LinkedList<Ball> ballsRow)
	{
		this.ballsRow = ballsRow;
	}

	/**
	 * Konstruktor kopiujący
	 * 
	 * @param another
	 */
	Row(final Row another)
	{
		this.ballsRow = new LinkedList<Ball>();
		for (Ball ball : another)
		{
			ballsRow.add(new Ball(ball.getBallColor()));
		}
	}

	/**
	 * Dodaje kulke do rzędu. W jednym rzędzie nie może sie dwa razy znajdować
	 * ta sama kulka.
	 * 
	 * @param ball
	 * @return true if adding was successful
	 */
	boolean add(final Ball ball)
	{
		if (ballsRow.contains(ball))
		{
			return false;
		}
		ballsRow.add(ball);
		return true;
	}

	/**
	 * Usuwa (jesli istanieje w rzędzi) kulke z rzędu.
	 * 
	 * @param ball
	 * @return true if removing was successful
	 */
	boolean remove(final Ball ball)
	{
		return ballsRow.remove(ball);
	}

	/**
	 * Zwraca true jeśli w rzędzie znajduje się piłka.
	 * 
	 * @param ball
	 * @return true if row contains ball.
	 */
	boolean contains(final Ball ball)
	{
		for (Ball ballInside : ballsRow)
		{
			if (ballInside == ball)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Zwraca rozmiar rzedu.
	 * 
	 * @return number of balls in row.
	 */
	int size()
	{
		return ballsRow.size();
	}

	/**
	 * Zwraca kulke w kolejności index w rzędzie.
	 * 
	 * @param index
	 * @return ball
	 */
	Ball get(final int index)
	{
		return ballsRow.get(index);
	}

	/**
	 * Rzędy są sobie równe tylko jeśli są referencjami do tego samego obiektu!
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}

		return false;
	}

	/**
	 * Sprawdza czy rząd nie sklada sie z samych pustych kulek
	 * 
	 * @return true if row size is 0 or column contains only empty balls
	 */
	boolean isEmpty()
	{
		if (this.ballsRow.size() == 0)
		{
			return true;
		}
		for (Ball ball : this.ballsRow)
		{
			if (ball.getBallColor() != BallColor.EMPTY)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Rozszeza rozmiar rzedu do wymiaru zadanego w argumencie uzupełniajac
	 * rzad pustymi kulkami.
	 * 
	 * @param size
	 */
	void fillWithEmpty(final int maxRowSize)
	{
		int diffrence = maxRowSize - this.size();
		for (int i = 0; i < diffrence; ++i)
		{
			this.ballsRow.addLast(new Ball(BallColor.EMPTY));
		}

	}
	
	@Override
	public Iterator<Ball> iterator()
	{
		return ballsRow.iterator();
	}
}
