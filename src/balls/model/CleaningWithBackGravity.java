/**
 * 
 */
package balls.model;

/**
 * @author Przemysław Piórkowski
 * 
 */
public final class CleaningWithBackGravity implements Cleaning
{
	private RowSet rowSet;
	private ColumnSet columnSet;
	private BallSet ballSet;

	/**
	 * Ustawienie danych na temat planszy na ktorej ma zostac wykonane
	 * czyszczenie.
	 */
	CleaningWithBackGravity(final ColumnSet columnSet, final RowSet rowSet,
			final BallSet ballSet)
	{
		this.columnSet = columnSet;
		this.rowSet = rowSet;
		this.ballSet = ballSet;
	}

	/**
	 * Domyślny konstruktor.
	 */
	CleaningWithBackGravity()
	{
		this.columnSet = new ColumnSet();
		this.rowSet = new RowSet();
		this.ballSet = new BallSet();
	}

	/**
	 * Wykonanie czyszczenia.
	 */
	@Override
	public void doCleaning()
	{
		for (Ball ball : ballSet)
		{
			rowSet.remove(ball);
		}
		rowSet.removeEmptyRows();
		rowSet.setToMaximumSize();
		columnSet = rowSet.getColumnReprezentation();
		columnSet.removeEmptyBalls();
		columnSet.setToMaximumSize();
		rowSet = columnSet.getRowReprezentation();
	}

	/**
	 * Pobranie aktualnego zbioru wierszy.
	 */
	@Override
	public RowSet getActualRowSet()
	{
		return rowSet;
	}

	/**
	 * Pobranie aktualnego zbioru kolumn.
	 */
	@Override
	public ColumnSet getActualColumnSet()
	{
		return columnSet;
	}

	@Override
	public void setVariables(final ColumnSet columnSet, final RowSet rowSet,
			final BallSet ballSet)
	{
		this.rowSet = rowSet;
		this.columnSet = columnSet;
		this.ballSet = ballSet;

	}

}
