/**
 * 
 */
package balls.model;

import java.util.Iterator;
import java.util.LinkedList;

import balls.model.mockups.BallColor;

/**
 * Klasa przechowujaca kolumne kulek.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class Column implements Iterable<Ball>
{
	private final LinkedList<Ball> ballsColumn;

	/**
	 * Domyślny konstruktor tworzy pustą kolumne.
	 */
	Column()
	{
		ballsColumn = new LinkedList<Ball>();
	}

	/**
	 * Tworzy kolumne identyczną jak argument.
	 * 
	 * @param ballsColumn
	 */
	Column(final LinkedList<Ball> ballsColumn)
	{
		this.ballsColumn = ballsColumn;
	}

	/**
	 * Konkstruktor kopiujący
	 * 
	 * @param another
	 */
	Column(final Column another)
	{
		this.ballsColumn = new LinkedList<Ball>();
		for (Ball ball : another)
		{
			ballsColumn.add(new Ball(ball.getBallColor()));
		}
	}

	/**
	 * Dodaje kulke do kolumny. W jednej kolumnie nie może sie dwa razy
	 * znajdować ta sama kulka.
	 * 
	 * @param ball
	 * @return true if adding was successful
	 */
	boolean add(final Ball ball)
	{
		if (ballsColumn.contains(ball))
		{
			return false;
		}
		ballsColumn.add(ball);
		return true;
	}

	/**
	 * Usuwa (jesli istanieje w kolumnie) kulke z kolumny.
	 * 
	 * @param ball
	 * @return true if removing was successful
	 */
	boolean remove(final Ball ball)
	{
		return ballsColumn.remove(ball);
	}

	/**
	 * Zwraca true jeśli kulka należy do kolumny.
	 * 
	 * @param ball
	 * @return true if column contains ball
	 */
	boolean contains(final Ball ball)
	{
		for (Ball ballInside : ballsColumn)
		{
			if (ballInside == ball)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Zwraca rozmiar kolumny.
	 * 
	 * @return number of balls in column.
	 */
	int size()
	{
		return ballsColumn.size();
	}

	/**
	 * Pobiera pilke nr index z rzędu.
	 * 
	 * @param index
	 * @return ball
	 */
	Ball get(final int index)
	{
		return ballsColumn.get(index);
	}

	/**
	 * Rozszeza rozmiar kolumny do wymiaru zadanego w argumencie uzupełniajac
	 * kolumne pustymi kulkami.
	 * 
	 * @param size
	 */
	void fillWithEmpty(final int size)
	{
		int diffrence = size - this.size();
		for (int i = 0; i < diffrence; ++i)
		{
			ballsColumn.addLast(new Ball(BallColor.EMPTY));
		}
	}

	/**
	 * Sprawdza czy kolumna nie sklada sie z samych pustych kulek
	 * 
	 * @return true if column size is 0 or column contains only empty balls
	 */
	boolean isEmpty()
	{
		if (this.size() == 0)
		{
			return true;
		}
		for (Ball ball : this.ballsColumn)
		{
			if (ball.getBallColor() != BallColor.EMPTY)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Usuwa puste kulki z kolumny
	 */
	void removeEmptyBalls()
	{
		for (int i = 0; i < ballsColumn.size(); ++i)
		{
			if (ballsColumn.get(i).getBallColor() == BallColor.EMPTY)
			{
				ballsColumn.remove(i);
				--i;
			}
		}
	}

	/**
	 * Kolumny są sobie równe tylko jeśli są referencjami do tego samego
	 * obiektu!
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

	@Override
	public Iterator<Ball> iterator()
	{
		return ballsColumn.iterator();
	}
}
