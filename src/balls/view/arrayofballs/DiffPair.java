package balls.view.arrayofballs;

import balls.model.mockups.BallColor;

/**
 * Klasa o widoczności pakietowej do wyznaczania różnic pomiedzy poszczegolnymi makietami. Zawiera minimum
 * informacji niezbędnych do wydajnego odświeżenia ArrayOfBalls
 * 
 * @author Przemysław Piórkowski
 * 
 */
class DiffPair
{
	private final BallColor color;
	private final int i, j;

	/**
	 * Kontruktor przechowujacy informacje nt pola ktore trzeba zmienic
	 * 
	 * @param color kolor na który należy ustawić dane pole tablicy
	 * @param i pierwszy indeks tablicy
	 * @param j drugi indeks tablicy
	 */
	DiffPair(final BallColor color, final int i, final int j)
	{
		this.color = color;
		this.i = i;
		this.j = j;
	}

	/**
	 * Zwraca pole color
	 * 
	 * @return color
	 */
	BallColor getColor()
	{
		return color;
	}

	/**
	 * Zwraca pole i
	 * 
	 * @return i
	 */
	int getI()
	{
		return i;
	}

	/**
	 * Zwraca pole j
	 * 
	 * @return j
	 */
	public int getJ()
	{
		return j;
	}
}
