/**
 * 
 */
package balls.model;

/**
 * Czyszczenie planszy z grupy kulek z ustawioną grawitacją normalną.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public final class CleaningWithNormalGravity implements Cleaning
{
	private ColumnSet columnSet;
	private RowSet rowSet;
	private BallSet ballSet;

	/**
	 * Ustawienie zmiennych opisujących plansze na ktorej ma zostac wykonane
	 * czyszczenie.
	 */
	CleaningWithNormalGravity(final ColumnSet columnSet, final RowSet rowSet,
			final BallSet ballSet)
	{
		this.columnSet = columnSet;
		this.rowSet = rowSet;
		this.ballSet = ballSet;
	}

	/**
	 * Domyślny konstruktor
	 */
	CleaningWithNormalGravity()
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
			columnSet.remove(ball);
		}
		columnSet.removeEmptyColumns();
		columnSet.setToMaximumSize();
		rowSet = columnSet.getRowReprezentation();
	}

	/**
	 * Pobranie aktualnego rowSet
	 */
	@Override
	public RowSet getActualRowSet()
	{
		return rowSet;
	}

	/**
	 * Pobranie aktualnego columnSet
	 */
	@Override
	public ColumnSet getActualColumnSet()
	{
		return columnSet;
	}

	@Override
	public void setVariables(ColumnSet columnSet, RowSet rowSet, BallSet ballSet)
	{
		this.columnSet = columnSet;
		this.rowSet = rowSet;
		this.ballSet = ballSet;

	}

}
