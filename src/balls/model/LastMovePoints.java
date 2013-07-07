/**
 * 
 */
package balls.model;

/**
 * Przechowanie punktów ostatniego ruchu.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class LastMovePoints
{
	private int lastMovePoints;

	/**
	 * Konstruktor parametryczny
	 * 
	 * @param lastMovePoints
	 */
	LastMovePoints(final int lastMovePoints)
	{
		this.lastMovePoints = lastMovePoints;
	}

	/**
	 * Domyślny kontruktor ustawiajacy ilosc punktow na 0.
	 */
	LastMovePoints()
	{
		this(0);
	}

	/**
	 * Konstruktor kopiujący ustawiajacy ilośc punków na identyczną z
	 * argumentem.
	 * 
	 * @param another
	 */
	LastMovePoints(final LastMovePoints another)
	{
		this.lastMovePoints = another.getLastMovePoints();
	}

	/**
	 * Pobranie punktów ostatniego ruchu.
	 * 
	 * @return int
	 */
	int getLastMovePoints()
	{
		return lastMovePoints;
	}

	/**
	 * Ustawienie wartości punktów ostatniego ruchu. Jeśli proba ustawienia na
	 * 0, to zwracany jest false i nie sa zmieniane punkty.
	 * 
	 * @param value
	 * @return false if set value is 0.
	 */
	boolean setLastMovePoints(final int value)
	{
		if (value == 0)
		{
			return false;
		}
		this.lastMovePoints = value;
		return true;
	}
}
