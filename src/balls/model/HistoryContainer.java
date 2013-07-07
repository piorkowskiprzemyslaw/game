/**
 * 
 */
package balls.model;

/**
 * Klasa służąca do przechowywania historii ruchów w grze.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class HistoryContainer
{
	private final ColumnSet columnSet;
	private final RowSet rowSet;
	private final Points points;
	private final LastMovePoints lastMovePoints;

	HistoryContainer(final ColumnSet columnSet, final RowSet rowSet,
			final Points points, final LastMovePoints lastMovePoints)
	{
		this.columnSet = new ColumnSet(columnSet);
		this.rowSet = columnSet.getRowReprezentation();
		this.points = new Points(points);
		this.lastMovePoints = new LastMovePoints(lastMovePoints);
	}

	/**
	 * Pobranie columnSeta
	 * 
	 * @return columnSet
	 */
	ColumnSet getColumnSet()
	{
		return columnSet;
	}

	/**
	 * Pobranie rowSeta
	 * 
	 * @return rowSet
	 */
	RowSet getRowSet()
	{
		return rowSet;
	}

	/**
	 * Pobranie punktów.
	 * 
	 * @return points
	 */
	Points getPoints()
	{
		return points;
	}

	/**
	 * Pobranie punktów ostatniego ruchu.
	 * 
	 * @return lastMovePoints
	 */
	LastMovePoints getLastMovePoints()
	{
		return lastMovePoints;
	}
}
