/**
 * 
 */
package balls.model;

import java.util.LinkedList;
import java.util.Random;

import balls.model.mockups.BallColor;

/**
 * Klasa generująca plansze do gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class Generator
{
	private final int size;
	private final Random rand;
	private final LinkedList<LinkedList<Ball>> balls;

	/**
	 * Domyślny konstruktor przyjmuje za rozmiar planszy do gry 15.
	 */
	Generator()
	{
		this(15);
	}

	/**
	 * Konstruktor przyjmujacy jako argument rozmiar planszy.
	 * 
	 * @param size
	 */
	Generator(final int size)
	{
		this.size = size;
		this.rand = new Random();
		this.balls = new LinkedList<LinkedList<Ball>>();
	}

	/**
	 * Generuje kulki zgodnie z rozkładem równomiernym.
	 */
	void uniformDistribution()
	{
		rand.setSeed(System.currentTimeMillis());
		for (int i = 0; i < size; ++i)
		{
			LinkedList<Ball> arrayOfBalls = new LinkedList<Ball>();
			for (int j = 0; j < size; ++j)
			{
				double b = rand.nextDouble();
				if (b <= 0.2)
					arrayOfBalls.add(new Ball(BallColor.BLUE));
				else if (b <= 0.4)
					arrayOfBalls.add(new Ball(BallColor.GREEN));
				else if (b <= 0.6)
					arrayOfBalls.add(new Ball(BallColor.ORANGE));
				else if (b <= 0.8)
					arrayOfBalls.add(new Ball(BallColor.RED));
				else if (b <= 1)
					arrayOfBalls.add(new Ball(BallColor.VIOLET));
			}
			balls.add(arrayOfBalls);
		}
	}

	/**
	 * Zwraca Wygenerowaną plansze do gry w postaci obiektu ColumnSet
	 * 
	 * @return columnSet
	 */
	ColumnSet getColumnSet()
	{
		ColumnSet returned = new ColumnSet();
		Column column;
		for (LinkedList<Ball> listOfBalls : balls)
		{
			column = new Column(listOfBalls);
			returned.add(column);
		}
		return returned;
	}

	/**
	 * Zwraca wygenerowaną plansze do gry w postaci obiektu RowSet
	 * 
	 * @return rowSet
	 */
	RowSet getRowSet()
	{
		RowSet returned = new RowSet();
		Row row;
		for (int j = 0; j < size; ++j)
		{
			row = new Row();
			for (int i = 0; i < size; ++i)
			{
				row.add(balls.get(i).get(j));
			}
			returned.add(row);
		}
		return returned;
	}
}
