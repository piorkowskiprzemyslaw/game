/**
 * 
 */
package balls.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import balls.model.mockups.BallColor;

/**
 * Klasa reprezentujaca grupe kulek o jednakowym kolorze, spełniających warunki
 * gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public final class BallSet implements Iterable<Ball>
{
	private BallColor color;
	private final Set<Ball> ballSet;

	/**
	 * Tworzy pusty zbiór kulek który nie ma koloru.
	 */
	BallSet()
	{
		this.color = BallColor.EMPTY;
		this.ballSet = new HashSet<Ball>();
	}

	/**
	 * Dodaje kulke do zbioru.
	 * 
	 * @param ball
	 * @return boolean which indicates if add was succesfull.
	 */
	boolean add(final Ball ball)
	{
		if (this.color == BallColor.EMPTY && this.size() == 0)
		{
			this.color = ball.getBallColor();
		}

		if (this.color == ball.getBallColor())
		{
			return this.ballSet.add(ball);
		}

		return false;
	}

	/**
	 * Dodaje do aktualnej grupy wszystkie elementy z drugiej.
	 * 
	 * @param another
	 * @return boolean which indicates if add was succesfull
	 */
	boolean add(final BallSet another)
	{
		if (this.color == BallColor.EMPTY && this.size() == 0)
		{
			this.color = another.color;
		}

		if (this.color == another.color)
		{
			return this.ballSet.addAll(another.ballSet);
		}
		return false;
	}

	/**
	 * Zwraca rozmiar zbioru.
	 * 
	 * @return int whith size of this set.
	 */
	int size()
	{
		return ballSet.size();
	}

	/**
	 * Sprawdza czy dana kulka jest w grupie.
	 * 
	 * @param ball
	 * @return boolean which indicates if ball is contained in this BallSet.
	 */
	boolean contains(final Ball ball)
	{
		if (this.color == ball.getBallColor())
		{
			return this.ballSet.contains(ball);
		}
		return false;
	}

	/**
	 * Reprezentacja w postaci lancucha znakow.
	 */
	public String toString()
	{
		return color + " " + size();
	}

	/**
	 * Metoda equals przeciazona tak, żeby wszystkie zbiory w ramach jednej
	 * grupy kulek były rozłączne.
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		BallSet another = (BallSet) obj;

		for (Ball ball : another)
		{
			if (this.ballSet.contains(ball))
				return true;
		}

		return false;

	}

	@Override
	public Iterator<Ball> iterator()
	{
		return ballSet.iterator();
	}
}
