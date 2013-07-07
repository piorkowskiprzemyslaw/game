/**
 * 
 */
package balls.model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Klasa przechowująca zbiór rzędów kulek.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class RowSet implements Iterable<Row>
{
	private final LinkedList<Row> rowsSet;

	/**
	 * Konsktruktor tworzacy pusty zbior rzędów kulek.
	 */
	RowSet()
	{
		this.rowsSet = new LinkedList<Row>();
	}

	/**
	 * Konstruktor tworzący zbiór rzędów identyczny z przekazanym w argumencie.
	 * 
	 * @param rowsSet
	 */
	RowSet(final LinkedList<Row> rowsSet)
	{
		this.rowsSet = rowsSet;
	}

	/**
	 * Konstruktor kopiujący.
	 * 
	 * @param another
	 */
	RowSet(final RowSet another)
	{
		this.rowsSet = new LinkedList<Row>();
		for (Row row : another)
		{
			rowsSet.add(new Row(row));
		}
	}

	/**
	 * Dodaje rząd do zbioru rzędów,dany rząd może być dodany do zbioru tylko
	 * jeden raz.
	 * 
	 * @param row
	 * @return true if adding was successful
	 */
	boolean add(final Row row)
	{
		if (rowsSet.contains(row))
		{
			return false;
		}
		rowsSet.add(row);
		return true;
	}

	/**
	 * Usuwa rząd ze zbioru.
	 * 
	 * @param row
	 * @return true if removing was successful
	 */
	boolean remove(final Row row)
	{
		return rowsSet.remove(row);
	}

	/**
	 * Usuwa kulke z rzędów.
	 * 
	 * @param ball
	 * @return true if removing was successful
	 */
	boolean remove(final Ball ball)
	{
		for (Row row : rowsSet)
		{
			if (row.remove(ball))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Zwraca true jeśli zbiór rzędów zawiera kolumne.
	 * 
	 * @param column
	 * @return true if set contain row
	 */
	boolean contains(final Row row)
	{
		return rowsSet.contains(row);
	}

	/**
	 * Zwraca true jeśli zbiór rzędów zawiera kulke
	 * 
	 * @param ball
	 * @return true if set contain ball
	 */
	boolean contains(final Ball ball)
	{
		for (Row row : rowsSet)
		{
			if (row.contains(ball))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Zwraca ilość wierszy przechowywanych w zbiorze.
	 * 
	 * @return number of rows in set.
	 */
	int size()
	{
		return rowsSet.size();
	}

	/**
	 * Zwraca wiersz index w kolejności.
	 * 
	 * @param index
	 * @return row
	 */
	Row get(final int index)
	{
		return rowsSet.get(index);
	}

	/**
	 * Usuwa puste rzędy.
	 */
	void removeEmptyRows()
	{
		for (int i = 0; i < rowsSet.size(); ++i)
		{
			if (rowsSet.get(i).isEmpty())
			{
				rowsSet.remove(i);
				--i;
			}
		}

	}

	/**
	 * Zwraca rozmiar najwiekszego z przechowywanych rzędów.
	 * 
	 * @return int.
	 */
	int getMaxRowSize()
	{
		int max = 0;
		for (Row row : this.rowsSet)
		{
			if (row.size() > max)
			{
				max = row.size();
			}
		}

		return max;
	}

	/**
	 * Uzupełnia rozmiar wszystkich rzędów do maksymalnego rozmiaru.
	 */
	void setToMaximumSize()
	{
		for (Row row : rowsSet)
		{
			row.fillWithEmpty(this.getMaxRowSize());
		}
	}

	/**
	 * Zwraca kolumnową reprezentacje.
	 * 
	 * @return columnSet
	 */
	ColumnSet getColumnReprezentation()
	{
		int size = this.size();
		int maxColumnSize = this.getMaxRowSize();
		ColumnSet columnSet = new ColumnSet();
		Column col;

		for (int j = 0; j < maxColumnSize; ++j)
		{
			col = new Column();
			for (int i = 0; i < size; ++i)
			{
				col.add(this.rowsSet.get(i).get(j));
			}
			columnSet.add(col);
		}

		return columnSet;
	}

	@Override
	public Iterator<Row> iterator()
	{
		return rowsSet.iterator();
	}
}
