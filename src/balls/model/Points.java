/**
 * 
 */
package balls.model;

/**
 * Klasa przechowująca punkty rozgrywki.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class Points
{
	private int points;

	/**
	 * Konstruktor argumentowy.
	 * 
	 * @param beginValue
	 */
	Points(final int beginValue)
	{
		this.points = beginValue;
	}

	/**
	 * Konstrutkor kopiujący ustawia ilosc punktów na identyczną z arugmentem
	 * przekazanym.
	 * 
	 * @param another
	 */
	Points(final Points another)
	{
		this.points = another.getPoints();
	}

	/**
	 * Domyślny kontruktor ustawia ilosc punktow na 0.
	 */
	Points()
	{
		this(0);
	}

	/**
	 * Pobiera ilosc zdobytych punków.
	 * 
	 * @return int.
	 */
	int getPoints()
	{
		return points;
	}

	/**
	 * Dodaje ostatio zdobyte punkty.
	 * 
	 * @param lastMove
	 */
	void addPoints(final LastMovePoints lastMove)
	{
		points += lastMove.getLastMovePoints();
	}
}
