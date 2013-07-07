/**
 * 
 */
package balls.model;

/**
 * Klasa obliczająca punkty.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class PointsCounter
{
	/**
	 * Metoda obliczająca punkty na podstawie ilości znalezionych kul jednego
	 * koloru.
	 * 
	 * @param numberOfBallsSelected
	 *            ilość kulek znalezionych
	 * @return liczba punktów przyznana.
	 */
	static int computePoint(final int numberOfBallsSelected)
	{
		int returned = 0;
		for (int i = 1; i <= numberOfBallsSelected; ++i)
		{
			returned += i;
		}
		return returned;
	}
}
