/**
 * 
 */
package balls.model;

/**
 * Klasa sprawdzajaca czy w danym momencie zgodnie z zasadami został osiągnięty
 * koniec gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class EndChecker
{
	private final BallSetGroup ballSetGroup;

	EndChecker(final BallSetGroup ballSetGroup)
	{
		this.ballSetGroup = ballSetGroup;
	}

	/**
	 * Sprawdź czy na planszy nastąpił koniec gry.
	 * 
	 * @return true - koniec gry.
	 */
	boolean check()
	{
		for (BallSet ballSet : ballSetGroup)
		{
			if (ballSet.size() >= 2)
			{
				return false;
			}
		}
		return true;
	}
}
