/**
 * 
 */
package balls.model;

import balls.model.mockups.BallColor;

/**
 * Klasa reprezentujaca kulke i wszystkie jej właściwości.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public final class Ball
{
	private BallColor color;

	/**
	 * Domyślny konstruktor ustawiajacy kolor na wartosc przekazywaną w
	 * argumencie.
	 * 
	 * @param color
	 */
	Ball(final BallColor color)
	{
		this.color = color;
	}

	/**
	 * Metoda pobierajaca kolor kulki.
	 * 
	 * @return ballColor of ball
	 */
	BallColor getBallColor()
	{
		return color;
	}

	/** 
	 * Kulki są sobie równe tylko jeśli są referencjami do tego samego obiektu!
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}

		return false;
	}
}
