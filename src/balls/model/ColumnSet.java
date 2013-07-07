/**
 * 
 */
package balls.model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Klasa przechowujaca zbior kolumn kulek.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class ColumnSet implements Iterable<Column>
{
	private LinkedList<Column> columnsSet;

	/**
	 * Tworzy pusty zbiór kolumn.
	 */
	ColumnSet()
	{
		this.columnsSet = new LinkedList<Column>();
	}

	/**
	 * Tworzy zbiór kolumn identyczny z przekazanym w argumencie.
	 * 
	 * @param columnsSet
	 */
	ColumnSet(final LinkedList<Column> columnsSet)
	{
		this.columnsSet = columnsSet;
	}

	/**
	 * Konstruktor kopiujący.
	 * 
	 * @param another
	 */
	ColumnSet(final ColumnSet another)
	{
		this.columnsSet = new LinkedList<Column>();
		for (Column column : another)
		{
			columnsSet.add(new Column(column));
		}
	}

	/**
	 * Dodaje kolumne do zbioru. Do jednego zbioru nie mozna dwa razy dodac
	 * jednej kolumny.
	 * 
	 * @param column
	 * @return true if adding was successful.
	 */
	boolean add(final Column column)
	{
		if (columnsSet.contains(column))
		{
			return false;
		}
		columnsSet.add(column);
		return true;
	}

	/**
	 * Jesli kolumna istnieje w zbiorze to ją usuwa.
	 * 
	 * @param column
	 * @return true if removing was successful
	 */
	boolean remove(final Column column)
	{
		return columnsSet.remove(column);
	}

	/**
	 * Jeśli piłka znajduje się w kolumnach z danego zbioru kolumn, to usuwa tą
	 * piłke.
	 * 
	 * @param ball
	 * @return true if removing was successful
	 */
	boolean remove(final Ball ball)
	{
		for (Column column : columnsSet)
		{
			if (column.remove(ball))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Usuwa puste kulki.
	 */
	void removeEmptyBalls()
	{
		for (Column column : columnsSet)
		{
			column.removeEmptyBalls();
		}
	}

	/**
	 * Usuwa kolumny w których ilosc pilek wynosi zero.
	 */
	void removeEmptyColumns()
	{
		for (int i = 0; i < this.columnsSet.size(); ++i)
		{
			if (columnsSet.get(i).isEmpty())
			{
				this.columnsSet.remove(i);
				--i;
			}
		}
	}

	/**
	 * Zwraca true jeśli zbiór kolumn zawiera kolumne.
	 * 
	 * @param column
	 * @return true if set contains column.
	 */
	boolean contains(final Column column)
	{
		return columnsSet.contains(column);
	}

	/**
	 * Zwraca true jeśli zbiór kolumn zawiera kulke
	 * 
	 * @param ball
	 * @return true if set contians ball
	 */
	boolean contains(final Ball ball)
	{
		for (Column column : columnsSet)
		{
			if (column.contains(ball))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Zwraca ilosc kolumn przechowywanych w zbiorze.
	 * 
	 * @return number of columns in set.
	 */
	int size()
	{
		return columnsSet.size();
	}

	/**
	 * Zwraca kolumne index w kolejnosci.
	 * 
	 * @param index
	 * @return column
	 */
	Column get(final int index)
	{
		return columnsSet.get(index);
	}

	/**
	 * Pobiera rozmiar najwiekszej przechowywanej kolumny.
	 * 
	 * @return size of the biggest column in set.
	 */
	int getMaxColumnSize()
	{
		int max = 0;
		for (Column col : columnsSet)
		{
			if (col.size() > max)
			{
				max = col.size();
			}
		}

		return max;
	}

	/**
	 * Ustaw każdą kolumne do wielkosci rozmiaru najwiekszej kolumny.
	 */
	void setToMaximumSize()
	{
		for (Column col : columnsSet)
		{
			col.fillWithEmpty(this.getMaxColumnSize());
		}
	}

	/**
	 * Zwraca reprezentacje aktualnego zbioru w postaci wierszy.
	 * 
	 * @return rowSet
	 */
	RowSet getRowReprezentation()
	{
		int size = this.size();
		int maxColumnSize = this.getMaxColumnSize();
		RowSet rowSet = new RowSet();
		Row row;

		for (int j = 0; j < maxColumnSize; ++j)
		{
			row = new Row();
			for (int i = 0; i < size; ++i)
			{
				row.add(this.columnsSet.get(i).get(j));
			}
			rowSet.add(row);
		}

		return rowSet;
	}

	@Override
	public Iterator<Column> iterator()
	{
		return columnsSet.iterator();
	}
}
