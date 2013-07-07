/**
 * 
 */
package balls.model;

/**
 * Interfejs zapewniający umiejętnośc czyszczenia planszy.
 * 
 * @author Przemysław Piórkowski
 * 
 */
interface Cleaning
{

	/**
	 * Ustawienie danych dotyczących planszy.
	 * 
	 * @param columnSet
	 * @param rowSet
	 * @param ballSet
	 */
	void setVariables(final ColumnSet columnSet, final RowSet rowSet,
			final BallSet ballSet);

	/**
	 * Czyszczenie planszy.
	 */
	void doCleaning();

	/**
	 * Pobranie rowSet po czyszczeniu.
	 * 
	 * @return rowSet
	 */
	RowSet getActualRowSet();

	/**
	 * Pobranie columnSet po czyszczeniu.
	 * 
	 * @return columnSet
	 */
	ColumnSet getActualColumnSet();
}
